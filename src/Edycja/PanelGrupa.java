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
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class PanelGrupa extends Panel{
	
	/**
	 * ID grupy do modyfikacji. 
	 */
	private int id_grupa;
	/**
	 * Napis informuj¹cy czy nowa grupa, czy edycja istniej¹cej.
	 */
	private JLabel lblTytul;
	/**
	 * Przycisk potwierdzaj¹cy dodanie lub modyfikacjê grupy.
	 */
	private JButton btnPotwierdzAkcje;
	/**
	 * Pole na nazwê grupy.
	 */
	private JTextField tfNazwa;
	/**
	 * Opis pola na nazwê grupy.
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
	 * Inicjowanie pól w klasie
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
		lblTytul.setText("Modyfikuj grupê o ID: " + id_grupa);
		btnPotwierdzAkcje.setText("Modyfikuj grupê");
		btnPotwierdzAkcje.addActionListener(new ModyfikujGrupe());
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
	 * Sprawdza, czy pole "nazwa grupy" ma zawartoœæ.
	 */
	private void walidacjaCzyJestNazwaGrupy() {
		if(!czyPolePelne(tfNazwa.getText())) {
			JOptionPane.showMessageDialog(
					null,
					"Nadaj nazwê grupie!",
					"Uwaga",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void walidacjaCzyNazwaUnikalna() {
		for(int i = 0; i < grupy.size(); i++) {
			if(grupy.get(i).pobierzNazwaGrupy().equals(tfNazwa.getText())) {
				JOptionPane.showMessageDialog(
						null,
						"Grupa o podanej nazwie ju¿ istnieje!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}
	
	/**
	 * Klasa wewnêtrzna dodania grupy.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class DodajGrupe implements ActionListener {
		/**
		 * Konstruktor klasy DodajGrupe.
		 */
		public DodajGrupe() {}
		
		/**
		 * Akcja dodania grupy.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			walidacjaCzyJestNazwaGrupy();
			walidacjaCzyNazwaUnikalna();
		}
	}
	
	/**
	 * Klasa wewnêtrzna modyfikacji grupy.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class ModyfikujGrupe implements ActionListener {
		/**
		 * Konstruktor klasy ModyfikujGrupe 
		 */
		public ModyfikujGrupe() {}
		
		/**
		 * Akcja modyfikacji grupy.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			walidacjaCzyJestNazwaGrupy();
			walidacjaCzyNazwaUnikalna();
		}
	}
}
