package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
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
		lblWidok.setBounds(0, 13, 1200, 20);
		add(lblWidok);
		
		
		
		utworzTabele();

		table.setBounds(400, 46, 800, 140);
		add(table);
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
	}
	
	/**
	 * Tworzenie tabeli i obiektów w niej
	 */
	private void utworzTabele() {
		String[] nazwyKolumn = {"S³owo",
								"T³umaczenie",
								"Czêœæ mowy",
								"Czy nauczone"
		};
		Object[][] dane = {
				{"computer", "komputer", "n", "tak"}
		};
		table = new JTable(dane, nazwyKolumn);
	}
}
