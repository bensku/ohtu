
package ohtu.kivipaperisakset.pelaaja;

import ohtu.kivipaperisakset.Siirto;

// "Muistava tekoäly"

class TekoalyParannettu implements Pelaaja {

    private Siirto[] muisti;
    private int vapaaMuistiIndeksi;

    public TekoalyParannettu(int muistinKoko) {
        muisti = new Siirto[muistinKoko];
        vapaaMuistiIndeksi = 0;
    }

    public void asetaSiirto(Pelaaja toinen, Siirto siirto) {
        // jos muisti täyttyy, unohdetaan viimeinen alkio
        if(vapaaMuistiIndeksi == muisti.length) {
            for(int i = 1; i < muisti.length; i++) {
                muisti[i-1] = muisti[i];
            }

            vapaaMuistiIndeksi--;
        }

        muisti[vapaaMuistiIndeksi] = siirto;    
        vapaaMuistiIndeksi++;
    }


    public Siirto annaSiirto() {
        if(vapaaMuistiIndeksi == 0 || vapaaMuistiIndeksi == 1) {
            return Siirto.KIVI;
        }

        Siirto viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];

        int kivet = 0;
        int paperit = 0;
        int sakset = 0;


        for(int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if(viimeisinSiirto.equals(muisti[i])) {
                Siirto seuraava = muisti[i+1];

                if (seuraava == Siirto.KIVI) {
                    kivet++;
                } else if (seuraava == Siirto.PAPERI) {
                    paperit++;
                } else {
                    sakset++;
                }        
            }
        }


        // Tehdään siirron valinta esimerkiksi seuraavasti;
        // - jos kiviä eniten, annetaan aina paperi
        // - jos papereita eniten, annetaan aina sakset
        // muulloin annetaan aina kivi
        if(kivet > paperit && kivet > sakset) {
            return Siirto.PAPERI;
        }
        else if (paperit > kivet && paperit > sakset) {
            return Siirto.SAKSET;
        } else {
            return Siirto.KIVI;
        }

        // Tehokkaampiakin tapoja löytyy, mutta niistä lisää 
        // Johdatus Tekoälyyn kurssilla!
    }
}