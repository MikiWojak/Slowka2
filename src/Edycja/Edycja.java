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
	 * Utworzenie okienka.
	 * @param tryb nr trybu:
	 * <ul>
	 * <li>0 - Dodaj grupe</li>
	 * <li>1 - Modyfikuj grupe</li>
	 * <li>2 - Dodaj slowo</li>
	 * <li>3 - Modyfikuj slowo</li>
	 * </ul>
	 */
	public Edycja(int tryb) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		switch(tryb) {
		case 0:
			panel = new Grupa();
			setTitle("Dodaj grupe");
			break;
		case 2:
			panel = new Slowo();
			setTitle("Dodaj s³owo");
			break;
		}
		
		add(panel);
		pack();
		
		inicjujKoniec();
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
