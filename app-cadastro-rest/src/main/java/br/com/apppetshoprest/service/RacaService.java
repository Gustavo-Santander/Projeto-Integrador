package br.com.apppetshoprest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apppetshoprest.adapter.DozerAdapter;
import br.com.apppetshoprest.data.entity.Raca;
import br.com.apppetshoprest.data.vo.v1.RacaVO;
import br.com.apppetshoprest.repository.RacaRepository;


@Service
public class RacaService {
	
	@Autowired
	RacaRepository repository;
	
	private RacaVO converteParaRacaVO(Raca entity) {
		return DozerAdapter.parseObject(entity, RacaVO.class); 
	}

		public RacaVO inserir(RacaVO racaVO) {
			var entity = DozerAdapter.parseObject(racaVO, Raca.class);
			var vo = DozerAdapter.parseObject(repository.save(entity), RacaVO.class);
			
			return vo;
		}
		
		
		public Page<RacaVO> buscarTodos(Pageable pageable) {
			var page = repository.findAll(pageable);
			return page.map(this::converteParaRacaVO);
		}

		
		public RacaVO buscarPorId(Long idRacaVO) {
			var entity = repository.findById(idRacaVO)
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			return DozerAdapter.parseObject(entity, RacaVO.class);
		}
		
		public Page<RacaVO> buscarRacaPorNome(String nome, Pageable pageable){
			var page = repository.buscarRacaPorNome(nome, pageable);
			return page.map(this::converteParaRacaVO);
			
		}
	
		public RacaVO atualizar(RacaVO racaVO) {
			var entity = repository.findById(racaVO.getId())
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			entity.setNomeRaca(racaVO.getNomeRaca());
			
			var vo = DozerAdapter.parseObject(repository.save(entity), RacaVO.class);
			return vo;
		}
		
		public void deletar(Long idRacaVO) {
			var entity = repository.findById(idRacaVO).orElseThrow(()
					-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			repository.delete(entity);
		}
		

}
