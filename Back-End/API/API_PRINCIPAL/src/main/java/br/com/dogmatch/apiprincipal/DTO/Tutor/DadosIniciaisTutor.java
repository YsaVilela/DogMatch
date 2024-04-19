package br.com.dogmatch.apiprincipal.DTO.Tutor;

import java.util.List;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosIniciasCompletosPet;

public record DadosIniciaisTutor(
		DadosDetalhamentoTutor dadosTutor,
		List<DadosIniciasCompletosPet> dadosPets) {
}
