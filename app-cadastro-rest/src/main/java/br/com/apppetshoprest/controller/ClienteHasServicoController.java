package br.com.apppetshoprest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apppetshoprest.data.vo.v1.ClienteHasServicoVO;
import br.com.apppetshoprest.service.ClienteHasServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Servico ao Cliente Endpoint")
@RestController
@RequestMapping("api/servicocliente/v1")
public class ClienteHasServicoController {

	@Autowired
	ClienteHasServicoService service;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todas os Horarios de servico")
	@GetMapping(produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<ClienteHasServicoVO>> exibirHorarios(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<ClienteHasServicoVO> clienteHasServicoVO = service.buscarTodos(pageable);
		clienteHasServicoVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(ClienteHasServicoController.class)
					.exibirHorarioPorId((long) p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(clienteHasServicoVO));
	}

	
	@CrossOrigin({"localhost:8080","http://www.fgatech.com.br"})
	@Operation(summary="Listar Horarios através do Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public ClienteHasServicoVO exibirHorarioPorId(@PathVariable("id") Long id) {
		ClienteHasServicoVO clienteHasServicoVO =  service.buscarPorId(id);
		clienteHasServicoVO.add(linkTo(methodOn(ClienteHasServicoController.class)
				.exibirHorarioPorId(id)).withSelfRel());
		return clienteHasServicoVO;
	}
	
	
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Insere dados de um novo Horario")
	@PostMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public ClienteHasServicoVO inserirHorario(@RequestBody ClienteHasServicoVO clienteHasServico) {
		ClienteHasServicoVO clienteHasServicoVO =  service.inserir(clienteHasServico);
		clienteHasServicoVO.add(linkTo(methodOn(ClienteHasServicoController.class)
				.exibirHorarioPorId((long) clienteHasServicoVO.getId())).withSelfRel());
		return clienteHasServicoVO;
		
		
	}
		
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Remove dados de um Horario através do Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> 
	excluirHorario(@PathVariable("id") Long id){
		service.deletar(id);
		return ResponseEntity.ok().build();
	}
	

}
