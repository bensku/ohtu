package ohtu.kivipaperisakset.pelaaja;

import java.io.PrintStream;
import java.util.Scanner;

import ohtu.kivipaperisakset.Siirto;

class Ihminen implements Pelaaja {

    private final Scanner in;
    private final PrintStream out;
    
    public Ihminen(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }
    
    @Override
    public Siirto annaSiirto() {
        out.print("Siirto: ");
        return Siirto.lue(in.nextLine());
    }

    @Override
    public void asetaSiirto(Pelaaja toinen, Siirto siirto) {
        if (!(toinen instanceof Ihminen)) {
            out.println("Toinen pelaaja valitsi: " + siirto.name().toLowerCase());
        }
    }

}
