package br.com.dogmatch.apiprincipal.infra.Exception;

public class ProcessInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProcessInvalidException(String mensagem) {
		super(mensagem);
	}
}