package br.com.dogmatch.apiprincipal.DTO.Pet;

import br.com.dogmatch.apiprincipal.Entity.Pet;

public record DadosFeedPet(
		Long id,
		String nome,
		String dataNascimento, 
		String raca,
		String fotoDePerfil,
		String descricao,
		String porte,
		String genero,
		boolean pedigree,
		Long distancia
		) {

	public DadosFeedPet (Pet pet, boolean pedigree, Long distancia) {
		this(
			pet.getId(),
			pet.getNome(),
			pet.getDataDeNascimento(),
			pet.getRaca(),
			pet.getFotoDePerfil(),
			pet.getPorte(),
			pet.getGenero(),
			pet.getDescricao(),
			pedigree,
			distancia);
	}
}
