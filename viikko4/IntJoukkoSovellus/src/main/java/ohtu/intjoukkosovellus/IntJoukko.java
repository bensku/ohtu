
package ohtu.intjoukkosovellus;

public class IntJoukko {

	// Vakioasetukset konstruktorille, joka ei ota parametrejä
    private static final int KAPASITEETTI = 5;
    private static final int OLETUSKASVATUS = 5;
    
    /**
     * Kun taulukkoa suurennetaan, uusi koko on tämän verran vanhaa suurempi.
     */
    private final int kasvatuskoko;
    
    /**
     * Taulukko, joka toteuttaa joukon.
     */
    private int[] ljono;
    
    /**
     * Alkioiden määrä taulukossa.
     */
    private int koko;

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
    	// Tarkista parametrien oikeellisuus
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("kapasiteetti < 0");
        }
        if (kasvatuskoko < 0) {
        	throw new IllegalArgumentException("kasvatuskoko < 0");
        }
        
        this.ljono = new int[kapasiteetti];
        this.koko = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public boolean kuuluu(int luku) {
        return etsi(luku) != -1;
    }
    
    private int etsi(int luku) {
    	for (int i = 0; i < koko; i++) {
    		if (ljono[i] == luku) {
    			return i;
    		}
    	}
    	return -1;
    }

    public boolean lisaa(int luku) {
    	if (kuuluu(luku)) {
    		return false; // On jo taulukossa
    	} else {
    		if (koko == ljono.length) {
    			suurennaTaulukkoa(); // Tarvitaan lisää tilaa
    		}
    		ljono[koko++] = luku;
    		
    		return true; // Lisätty
    	}
    }

    private void suurennaTaulukkoa() {
    	int[] uusi = new int[ljono.length + kasvatuskoko];
    	System.arraycopy(ljono, 0, uusi, 0, ljono.length);
    	ljono = uusi;
    }
    
    public boolean poista(int luku) {
    	int paikka = etsi(luku); // Mistä poistetaan
    	if (paikka == -1) {
    		return false; // Ei löytynyt tästä joukosta
    	}
    	
    	// Siirrä kaikki poistetun jälkeen taaemmas
    	for (int i = paikka + 1; i < koko; i++) {
    		ljono[i - 1] = ljono[i];
    	}
    	koko--;
    	
        return true;
    }

    public int koko() {
        return koko;
    }
    
    public int[] toIntArray() {
        int[] taulu = new int[koko];
        System.arraycopy(ljono, 0, taulu, 0, koko);
        return taulu;
    }

    @Override
    public String toString() {
        if (koko == 0) {
            return "{}";
        } else if (koko == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < koko - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[koko - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
        
}
