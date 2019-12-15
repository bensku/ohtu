package ohtu.kivipaperisakset;

/**
 * Pelaaja, ihminen tai tekoäly.
 *
 */
public interface Pelaaja {

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
