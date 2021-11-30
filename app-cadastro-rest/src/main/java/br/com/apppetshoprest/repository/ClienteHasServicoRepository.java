package br.com.apppetshoprest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apppetshoprest.data.entity.ClienteHasServico;


@Repository
public interface ClienteHasServicoRepository extends JpaRepository<ClienteHasServico, Long>{


	@Query("SELECT chs FROM ClienteHasServico chs WHERE chs.data LIKE LOWER(CONCAT ('%', :data ,'%'))")
	Page<ClienteHasServico> buscarServicoPorData(@Param("data") String data, Pageable pageable);

}
