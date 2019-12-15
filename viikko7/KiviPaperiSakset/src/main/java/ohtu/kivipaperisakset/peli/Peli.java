package ohtu.kivipaperisakset.peli;

import ohtu.kivipaperisakset.pelaaja.Pelaaja;

public interface Peli {

    public static Peli kiviPaperiSakset(Pelaaja eka, Pelaaja toka) {
        return new KPSPeli(eka, toka);
    }
    
    void pelaa();
}
