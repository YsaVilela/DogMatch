package br.com.dogmatch.apidominio.DTO;

import br.com.dogmatch.apidominio.entity.Estados;

public record DadosEstados(
		Long id,
		String uf,
		String nome) {
	
	public DadosEstados (Estados estado) {
		this(estado.getId(),
				estado.getUf(),
				estado.getNome());
	}
}
