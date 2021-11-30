package br.com.apppetshoprest.data.entity;


import javax.persistence.Column;
//import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="cliente")
public class Cliente{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome", nullable = false, length=45)
	private String nome;
	
	@Column(name = "telefone",nullable = false, length=30)
	private String telefone;
	
	@Column(name = "email",nullable = false, length=100)
	private String email;

	@Column(name = "rua",nullable = false)
	private String rua;
	
	@Column(name = "cidade",nullable = false)
	private String cidade;
	
	@Column(name = "estado",nullable = false)
	private String estado;
	
	@Column(name = "cep",nullable = false)
	private String cep;
	
	/*
	@Embedded
	private Endereco endereco;
	*/

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
/*
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
*/
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
	
		
}
