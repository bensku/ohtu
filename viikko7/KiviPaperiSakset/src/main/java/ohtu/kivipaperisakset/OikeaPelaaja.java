package ohtu.kivipaperisakset;

import java.io.PrintStream;
import java.util.Scanner;

public class OikeaPelaaja implements Pelaaja {

    private final Scanner in;
    private final PrintStream out;
    
    public OikeaPelaaja(Scanner in, PrintStream out) {
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
        if (!(toinen instanceof OikeaPelaaja)) {
            out.println("Toinen pelaaja valitsi: " + siirto.name().toLowerCase());
        }
    }

}
