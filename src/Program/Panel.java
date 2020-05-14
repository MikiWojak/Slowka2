package Program;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * G³ówny panel programu. Zawiera podstawowe informacje i z niego bêd¹ dziedziczyæ panele odpowiedzialne za funkcje.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Panel extends JPanel {
	
	/**
	 * Szerokoœæ okna
	 */
	protected final int OKNO_SZEROKOSC = 1200;
	/**
	 * Wysokoœæ okna
	 */
	protected final int OKNO_WYSOKOSC = 600;
	
	/**
	 * Utworzenie panelu g³ównego.
	 * Konstruktor klasy Panel.
	 */
	public Panel() {
		setLayout(null);
		setPreferredSize(new Dimension(OKNO_SZEROKOSC, OKNO_WYSOKOSC));
	}

}
