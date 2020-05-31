package Program;

import java.awt.Color;
import java.awt.Component;

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
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

/**
 * Panel z widokiem na grupy i s³owa.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Widok extends Panel {
	private JLabel lblNazwaGrupa;
	private JTable tableSlowa;
	
	private int rekordy = 34;
	private Object[] nazwyKolumn = new Object[4];
	private Object[][] dane = new Object[rekordy][4];
	private JScrollPane scrollSlowa;
	
	private Vector<String> nazwyKolumnV = new Vector<String>();
	private Vector<Vector<Object>> daneV = new Vector<Vector<Object>>();
	private JButton btnAktualizuj;
	private JButton btnComputers;
	private JButton btnWar;
	
	private int index = 0;
	
	List<Slowo>slowa = new LinkedList<Slowo>();
	
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
		lblNazwaGrupa = new JLabel("Grupa");
		lblNazwaGrupa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNazwaGrupa.setFont(new Font("Arial", Font.BOLD, 16));
		lblNazwaGrupa.setBounds(400, 13, 800, 20);
		add(lblNazwaGrupa);
		
		//Tabele
		utworzTabele();
		utworzVektory();
		//aktualizujDane();
		//table
		//tableSlowa = new JTable(dane, nazwyKolumn);
		tableSlowa = new JTable(daneV, nazwyKolumnV) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				Component c = super.prepareRenderer(renderer, row, column);

				//  Color row based on a cell value

					//Czcionka
					c.setFont(getFont());
					boolean type = slowa.get(row).pobierzCzyZapamietane();
					if (false == type) c.setFont(new Font("Arial", Font.PLAIN, 16));
					if (true == type) c.setFont(new Font("Arial", Font.BOLD, 16));

					//Kolor t³a
					if (!isRowSelected(row))
					{
						c.setBackground(getBackground());
						type = slowa.get(row).pobierzCzyZapamietane();
						if (false == type) c.setBackground(Color.LIGHT_GRAY);
						if (true == type) c.setBackground(Color.GREEN);
					}
					
				return c;
			}
		};
		//tableSlowa.setFont(new Font("Arial", Font.PLAIN, 16));
		tableSlowa.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableSlowa.setRowHeight(25);

		tableSlowa.getColumnModel().getColumn(0).setPreferredWidth(340);
		tableSlowa.getColumnModel().getColumn(1).setPreferredWidth(340);
		tableSlowa.getColumnModel().getColumn(2).setPreferredWidth(120);
		
		scrollSlowa = new JScrollPane();
		scrollSlowa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSlowa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSlowa.setBounds(400, 46, 800, 500);
		add(scrollSlowa);
		scrollSlowa.setViewportView(tableSlowa);
		
		btnAktualizuj = new JButton("Medieval");
		btnAktualizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aktualizujTabele(1);
			}
		});
		btnAktualizuj.setBounds(400, 559, 200, 25);
		add(btnAktualizuj);
		
		btnComputers = new JButton("Computers");
		btnComputers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aktualizujTabele(2);
			}
		});
		btnComputers.setBounds(615, 559, 200, 25);
		add(btnComputers);
		
		btnWar = new JButton("War");
		btnWar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aktualizujTabele(3);
			}
		});
		btnWar.setBounds(830, 557, 200, 25);
		add(btnWar);
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
		nazwyKolumn[0] = "S³owo";
		nazwyKolumn[1] = "T³umaczenie";
		nazwyKolumn[2] = "Czêœæ mowy";
		nazwyKolumn[3] = "Czy nauczone";
		
		for(int i = 0; i < rekordy; i++) {
			dane[i][0] = "computer";
			dane[i][1] = "komputer";
			dane[i][2] = "n";
			dane[i][3] = "tak";
		}
	}
	
	private void utworzVektory() {
		nazwyKolumnV.add("S³owo");
		nazwyKolumnV.add("T³umaczenie");
		nazwyKolumnV.add("Czêœæ mowy");
	}
	
	private void aktualizujDane() {
		interfejsBD.otworzPolaczenie();
		slowa = interfejsBD.pobierzSlowaZGrupy(0);
		
		daneV.clear();
		
		for(int i = 0; i < slowa.size(); i++) {
			Vector<Object> kolumna = new Vector<Object>();
			
			kolumna.add(slowa.get(i).pobierzSlowo());
			kolumna.add(slowa.get(i).pobierzTlumaczenie());
			kolumna.add(slowa.get(i).pobierzCzescMowy());
			
			daneV.add(kolumna);
		}
	}
	
	private void aktualizujTabele(int id_grupa) {
		//Model tabeli
		DefaultTableModel tableModel = (DefaultTableModel) tableSlowa.getModel();
		//usuniêcie danych
	    tableModel.setRowCount(0);
	    
	    //pobranie z BD
	    interfejsBD.otworzPolaczenie();
		slowa = interfejsBD.pobierzSlowaZGrupy(id_grupa);
		
		//wyczyszczenie wektora
		daneV.clear();
		
		for(int i = 0; i < slowa.size(); i++) {
			Vector<Object> kolumna = new Vector<Object>();
			
			kolumna.add(slowa.get(i).pobierzSlowo());
			kolumna.add(slowa.get(i).pobierzTlumaczenie());
			kolumna.add(slowa.get(i).pobierzCzescMowy());
			kolumna.add(slowa.get(i).pobierzCzyZapamietane());
			
			tableModel.addRow(kolumna);
		}
	    
		//zmiana danych w tabeli
	    tableSlowa.setModel(tableModel);
	    
	    //ignorowanie takich samych danych
	    tableModel.fireTableDataChanged();
	}
}
