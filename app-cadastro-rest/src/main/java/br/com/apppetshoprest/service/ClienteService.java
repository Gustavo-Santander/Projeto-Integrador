package br.com.apppetshoprest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apppetshoprest.adapter.DozerAdapter;
import br.com.apppetshoprest.data.entity.Cliente;
import br.com.apppetshoprest.data.vo.v1.ClienteVO;
import br.com.apppetshoprest.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	private ClienteVO converteParaClienteVO(Cliente entity) {
		return DozerAdapter.parseObject(entity, ClienteVO.class); 
	}

		public ClienteVO inserir(ClienteVO ClienteVO) {
			var entity = DozerAdapter.parseObject(ClienteVO, Cliente.class);
			var vo = DozerAdapter.parseObject(repository.save(entity), ClienteVO.class);
			return vo;
		}
		
		
		public Page<ClienteVO> buscarTodos(Pageable pageable) {
			var page = repository.findAll(pageable);
			return page.map(this::converteParaClienteVO);
		}

		
		public ClienteVO buscarPorId(Long idClienteVO) {
			var entity = repository.findById(idClienteVO)
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			return DozerAdapter.parseObject(entity, ClienteVO.class);
		}
		
		public Page<ClienteVO> buscarClientePorNome(String nome, Pageable pageable){
			var page = repository.buscarClientePorNome(nome, pageable);
			return page.map(this::converteParaClienteVO);
			
		}
	
		public ClienteVO atualizar(ClienteVO ClienteVO) {
			var entity = repository.findById(ClienteVO.getId())
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			entity.setNome(ClienteVO.getNome());
			entity.setTelefone(ClienteVO.getTelefone());
			entity.setEmail(ClienteVO.getEmail());
			
			var vo = DozerAdapter.parseObject(repository.save(entity), ClienteVO.class);
			return vo;
		}
		
		public void deletar(Long idClienteVO) {
			var entity = repository.findById(idClienteVO).orElseThrow(()
					-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			repository.delete(entity);
		}
		

}
