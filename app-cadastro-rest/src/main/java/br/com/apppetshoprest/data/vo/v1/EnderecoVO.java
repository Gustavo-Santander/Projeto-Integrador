package br.com.apppetshoprest.data.vo.v1;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class EnderecoVO {


	@EqualsAndHashCode.Include
	private Long id;
	
	
	private String rua;
	
	
	private String cidade;
	
	
	private String estado;
	
	
	private String cep;
	
		
}
