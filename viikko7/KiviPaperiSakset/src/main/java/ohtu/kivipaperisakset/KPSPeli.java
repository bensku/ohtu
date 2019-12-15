package ohtu.kivipaperisakset;

public class KPSPeli {

    // Pelajat (ihmisiä/tekoälyjä)
    private final Pelaaja eka;
    private final Pelaaja toka;
    
    public KPSPeli(Pelaaja eka, Pelaaja toka) {
        this.eka = eka;
        this.toka = toka;
    }
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        
        while (true) {
            Siirto ekanSiirto = eka.annaSiirto();
            Siirto tokanSiirto = toka.annaSiirto();
            
            if (ekanSiirto == null || tokanSiirto == null) {
                System.out.println(tuomari);
                return; // Peli päättyi
            }
            
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari); // Pelitilanne
            System.out.println();
            
            eka.asetaSiirto(toka, tokanSiirto);
            toka.asetaSiirto(eka, ekanSiirto);
        }
    }
}
