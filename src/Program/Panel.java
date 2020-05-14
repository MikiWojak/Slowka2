package Program;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * G��wny panel programu. Zawiera podstawowe informacje i z niego b�d� dziedziczy� panele odpowiedzialne za funkcje.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Panel extends JPanel {
	
	/**
	 * Szeroko�� okna
	 */
	protected final int OKNO_SZEROKOSC = 1200;
	/**
	 * Wysoko�� okna
	 */
	protected final int OKNO_WYSOKOSC = 600;
	
	/**
	 * Utworzenie panelu g��wnego.
	 * Konstruktor klasy Panel.
	 */
	public Panel() {
		setLayout(null);
		setPreferredSize(new Dimension(OKNO_SZEROKOSC, OKNO_WYSOKOSC));
	}

}
