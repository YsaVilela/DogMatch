package br.com.dogmatch.apiemail.infra.exception;

public class InvalidEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidEmailException(String mensagem) {
		super(mensagem);
	}


}
