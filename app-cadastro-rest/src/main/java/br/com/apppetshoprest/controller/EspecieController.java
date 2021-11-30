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

import br.com.apppetshoprest.data.vo.v1.EspecieVO;
import br.com.apppetshoprest.service.EspecieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Especie Endpoint")
@RestController
@RequestMapping("api/especie/v1")
public class EspecieController {

	@Autowired
	EspecieService service;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos as especies")
	@GetMapping(produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<EspecieVO>> exibirEspecies(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nomeEspecie"));
		
		Page<EspecieVO> especieVO = service.buscarTodos(pageable); 
		especieVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(EspecieController.class)
					.exibirEspeciePorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(especieVO));
	}

	
	@CrossOrigin({"localhost:8080","http://www.fgatech.com.br"})
	@Operation(summary="Listar especies através do Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public EspecieVO exibirEspeciePorId(@PathVariable("id") Long idEspecie) {
		EspecieVO especieVO =  service.buscarPorId(idEspecie);
		especieVO.add(linkTo(methodOn(EspecieController.class)
				.exibirEspeciePorId(idEspecie)).withSelfRel());
		return especieVO;
	}
	
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar especies por nome")
	@GetMapping(value="/buscarPorNome/{nome}",
			produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<EspecieVO>> exibirEspeciesPorNome(
			
			@PathVariable("nome")String nome,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<EspecieVO> especieVO = service.buscarEspeciePorNome(nome,pageable);
		especieVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(EspecieController.class)
					.exibirEspeciePorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(especieVO));
	}
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Insere dados de uma nova especie")
	@PostMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public EspecieVO inserirEspecie(@RequestBody EspecieVO especie) {
		EspecieVO especieVO =  service.inserir(especie);
		especieVO.add(linkTo(methodOn(EspecieController.class)
				.exibirEspeciePorId(especieVO.getId())).withSelfRel());
		return especieVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Atualiza dados de uma especie")
	@PutMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public EspecieVO atualizarEspecie(@RequestBody EspecieVO especie) {
		EspecieVO especieVO = service.atualizar(especie);
		especieVO.add(linkTo(methodOn(EspecieController.class)
				.exibirEspeciePorId(especieVO.getId())).withSelfRel());
		return especieVO;
	}
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Remove dados de uma especie através do Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> 
	excluirEspecie(@PathVariable("id") Long idEspecie){
		service.deletar(idEspecie);
		return ResponseEntity.ok().build();
	}
	

}
