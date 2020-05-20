package Program;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * G��wny panel programu. Zawiera podstawowe informacje i z niego b�d� dziedziczy� panele odpowiedzialne za funkcje.
 * Klasa abstrakcyjna - s�u�y tylko jako podstawa dla innych paneli.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public abstract class Panel extends JPanel {
	/**
	 * Szeroko�� panelu.
	 */
	protected final int PANEL_SZEROKOSC = 1200;
	/**
	 * Wysoko�� panelu.
	 */
	protected final int PANEL_WYSOKOSC = 600;
	
	/**
	 * Utworzenie panelu g��wnego.
	 * Konstruktor klasy Panel.
	 */
	protected Panel() {
		setBounds(0, 0, PANEL_SZEROKOSC, PANEL_WYSOKOSC);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
	}

}
