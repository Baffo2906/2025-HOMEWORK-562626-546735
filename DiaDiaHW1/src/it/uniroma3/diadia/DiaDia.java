package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.Scanner;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine"};

	private IOConsole io;
	private Partita partita;

	public DiaDia(IOConsole console) {
		this.io = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		}
		else if(comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Comando "prendi".
	 * Prende un oggetto da una stanza e lo aggiunge alla borsa.
	 */
	private void prendi(String nomeAttrezzo) {
		Attrezzo attrezzoDaPrendere = null;
		if(nomeAttrezzo == null)
			io.mostraMessaggio("Scegli un attrezzo presente nella stanza.");
		if(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			attrezzoDaPrendere = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				io.mostraMessaggio("Attrezzo aggiunto alla borsa");
			}
			else
			{
				io.mostraMessaggio("La borsa è piena o troppo pesante.");
			}
		}
		return;
	}
	
	/**
	 * Comando "posa".
	 * Prende un oggetto dalla borsa e lo aggiunge alla stanza corrente.
	 */
	private void posa(String nomeAttrezzo) {
		Attrezzo daPosare = null;
		if(nomeAttrezzo == null)
			io.mostraMessaggio("Prendi un attrezzo presente nella borsa.");
		if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			daPosare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(daPosare)) {
				this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				io.mostraMessaggio("Attrezzo aggiunto alla stanza");
			}
			else
			{
				io.mostraMessaggio("La stanza è piena");
			}
		}
		return;
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}