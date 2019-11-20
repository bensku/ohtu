package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
	
	private Pankki pankki;
	private Viitegeneraattori viite;
	private Varasto varasto;
	private Kauppa kauppa;

	@Before
	public void init() {
		pankki = mock(Pankki.class);
		viite = mock(Viitegeneraattori.class);
    	when(viite.uusi()).thenReturn(42);
		varasto = mock(Varasto.class);
		kauppa = new Kauppa(varasto, pankki, viite);
	}
	
    @Test
    public void ostosToimii() {
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));      

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
    }
    
    @Test
    public void kaksiTuotetta() {
    	when(varasto.saldo(anyInt())).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "leipä", 10));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito", 5));
    	
    	kauppa.aloitaAsiointi();
    	kauppa.lisaaKoriin(1);
    	kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(15)); 
    }
    
    @Test
    public void montaKertaa() {
    	when(varasto.saldo(anyInt())).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "leipä", 10));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito", 5));
    	
    	kauppa.aloitaAsiointi();
    	kauppa.lisaaKoriin(1);
    	kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(20)); 
    }
    
    @Test
    public void tuoteLoppu() {
    	when(varasto.saldo(1)).thenReturn(0);
    	when(varasto.saldo(2)).thenReturn(1);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "leipä", 10));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito", 5));
    	
    	kauppa.aloitaAsiointi();
    	kauppa.lisaaKoriin(1);
    	kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5)); 
    }
    
    @Test
    public void nollaus() {
    	kaksiTuotetta();
    	
    	kauppa.aloitaAsiointi();
    	kauppa.lisaaKoriin(1);
    	kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki, times(2)).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(15)); 
    }
    
    @Test
    public void uusiViitenumero() {
    	nollaus();
    	verify(viite, times(2)).uusi();
    }
    
    @Test
    public void koristaPoisto() {
    	when(varasto.saldo(anyInt())).thenReturn(10);
    	Tuote leipä = new Tuote(1, "leipä", 10);
    	when(varasto.haeTuote(1)).thenReturn(leipä);
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito", 5));
    	
    	kauppa.aloitaAsiointi();
    	kauppa.lisaaKoriin(1);
    	kauppa.poistaKorista(1);
    	
    	verify(varasto).palautaVarastoon(leipä);
    }
}

