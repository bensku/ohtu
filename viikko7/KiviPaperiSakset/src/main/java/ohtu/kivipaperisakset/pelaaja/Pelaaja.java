package ohtu.kivipaperisakset.pelaaja;

import java.io.PrintStream;
import java.util.Scanner;

import ohtu.kivipaperisakset.Siirto;

/**
 * Pelaaja, ihminen tai tekoäly.
 *
 */
public interface Pelaaja {
    
    public static Pelaaja ihminen(Scanner in, PrintStream out) {
        return new Ihminen(in, out);
    }
    
    public static Pelaaja tekoaly(Vaikeus vaikeus) {
        if (vaikeus == Vaikeus.HELPPO) {
            return new Tekoaly();
        } else if (vaikeus == Vaikeus.VAIKEA) {
            return new TekoalyParannettu(20);
        }
        throw new AssertionError("tuntematon vaikeus");
    }

    /**
     * Antaa uuden siirron.
     * @return Uusi siirto.
     */
    Siirto annaSiirto();
    
    /**
     * Kertoo pelaajalle vastustajan siirron.
     * @param toinen Vastustaja.
     * @param siirto Vastustajan siirto.
     */
    void asetaSiirto(Pelaaja toinen, Siirto siirto);
}
