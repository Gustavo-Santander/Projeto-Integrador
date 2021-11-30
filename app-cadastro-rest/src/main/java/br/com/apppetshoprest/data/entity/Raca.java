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
@Table(name="raca")
public class Raca implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome_raca",nullable = false, length=45)
	private String nomeRaca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeRaca() {
		return nomeRaca;
	}

	public void setNomeRaca(String nomeRaca) {
		this.nomeRaca = nomeRaca;
	}

	public Raca() {}

	@Override
	public int hashCode() {
		return Objects.hash(id, nomeRaca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Raca other = (Raca) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeRaca, other.nomeRaca);
	}
	
	
	
}
	