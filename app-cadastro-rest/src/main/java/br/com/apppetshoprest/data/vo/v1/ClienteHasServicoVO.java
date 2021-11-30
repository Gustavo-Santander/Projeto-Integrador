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
	private int id;
	
	@JsonProperty("data")
	private String data;
	
	@JsonProperty("horario")
	private String horario;

	public ClienteHasServicoVO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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


	
	
	
}
