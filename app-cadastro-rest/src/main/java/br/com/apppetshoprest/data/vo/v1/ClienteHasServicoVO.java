package br.com.apppetshoprest.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;


@JsonPropertyOrder({"horario","data", "id"})
public class ClienteHasServicoVO extends RepresentationModel<ClienteHasServicoVO> implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("data")
	private String data;
	
	@JsonProperty("horario")
	private String horario;

	public ClienteHasServicoVO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(data, horario, id);
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
		ClienteHasServicoVO other = (ClienteHasServicoVO) obj;
		return Objects.equals(data, other.data) && Objects.equals(horario, other.horario)
				&& Objects.equals(id, other.id);
	}


	
	
	
}
