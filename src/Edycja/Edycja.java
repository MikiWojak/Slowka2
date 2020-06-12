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
	 * Utworzenie okienka.
	 */
	public Edycja() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
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
