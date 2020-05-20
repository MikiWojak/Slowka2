package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Panel testu ze znajomoœci s³ówek.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Test extends Panel {
	private JLabel lblTest;

	/**
	 * Utworzenie panelu z testem.
	 * Konstruktor klasy Test.
	 */
	public Test() {
		
		lblTest = new JLabel("Test");
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblTest.setFont(new Font("Arial", Font.BOLD, 16));
		lblTest.setBounds(0, 13, 1200, 20);
		add(lblTest);
		inicjujPola();
		
		log.debug("Utworzono obiekt klasy Test.");
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Test.class);
	}

}
