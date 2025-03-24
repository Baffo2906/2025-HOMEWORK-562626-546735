import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.Giocatore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartitaTest {
	
	private Partita p;
	private Labirinto l;
	private Giocatore g;
	
	@BeforeEach
	public void setUp() {
		p = new Partita();
		l = p.getLabirinto();
		g = p.getGiocatore();
		
	}
	
	//Test per metodo vinta
	@Test
	public void testVintaStanzaVincente() {
		this.p.getLabirinto().setStanzaCorrente(l.getStanzaVincente());
		assertTrue(p.vinta());
	}
	
	@Test
	public void testVintaStanzaNonVincente() {
		Stanza stanzaNonVincente = new Stanza("stanzaNonVincente");
		this.p.getLabirinto().setStanzaCorrente(stanzaNonVincente);
		assertFalse(p.vinta());
	}
	
	@Test
	public void testVintaStanzaNull() {
		this.p.getLabirinto().setStanzaCorrente(null);
		assertFalse(p.vinta());
	}
	
	//Test per metodo isFinita
	@Test
	public void testIsFinitaPartitaVinta() {
		this.p.getLabirinto().setStanzaCorrente(l.getStanzaVincente());
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testIsFinitaPartitaZeroCfu() {
		g.setCfu(0);
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testIsFinitaInizioPartita() {
		assertFalse(p.isFinita());
	}
	
	//Test per il metodo setLabirinto
	@Test
	public void testSetLabirinto() {
		Labirinto nuovoLabirinto = new Labirinto();
		p.setLabirinto(nuovoLabirinto);
		assertEquals(nuovoLabirinto, p.getLabirinto());
	}
	
	//Test per il metodo setGiocatore
	@Test
	public void testSetGiocatore() {
		Giocatore nuovoGiocatore = new Giocatore();
		p.setGiocatore(nuovoGiocatore);
		assertEquals(nuovoGiocatore, p.getGiocatore());
	}
	
	//Test per metodo setFinita
	@Test
	public void testSetFinita() {
		p.setFinita();
		assertTrue(p.isFinita());
	}

}
