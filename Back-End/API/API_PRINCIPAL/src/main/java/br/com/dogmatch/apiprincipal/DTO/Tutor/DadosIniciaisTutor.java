package br.com.dogmatch.apiprincipal.DTO.Tutor;

import java.util.List;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosCompletosPet;

public record DadosIniciaisTutor(
		DadosDetalhamentoTutor dadosTutor,
		List<DadosCompletosPet> dadosPets) {
}
