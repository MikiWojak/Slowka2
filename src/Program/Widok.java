package Program;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.event.ListSelectionEvent;

/**
 * Panel z widokiem na grupy i s³owa.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Widok extends Panel {
	/**
	 * Napis na nazwê wybranej grupy.
	 */
	private JLabel lblGrupa;
	/**
	 * Tabela s³ów wybranej grupy.
	 */
	private JTable tableSlowa;
	/**
	 * Scroll do listy grup.
	 */
	private JScrollPane scrollGrupy;
	/**
	 * Scroll do tabeli s³ów.
	 */
	private JScrollPane scrollSlowa;
	/**
	 * Lista grup ze s³owami.
	 */
	private JList listGrupy;
	/**
	 * Wektor z nazwami kolumn do tabeli s³ów.
	 */
	private Vector<String>nazwyKolumnTabelaSlow;
	/**
	 * Wektor z danymi do tabeli.
	 */
	private Vector<Vector<Object>>daneTabelaSlow;
	
	/**
	 * Utworzenie panelu z widokiem.
	 * Konstruktor klasy Widok.
	 */
	public Widok() {
		inicjujPola();
		inicjujKomponenty();
		
		log.debug("Utworzono obiekt klasy Widok.");
	}
	
	/**
	 * Inicjacja elementów panelu.
	 * G³ównie elementów z biblioteki Swing.
	 */
	private void  inicjujKomponenty() {
		lblGrupa = new JLabel("Wybierz grup\u0119 po lewej");
		lblGrupa.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupa.setFont(new Font("Arial", Font.BOLD, 16));
		lblGrupa.setBounds(400, 0, 800, 50);
		add(lblGrupa);
		
		scrollGrupy = new JScrollPane();
		scrollGrupy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollGrupy.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollGrupy.setBounds(0, 0, 350, 550);
		add(scrollGrupy);
		
		listGrupy = new JList();
		listGrupy.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				zmienGrupe();
			}
		});
		listGrupy.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollGrupy.setViewportView(listGrupy);
		//grupy do listy
		pobierzGrupyBD();
		listGrupy.setModel(utworzListeGrup());
		
		scrollSlowa = new JScrollPane();
		scrollSlowa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSlowa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSlowa.setBounds(400, 50, 800, 500);
		add(scrollSlowa);
		
		//nazwy kolumn
		utworzNazwyKolumnTabebaSlow();																
		tableSlowa = new JTable(daneTabelaSlow, nazwyKolumnTabelaSlow) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				JComponent jcomponent = (JComponent)component;
				//Do granic
				Border outside = new MatteBorder(2, 0, 2, 0, Color.RED);
				Border inside = new EmptyBorder(0, 2, 0, 2);
				Border highlight = new CompoundBorder(outside, inside);
				//Czcionka
				component.setFont(getFont());
				boolean type = slowa.get(row).pobierzCzyZapamietane();
				if (false == type) { component.setFont(new Font("Arial", Font.PLAIN, 16)); }
				if (true == type) { component.setFont(new Font("Arial", Font.BOLD, 16)); }
				//Kolor t³a
				component.setBackground(getBackground());
				type = slowa.get(row).pobierzCzyZapamietane();
				if (false == type) { component.setBackground(Color.LIGHT_GRAY); }
				if (true == type) { component.setBackground(Color.GREEN); }
				//Wybrany rekord
				if (isRowSelected(row)) { jcomponent.setBorder(highlight); }
					
				return component;
			}
		};
		tableSlowa.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				/*
				int index = tableSlowa.getSelectedRow();
				System.out.println(index);
				*/
				
				/*
				int index[] = tableSlowa.getSelectedRows();
				int size = tableSlowa.getSelectedRowCount();
				
				System.out.println(size);
				for(int i = 0; i < size; i++) {
					System.out.print(index[i] + "\t");
				}
				System.out.println();
				*/
			}
		});
		tableSlowa.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableSlowa.setFont(new Font("Arial", Font.PLAIN, 16));
		tableSlowa.setRowHeight(25);
		tableSlowa.getColumnModel().getColumn(0).setPreferredWidth(340);
		tableSlowa.getColumnModel().getColumn(1).setPreferredWidth(340);
		tableSlowa.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableSlowa.setDefaultEditor(Object.class, null);
		scrollSlowa.setViewportView(tableSlowa);
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
		//wektory
		nazwyKolumnTabelaSlow = new Vector<String>();
		daneTabelaSlow = new Vector<Vector<Object>>();
	}
	
	/**
	 * Pobranie wszystkich grup z bazy danych. Grupy nie s¹ sortowane.
	 */
	private void pobierzGrupyBD() {
		interfejsBD.otworzPolaczenie();
		grupy = interfejsBD.pobierzGrupyWszystkie();
		interfejsBD.zamknijPolaczenie();
	}
	
	/**
	 * Utworzenie lub aktualizacja listy grup ze s³owami.
	 * Nale¿y u¿yæ po funkcji: pobierzGrupyBD.
	 * @return grupy do umieszczenia w liœcie grup
	 */
	private DefaultListModel<Object>utworzListeGrup() {
		DefaultListModel<Object>lista_grup = new DefaultListModel<Object>();
		try {
			for(int i = 0; i < grupy.size(); i++) {
				lista_grup.addElement(grupy.get(i).pobierzNazwaGrupy());
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
		return lista_grup;
	}
	
	/**
	 * Zmiana aktualnie wyœwietlanej grupy. Do zastosowania z listenerem listy grup.
	 */
	private void zmienGrupe() {
		int indexLista = listGrupy.getSelectedIndex();
		//nag³ówek listy s³ów
		lblGrupa.setText(grupy.get(indexLista).pobierzNazwaGrupy());
		//tabela
		aktualizujTabele(grupy.get(indexLista).pobierzIdGrupa());
		log.debug("Wyœwietlono grupê o ID: " + grupy.get(indexLista).pobierzIdGrupa());
	}
	
	/**
	 * Dodanie nazw kolumn do wektora. Utworzenie kolumn w tabeli.
	 */
	private void utworzNazwyKolumnTabebaSlow() {
		nazwyKolumnTabelaSlow.add("S³owo");
		nazwyKolumnTabelaSlow.add("T³umaczenie");
		nazwyKolumnTabelaSlow.add("Czêœæ mowy");
	}
	
	/**
	 * Utworzenie listy s³ów do tabeli.
	 * @param id_grupa ID wybranej grupy.
	 */
	private void aktualizujTabele(int id_grupa) {
		DefaultTableModel daneDoTabeli = (DefaultTableModel) tableSlowa.getModel();
		//usuniêcie danych
		daneDoTabeli.setRowCount(0);
		//pobranie s³ów z BD
		interfejsBD.otworzPolaczenie();
		slowa = interfejsBD.pobierzSlowaZGrupy(id_grupa);
		interfejsBD.zamknijPolaczenie();
		//wyczyszczenie wektora
		daneTabelaSlow.clear();
		//dane do wektora
		for(int i = 0; i < slowa.size(); i++) {
			Vector<Object> kolumna = new Vector<Object>();	
			kolumna.add(slowa.get(i).pobierzSlowo());
			kolumna.add(slowa.get(i).pobierzTlumaczenie());
			kolumna.add(slowa.get(i).pobierzCzescMowy());
			kolumna.add(slowa.get(i).pobierzCzyZapamietane());
			daneDoTabeli.addRow(kolumna);
		}
		//zmiana danych w tabeli
		tableSlowa.setModel(daneDoTabeli);
		//ignorowanie takich samych danych
		daneDoTabeli.fireTableDataChanged();
	}
}
