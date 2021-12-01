package br.com.apppetshoprest.data.entity;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//@Table(name="cliente_has_servico")


@Data
@Entity
@Table(name="cliente_has_servico")
public class ClienteHasServico{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "horario")
	private String horario;


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
		return Objects.hash(data, horario, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteHasServico other = (ClienteHasServico) obj;
		return Objects.equals(data, other.data) && Objects.equals(horario, other.horario)
				&& Objects.equals(id, other.id);
	}


	
	
	
	
}
