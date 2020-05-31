package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTable;

/**
 * Panel z widokiem na grupy i s³owa.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Widok extends Panel {
	private JLabel lblWidok;
	private JTable table;
	
	/**
	 * Utworzenie panelu z widokiem.
	 * Konstruktor klasy Widok.
	 */
	public Widok() {
		inicjujKomponenty();
		inicjujPola();
		
		log.debug("Utworzono obiekt klasy Widok.");
	}
	
	/**
	 * Inicjacja elementów panelu.
	 * G³ównie elementów z biblioteki Swing.
	 */
	private void  inicjujKomponenty() {
		lblWidok = new JLabel("Widok");
		lblWidok.setHorizontalAlignment(SwingConstants.CENTER);
		lblWidok.setFont(new Font("Arial", Font.BOLD, 16));
		lblWidok.setBounds(400, 0, 800, 50);
		add(lblWidok);
		
		JList list = new JList();
		list.setBounds(0, 0, 350, 550);
		add(list);
		
		table = new JTable();
		table.setBounds(400, 50, 800, 500);
		add(table);
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
	}
}
