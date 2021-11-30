package br.com.apppetshoprest.data.vo.v1;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;
@JsonPropertyOrder({"nome","email", "telefone", "id", "rua", "cidade", "estado", "cep"})
public class ClienteVO extends RepresentationModel<ClienteVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("telefone")
	private String telefone;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("rua")
	private String rua;
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

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

	@JsonProperty("cidade")
	private String cidade;
	
	@JsonProperty("estado")
	private String estado;
	
	@JsonProperty("cep")
	private String cep;

	/*@JsonProperty("endereco")
	private Endereco endereco;*/
	
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public ClienteVO() {	}

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
	
}
