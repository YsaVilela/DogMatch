package br.com.dogmatch.apiemail.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "codigoVerificacao")
public class Verificacao {

	@Id
	private String id;
	private String email;
	private int codigo;
	private LocalDateTime dataExpiracao;
	
	public String getId() {
        return id;
    }

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
	public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public void setdataExpiracao(LocalDateTime dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}
