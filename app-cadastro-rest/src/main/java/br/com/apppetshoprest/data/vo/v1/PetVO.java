package br.com.apppetshoprest.data.vo.v1;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;



import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"nome","peso", "dataNasc","genero", "id"})
public class PetVO  extends RepresentationModel<PetVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("peso")
	private String peso;
	
	@JsonProperty("dataNasc")
	private Date dataNasc;
		
	@JsonProperty("genero")
	private String genero;

	public PetVO() {}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataNasc, genero, id, nome, peso);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PetVO other = (PetVO) obj;
		return Objects.equals(dataNasc, other.dataNasc) && Objects.equals(genero, other.genero)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(peso, other.peso);
	}
	
	
	
	
}
