package ohtu.kivipaperisakset;

/**
 * Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
 */
public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;
    }

    public void kirjaaSiirto(Siirto ekanSiirto, Siirto tokanSiirto) {
        if (ekanSiirto == tokanSiirto) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    // sisäinen metodi joka tarkastaa voittaako eka pelaaja tokan
    private static boolean ekaVoittaa(Siirto eka, Siirto toka) {
        if (eka == Siirto.KIVI && toka == Siirto.SAKSET) {
            return true;
        } else if (eka == Siirto.SAKSET && toka == Siirto.PAPERI) {
            return true;
        } else if (eka == Siirto.PAPERI && toka == Siirto.KIVI) {
            return true;
        }

        return false;
    }

    public String toString() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
    }
}