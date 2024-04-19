package br.com.dogmatch.apiprincipal.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "TB_FOTO")
@Entity (name = "Foto")
public class Foto {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "foto") 
	private String foto;
	
	@Column(name = "legenda") 
	private String legenda;
	
	@Column(name = "data_publicacao") 
	private LocalDateTime dataDePublicacao;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_pet")
    protected Pet pet;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public LocalDateTime getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setDataDePublicacao(LocalDateTime dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Long getId() {
		return id;
	}
}
