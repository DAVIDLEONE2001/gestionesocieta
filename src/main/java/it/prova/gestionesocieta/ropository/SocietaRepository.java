package it.prova.gestionesocieta.ropository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Societa;

public interface SocietaRepository extends CrudRepository<Societa, Long> , QueryByExampleExecutor<Societa> {

	@Query("SELECT u FROM Societa u WHERE u.id = ?1")
	Optional<Societa> findSocietaByIdLazy(Long id);
	
	@EntityGraph(attributePaths = { "dipendenti" })
	Optional<Societa> findById (Long id);
	
	List<Societa> findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan (Integer Ral );
	
	
}
