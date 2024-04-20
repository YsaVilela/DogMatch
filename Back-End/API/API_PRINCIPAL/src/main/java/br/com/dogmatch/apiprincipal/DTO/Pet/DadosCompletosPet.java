package br.com.dogmatch.apiprincipal.DTO.Pet;

import java.util.List;

import br.com.dogmatch.apiprincipal.DTO.Pet.Foto.DadosDetalhamentoFoto;
import br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree.DadosDetalhamentoPedigree;

public record DadosCompletosPet(
		DadosIniciasPet dadosPet,
		DadosDetalhamentoPedigree pedigree,
		List<DadosDetalhamentoFoto> fotos
		) {

}
