package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Panel z widokiem na grupy i s�owa.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Widok extends Panel {
	private JLabel lblNazwaGrupa;
	private JTable tableSlowa;
	
	private int rekordy = 40;
	private Object[] nazwyKolumn = new Object[4];
	private Object[][] dane = new Object[rekordy][4];
	private JScrollPane scrollSlowa;
	
	private Vector<String> nazwyKolumnV = new Vector<String>();
	private Vector<Vector<Object>> daneV = new Vector<Vector<Object>>();
	
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
		lblNazwaGrupa = new JLabel("Grupa");
		lblNazwaGrupa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNazwaGrupa.setFont(new Font("Arial", Font.BOLD, 16));
		lblNazwaGrupa.setBounds(400, 13, 800, 20);
		add(lblNazwaGrupa);
		
		//Tabele
		utworzTabele();
		utworzVektory();
		//table
		//tableSlowa = new JTable(dane, nazwyKolumn);
		tableSlowa = new JTable(daneV, nazwyKolumnV);
		tableSlowa.setFont(new Font("Arial", Font.PLAIN, 16));
		tableSlowa.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableSlowa.setRowHeight(25);

		tableSlowa.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableSlowa.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableSlowa.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableSlowa.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		scrollSlowa = new JScrollPane();
		scrollSlowa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSlowa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSlowa.setBounds(400, 46, 800, 500);
		add(scrollSlowa);
		scrollSlowa.setViewportView(tableSlowa);
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
		nazwyKolumn[0] = "S�owo";
		nazwyKolumn[1] = "T�umaczenie";
		nazwyKolumn[2] = "Cz�� mowy";
		nazwyKolumn[3] = "Czy nauczone";
		
		for(int i = 0; i < rekordy; i++) {
			dane[i][0] = "computer";
			dane[i][1] = "komputer";
			dane[i][2] = "n";
			dane[i][3] = "tak";
		}
	}
	
	private void utworzVektory() {
		nazwyKolumnV.add("S�owo");
		nazwyKolumnV.add("T�umaczenie");
		nazwyKolumnV.add("Cz�� mowy");
		nazwyKolumnV.add("Czy nauczone");
		
		for(int i = 0; i < nazwyKolumnV.size(); i++) {
			System.out.println(nazwyKolumnV.get(i));
		}
		for(int i = 0; i < rekordy; i++) {
			Vector<Object> kolumna = new Vector<Object>();
			/*
			for(int j = 0; j < nazwyKolumnV.size(); j++) {
			}
			*/
			kolumna.add("coat of plates");
			kolumna.add("zbroja typu p�aty");
			kolumna.add("noun");
			kolumna.add(false);
			
			daneV.add(kolumna);
		}
		
	}
}
