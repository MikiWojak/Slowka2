package Edycja;

import java.awt.EventQueue;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;

/**
 * Okno edycji grupy lub s�owa. W nim b�dzie panel odpowiedzialny za funkcjonowanie okienka.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Edycja extends JDialog {
	
	/**
	 * Panel do edycji grupy lub s�owa.
	 */
	private Panel panel;
	
	/**
	 * Utworzenie okienka.
	 */
	public Edycja(int tryb) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		switch(tryb) {
		case 0:
			panel = new Grupa();
			setTitle("Dodaj grupe");
			break;
		case 1:
			panel = new Slowo();
			setTitle("Dodaj s�owo");
			break;
		}
		
		add(panel);
		pack();
		
		inicjujKoniec();
	}
	
	/**
	 * Odpowiada za zintegorwanie z g��wnym oknem programu oraz za widoczno��.
	 * Nie pozwala na dzia�anie w aplikacji dop�ki okienko nie zostanie zamkni�te.
	 * Na sam koniec konstruktora.
	 */
	private void inicjujKoniec() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}

}
