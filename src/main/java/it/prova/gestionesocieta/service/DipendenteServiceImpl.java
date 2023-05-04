package it.prova.gestionesocieta.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.ropository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {

		dipendenteRepository.save(dipendenteInstance);
		
	}

	@Override
	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Dipendente DipendenteInstance) {

		dipendenteRepository.delete(DipendenteInstance);
		
	}

	@Override
	public Dipendente trovaIlPiuAnzianoConSocietaDopoIl1990() {
		return dipendenteRepository.findTopBySocieta_DataFondazioneLessThanOrderByDataAssunzioneDesc(LocalDate.parse("1990-01-01"));
	}

}
