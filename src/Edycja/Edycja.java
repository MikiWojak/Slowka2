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
	 * Utworzenie okienka do dodawania grupy.
	 */
	public Edycja() {
		panel = new Grupa();
		inicjujKomponenty("Dodaj grup�");
		inicjujKoniec();
	}
	
	/**
	 * Utworzenie okienka do modyfikacji grupy lub dodania s�owa.
	 * @param tryb nr trybu:
	 * <ul>
	 * <li>0 - Modyfikuj grup�</li>
	 * <li>1 - Dodaj s�owo</li>
	 * </ul>
	 * @param id_grupa ID grupy niezale�nie od trybu
	 */
	public Edycja(int tryb, int id_grupa) {
		switch(tryb) {
		case 0:
			panel = new Grupa(id_grupa);
			inicjujKomponenty("Modyfikuj grup�");
			break;
		case 1:
			panel = new Slowo(id_grupa);
			inicjujKomponenty("Dodaj s�owo");
			break;
		default:
			break;
		}
		inicjujKoniec();
	}
	
	/**
	 * Utworzenie okienka do modyfikacji s�owa.
	 * @param id_slowo ID s�owa do modyfikacji
	 */
	public Edycja(int id_slowo) {
		panel = new Slowo(id_slowo, false);
		inicjujKomponenty("Modyfikuj s�owo");
		inicjujKoniec();
	}
	
	/**
	 * Inicjuje komponenty okienka do dodawania lub modyfikacji rekord�w.
	 * Nadaje tytu� dla okienka.
	 * Dodaje panel do okienka.
	 * @param tytul tytu� dla okienka
	 */
	private void inicjujKomponenty(String tytul) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
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
