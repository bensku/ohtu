package laskin;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.komento.Komento;

public class Tapahtumankuuntelija {
    private TextField tuloskentta; 
    private TextField syotekentta; 
    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    
    private Komento edellinen;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
    }
    
    private void komento(Komento komento) {
    	komento.tee(sovellus);
    	edellinen = komento;
    	
    	undo.setDisable(false);
    	päivitäUi();
    }
    
    private void päivitäUi() {
        if (sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
    
    private int syöte() {
    	try {
    		return Integer.parseInt(syotekentta.getText());
    	} catch (NumberFormatException e) {
    		return 0;
    	}
    }
    
    public void plus(ActionEvent event) {
    	int arvo = syöte();
    	komento(new Komento(sovellus -> sovellus.plus(arvo), sovellus -> sovellus.miinus(arvo)));
    }
    
    public void miinus(ActionEvent event) {
    	int arvo = syöte();
    	komento(new Komento(sovellus -> sovellus.miinus(arvo), sovellus -> sovellus.plus(arvo)));
    }
    
    public void nollaa(ActionEvent event) {
    	int vanha = sovellus.tulos();
    	komento(new Komento(sovellus -> sovellus.nollaa(), sovellus -> sovellus.plus(vanha)));
    }
    
    public void peru(ActionEvent event) {
    	if (edellinen == null) {
    		return;
    	}
    	edellinen.peru(sovellus);
    	edellinen = null;
    	
    	undo.setDisable(true);
    	päivitäUi();
    }

}
