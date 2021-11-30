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

import br.com.apppetshoprest.data.vo.v1.PetVO;
import br.com.apppetshoprest.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Pet Endpoint")
@RestController
@RequestMapping("api/pet/v1")
public class PetController {

	@Autowired
	PetService service;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos as pet")
	@GetMapping(produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<PetVO>> exibirPet(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<PetVO> petVO = service.buscarTodos(pageable);
		petVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PetController.class)
					.exibirPetPorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(petVO));
	}

	
	@CrossOrigin({"localhost:8080","http://www.fgatech.com.br"})
	@Operation(summary="Listar pet através do Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public PetVO exibirPetPorId(@PathVariable("id") Long idPet) {
		PetVO petVO =  service.buscarPorId(idPet);
		petVO.add(linkTo(methodOn(PetController.class)
				.exibirPetPorId(idPet)).withSelfRel());
		return petVO;
	}
	
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar pets por nome")
	@GetMapping(value="/buscarPorNome/{nome}",
			produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<PetVO>> exibirPetPorNome(
			
			@PathVariable("nome")String nome,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="15") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<PetVO> petVO = service.buscarPetPorNome(nome,pageable);
		petVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PetController.class)
					.exibirPetPorId(p.getId()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(petVO));
	}
	
		
	@CrossOrigin("localhost:8080")
	@Operation(summary="Insere dados de um novo pet")
	@PostMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public PetVO inserirPet(@RequestBody PetVO pessoa) {
		PetVO petVO =  service.inserir(pessoa);
		petVO.add(linkTo(methodOn(PetController.class)
				.exibirPetPorId(petVO.getId())).withSelfRel());
		return petVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Atualiza dados de um pet")
	@PutMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public PetVO atualizarPet(@RequestBody PetVO pessoa) {
		PetVO petVO = service.atualizar(pessoa);
		petVO.add(linkTo(methodOn(PetController.class)
				.exibirPetPorId(petVO.getId())).withSelfRel());
		return petVO;
	}
	

	@CrossOrigin("localhost:8080")
	@Operation(summary="Remove dados de uma pet através do Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> 
	excluirPet(@PathVariable("id") Long idPet){
		service.deletar(idPet);
		return ResponseEntity.ok().build();
	}
	

}
