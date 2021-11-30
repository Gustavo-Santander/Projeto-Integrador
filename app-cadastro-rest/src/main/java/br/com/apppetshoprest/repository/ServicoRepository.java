package br.com.apppetshoprest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apppetshoprest.data.entity.Servico;


@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{

	@Query("SELECT s FROM Servico s WHERE s.tipoServico LIKE LOWER(CONCAT ('%', :tipoServico ,'%'))")
	Page<Servico> buscarServicoPorNome(@Param("tipoServico") String tipoServico, Pageable pageable);
	
}
