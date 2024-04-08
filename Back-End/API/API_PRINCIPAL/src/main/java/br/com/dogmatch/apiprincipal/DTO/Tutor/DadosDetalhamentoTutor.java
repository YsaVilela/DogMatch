package br.com.dogmatch.apiprincipal.DTO.Tutor;

import br.com.dogmatch.apiprincipal.Entity.Endereco;
import br.com.dogmatch.apiprincipal.Entity.Tutor;

public record DadosDetalhamentoTutor(
		Long id,
		String nome,
		String sobrenome,
		String dataDeNascimento,
		String genero,
		String cpf,
		String email,
		String telefone,
		Endereco endereco
		) {
	
	public DadosDetalhamentoTutor (Tutor tutor) {
		this(
			tutor.getId(),
			tutor.getNome(),
			tutor.getSobrenome(),
			tutor.getDataDeNascimento(),
			tutor.getGenero(),
			tutor.getCpf(),
			tutor.getEmail(),
			tutor.getTelefone(),
			tutor.getEndereco()
			);
	}

}
