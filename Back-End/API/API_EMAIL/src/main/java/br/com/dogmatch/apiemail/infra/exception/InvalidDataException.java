package br.com.dogmatch.apiemail.infra.exception;

public class InvalidDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidDataException(String mensage) {
        super(mensage);
    }
}
