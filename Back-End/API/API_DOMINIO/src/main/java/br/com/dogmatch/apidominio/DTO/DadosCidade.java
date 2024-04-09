package br.com.dogmatch.apidominio.DTO;

import br.com.dogmatch.apidominio.entity.Cidade;
import br.com.dogmatch.apidominio.entity.Estados;

public record DadosCidade (
	Long id,
	Estados estado,
	String nome)
{

	public DadosCidade(Cidade cidade) {
		this(cidade.getId(),
				cidade.getEstado(),
				cidade.getNome());
	}
}


