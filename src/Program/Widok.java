package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import BazaDanych.Slowo;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JButton btnAktualizuj;
	
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
		aktualizujDane();
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
		
		btnAktualizuj = new JButton("Aktualizuj");
		btnAktualizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dodaj dane do tabeli
				utworzVektory();
				DefaultTableModel model = (DefaultTableModel)tableSlowa.getModel();
				model.addRow(daneV);
			}
		});
		btnAktualizuj.setBounds(400, 559, 97, 25);
		add(btnAktualizuj);
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
	
	private void aktualizujDane() {
		interfejsBD.otworzPolaczenie();
		List<Slowo>slowa = new LinkedList<Slowo>();
		slowa = interfejsBD.pobierzSlowaZGrupy(3);
		
		daneV.clear();
		
		for(int i = 0; i < slowa.size(); i++) {
			Vector<Object> kolumna = new Vector<Object>();
			
			kolumna.add(slowa.get(i).pobierzSlowo());
			kolumna.add(slowa.get(i).pobierzTlumaczenie());
			kolumna.add(slowa.get(i).pobierzCzescMowy());
			kolumna.add(slowa.get(i).pobierzCzyZapamietane());
			
			daneV.add(kolumna);
		}
	}
}
