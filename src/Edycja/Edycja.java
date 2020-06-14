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
	 * Utworzenie okienka do dodawania s�owa lub grupy.
	 * @param tryb nr trybu:
	 * <ul>
	 * <li>0 - Dodaj grup�</li>
	 * <li>1 - Dodaj slowo</li>
	 * </ul>
	 */
	public Edycja(int tryb) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		switch(tryb) {
		case 0:
			panel = new Grupa();
			inicjujKomponenty("Dodaj grup�");
			break;
		case 1:
			panel = new Slowo();
			inicjujKomponenty("Dodaj s�owo");
			break;
		default:
			break;
		}
		
		inicjujKoniec();
	}
	
	/**
	 * Inicjuje komponenty okienka do dodawania lub modyfikacji rekord�w.
	 * Nadaje tytu� dla okienka.
	 * Dodaje panel do okienka.
	 * @param tytul tytu� dla okienka
	 */
	private void inicjujKomponenty(String tytul) {
		setTitle(tytul);
		add(panel);
		pack();
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
