package br.com.dogmatch.apiprincipal.infra.Exception;

public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ValidationException(String mensage) {
        super(mensage);
    }
}