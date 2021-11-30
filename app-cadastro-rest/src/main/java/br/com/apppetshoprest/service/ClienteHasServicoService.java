package br.com.apppetshoprest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apppetshoprest.adapter.DozerAdapter;
import br.com.apppetshoprest.data.entity.ClienteHasServico;
import br.com.apppetshoprest.data.vo.v1.ClienteHasServicoVO;
import br.com.apppetshoprest.repository.ClienteHasServicoRepository;
import br.com.apppetshoprest.repository.ClienteRepository;

@Service
public class ClienteHasServicoService {
	
	@Autowired
	ClienteHasServicoRepository repository;
	
	private ClienteHasServicoVO converteParaClienteHasServicoVO(ClienteHasServico entity) {
		return DozerAdapter.parseObject(entity, ClienteHasServicoVO.class); 
	}

		public ClienteHasServicoVO inserir(ClienteHasServicoVO ClienteHasServicoVO) {
			var entity = DozerAdapter.parseObject(ClienteHasServicoVO, ClienteHasServico.class);
			var vo = DozerAdapter.parseObject(repository.save(entity), ClienteHasServicoVO.class);
			return vo;
		}
		
		
		public Page<ClienteHasServicoVO> buscarTodos(Pageable pageable) {
			var page = repository.findAll(pageable);
			return page.map(this::converteParaClienteHasServicoVO);
		}

		
		public ClienteHasServicoVO buscarPorId(Long id) {
			var entity = repository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			return DozerAdapter.parseObject(entity, ClienteHasServicoVO.class);
		}
		

	
		public void deletar(Long idClienteVO) {
			var entity = repository.findById(idClienteVO).orElseThrow(()
					-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			repository.delete(entity);
		}
		

}
