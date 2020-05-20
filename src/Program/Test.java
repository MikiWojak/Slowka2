package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

/**
 * Panel testu ze znajomo�ci s��wek.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Test extends Panel {

	/**
	 * Utworzenie panelu z testem.
	 * Konstruktor klasy Test.
	 */
	public Test() {
		setBackground(Color.RED);
		
		inicjujPola();
		
		log.debug("Utworzono obiekt klasy Test.");
	}
	
	/**
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Test.class);
	}

}
