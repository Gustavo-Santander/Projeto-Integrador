package br.com.apppetshoprest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apppetshoprest.data.entity.Especie;
@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {

	
	@Query("SELECT e FROM Especie e WHERE e.nomeEspecie LIKE LOWER(CONCAT ('%', :nomeEspecie ,'%'))")
	Page<Especie> buscarEspeciePorNome(@Param("nomeEspecie") String nomeEspecie, Pageable pageable);

	
	

}
