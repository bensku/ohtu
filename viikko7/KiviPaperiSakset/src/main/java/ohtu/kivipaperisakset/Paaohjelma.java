package ohtu.kivipaperisakset;

import java.util.Scanner;

import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import ohtu.kivipaperisakset.pelaaja.Vaikeus;
import ohtu.kivipaperisakset.peli.Peli;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            
            Pelaaja eka = Pelaaja.ihminen(scanner, System.out);
            Pelaaja toka;
            if (vastaus.endsWith("a")) {
                toka = Pelaaja.ihminen(scanner, System.out);
            } else if (vastaus.endsWith("b")) {
                toka = Pelaaja.tekoaly(Vaikeus.HELPPO);
            } else if (vastaus.endsWith("c")) {
                toka = Pelaaja.tekoaly(Vaikeus.VAIKEA);
            } else {
                break;
            }
            Peli peli = Peli.kiviPaperiSakset(eka, toka);
            peli.pelaa();
        }

    }
}
