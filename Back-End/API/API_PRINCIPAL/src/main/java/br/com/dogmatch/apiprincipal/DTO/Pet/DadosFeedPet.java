package br.com.dogmatch.apiprincipal.DTO.Pet;

import br.com.dogmatch.apiprincipal.Entity.Pet;

public record DadosFeedPet(
		Long id,
		String nome,
		String dataNascimento, 
		String raca,
		String fotoDePerfil,
		String descricao,
		boolean pedigree,
		String distancia
		) {

	public DadosFeedPet (Pet pet, boolean pedigree, String distancia) {
		this(
			pet.getId(),
			pet.getNome(),
			pet.getDataDeNascimento(),
			pet.getRaca(),
			pet.getFotoDePerfil(),
			pet.getDescricao(),
			pedigree,
			distancia);
	}
}
