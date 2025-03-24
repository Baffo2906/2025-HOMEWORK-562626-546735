import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StanzaTest {
	private Stanza stanza;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	
	@BeforeEach
	public void setUp() {
		stanza = new Stanza("stanza");
		attrezzo1 = new Attrezzo("attrezzo1", 1);
		attrezzo2 = new Attrezzo("attrezzo2", 2);
		attrezzo3 = new Attrezzo("attrezzo3", 3);
	}
	
	
	//Test per metodo addAttrezzo
	@Test
	public void testAddAttrezzo() {
		assertTrue(stanza.addAttrezzo(attrezzo1));
	}
	
	@Test
	public void testAddAttrezzoStanzaPiena() {
		for(int i = 0; i < 10; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		assertFalse(stanza.addAttrezzo(attrezzo3));
	}
	
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(stanza.addAttrezzo(null));
	}
	
	//Test per metodo hasAttrezzo
	@Test
	public void testHasAttrezzo() {
		stanza.addAttrezzo(attrezzo2);
		assertTrue(stanza.hasAttrezzo("attrezzo2"));
	}
	
	@Test
	public void testHasAttrezzoNull() {
		assertFalse(stanza.hasAttrezzo(null));
	}
	
	@Test
	public void testHasAttrezzoSconosciuto() {
		assertFalse(stanza.hasAttrezzo("attrezzoSconosciuto"));
	}
	
	//Test per metodo getAttrezzo
	@Test
	public void testGetAttrezzoStanzaVuota() {
		assertNull(new Stanza("stanza").getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testGetAttrezzoPresente() {
		stanza.addAttrezzo(new Attrezzo("attrezzo", 1));
		assertNotNull(stanza.getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testGetAttrezzoNonPresente() {
		stanza.addAttrezzo(new Attrezzo("attrezzo", 1));
		assertNull(stanza.getAttrezzo("attrezzoNonPresente"));
		
	}
	
	//Test per metodo getStanzaAdiacente
	@Test
	public void testGetStanzaAdiacenteEsistente() {
		Stanza stanza1 = new Stanza("stanza1");
		stanza.impostaStanzaAdiacente("nord", stanza1);
		assertEquals(stanza1, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteNonEsistente() {
		assertNull(stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneNull() {
		assertNull(stanza.getStanzaAdiacente(null));
	}
	
	//Test per metodo removeAttrezzo
	@Test
	public void testRemoveAttrezzoPresente() {
		stanza.addAttrezzo(attrezzo1);
		assertTrue(stanza.removeAttrezzo(attrezzo1));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		stanza.addAttrezzo(attrezzo1);
		assertFalse(stanza.removeAttrezzo(attrezzo2));
	}
	
	//Test per metodo getDirezioni
	@Test
	public void testGetDirezioni() {
		Stanza stanzaNord = new Stanza("stanzaNord");
		Stanza stanzaCentrale = new Stanza("StanzaCentrale");
		String[] direzioniAttese = {"nord"};
		stanzaCentrale.impostaStanzaAdiacente("nord", stanzaNord);
		
		assertArrayEquals(direzioniAttese, stanzaCentrale.getDirezioni());
		
	}
	
	//Test per getDescrizione
	@Test
	public void testGetDescrizione() {
		Stanza stanza1 = new Stanza("stanza1");
		Stanza stanza2 = new Stanza("stanza2");
		
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		stanza1.addAttrezzo(attrezzo1);
		
        String risultatoAtteso = "stanza1\nUscite:  nord\nAttrezzi nella stanza: attrezzo1 (1kg)";
        
        assertEquals(risultatoAtteso.trim(), stanza1.getDescrizione().trim());
	}
	
	//Test getNome
	@Test
	public void testGetNome() {
		Stanza stanzaNome = new Stanza("stanzaNome");
		assertTrue(stanzaNome.getNome().equals("stanzaNome"));
	}
	
	//Test per getAttrezzi
	@Test
	public void testGetAttrezzi() {
		stanza.addAttrezzo(attrezzo1);
		assertEquals(stanza.getAttrezzi()[0], attrezzo1);
	}
}
