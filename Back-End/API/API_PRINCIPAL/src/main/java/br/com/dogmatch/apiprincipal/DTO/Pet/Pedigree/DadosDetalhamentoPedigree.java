package br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree;

import br.com.dogmatch.apiprincipal.Entity.Pedigree;

public record DadosDetalhamentoPedigree(
		Long id,
		String rg,
		String DataEmissao,
		String linkDocumento) {

	public DadosDetalhamentoPedigree (Pedigree pedigree) {
		this(
			pedigree.getId(),
			pedigree.getRg(),
			pedigree.getDataDeEmissao(),
			pedigree.getFotoPedigree()
		);
	}
}
