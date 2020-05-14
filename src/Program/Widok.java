package Program;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * Panel z widokiem na grupy i s³owa.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Widok extends Panel {
	private JButton btnUruchomTest;
	
	/**
	 * Utworzenie panelu z widokiem.
	 * Konstruktor klasy Widok.
	 * Dziedziczy po konstruktorze klasy Panel.
	 */
	public Widok() {
		setBackground(Color.RED);
		
		btnUruchomTest = new JButton("Test");
		btnUruchomTest.setBounds(12, 13, 97, 25);
		add(btnUruchomTest);
	}
}
