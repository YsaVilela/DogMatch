package br.com.dogmatch.apiprincipal.infra.Exception;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageException(String mensagem) {
		super(mensagem);
	}
}
