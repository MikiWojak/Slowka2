package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Panel z widokiem na grupy i s�owa.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Widok extends Panel {
	private JLabel lblWidok;
	private JTable table;
	
	Object[] nazwyKolumn;
	
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
	 * Inicjacja element�w panelu.
	 * G��wnie element�w z biblioteki Swing.
	 */
	private void  inicjujKomponenty() {
		lblWidok = new JLabel("Widok");
		lblWidok.setHorizontalAlignment(SwingConstants.CENTER);
		lblWidok.setFont(new Font("Arial", Font.BOLD, 16));
		lblWidok.setBounds(0, 13, 1200, 20);
		add(lblWidok);
		
		
		
		utworzTabele();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(400, 46, 800, 140);
		add(scrollPane);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
	}
	
	/**
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
	}
	
	/**
	 * Tworzenie tabeli i obiekt�w w niej
	 */
	private void utworzTabele() {
		Object[] nazwyKolumn = {"S�owo",
								"T�umaczenie",
								"Cz�� mowy",
								"Czy nauczone"
		};
		Object[][] dane = {
				{"computer", "komputer", "n", "tak"}, 
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"}, 
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"},
				{"computer", "komputer", "n", "tak"}
		};
		
		table = new JTable(dane, nazwyKolumn);
	}
}
