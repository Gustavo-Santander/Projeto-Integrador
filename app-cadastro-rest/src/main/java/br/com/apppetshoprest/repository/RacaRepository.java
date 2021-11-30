package br.com.apppetshoprest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import br.com.apppetshoprest.data.entity.Raca;

@Repository
public interface RacaRepository  extends JpaRepository<Raca, Long>{


	@Query("SELECT r FROM Raca r WHERE r.nomeRaca LIKE LOWER(CONCAT ('%', :nomeRaca ,'%'))")
	Page<Raca> buscarRacaPorNome(@Param("nomeRaca") String nomeRaca, Pageable pageable);

}
