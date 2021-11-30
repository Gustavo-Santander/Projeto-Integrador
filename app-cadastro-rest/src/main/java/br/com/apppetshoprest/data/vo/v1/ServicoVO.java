package br.com.apppetshoprest.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"tipoServico", "id"})
public class ServicoVO extends RepresentationModel<ServicoVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("tipoServico")
	private String tipoServico;

	public ServicoVO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, tipoServico);
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
		ServicoVO other = (ServicoVO) obj;
		return Objects.equals(id, other.id) && Objects.equals(tipoServico, other.tipoServico);
	}

		
	
}
