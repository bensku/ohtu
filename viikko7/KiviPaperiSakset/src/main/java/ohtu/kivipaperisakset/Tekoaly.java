package ohtu.kivipaperisakset;

public class Tekoaly implements Pelaaja {

    int siirto;

    public Tekoaly() {
        siirto = 0;
    }

    @Override
    public Siirto annaSiirto() {
        siirto++;
        siirto = siirto % 3;

        if (siirto == 0) {
            return Siirto.KIVI;
        } else if (siirto == 1) {
            return Siirto.PAPERI;
        } else {
            return Siirto.SAKSET;
        }
    }

    @Override
    public void asetaSiirto(Pelaaja toinen, Siirto ekanSiirto) {
        // ei tehdä mitään 
    }
}
