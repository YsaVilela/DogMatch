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

@Table(name = "TB_TUTOR")
@Entity (name = "Tutor")
public class Tutor {
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
	
	@Column(name = "cpf", unique = true) 
	private String cpf;
	
	@Column(name = "email", unique = true) 
	private String email;
	
	@Column(name = "telefone") 
	private String telefone;
	
	@Column(name = "status") 
	private boolean status;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_endereco")
    protected Endereco endereco;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setDataDeNascimento(String dataDeConstituicao) {
		this.dataDeNascimento = dataDeConstituicao;
	}
	
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}
}
