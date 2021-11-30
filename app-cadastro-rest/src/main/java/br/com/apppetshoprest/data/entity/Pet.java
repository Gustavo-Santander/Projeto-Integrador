package br.com.apppetshoprest.data.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pet")
public class Pet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome",nullable = false, length=45)
	private String nome;
	
	@Column(name = "peso",nullable = false, length=45)
	private String peso;
	
	@Column(name = "data_nasc")
	private Date dataNasc;
	
	
	@Column(name = "genero",nullable = false, length=45)
	private String genero;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
	}


	public Date getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public Pet() {}


	@Override
	public int hashCode() {
		return Objects.hash(dataNasc, genero, id, nome, peso);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return Objects.equals(dataNasc, other.dataNasc) && Objects.equals(genero, other.genero)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(peso, other.peso);
	}

	
	
	
}
