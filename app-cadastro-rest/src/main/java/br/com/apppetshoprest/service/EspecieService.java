package br.com.apppetshoprest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apppetshoprest.adapter.DozerAdapter;
import br.com.apppetshoprest.data.entity.Especie;
import br.com.apppetshoprest.data.vo.v1.EspecieVO;
import br.com.apppetshoprest.repository.EspecieRepository;


@Service
public class EspecieService {
	
	@Autowired
	EspecieRepository repository;
	
	private EspecieVO converteParaEspecieVO(Especie entity) {
		return DozerAdapter.parseObject(entity, EspecieVO.class); 
	}

		public EspecieVO inserir(EspecieVO especieVO) {
			var entity = DozerAdapter.parseObject(especieVO, Especie.class);
			var vo = DozerAdapter.parseObject(repository.save(entity), EspecieVO.class);
			return vo;
		}
		
		
		public Page<EspecieVO> buscarTodos(Pageable pageable) {
			var page = repository.findAll(pageable);
			return page.map(this::converteParaEspecieVO);
		}

		
		public EspecieVO buscarPorId(Long idEspecieVO) {
			var entity = repository.findById(idEspecieVO)
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			return DozerAdapter.parseObject(entity, EspecieVO.class);
		}
		
		public Page<EspecieVO> buscarEspeciePorNome(String nome, Pageable pageable){
			var page = repository.buscarEspeciePorNome(nome, pageable);
			return page.map(this::converteParaEspecieVO);
			
		}
	
		public EspecieVO atualizar(EspecieVO especieVO) {
			var entity = repository.findById(especieVO.getId())
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			entity.setNomeEspecie(especieVO.getNomeEspecie());
			
			var vo = DozerAdapter.parseObject(repository.save(entity), EspecieVO.class);
			return vo;
		}
		
		public void deletar(Long idEspecieVO) {
			var entity = repository.findById(idEspecieVO).orElseThrow(()
					-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			repository.delete(entity);
		}
		

}
