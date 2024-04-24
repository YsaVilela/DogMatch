package br.com.dogmatch.apiprincipal.DTO;

import java.time.LocalDateTime;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosFeedPet;
import br.com.dogmatch.apiprincipal.Entity.Solicitacao;

public record DetalhamentoSolicitacao(
		Long id,
		String status,
		DadosFeedPet dadosPet,
		LocalDateTime dataSolicitacao) {
	
	public DetalhamentoSolicitacao (Solicitacao solicitacao, DadosFeedPet dadosPet) {
		this(solicitacao.getId(),
			solicitacao.getStatus(),
			dadosPet,
			solicitacao.getDataDeSolicitacao());
	}

}
