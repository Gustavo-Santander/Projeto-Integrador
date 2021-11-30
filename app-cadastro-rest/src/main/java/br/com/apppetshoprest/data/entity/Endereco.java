package br.com.apppetshoprest.data.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Data;


@Data
@Embeddable
public class Endereco {

	
	@Column(name = "rua", nullable = true)
	private String rua;
	
	@Column(name = "cidade",nullable = true)
	private String cidade;
	
	@Column(name = "estado",nullable = true)
	private String estado;
	
	@Column(name = "cep",nullable = true)
	private String cep;
	

	

}
