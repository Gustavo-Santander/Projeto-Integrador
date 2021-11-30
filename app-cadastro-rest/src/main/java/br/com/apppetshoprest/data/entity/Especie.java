package br.com.apppetshoprest.data.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="especie")
public class Especie implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nomeEspecie",nullable = false, length=45)
	private String nomeEspecie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEspecie() {
		return nomeEspecie;
	}

	public void setNomeEspecie(String nomeEspecie) {
		this.nomeEspecie = nomeEspecie;
	}

	public Especie() {	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nomeEspecie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especie other = (Especie) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeEspecie, other.nomeEspecie);
	}
	
	

}
