package br.com.apppetshoprest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.apppetshoprest.adapter.DozerAdapter;
import br.com.apppetshoprest.data.entity.Pet;
import br.com.apppetshoprest.data.vo.v1.PetVO;
import br.com.apppetshoprest.exception.ResourceNotFoundException;
import br.com.apppetshoprest.repository.PetRepository;

@Service
public class PetService {

	@Autowired
	PetRepository repository;
	
	private PetVO converteParaPetVO(Pet entity) {
		return DozerAdapter.parseObject(entity, PetVO.class); 
	}

	
	
		public PetVO inserir(PetVO petVO) {
			var entity = DozerAdapter.parseObject(petVO, Pet.class);
			var vo = DozerAdapter.parseObject(repository.save(entity), PetVO.class);
			return vo;
		}
		
		
		public Page<PetVO> buscarTodos(Pageable pageable) {
			var page = repository.findAll(pageable);
			return page.map(this::converteParaPetVO);
		}

		
		public PetVO buscarPorId(Long idPetVO) {
			var entity = repository.findById(idPetVO)
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			return DozerAdapter.parseObject(entity, PetVO.class);
		}
		
		public Page<PetVO> buscarPetPorNome(String nome, Pageable pageable){
			var page = repository.buscarPetPorNome(nome, pageable);
			return page.map(this::converteParaPetVO);
			
		}
	
		
		public PetVO atualizar(PetVO petVO) {
			var entity = repository.findById(petVO.getId())
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			entity.setNome(petVO.getNome());
			
				
			var vo = DozerAdapter.parseObject(repository.save(entity), PetVO.class);
			return vo;
		}
		
		public void deletar(Long idPetVO) {
			var entity = repository.findById(idPetVO).orElseThrow(()
					-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			repository.delete(entity);
		}
		
	
	
	
	
}
