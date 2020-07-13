package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private boolean flag = false;
	private Integer anno;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> boxAnno;

    @FXML
    private TextField txtFieldStudenti;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	Integer anno = this.boxAnno.getValue();
    	
    	if(anno==null) {
    		this.txtResult.setText("Devi selezionare un anno");
    		return;
    	}
    	
    	
    	this.model.creaGrafo(anno);
    	
    	this.anno = anno;
    	
    	this.txtResult.setText("Grafo creato\n#VERTICI: "+this.model.getNumeroVertici()+"\n#ARCHI: "+this.model.getNumeroArchi()+"\n");

    	if(this.model.verificaConnessione()==false) {
    		this.txtResult.appendText("Il grafo non è fortemente connesso\n");
    	}else {
    		this.txtResult.appendText("Il grafo è fortemente connesso\n");
    	}
    	
    	this.txtResult.appendText("La mostra con più opere d'arte in esposizione è:\n"+this.model.getMostraMigliore()+"\n");
    	
    	this.flag = true;
    	
    }

    @FXML
    void handleSimula(ActionEvent event) {
    	
    	if(this.flag == false) {
    		this.txtResult.setText("Devi creare prima il grafo\n");
    		return;
    	}
    	
    	
    	int numero;
    	
    	try {
    	    		
    	    numero = Integer.parseInt(this.txtFieldStudenti.getText());
    	    	    		
    	}catch(NumberFormatException ne) {
    	    this.txtResult.setText("Formato numero studenti errato\n");
    	    return;
    	}
    	
    	this.txtResult.clear();
    	for(Studente s: this.model.simula(numero, anno)) {
    		this.txtResult.appendText(s+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxAnno.getItems().addAll(this.model.getAnno());
	}
}