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

@Table(name = "TB_PET")
@Entity (name = "Pet")
public class Pet {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "nome") 
	private String nome;
	
	@Column(name = "sobrenome") 
	private String sobrenome;
	
	@Column(name = "data_nascimento") 
	private String dataDeNascimento;
	
	@Column(name = "genero") 
	private String genero;
	
	@Column(name = "raca") 
	private String raca;
	
	@Column(name = "cor") 
	private String cor;
	
	@Column(name = "porte") 
	private String porte;

	@Column(name = "carteira_vacinacao") 
	private String carteiraDeVacinacao;
	
	@Column(name = "foto_perfil") 
	private String fotoDePerfil;
	
	@Column(name = "descricao") 
	private String descricao;
	
	@Column(name = "interesse") 
	private String interesse;
	
	@Column(name = "castrado") 
	private boolean castrado;
	
	@Column(name = "status") 
	private boolean status;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_tutor")
    protected Tutor tutor;
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}
	
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getCarteiraDeVacinacao() {
		return carteiraDeVacinacao;
	}

	public void setCarteiraDeVacinacao(String carteiraDeVacinacao) {
		this.carteiraDeVacinacao = carteiraDeVacinacao;
	}

	public String getFotoDePerfil() {
		return fotoDePerfil;
	}

	public void setFotoDePerfil(String fotoDePerfil) {
		this.fotoDePerfil = fotoDePerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInteresse() {
		return interesse;
	}

	public void setInteresse(String interesse) {
		this.interesse = interesse;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public boolean getCastrado() {
		return castrado;
	}

	public void setCastrado(boolean castrado) {
		this.castrado = castrado;
	}

}
