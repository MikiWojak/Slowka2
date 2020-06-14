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
	 * Utworzenie okienka do dodawania s³owa lub grupy.
	 * @param tryb nr trybu:
	 * <ul>
	 * <li>0 - Dodaj grupê</li>
	 * <li>1 - Dodaj slowo</li>
	 * </ul>
	 */
	public Edycja(int tryb) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		switch(tryb) {
		case 0:
			panel = new Grupa();
			inicjujKomponenty("Dodaj grupê");
			break;
		case 1:
			panel = new Slowo();
			inicjujKomponenty("Dodaj s³owo");
			break;
		default:
			break;
		}
		
		inicjujKoniec();
	}
	
	/**
	 * Inicjuje komponenty okienka do dodawania lub modyfikacji rekordów.
	 * Nadaje tytu³ dla okienka.
	 * Dodaje panel do okienka.
	 * @param tytul tytu³ dla okienka
	 */
	private void inicjujKomponenty(String tytul) {
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
