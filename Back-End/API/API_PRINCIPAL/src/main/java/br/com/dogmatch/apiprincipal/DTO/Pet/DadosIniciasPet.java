package br.com.dogmatch.apiprincipal.DTO.Pet;

import br.com.dogmatch.apiprincipal.Entity.Pet;

public record DadosIniciasPet(
		Long id,
		String nome, 
		String sobrenome,
		String dataDeNascimento,
		String genero,
		String raca,
		String cor,
		String porte,
		String carteiraDeVacinacao,
		String fotoPerfil,
		String descricao,
		boolean castrado,
		String interesse,
		boolean status
		) {
	
	public DadosIniciasPet (Pet pet) {
		this(
			pet.getId(),
			pet.getNome(),
			pet.getSobrenome(),
			pet.getDataDeNascimento(),
			pet.getGenero(),
			pet.getRaca(),
			pet.getCor(),
			pet.getPorte(),
			pet.getCarteiraDeVacinacao(),
			pet.getFotoDePerfil(),
			pet.getDescricao(),
			pet.getCastrado(),
			pet.getInteresse(),
			pet.isStatus()
			);
	}

}
