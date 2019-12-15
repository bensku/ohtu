package ohtu.kivipaperisakset;

public enum Siirto {

    KIVI,
    SAKSET,
    PAPERI;
    
    public static Siirto lue(String syöte) {
        if (syöte.equals("k")) {
            return KIVI;
        } else if (syöte.equals("s")) {
            return SAKSET;
        } else if (syöte.equals("p")) {
            return PAPERI;
        }
        return null;
    }
}
