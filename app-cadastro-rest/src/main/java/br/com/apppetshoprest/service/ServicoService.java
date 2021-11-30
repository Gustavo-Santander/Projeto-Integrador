package br.com.apppetshoprest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.apppetshoprest.adapter.DozerAdapter;
import br.com.apppetshoprest.data.entity.Servico;
import br.com.apppetshoprest.data.vo.v1.ServicoVO;
import br.com.apppetshoprest.exception.ResourceNotFoundException;
import br.com.apppetshoprest.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	ServicoRepository repository;
	
	private ServicoVO converteParaServicoVO(Servico entity) {
		return DozerAdapter.parseObject(entity, ServicoVO.class); 
	}

	
	
		public ServicoVO inserir(ServicoVO servicoVO) {
			var entity = DozerAdapter.parseObject(servicoVO, Servico.class);
			var vo = DozerAdapter.parseObject(repository.save(entity), ServicoVO.class);
			return vo;
		}
		
		
		public Page<ServicoVO> buscarTodos(Pageable pageable) {
			var page = repository.findAll(pageable);
			return page.map(this::converteParaServicoVO);
		}

		
		public ServicoVO buscarPorId(Long idServicoVO) {
			var entity = repository.findById(idServicoVO)
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			return DozerAdapter.parseObject(entity, ServicoVO.class);
		}
		
		public Page<ServicoVO> buscarServicoPorNome(String nome, Pageable pageable){
			var page = repository.buscarServicoPorNome(nome, pageable);
			return page.map(this::converteParaServicoVO);
			
		}
	
		
		public ServicoVO atualizar(ServicoVO servicoVO) {
			var entity = repository.findById(servicoVO.getId())
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			entity.setTipoServico(servicoVO.getTipoServico()); 
				
			var vo = DozerAdapter.parseObject(repository.save(entity), ServicoVO.class);
			return vo;
		}
		
		public void deletar(Long idServicoVO) {
			var entity = repository.findById(idServicoVO).orElseThrow(()
					-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			repository.delete(entity);
		}
		
	
	
	
	
}
