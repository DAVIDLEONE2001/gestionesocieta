package it.prova.gestionesocieta.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;
	@Autowired
	private DipendenteService dipendenteService;

	public void testInserisciSocieta() {
		Long nowInMillisecondi = new Date().getTime();

		Societa societa = new Societa("a" + nowInMillisecondi, "b" + nowInMillisecondi, LocalDate.now());
		if (societa.getId() != null)
			throw new RuntimeException("testInserisciSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		if (societa.getId() == null || societa.getId() < 1)
			throw new RuntimeException("testInserisciSocieta...failed: inserimento fallito");

		System.err.println("testInserisciSocieta........OK");
		societaService.rimuovi(societa);
	}

	public void testFindByExampleSocieta() {
		Long nowInMillisecondi = new Date().getTime();

		Societa societa = new Societa("albero" + nowInMillisecondi, "susina" + nowInMillisecondi, LocalDate.now());
		Societa societa2 = new Societa("babba" + nowInMillisecondi, "cavolo" + nowInMillisecondi,
				LocalDate.parse("1990-01-01"));
		if (societa.getId() != null || societa2.getId() != null)
			throw new RuntimeException("testFindByExampleSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);
		societaService.inserisciNuovo(societa2);

		if (societa.getId() == null || societa.getId() < 1 || societa2.getId() == null || societa2.getId() < 1)
			throw new RuntimeException("testFindByExampleSocieta...failed: inserimento fallito");

		Societa xampleRagioneSociale = new Societa();

		xampleRagioneSociale.setRagioneSociale("al");

		List<Societa> resultXampleRagioneSociale = societaService.findByExample(xampleRagioneSociale);

		if (resultXampleRagioneSociale.size() != 1) {
			throw new RuntimeException("testFindByExampleSocieta...failed: inserimento fallito");

		}
		Societa xampleIndirizzo = new Societa();

		xampleIndirizzo.setIndirizzo("su");

		List<Societa> resultXampleIndirizzo = societaService.findByExample(xampleIndirizzo);

		if (resultXampleIndirizzo.size() != 1) {
			throw new RuntimeException("testFindByExampleSocieta...failed: inserimento fallito");

		}
		Societa xampleData = new Societa();

		xampleData.setDataFondazione(LocalDate.parse("2000-01-01"));

		List<Societa> resultxampleData = societaService.findByExample(xampleData);

		if (resultxampleData.size() != 1) {
			throw new RuntimeException("testFindByExampleSocieta...failed: inserimento fallito");

		}

		System.err.println("testFindByExampleSocieta........OK");
	}

	public void testDeleteSocieta() {
		Long nowInMillisecondi = new Date().getTime();

		Societa societa = new Societa("mimmoSRL" + nowInMillisecondi, "via palindro" + nowInMillisecondi,
				LocalDate.now());
		if (societa.getId() != null)
			throw new RuntimeException("testFindByExampleSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		if (societa.getId() == null || societa.getId() < 1)
			throw new RuntimeException("testFindByExampleSocieta...failed: inserimento fallito");

		societaService.rimuovi(societa);

		if (societaService.caricaSingoloSocieta(societa.getId()) != null)
			throw new RuntimeException("testFindByExampleSocieta...failed: rimozione fallita");

		System.err.println("testFindByExampleSocieta........OK");
	}

	public void testInserisciNuovoDipendenteASocieta() {
		Long nowInMillisecondi = new Date().getTime();

		Societa societa = new Societa("a" + nowInMillisecondi, "b" + nowInMillisecondi, LocalDate.now());
		if (societa.getId() != null)
			throw new RuntimeException(
					"testInserisciNuovoDipendenteASocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		Dipendente dipendente = new Dipendente("mario", "rossi", LocalDate.now(), 12000);

		societaService.inserisciNuovoDipendenteaSocieta(societa, dipendente);

		if (societa.getId() == null || societa.getId() < 1)
			throw new RuntimeException("testInserisciNuovoDipendenteASocieta...failed: inserimento fallito");

		Societa societaReload = societaService.caricaSingoloSocietaEagher(societa.getId());

		if (societaReload.getDipendenti().size() != 1) {

			throw new RuntimeException("testInserisciNuovoDipendenteASocieta...failed: collegamento fallito");
		}

		System.err.println("testInserisciNuovoDipendenteASocieta........OK");
		dipendenteService.rimuovi(dipendente);
		societaService.rimuovi(societa);
	}

	public void testAggiornaDipendente() {
		Long nowInMillisecondi = new Date().getTime();

		Societa societa = new Societa("a" + nowInMillisecondi, "b" + nowInMillisecondi, LocalDate.now());
		Dipendente dipendente = new Dipendente("mario", "rossi", LocalDate.now(), 12000, societa);

		if (societa.getId() != null || dipendente.getId() != null)
			throw new RuntimeException("testAggiornaDipendente...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);
		dipendenteService.inserisciNuovo(dipendente);

		if (societa.getId() == null || societa.getId() < 1 || dipendente.getId() == null || dipendente.getId() < 1)
			throw new RuntimeException("testAggiornaDipendente...failed: inserimento fallito");

		dipendente.setNome("Mimmo");
		dipendenteService.aggiorna(dipendente);

		Dipendente dipendenteReload = dipendenteService.caricaSingoloDipendente(dipendente.getId());

		if (!dipendenteReload.getNome().equals("Mimmo")) {

			throw new RuntimeException("testAggiornaDipendente...failed: moadifica fallita");

		}

		System.err.println("testAggiornaDipendente........OK");
		dipendenteService.rimuovi(dipendente);
		societaService.rimuovi(societa);
	}

	public void testTrovaDistinteSocietaConImpiegatoConRal30000() {
		Long nowInMillisecondi = new Date().getTime();

		IntStream.range(1, 5).forEach(i -> {
			int ralToSet = i % 2 == 0 ? 20000 : 50000;
			Societa societa = new Societa("Mio SpA" + nowInMillisecondi, "Via Fornelli" + nowInMillisecondi,
					LocalDate.now());
			societaService.inserisciNuovo(societa);
			dipendenteService
					.inserisciNuovo(new Dipendente("mario" + i, "rossi" + i, LocalDate.now(), ralToSet, societa));
			dipendenteService
					.inserisciNuovo(new Dipendente("mario" + i, "rossi" + i, LocalDate.now(), ralToSet, societa));

		});

		List<Societa> societaListResult = societaService.trovaDistinteSocietaConImpiegatoConRal30000();

		if (societaListResult.size() != 2) {

			throw new RuntimeException("testTrovaDistinteSocietaConImpiegatoConRal30000...failed: risultato inatteso");

		}

		System.err.println("testTrovaDistinteSocietaConImpiegatoConRal30000........OK");
	}

	public void testTrovaIlPiuAnzianoConSocietaDopoIl1990() {
		Long nowInMillisecondi = new Date().getTime();

		IntStream.range(1, 5).forEach(i -> {
			int yearToSet = i % 2 == 0 ? 2000 : 1980;
			Societa societa = new Societa("Mio SpA" + nowInMillisecondi, "Via Fornelli" + nowInMillisecondi,
					LocalDate.of(yearToSet, 01, 01));
			societaService.inserisciNuovo(societa);
			dipendenteService.inserisciNuovo(new Dipendente("mario" + i, "rossi" + i,
					i == 1 ? LocalDate.parse("1800-01-01") : LocalDate.now(), 300, societa));
			dipendenteService.inserisciNuovo(new Dipendente("mario" + i, "rossi" + i, LocalDate.now(), 300, societa));

		});

		Dipendente dipendenteResult = dipendenteService.trovaIlPiuAnzianoConSocietaDopoIl1990();

		if (!dipendenteResult.getNome().equals("mario1")) {

			throw new RuntimeException("testTrovaIlPiuAnzianoConSocietaDopoIl1990...failed: risultato inatteso");

		}

		System.err.println("testTrovaIlPiuAnzianoConSocietaDopoIl1990........OK");
	}

}
