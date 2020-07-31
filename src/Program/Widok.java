package Program;

import java.awt.Color;
import java.awt.Component;

import org.slf4j.LoggerFactory;

import Edycja.Edycja;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel z widokiem na grupy i s�owa.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Widok extends Panel {
	/**
	 * Napis na nazw� wybranej grupy.
	 */
	private JLabel lblGrupa;
	/**
	 * Tabela s��w wybranej grupy.
	 */
	private JTable tableSlowa;
	/**
	 * Scroll do listy grup.
	 */
	private JScrollPane scrollGrupy;
	/**
	 * Scroll do tabeli s��w.
	 */
	private JScrollPane scrollSlowa;
	/**
	 * Lista grup ze s�owami.
	 */
	private JList listGrupy;
	/**
	 * Wektor z nazwami kolumn do tabeli s��w.
	 */
	private Vector<String>nazwyKolumnTabelaSlow;
	/**
	 * Wektor z danymi do tabeli.
	 */
	private Vector<Vector<Object>>daneTabelaSlow;
	/**
	 * Przycisk do dodawania grupy.
	 */
	private JButton btnDodajGrupe;
	/**
	 * Przycisk do dodawania s�owa.
	 */
	private JButton btnDodajSlowo;
	/**
	 * Przycisk do modyfikacji grupy.
	 */
	private JButton btnModyfikujGrupe;
	/**
	 * Przycisk do modyfikacji s�owa.
	 */
	private JButton btnModyfikujSlowo;
	/**
	 * Okienko dialogowe do edycji grupy lub s�owa.
	 */
	private JDialog edycja;
	/**
	 * Napis do pokazania ilo�ci grup w bazie danych.
	 */
	private JLabel lblIloscGrup;
	/**
	 * Napis do pokazania ilo�ci s��w w wybranej grupie.
	 */
	private JLabel lblIloscSlow;
	/**
	 * Przycisk do usuwania grupy lub grup.
	 */
	private JButton btnUsunGrupy;
	/**
	 * Przycisk do usuwania s�owa lub s��w.
	 */
	private JButton btnUsunSlowa;
	
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
	 * Inicjacja element�w panelu.
	 * G��wnie element�w z biblioteki Swing.
	 */
	private void  inicjujKomponenty() {
		lblGrupa = new JLabel("Wybierz grup\u0119 po lewej");
		lblGrupa.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupa.setFont(new Font("Arial", Font.BOLD, 16));
		lblGrupa.setBounds(400, 0, 800, 50);
		add(lblGrupa);
		
		lblIloscGrup = new JLabel("Ilo\u015B\u0107 grup:");
		lblIloscGrup.setFont(new Font("Arial", Font.PLAIN, 16));
		lblIloscGrup.setBounds(0, 570, 400, 30);
		add(lblIloscGrup);
		
		lblIloscSlow = new JLabel("Ilo\u015B\u0107 wy\u015Bwietlonych s\u0142\u00F3w:");
		lblIloscSlow.setFont(new Font("Arial", Font.PLAIN, 16));
		lblIloscSlow.setBounds(400, 570, 800, 30);
		add(lblIloscSlow);
		
		scrollGrupy = new JScrollPane();
		scrollGrupy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollGrupy.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollGrupy.setBounds(0, 0, 350, 450);
		add(scrollGrupy);
		
		listGrupy = new JList();
		listGrupy.addListSelectionListener(new ZmienGrupe());
		listGrupy.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollGrupy.setViewportView(listGrupy);
		//grupy do listy
		pobierzGrupyBD();
		listGrupy.setModel(utworzListeGrup());
		lblIloscGrup.setText("Ilo�� grup: " + iloscGrup);
		
		scrollSlowa = new JScrollPane();
		scrollSlowa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSlowa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSlowa.setBounds(400, 50, 800, 400);
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
				//Kolor t�a
				component.setBackground(getBackground());
				type = slowa.get(row).pobierzCzyZapamietane();
				if (false == type) { component.setBackground(Color.LIGHT_GRAY); }
				if (true == type) { component.setBackground(Color.GREEN); }
				//Wybrany rekord
				if (isRowSelected(row)) { jcomponent.setBorder(highlight); }
					
				return component;
			}
		};
		tableSlowa.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableSlowa.setFont(new Font("Arial", Font.PLAIN, 16));
		tableSlowa.setRowHeight(25);
		tableSlowa.getColumnModel().getColumn(0).setPreferredWidth(340);
		tableSlowa.getColumnModel().getColumn(1).setPreferredWidth(340);
		tableSlowa.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableSlowa.setDefaultEditor(Object.class, null);
		scrollSlowa.setViewportView(tableSlowa);
		
		btnDodajGrupe = new JButton("Dodaj grup\u0119");
		btnDodajGrupe.setBackground(new Color(60, 179, 113));
		btnDodajGrupe.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDodajGrupe.addActionListener(new DodajGrupe());
		btnDodajGrupe.setBounds(0, 466, 150, 30);
		add(btnDodajGrupe);
		
		btnDodajSlowo = new JButton("Dodaj s\u0142owo");
		btnDodajSlowo.setBackground(new Color(60, 179, 113));
		btnDodajSlowo.addActionListener(new DodajSlowo());
		btnDodajSlowo.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDodajSlowo.setBounds(400, 466, 150, 30);
		add(btnDodajSlowo);
		
		btnModyfikujGrupe = new JButton("Modyfikuj grup\u0119");
		btnModyfikujGrupe.setBackground(new Color(30, 144, 255));
		btnModyfikujGrupe.addActionListener(new ModyfikujGrupe());
		btnModyfikujGrupe.setFont(new Font("Arial", Font.PLAIN, 16));
		btnModyfikujGrupe.setBounds(0, 504, 150, 30);
		add(btnModyfikujGrupe);
		
		btnModyfikujSlowo = new JButton("Modyfikuj s\u0142owo");
		btnModyfikujSlowo.setBackground(new Color(30, 144, 255));
		btnModyfikujSlowo.addActionListener(new ModyfikujSlowo());
		btnModyfikujSlowo.setFont(new Font("Arial", Font.PLAIN, 16));
		btnModyfikujSlowo.setBounds(400, 505, 150, 30);
		add(btnModyfikujSlowo);
		
		btnUsunGrupy = new JButton("Usu\u0144 grup\u0119/y");
		btnUsunGrupy.setBackground(new Color(255, 69, 0));
		btnUsunGrupy.addActionListener(new UsunGrupy());
		btnUsunGrupy.setFont(new Font("Arial", Font.PLAIN, 16));
		btnUsunGrupy.setBounds(200, 466, 150, 30);
		add(btnUsunGrupy);
		
		btnUsunSlowa = new JButton("Usu\u0144 s\u0142owo/a");
		btnUsunSlowa.setBackground(new Color(255, 69, 0));
		btnUsunSlowa.addActionListener(new UsunSlowa());
		btnUsunSlowa.setFont(new Font("Arial", Font.PLAIN, 16));
		btnUsunSlowa.setBounds(565, 466, 150, 30);
		add(btnUsunSlowa);
	}
	
	/**
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
		//wektory
		nazwyKolumnTabelaSlow = new Vector<String>();
		daneTabelaSlow = new Vector<Vector<Object>>();
	}
	
	/**
	 * Pobranie wszystkich grup z bazy danych. Grupy nie s� sortowane.
	 * Ilo�� grup w bazie danych na podstawie ilo�ci rekord�w w li�cie.
	 */
	private void pobierzGrupyBD() {
		interfejsBD.otworzPolaczenie();
		grupy = interfejsBD.pobierzGrupyWszystkie();
		interfejsBD.zamknijPolaczenie();
		iloscGrup = grupy.size();
	}
	
	/**
	 * Utworzenie lub aktualizacja listy grup ze s�owami.
	 * Nale�y u�y� po funkcji: pobierzGrupyBD.
	 * @return grupy do umieszczenia w li�cie grup
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
	 * Zmiana aktualnie wy�wietlanej grupy. Do zastosowania z listenerem listy grup.
	 * Aktualizacja ilo�ci wy�wietlonych s��w.
	 * @param indexGrupa indeks grupy na li�cie
	 */
	private void zmienGrupe(int indexGrupa) {
		if(indexGrupa >= 0) {
			//nag��wek listy s��w
			lblGrupa.setText(grupy.get(indexGrupa).pobierzNazwaGrupy());
			//tabela
			aktualizujTabele(grupy.get(indexGrupa).pobierzIdGrupa());
			lblIloscSlow.setText("Ilo�� wy�wietlonych s��w: " + iloscSlow);
			log.debug("Wy�wietlono grup� o ID: " + grupy.get(indexGrupa).pobierzIdGrupa());
		}
	}
	
	/**
	 * Dodanie nazw kolumn do wektora. Utworzenie kolumn w tabeli.
	 */
	private void utworzNazwyKolumnTabebaSlow() {
		nazwyKolumnTabelaSlow.add("S�owo");
		nazwyKolumnTabelaSlow.add("T�umaczenie");
		nazwyKolumnTabelaSlow.add("Cz�� mowy");
	}
	
	/**
	 * Utworzenie listy s��w do tabeli.
	 * Pobranie ilo�ci s�ow z danej grupy. 
	 * @param id_grupa ID wybranej grupy.
	 */
	private void aktualizujTabele(int id_grupa) {
		DefaultTableModel daneDoTabeli = (DefaultTableModel) tableSlowa.getModel();
		//usuni�cie danych
		daneDoTabeli.setRowCount(0);
		//pobranie s��w z BD
		interfejsBD.otworzPolaczenie();
		slowa = interfejsBD.pobierzSlowaZGrupy(id_grupa);
		interfejsBD.zamknijPolaczenie();
		iloscSlow = slowa.size();
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
	
	/**
	 * Aktualizacja listy i ilo�ci grup.
	 * Do dodawania, modyfikacji i usuwania grup.
	 */
	private void aktualizujGrupy() {
		pobierzGrupyBD();
		listGrupy.setModel(utworzListeGrup());
		lblIloscGrup.setText("Ilo�� grup: " + iloscGrup);
	}
	
	/**
	 * Klasa wewn�trzna do dodawania grupy do bazy danych.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class DodajGrupe implements ActionListener {
		/**
		 * Konstruktor klasy DodajGrupe. 
		 */
		public DodajGrupe() {}
		
		/**
		 * Akcja dodawania grupy.
		 * Od�wie�enie listy grup i tabeli.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			log.debug("Uruchomienie okienka do dodawania grupy.");
			edycja = new Edycja();
			aktualizujGrupy();
		}
	}
	
	/**
	 * Klasa wewn�trzna do modyfikacji grup do bazy danych.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class ModyfikujGrupe implements ActionListener {
		/**
		 * Konstruktor klasy ModyfikujGrupe. 
		 */
		public ModyfikujGrupe() {}
		
		/**
		 * Akcja modyfikacji grupy.
		 * Pobranie ID grupy w BD tylko, gdy wybrana grupa.
		 * Od�wie�enie listy grup i tabeli.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int indexGrupa = listGrupy.getSelectedIndex();
			if(indexGrupa >= 0) {
				int idGrupa = grupy.get(indexGrupa).pobierzIdGrupa();
				log.debug("Uruchomienie okienka do modyfikacji grupy.");
				edycja = new Edycja(0, idGrupa);
				aktualizujGrupy();
				zmienGrupe(indexGrupa);
			} else {
				JOptionPane.showMessageDialog(
						null,
						"Wybierz grup� z listy!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**
	 * Klasa wewn�trzna do usuwania grup z bazy danych.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class UsunGrupy implements ActionListener {
		/**
		 * Konstruktor klasy UsunGrupe.
		 */
		public UsunGrupy() {}
		
		/**
		 * Akcja usuwania grupy lub grup.
		 * Potwierdzenie akcji.
		 * Weryfikacja i rezultat.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(listGrupy.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(
						null,
						"Wybierz grup� lub grupy z listy!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
			} else if(usunGrupyZgoda()) {
				int indexy[] = listGrupy.getSelectedIndices();
				boolean flaga = true;
				
				interfejsBD.otworzPolaczenie();
				
				for(int i = 0; i < grupy.size(); i++) {
					try {
						flaga = interfejsBD.usunGrupe(
								grupy.get(indexy[i]).pobierzIdGrupa());
						if(!flaga) {
							JOptionPane.showMessageDialog(
									null,
									"B��d przy usuwaniu grup!",
									"B��d",
									JOptionPane.ERROR_MESSAGE);
							break;
						}
					} catch (IndexOutOfBoundsException e2) {
						//B��d - wykroczenie poza zakres tablicy
						break;
					}
				}
				
				if(flaga) {
					JOptionPane.showMessageDialog(
							null,
							"Pomy�lnie usuni�to wybrane grupy i powi�zane z nimi s�owa.",
							"Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
				aktualizujGrupy();
				
				interfejsBD.zamknijPolaczenie();
			}
		}
		
		/**
		 * Okno dialogowe z pytaniem, czy na pewno usun�� zaznaczone grupy.
		 * @return true - tak; false - nie
		 */
		private boolean usunGrupyZgoda() {
			Object nazwaOpcja[] = {"Tak", "Nie"};
			int opcja = JOptionPane.showOptionDialog(
					null,
					"Czy na pewno usun�� zaznaczone grupy i powi�zane z nimi s�owa?",
					"Pytanie",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					nazwaOpcja,
					nazwaOpcja[1]);
			if(opcja == 0) { return true; }
			return false;
		}
		
	}
	
	/**
	 * Klasa wewn�trzna do dodawania s��w do bazy danych.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class DodajSlowo implements ActionListener {
		/**
		 * Konstruktor klasy DodajSlowo.
		 */
		public DodajSlowo() {}
		/**
		 * Akcja dodawania s�owa.
		 * Sprawdzenie, czy grupa zosta�a wybrana.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int indexGrupa = listGrupy.getSelectedIndex();
			//Grupa nie jest wybrana
			if(indexGrupa < 0) {
				JOptionPane.showMessageDialog(
						null,
						"Wybierz grup� z listy!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
			}
			//Wybrano grup�
			else {
				int id_grupa = grupy.get(indexGrupa).pobierzIdGrupa();
				log.debug("Uruchomiono okienko do dodawania s�owa.");
				edycja = new Edycja(1, id_grupa);
				zmienGrupe(indexGrupa);
			}
		}
		
	}
	
	/**
	 * Klasa wewn�trzna do modyfikacji s��w do bazy danych.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class ModyfikujSlowo implements ActionListener {
		/**
		 * Konstruktor klasy ModyfikujSlowo.
		 */
		public ModyfikujSlowo() {}
		/**
		 * Akcja modyfikacji grupy.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int indexSlowo = tableSlowa.getSelectedRow();
			//S�owo nie jest wybrane
			if(indexSlowo < 0) {
				JOptionPane.showMessageDialog(
						null,
						"Wybierz rekord z tabeli!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
			}
			//Wybrano slowo
			else {
				int id_slowo = slowa.get(indexSlowo).pobierzIdSlowo();
				log.debug("Uruchomiono okienko do modyfikacji s�owa.");
				edycja = new Edycja(id_slowo);
				zmienGrupe(listGrupy.getSelectedIndex());
			}
		}
	}
	
	/**
	 * Klasa wewn�trzna do usuwania s��w z bazy danych.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class UsunSlowa implements ActionListener {
		/**
		 * Konstruktor klasy UsunSlowa. 
		 */
		public UsunSlowa() {}
		/**
		 * Akcja usuwania s��w.
		 * Potwierdzenie akcji.
		 * Weryfikacja i rezultat.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(tableSlowa.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(
						null,
						"Wybierz s�owo lub s�owa z tabeli!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
			} else if(usunSlowaZgoda()){
				int indexy[] = tableSlowa.getSelectedRows();
				boolean flaga = true;
				interfejsBD.otworzPolaczenie();
				
				for(int i = 0; i < slowa.size(); i++) {
					try {
						flaga = interfejsBD.usunSlowo(slowa.get(indexy[i]).pobierzIdSlowo());
						
						if(!flaga) {
							JOptionPane.showMessageDialog(
									null,
									"B��d przy usuwaniu s��w!",
									"B��d",
									JOptionPane.ERROR_MESSAGE);
							break;
						}
					} catch (Exception e1) {
						//B��d - wykroczenie poza zakres tablicy
						break;
					}
				}
				
				if(flaga) {
					JOptionPane.showMessageDialog(
							null,
							"Pomy�lnie usuni�to zaznaczone s�owa",
							"Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
				zmienGrupe(listGrupy.getSelectedIndex());
				interfejsBD.zamknijPolaczenie();
			}
		}
		
		/**
		 * Okno dialogowe z pytaniem, czy na pewno usun�� wybrane s�owa.
		 * @return true, je�li chcesz usun�� wybrane s�owa
		 */
		private boolean usunSlowaZgoda() {
			Object nazwaOpcja[] = {"Tak", "Nie"};
			int opcja = JOptionPane.showOptionDialog(
					null,
					"Czy na pewno usun�� zaznaczone s�owa?",
					"Pytanie",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					nazwaOpcja,
					nazwaOpcja[1]);
			if(opcja == 0) { return true; }
			return false;
		}
	}
	
	/**
	 * Klasa wewn�trzna do wywo�ania akcji wybrania rekordu z listy
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class ZmienGrupe implements ListSelectionListener {
		/**
		 * Konstruktor klasy ZmienGrupe
		 */
		public ZmienGrupe() {}
		
		/**
		 * Akcja zw. z wybraniem grupy z listy.
		 * Wy�wietlenie s��w przypisanych do danej listy.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			zmienGrupe(listGrupy.getSelectedIndex());
		}
	}
}
