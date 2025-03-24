import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.Test;

public class GiocatoreTest {
	
	Giocatore g = new Giocatore();
	
	//Test per metodo getCfu
	@Test
	public void testgetCfu() {
		assertEquals(20, g.getCfu());
	}
	
	//Test per metodo setCfu
	@Test
	public void testSetCfu() {
		g.setCfu(1);
		assertEquals(1, g.getCfu());
	}
	
	//Test per metodo getBorsa
	@Test
	public void getBorsa() {
		assertNotNull(g.getBorsa());
	}
	
	//Test per metodo setBorsa
	@Test
	public void setBorsa() {
		Borsa b1 = new Borsa();
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		b1.addAttrezzo(attrezzo);
		g.setBorsa(b1);
		
		assertEquals(b1, g.getBorsa());
	}

}
