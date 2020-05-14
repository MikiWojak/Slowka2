package Program;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * Panel testu ze znajomo�ci s��wek.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Test extends Panel {
	private JButton btnUruchomWidok;

	/**
	 * Utworzenie panelu z testem.
	 * Konstruktor klasy Test.
	 * Dziedziczy po konstruktorze klasy Panel.
	 */
	public Test() {
		setBackground(Color.BLUE);
		
		btnUruchomWidok = new JButton("Widok");
		btnUruchomWidok.setBounds(12, 13, 97, 25);
		add(btnUruchomWidok);
	}

}
