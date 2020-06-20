package Edycja;

import BazaDanych.Grupa;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.slf4j.LoggerFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Panel do edycji grup.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class PanelGrupa extends Panel{
	
	/**
	 * ID grupy do modyfikacji. 
	 */
	private int id_grupa;
	/**
	 * Napis informuj�cy czy nowa grupa, czy edycja istniej�cej.
	 */
	private JLabel lblTytul;
	/**
	 * Przycisk potwierdzaj�cy dodanie lub modyfikacj� grupy.
	 */
	private JButton btnPotwierdzAkcje;
	/**
	 * Pole na nazw� grupy.
	 */
	private JTextField tfNazwa;
	/**
	 * Opis pola na nazw� grupy.
	 */
	private JLabel lblNazwa;
	/**
	 * Opis pola na opis grupy.
	 */
	private JLabel lblOpis;
	/**
	 * Scrolle do pola na opis grupy.
	 */
	private JScrollPane scrollOpis;
	/**
	 * Pole na opis grupy.
	 */
	private JTextPane tpOpis;
	/**
	 * Lista grup.
	 */
	private List<Grupa>grupy;
	/**
	 * Grupa przed modyfikacj�.
	 */
	private Grupa grupaPrzedMod;
	
	/**
	 * Konstruktor klasy Grupa - nowa grupa.
	 */
	public PanelGrupa() {
		inicjujPola();
		inicjujKomponenty();
		btnPotwierdzAkcje.addActionListener(new DodajGrupe());
	}
	
	/**
	 * Konstruktor klasy Grupa - edycja grupy.
	 * @param id_grupa ID grupy do modyfikacji
	 */
	public PanelGrupa(int id_grupa) {
		this.id_grupa = id_grupa;
		inicjujPola();
		inicjujKomponenty();
		modyfikujKomponenty();
	}
	
	/**
	 * Inicjuje komponenty na potrzeby nowej grupy.
	 */
	private void inicjujKomponenty() {
		setPreferredSize(new Dimension(800, 305));
		
		lblTytul = new JLabel("Dodaj grup\u0119");
		lblTytul.setHorizontalAlignment(SwingConstants.CENTER);
		lblTytul.setFont(new Font("Arial", Font.BOLD, 16));
		lblTytul.setBounds(0, 15, 800, 25);
		add(lblTytul);
		
		btnPotwierdzAkcje = new JButton("Dodaj grup\u0119");
		btnPotwierdzAkcje.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPotwierdzAkcje.setBounds(630, 260, 150, 30);
		add(btnPotwierdzAkcje);
		
		lblNazwa = new JLabel("Nazwa");
		lblNazwa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazwa.setFont(new Font("Arial", Font.ITALIC, 16));
		lblNazwa.setBounds(15, 55, 80, 25);
		add(lblNazwa);
		
		lblOpis = new JLabel("Opis");
		lblOpis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpis.setFont(new Font("Arial", Font.ITALIC, 16));
		lblOpis.setBounds(15, 95, 85, 150);
		add(lblOpis);
		
		tfNazwa = new JTextField();
		tfNazwa.setFont(new Font("Arial", Font.PLAIN, 16));
		tfNazwa.setBounds(115, 55, 665, 25);
		add(tfNazwa);
		tfNazwa.setColumns(10);
		
		scrollOpis = new JScrollPane();
		scrollOpis.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollOpis.setBounds(115, 95, 665, 150);
		add(scrollOpis);
		
		tpOpis = new JTextPane();
		scrollOpis.setViewportView(tpOpis);
		tpOpis.setFont(new Font("Arial", Font.PLAIN, 16));
	}
	
	/**
	 * Inicjowanie p�l w klasie
	 */
	private void inicjujPola() {
		log = LoggerFactory.getLogger(PanelGrupa.class);
		grupy = new LinkedList<Grupa>();
		pobierzGrupy();
	}
	
	/**
	 * Modyfikuje komponenty na potrzeby edycji grupy.
	 */
	private void modyfikujKomponenty() {
		lblTytul.setText("Modyfikuj grup� o ID: " + id_grupa);
		btnPotwierdzAkcje.setText("Modyfikuj grup�");
		btnPotwierdzAkcje.addActionListener(new ModyfikujGrupe());
		
		grupaPrzedMod = pobierzGrupeOId();
		tfNazwa.setText(grupaPrzedMod.pobierzNazwaGrupy());
		tpOpis.setText(grupaPrzedMod.pobierzOpisGrupy());
	}
	
	/**
	 * Pobranie wszystkich grup z bazy danych.
	 */
	private void pobierzGrupy() {
		interfejsBD.otworzPolaczenie();
		grupy = interfejsBD.pobierzGrupyWszystkie();
		interfejsBD.zamknijPolaczenie();
	}
	/**
	 * Sprawdza, czy pole "nazwa grupy" ma zawarto��.
	 * @return true - pole ma zawarto��; false - pole jest puste
	 */
	private boolean walidacjaCzyJestNazwaGrupy() {
		if(!czyPolePelne(tfNazwa.getText())) {
			JOptionPane.showMessageDialog(
					null,
					"Nadaj nazw� grupie!",
					"Uwaga",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Sprawdza, czy grupa o podanej nazwie ju� istnieje.
	 * Pozwala za zgod� u�ytkownika doda� grup� o takiej samej nazwie.
	 * @return true - nazwa dopuszczona; false - nazwa niedopuszczona
	 */
	private boolean walidacjaCzyNazwaUnikalna() {
		for(int i = 0; i < grupy.size(); i++) {
			if(grupy.get(i).pobierzNazwaGrupy().equals(tfNazwa.getText())) {
				Object nazwaOpcja[] = {"Tak", "Nie"};
				int opcja = JOptionPane.showOptionDialog(null,
						"Grupa o podanej nazwie ju� istnieje.\n"
						+ "Czy na pewno kontynuowa�?",
						"Pytanie",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null, 
						nazwaOpcja,
						nazwaOpcja[1]);
				if(opcja == 1) { return false; }
				break;
			}
		}
		return true;
	}
	
	/**
	 * Wyszukiwanie w li�cie grup grupy o podanym wcze�niej ID w bazie danych. 
	 * @return obiekt klasy Grupa o podanym wcze�niej ID
	 */
	private Grupa pobierzGrupeOId() {
		for(int i = 0; i < grupy.size(); i++) {
			if(grupy.get(i).pobierzIdGrupa() == id_grupa) {
				return grupy.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Sprawdza, czy nazwa jest taka sama, jak podano.
	 * Dla modyfikacji grupy.
	 * Umo�liwia zrobienie wyj�tku w kwestii nazwy grupy.
	 * @return true - nazwa grupy si� nie zmieni�a; false - nazwa grupy jest inna
	 */
	private boolean walidacjaCzyNazwaTakaSama() {
		if(tfNazwa.getText().equals(grupaPrzedMod.pobierzNazwaGrupy())) {
			return true;
		}
		return false;
	}
	/**
	 * Klasa wewn�trzna dodania grupy.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class DodajGrupe implements ActionListener {
		/**
		 * Konstruktor klasy DodajGrupe.
		 */
		public DodajGrupe() {}
		
		/**
		 * Akcja dodania grupy.
		 * Walidacja danych.
		 * Walidacja akcji.
		 * Rezultat.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(walidacjaCzyJestNazwaGrupy() &&
					walidacjaCzyNazwaUnikalna()) {
				interfejsBD.otworzPolaczenie();
				if(interfejsBD.dodajGrupe(tfNazwa.getText(), tpOpis.getText())) {
					JOptionPane.showMessageDialog(
							null,
							"Dodano grup�",
							"Info",
							JOptionPane.INFORMATION_MESSAGE);
					tfNazwa.setText("");
					tpOpis.setText("");
				} else {
					JOptionPane.showMessageDialog(
							null,
							"B��d przy dodawaniu grupy!",
							"B��d!",
							JOptionPane.ERROR_MESSAGE);
				}
				
				interfejsBD.zamknijPolaczenie();
			}
		}
	}
	
	/**
	 * Klasa wewn�trzna modyfikacji grupy.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class ModyfikujGrupe implements ActionListener {
		/**
		 * Konstruktor klasy ModyfikujGrupe 
		 */
		public ModyfikujGrupe() {}
		
		/**
		 * Akcja modyfikacji grupy.
		 * Walidacja danych.
		 * Walidacja akcji.
		 * Rezultat.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(walidacjaCzyJestNazwaGrupy() &&
					(walidacjaCzyNazwaTakaSama() || walidacjaCzyNazwaUnikalna())) {
				interfejsBD.otworzPolaczenie();
				if(interfejsBD.modyfikujGrupe(id_grupa, tfNazwa.getText(), tpOpis.getText())) {
					JOptionPane.showMessageDialog(
							null,
							"Zmodyfikowano grup�",
							"Info",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(
							null,
							"B��d przy modyfikacji grupy!",
							"B��d!",
							JOptionPane.ERROR_MESSAGE);
				}
				interfejsBD.zamknijPolaczenie();
			}
		}
	}
}
