package br.com.dogmatch.apiprincipal.infra.Exception;

public class InvalidDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidDataException(String mensage) {
        super(mensage);
    }
}