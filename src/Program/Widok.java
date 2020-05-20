package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Panel z widokiem na grupy i s³owa.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Widok extends Panel {
	private JLabel lblWidok;
	
	/**
	 * Utworzenie panelu z widokiem.
	 * Konstruktor klasy Widok.
	 */
	public Widok() {
		
		lblWidok = new JLabel("Widok");
		lblWidok.setHorizontalAlignment(SwingConstants.CENTER);
		lblWidok.setFont(new Font("Arial", Font.BOLD, 16));
		lblWidok.setBounds(0, 13, 1132, 20);
		add(lblWidok);
		inicjujPola();
		
		log.debug("Utworzono obiekt klasy Widok.");
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
	}
}
