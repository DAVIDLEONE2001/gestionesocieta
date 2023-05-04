package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

public interface SocietaService {
	
	public List<Societa> listAllSocieta() ;

	public Societa caricaSingoloSocieta(Long id);
	public Societa caricaSingoloSocietaEagher(Long id);

	public void aggiorna(Societa societaInstance);

	public void inserisciNuovo(Societa societaInstance);

	public void rimuovi(Societa societaInstance);
	
	public List<Societa> findByExample(Societa example);
	
	public void inserisciNuovoDipendenteaSocieta(Societa societaInstance, Dipendente dipendenteInstance);
	
	public List<Societa> trovaDistinteSocietaConImpiegatoConRal30000 ();
	


}
