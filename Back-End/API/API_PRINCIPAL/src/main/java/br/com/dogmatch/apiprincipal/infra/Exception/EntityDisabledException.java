package br.com.dogmatch.apiprincipal.infra.Exception;

public class EntityDisabledException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EntityDisabledException(String mensage) {
        super(mensage);
    }
}
