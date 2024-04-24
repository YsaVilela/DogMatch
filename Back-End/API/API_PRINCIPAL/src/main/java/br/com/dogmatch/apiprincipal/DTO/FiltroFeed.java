package br.com.dogmatch.apiprincipal.DTO;

public record FiltroFeed(
		Long tutorId, 
		Long idPet, 
		String localizacaoAtual, 
		Long distanciaMaxima, 
		Long idadeMinima,
		Long idadeMaxima, 
		String raca, 
		boolean devePossuirPedigree, 
		String porte,
		boolean deveSerCastrado) {

}
