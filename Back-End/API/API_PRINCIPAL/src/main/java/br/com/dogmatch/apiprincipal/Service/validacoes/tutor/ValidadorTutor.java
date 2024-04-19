package br.com.dogmatch.apiprincipal.Service.validacoes.tutor;

public interface ValidadorTutor {
	void validar (String cpf, String email, String dataDeNascimento, Long id);
}
