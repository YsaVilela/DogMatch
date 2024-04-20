package br.com.dogmatch.apiprincipal.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "TB_RECUSA")
@Entity (name = "Recusa")
public class Recusa {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_pet_recusa")
    protected Pet petRecusa;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_pet_recusado")
    protected Pet petRecusado;

	public Pet getPetRecusa() {
		return petRecusa;
	}

	public void setPetRecusa(Pet petRecusa) {
		this.petRecusa = petRecusa;
	}

	public Pet getPetRecusado() {
		return petRecusado;
	}

	public void setPetRecusado(Pet petRecusado) {
		this.petRecusado = petRecusado;
	}

	public Long getId() {
		return id;
	}

}
