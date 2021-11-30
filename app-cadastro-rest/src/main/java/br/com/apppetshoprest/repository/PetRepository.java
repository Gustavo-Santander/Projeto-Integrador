package br.com.apppetshoprest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apppetshoprest.data.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

	@Query("SELECT p FROM Pet p WHERE p.nome LIKE LOWER(CONCAT ('%', :nome ,'%'))")
	Page<Pet> buscarPetPorNome(@Param("nome") String nome, Pageable pageable);
	
}
