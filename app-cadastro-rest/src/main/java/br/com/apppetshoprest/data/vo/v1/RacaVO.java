package br.com.apppetshoprest.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class RacaVO extends RepresentationModel<RacaVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome_raca")
	private String nomeRaca;

	public RacaVO() {}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, nomeRaca);
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
		RacaVO other = (RacaVO) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeRaca, other.nomeRaca);
	}
	
	
}
