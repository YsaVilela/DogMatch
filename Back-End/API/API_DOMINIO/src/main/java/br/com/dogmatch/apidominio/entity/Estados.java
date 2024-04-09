package br.com.dogmatch.apidominio.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "TB_ESTADO")
@Entity (name = "Estados")

public class Estados {
	@Id 
	@Column(name = "id")
	private Long id;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
	@JsonIgnore
	private List<Cidade> cidade = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getUf() {
		return uf;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}