package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

/**
 * Panel z widokiem na grupy i s�owa.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Widok extends Panel {
	
	/**
	 * Utworzenie panelu z widokiem.
	 * Konstruktor klasy Widok.
	 */
	public Widok() {
		setBackground(Color.BLUE);
		
		inicjujPola();
		
		log.debug("Utworzono obiekt klasy Widok.");
	}
	
	/**
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
	}

}
