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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apppetshoprest.data.vo.v1.RacaVO;
import br.com.apppetshoprest.service.RacaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Raca Endpoint")
@RestController
@RequestMapping("api/raca/v1")
public class RacaController {

	@Autowired
	RacaService service;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos as racas")
	@GetMapping(produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<RacaVO>> exibirRacas(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nomeRaca"));
		
		Page<RacaVO> racaVO = service.buscarTodos(pageable); 
		racaVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(RacaController.class)
					.exibirRacaPorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(racaVO));
	}

	
	@CrossOrigin({"localhost:8080","http://www.fgatech.com.br"})
	@Operation(summary="Listar racas através do Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public RacaVO exibirRacaPorId(@PathVariable("id") Long idRaca) {
		RacaVO racaVO =  service.buscarPorId(idRaca);
		racaVO.add(linkTo(methodOn(RacaController.class)
				.exibirRacaPorId(idRaca)).withSelfRel());
		return racaVO;
	}
	
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar racas por nome")
	@GetMapping(value="/buscarPorNome/{nome}",
			produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<RacaVO>> exibirRacasPorNome(
			
			@PathVariable("nome")String nome,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<RacaVO> racaVO = service.buscarRacaPorNome(nome,pageable);
		racaVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(RacaController.class)
					.exibirRacaPorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(racaVO));
	}
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Insere dados de uma nova raca")
	@PostMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public RacaVO inserirRaca(@RequestBody RacaVO raca) {
		RacaVO racaVO =  service.inserir(raca);
		racaVO.add(linkTo(methodOn(RacaController.class)
				.exibirRacaPorId(racaVO.getId())).withSelfRel());
		return racaVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Atualiza dados de uma raca")
	@PutMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public RacaVO atualizarRaca(@RequestBody RacaVO raca) {
		RacaVO racaVO = service.atualizar(raca);
		racaVO.add(linkTo(methodOn(RacaController.class)
				.exibirRacaPorId(racaVO.getId())).withSelfRel());
		return racaVO;
	}
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Remove dados de uma raca através do Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> 
	excluirRaca(@PathVariable("id") Long idRaca){
		service.deletar(idRaca);
		return ResponseEntity.ok().build();
	}
	

}
