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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apppetshoprest.data.vo.v1.ClienteVO;
import br.com.apppetshoprest.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Cliente Endpoint")
@RestController
@RequestMapping("api/cliente/v1")
public class ClienteController {

	@Autowired
	ClienteService service;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todas os Clientes")
	@GetMapping(produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<ClienteVO>> exibirClientes(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<ClienteVO> clientesVO = service.buscarTodos(pageable);
		clientesVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(ClienteController.class)
					.exibirClientePorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(clientesVO));
	}

	
	@CrossOrigin({"localhost:8080","http://www.fgatech.com.br"})
	@Operation(summary="Listar Cliente através do Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public ClienteVO exibirClientePorId(@PathVariable("id") Long idCliente) {
		ClienteVO clienteVO =  service.buscarPorId(idCliente);
		clienteVO.add(linkTo(methodOn(ClienteController.class)
				.exibirClientePorId(idCliente)).withSelfRel());
		return clienteVO;
	}
	
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar Clientes por nome")
	@GetMapping(value="/buscarPorNome/{nome}",
			produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<ClienteVO>> exibirClientesPorNome(
			
			@PathVariable("nome")String nome,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<ClienteVO> clientesVO = service.buscarClientePorNome(nome,pageable);
		clientesVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(ClienteController.class)
					.exibirClientePorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(clientesVO));
	}
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Insere dados de um novo Cliente")
	@PostMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public ClienteVO inserirCliente(@RequestBody ClienteVO cliente) {
		ClienteVO clienteVO =  service.inserir(cliente);
		clienteVO.add(linkTo(methodOn(ClienteController.class)
				.exibirClientePorId(clienteVO.getId())).withSelfRel());
		return clienteVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Atualiza dados de uma Cliente")
	@PutMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public ClienteVO atualizarCliente(@RequestBody ClienteVO cliente) {
		ClienteVO clienteVO = service.atualizar(cliente);
		clienteVO.add(linkTo(methodOn(ClienteController.class)
				.exibirClientePorId(clienteVO.getId())).withSelfRel());
		return clienteVO;
	}
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Remove dados de uma Cliente através do Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> 
	excluirCliente(@PathVariable("id") Long idCliente){
		service.deletar(idCliente);
		return ResponseEntity.ok().build();
	}
	

}
