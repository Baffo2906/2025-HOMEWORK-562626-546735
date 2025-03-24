import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import org.junit.jupiter.api.Test;

public class BorsaTest {

	Borsa b = new Borsa();

	//Test per metodo addAttrezzo
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo1 = new Attrezzo("attrezzo1", 1);
		assertTrue(b.addAttrezzo(attrezzo1));
	}
	
	@Test 
	public void testAddAttrezzoBorsaPiena() {
        for (int i = 0; i < 10; i++) {
            b.addAttrezzo(new Attrezzo("attrezzo" + (i + 1), 1));
        }
        
        Attrezzo attrezzoDiTroppo = new Attrezzo("attrezzoDiTroppo", 1);
        assertFalse(b.addAttrezzo(attrezzoDiTroppo));
	}
	
	@Test
	public void testAddAttrezzoPesante() {
		Attrezzo attrezzoPesante = new Attrezzo("attrezzoPesante", 11);
		assertFalse(b.addAttrezzo(attrezzoPesante));
		
	}
	
	//Test per metodo getPeso
	@Test
	public void testGetPeso() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 7);
		b.addAttrezzo(attrezzo);
		assertEquals(7, b.getPeso());
	}
	
	//Test per metodo isEmpty
	@Test
	public void testIsEmptyTrue() {
		assertTrue(b.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		b.addAttrezzo(attrezzo);
		assertFalse(b.isEmpty());
	}
	
	//Test per hasAttrezzo (contiene anche getAttrezzo)
	@Test
	public void testHasAttrezzoTrue() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		b.addAttrezzo(attrezzo);
		assertTrue(b.hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void testHasAttrezzoFalse() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		b.addAttrezzo(attrezzo);
		assertFalse(b.hasAttrezzo("attrezzo1"));
	}
	
	//Test per removeAttrezzo
	@Test
	public void testRemoveAttrezzoPresente() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		b.addAttrezzo(attrezzo);
		assertEquals(attrezzo, b.removeAttrezzo("attrezzo"));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		b.addAttrezzo(attrezzo);
		assertNull(b.removeAttrezzo("attrezzo1"));
	}
	//Test per toString
	@Test
	public void testToStringBorsaVuota() {
		assertTrue(b.toString().equals("Borsa vuota"));
	}
	
	@Test
	public void testToStringBorsaNonVuota() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		b.addAttrezzo(attrezzo);
		String outputDesiderato = "Contenuto borsa (1kg/10kg): attrezzo (1kg)";
		assertEquals(outputDesiderato.trim(), b.toString().trim());
	}
		
	
	
	
}
