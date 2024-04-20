package br.com.dogmatch.apiprincipal.infra.utils;

import org.springframework.stereotype.Component;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

import br.com.dogmatch.apiprincipal.infra.Exception.NotFoundException;

@Component
public class CalcularDistancia {

	private final GeoApiContext geoApiContext;

	public CalcularDistancia() {
		this.geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyCFxcR-it580MrHGil68oFgl-knrKDTDkE").build();
	}

	public long calcularDistanciaEntreCEPs(String origem, String cepDestino) {
		try {
			DistanceMatrix result = DistanceMatrixApi.newRequest(geoApiContext).origins(origem).destinations(cepDestino)
					.mode(TravelMode.DRIVING).await();

			if (result.rows.length > 0 && result.rows[0].elements.length > 0) {
				return result.rows[0].elements[0].distance.inMeters;
			} else {
				throw new NotFoundException("Distância não encontrada");
			}
		} catch (Exception e) {
			throw new NotFoundException("Erro ao calcular a distância");
		}
	}

}
