package br.com.dogmatch.apiprincipal.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Table(name = "TB_PEDIGREE")
@Entity (name = "Pedigree")
public class Pedigree {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "rg") 
	private String rg;
	
	@Column(name = "data_emissao") 
	private String dataDeEmissao;
	
	@Column(name = "documento") 
	private String fotoPedigree;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_pet")
    protected Pet pet;
	
	
	public String getDataDeEmissao() {
		return dataDeEmissao;
	}

	public void setDataDeEmissao(String dataDeEmissao) {
		this.dataDeEmissao = dataDeEmissao;
	}

	public String getFotoPedigree() {
		return fotoPedigree;
	}

	public void setFotoPedigree(String fotoPedigree) {
		this.fotoPedigree = fotoPedigree;
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
}

