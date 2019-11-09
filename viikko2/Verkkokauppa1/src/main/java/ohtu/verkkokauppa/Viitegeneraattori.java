package ohtu.verkkokauppa;

public class Viitegeneraattori implements IViitegeneraattori {

    private static IViitegeneraattori instanssi;

    public static IViitegeneraattori getInstance() {
        if (instanssi == null) {
            instanssi = new Viitegeneraattori();
        }

        return instanssi;
    }
    
    private int seuraava;
    
    private Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
	public int uusi(){
        return seuraava++;
    }
}
