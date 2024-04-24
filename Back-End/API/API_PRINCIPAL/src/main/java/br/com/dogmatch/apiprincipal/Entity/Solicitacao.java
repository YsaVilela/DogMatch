package br.com.dogmatch.apiprincipal.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "TB_SOLICITACAO")
@Entity (name = "Solicitacao")
public class Solicitacao {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "status") 
	private String status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_solicitante")
    protected Pet petSolicitante;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_solicitado")
    protected Pet petSolicitado;
	
	@Column(name = "data_solicitacao") 
	private LocalDateTime dataDeSolicitacao;

	public Pet getPetSolicitante() {
		return petSolicitante;
	}

	public void setPetSolicitante(Pet petSolicitante) {
		this.petSolicitante = petSolicitante;
	}

	public Pet getPetSolicitado() {
		return petSolicitado;
	}

	public void setPetSolicitado(Pet petSolicitado) {
		this.petSolicitado = petSolicitado;
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDataDeSolicitacao() {
		return dataDeSolicitacao;
	}

	public void setDataDeSolicitacao(LocalDateTime dataDeSolicitacao) {
		this.dataDeSolicitacao = dataDeSolicitacao;
	}
	
}
