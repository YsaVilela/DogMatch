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

@Table(name = "TB_MATCH")
@Entity (name = "Match")
public class Match {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_pet1")
    protected Pet pet1;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_pet2")
    protected Pet pet2;

	public Pet getPet1() {
		return pet1;
	}

	public void setPet1(Pet pet1) {
		this.pet1 = pet1;
	}

	public Pet getPet2() {
		return pet2;
	}

	public void setPet2(Pet pet2) {
		this.pet2 = pet2;
	}

	public Long getId() {
		return id;
	}
	
}
