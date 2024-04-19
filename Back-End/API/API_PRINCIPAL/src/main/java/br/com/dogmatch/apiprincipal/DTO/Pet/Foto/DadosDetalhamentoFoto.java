package br.com.dogmatch.apiprincipal.DTO.Pet.Foto;

import java.time.LocalDateTime;

import br.com.dogmatch.apiprincipal.Entity.Foto;

public record DadosDetalhamentoFoto(
		Long id,
		String linkFoto,
		String legenda,
		LocalDateTime dataPublicacao) {
	
	public DadosDetalhamentoFoto(Foto foto) {
		this(
			foto.getId(),
			foto.getFoto(),
			foto.getLegenda(),
			foto.getDataDePublicacao());
	}

}
