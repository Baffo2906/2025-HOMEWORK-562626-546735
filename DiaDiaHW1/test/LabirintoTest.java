import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabirintoTest {
	
	Labirinto l;
	Stanza stanzaVincente;
	Stanza stanzaCorrente;
	
	@BeforeEach
	public void setUp() {
		l = new Labirinto();
		l.creaStanze();
		stanzaVincente = new Stanza("Biblioteca");
		stanzaCorrente = new Stanza("DS1");
	}
	
	//Test per il metodo getStanzaVincente
	@Test
	void testGetStanzaVincente() {
		assertEquals("Biblioteca", l.getStanzaVincente().getNome());
	}
	
	//Test per il metodo setStanzaCorrente
	@Test 
	void testSetStanzaCorrente() {
		l.setStanzaCorrente(stanzaCorrente);
		assertEquals("DS1", l.getStanzaCorrente().getNome());
	}

}
