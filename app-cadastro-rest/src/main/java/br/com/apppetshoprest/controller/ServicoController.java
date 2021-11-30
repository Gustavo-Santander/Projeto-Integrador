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

import br.com.apppetshoprest.data.vo.v1.ServicoVO;
import br.com.apppetshoprest.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Servico Endpoint")
@RestController
@RequestMapping("api/servico/v1")
public class ServicoController {

	@Autowired
	ServicoService service;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos os servicos")
	@GetMapping(produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<ServicoVO>> exibirServico(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "tipoServico"));
		
		Page<ServicoVO> servicoVO = service.buscarTodos(pageable);
		servicoVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(ServicoController.class)
					.exibirServicoPorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(servicoVO));
	}

	
	@CrossOrigin({"localhost:8080","http://www.fgatech.com.br"})
	@Operation(summary="Listar servico através do Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public ServicoVO exibirServicoPorId(@PathVariable("id") Long idServico) {
		ServicoVO servicoVO =  service.buscarPorId(idServico);
		servicoVO.add(linkTo(methodOn(ServicoController.class)
				.exibirServicoPorId(idServico)).withSelfRel());
		return servicoVO;
	}
	
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar servicos por nome")
	@GetMapping(value="/buscarPorNome/{nome}",
			produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<ServicoVO>> exibirServicoPorNome(
			
			@PathVariable("nome")String nome,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<ServicoVO> servicoVO = service.buscarServicoPorNome(nome,pageable);
		servicoVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(ServicoController.class)
					.exibirServicoPorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(servicoVO));
	}
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Insere dados de um novo servico")
	@PostMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public ServicoVO inserirServico(@RequestBody ServicoVO servico) {
		ServicoVO servicoVO =  service.inserir(servico);
		servicoVO.add(linkTo(methodOn(ServicoController.class)
				.exibirServicoPorId(servicoVO.getId())).withSelfRel());
		return servicoVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Atualiza dados de um servico")
	@PutMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public ServicoVO atualizarServico(@RequestBody ServicoVO servico) {
		ServicoVO servicoVO = service.atualizar(servico);
		servicoVO.add(linkTo(methodOn(ServicoController.class)
				.exibirServicoPorId(servicoVO.getId())).withSelfRel());
		return servicoVO;
	}
	

	@CrossOrigin("localhost:8080")
	@Operation(summary="Remove dados de um servico através do Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> 
	excluirServico(@PathVariable("id") Long idServico){
		service.deletar(idServico);
		return ResponseEntity.ok().build();
	}
	

}
