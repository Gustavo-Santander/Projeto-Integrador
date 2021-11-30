package br.com.apppetshoprest.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","nome_especie"})
public class EspecieVO extends RepresentationModel<EspecieVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome_especie")
	private String nomeEspecie;

	public EspecieVO() {	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, nomeEspecie);
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
		EspecieVO other = (EspecieVO) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeEspecie, other.nomeEspecie);
	}

	

}
