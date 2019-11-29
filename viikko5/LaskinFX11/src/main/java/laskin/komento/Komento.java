package laskin.komento;

import java.util.function.Consumer;

import laskin.Sovelluslogiikka;

public class Komento {

	private final Consumer<Sovelluslogiikka> tee;
	private final Consumer<Sovelluslogiikka> peru;
	
	public Komento(Consumer<Sovelluslogiikka> tee, Consumer<Sovelluslogiikka> peru) {
		this.tee = tee;
		this.peru = peru;
	}
	
	public void tee(Sovelluslogiikka sovellus) {
		tee.accept(sovellus);
	}
	
	public void peru(Sovelluslogiikka sovellus) {
		peru.accept(sovellus);
	}
}
