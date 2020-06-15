package Edycja;

import java.awt.EventQueue;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;

/**
 * Okno edycji grupy lub s³owa. W nim bêdzie panel odpowiedzialny za funkcjonowanie okienka.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Edycja extends JDialog {
	
	/**
	 * Panel do edycji grupy lub s³owa.
	 */
	private Panel panel;
	
	/**
	 * Utworzenie okienka do dodawania grupy.
	 */
	public Edycja() {
		panel = new Grupa();
		inicjujKomponenty("Dodaj grupê");
		inicjujKoniec();
	}
	
	/**
	 * Utworzenie okienka do modyfikacji grupy lub dodania s³owa.
	 * @param tryb nr trybu:
	 * <ul>
	 * <li>0 - Modyfikuj grupê</li>
	 * <li>1 - Dodaj s³owo</li>
	 * </ul>
	 * @param id_grupa ID grupy niezale¿nie od trybu
	 */
	public Edycja(int tryb, int id_grupa) {
		switch(tryb) {
		case 0:
			panel = new Grupa(id_grupa);
			inicjujKomponenty("Modyfikuj grupê");
			break;
		case 1:
			panel = new Slowo(id_grupa);
			inicjujKomponenty("Dodaj s³owo");
			break;
		default:
			break;
		}
		inicjujKoniec();
	}
	
	/**
	 * Utworzenie okienka do modyfikacji s³owa.
	 * @param id_slowo ID s³owa do modyfikacji
	 */
	public Edycja(int id_slowo) {
		panel = new Slowo(id_slowo, false);
		inicjujKomponenty("Modyfikuj s³owo");
		inicjujKoniec();
	}
	
	/**
	 * Inicjuje komponenty okienka do dodawania lub modyfikacji rekordów.
	 * Nadaje tytu³ dla okienka.
	 * Dodaje panel do okienka.
	 * @param tytul tytu³ dla okienka
	 */
	private void inicjujKomponenty(String tytul) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		setTitle(tytul);
		add(panel);
		pack();
	}
	
	/**
	 * Odpowiada za zintegorwanie z g³ównym oknem programu oraz za widocznoœæ.
	 * Nie pozwala na dzia³anie w aplikacji dopóki okienko nie zostanie zamkniête.
	 * Na sam koniec konstruktora.
	 */
	private void inicjujKoniec() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}

}
