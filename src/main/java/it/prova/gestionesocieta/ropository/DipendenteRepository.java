package it.prova.gestionesocieta.ropository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> , QueryByExampleExecutor<Dipendente> {

	Dipendente findTopBySocieta_DataFondazioneLessThanOrderByDataAssunzioneDesc(LocalDate dataFondazione);
}
