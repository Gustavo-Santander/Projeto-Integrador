package br.com.apppetshoprest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.apppetshoprest.data.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE LOWER(CONCAT ('%', :nome ,'%'))")
	Page<Cliente> buscarClientePorNome(@Param("nome") String nome, Pageable pageable);
	
}
