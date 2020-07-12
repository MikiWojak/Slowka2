package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.slf4j.LoggerFactory;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;

import BazaDanych.Slowo;

/**
 * Panel do edycji s��w.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class PanelSlowo extends Panel {
	
	/**
	 * ID s�owa do modyfikacji.
	 */
	private int id_slowo;
	/**
	 * ID grupy, do kt�rej ma nale�e� s�owo.
	 */
	private int id_grupa;
	/**
	 * Lista na s�owa.
	 */
	private List<Slowo>slowa;
	/**
	 * Rekord przed modyfikacj�.
	 */
	private Slowo slowoPrzedMod;
	/**
	 * Napis informuj�cy czy nowe s�owo, czy edycja istniej�cego.
	 */
	private JLabel lblOpis;
	/**
	 * Pole na s�owo w j�zyku obcym.
	 */
	private JTextField tfSlowo;
	/**
	 * Pole na t�umaczenie s�owa.
	 */
	private JTextField tfTlumaczenie;
	/**
	 * Pole na cz�� mowy s�owa.
	 */
	private JTextField tfCzescMowy;
	/**
	 * Opis pola na s�owo w j�zyku obcym.
	 */
	private JLabel lblSlowo;
	/**
	 * Opis pola na t�umaczenie s�owa.
	 */
	private JLabel lblTlumaczenie;
	/**
	 * Opis pola na cz�� mowy s�owa.
	 */
	private JLabel lblCzescMowy;
	/**
	 * Przycisk do dodawania lub modyfikacji s�owa.
	 */
	private JButton btnPotwierdzAkcje;
	
	/**
	 * Konstruktor klasy Slowo - nowe s�owo.
	 * @param id_grupa ID grupy, do kt�rej ma nale�e� s�owo
	 * @wbp.parser.constructor
	 */
	public PanelSlowo(int id_grupa) {
		this.id_grupa = id_grupa;
		inicjujPola();
		pobierzSlowa(id_grupa);
		inicjujKomponenty();
		dodajSlowoKomponenty();
		
		//DEBUG
		System.out.println("Dodaj s�owo");
		wyswietlSlowa();
	}
	
	/**
	 * Konstruktor klasy Slowo - modyfikacja s�owa.
	 * @param id_slowo ID s�owa do modyfikacji
	 * @param tryb true albo false - nie ma znaczenia; tylko do odr�nienia konstruktora
	 */
	public PanelSlowo(int id_slowo, boolean tryb) {
		this.id_slowo = id_slowo;
		inicjujPola();
		slowoPrzedMod = pobierzSlowo(id_slowo);
		pobierzSlowa(slowoPrzedMod.pobierzIdGrupy());
		inicjujKomponenty();
		modyfikujSlowoKomponenty();
		
		//DEBUG
		System.out.println("Modyfikuj s�owo");
		wyswietlSlowa();
		System.out.println(slowoPrzedMod);
	}

	/**
	 * Inicjuje komponenty.
	 * Surowa forma.
	 */
	private void inicjujKomponenty() {
		setPreferredSize(new Dimension(800, 225));
		
		lblOpis = new JLabel("");
		lblOpis.setFont(new Font("Arial", Font.BOLD, 16));
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setBounds(0, 15, 800, 25);
		add(lblOpis);
		
		tfSlowo = new JTextField();
		tfSlowo.setFont(new Font("Arial", Font.PLAIN, 16));
		tfSlowo.setColumns(10);
		tfSlowo.setBounds(145, 56, 640, 25);
		add(tfSlowo);
		
		lblSlowo = new JLabel("S\u0142owo");
		lblSlowo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSlowo.setFont(new Font("Arial", Font.ITALIC, 16));
		lblSlowo.setBounds(10, 56, 120, 25);
		add(lblSlowo);
		
		lblTlumaczenie = new JLabel("T\u0142umaczenie");
		lblTlumaczenie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTlumaczenie.setFont(new Font("Arial", Font.ITALIC, 16));
		lblTlumaczenie.setBounds(10, 97, 120, 25);
		add(lblTlumaczenie);
		
		tfTlumaczenie = new JTextField();
		tfTlumaczenie.setFont(new Font("Arial", Font.PLAIN, 16));
		tfTlumaczenie.setColumns(10);
		tfTlumaczenie.setBounds(145, 97, 640, 25);
		add(tfTlumaczenie);
		
		lblCzescMowy = new JLabel("Cz\u0119\u015B\u0107 mowy");
		lblCzescMowy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCzescMowy.setFont(new Font("Arial", Font.ITALIC, 16));
		lblCzescMowy.setBounds(10, 138, 120, 25);
		add(lblCzescMowy);
		
		tfCzescMowy = new JTextField();
		tfCzescMowy.setFont(new Font("Arial", Font.PLAIN, 16));
		tfCzescMowy.setColumns(10);
		tfCzescMowy.setBounds(145, 138, 640, 25);
		add(tfCzescMowy);
		
		btnPotwierdzAkcje = new JButton("");
		btnPotwierdzAkcje.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPotwierdzAkcje.setBounds(635, 179, 150, 30);
		add(btnPotwierdzAkcje);
	}
	
	/**
	 * Inicjacja p�l w klasie.
	 */
	private void inicjujPola() {
		log = LoggerFactory.getLogger(PanelSlowo.class);
		slowa = new LinkedList<Slowo>();
	}
	
	/**
	 * Zmiany w komponentach na potrzeby dodawania s�owa.
	 */
	private void dodajSlowoKomponenty() {
		lblOpis.setText("Dodaj s�owo do grupy");
		btnPotwierdzAkcje.setText("Dodaj s�owo");
		btnPotwierdzAkcje.addActionListener(new DodajSlowo());
	}
	/**
	 * Zmiany w komponentach na potrzeby modyfikacji s�owa.
	 */
	private void modyfikujSlowoKomponenty() {
		lblOpis.setText("Modyfikuj s�owo");
		btnPotwierdzAkcje.setText("Modyfikuj s�owo");
		btnPotwierdzAkcje.addActionListener(new ModyfikujSlowo());
		
		tfSlowo.setText(slowoPrzedMod.pobierzSlowo());
		tfTlumaczenie.setText(slowoPrzedMod.pobierzTlumaczenie());
		tfCzescMowy.setText(slowoPrzedMod.pobierzCzescMowy());
	}
	
	/**
	 * Pobierz z bazy danych s�owa o podanym ID.
	 * @param id_grupa ID grupy w bazie danych, do kt�rego maj� nale�e� s�owa
	 */
	private void pobierzSlowa(int id_grupa){
		interfejsBD.otworzPolaczenie();
		slowa = interfejsBD.pobierzSlowaZGrupy(id_grupa);
		interfejsBD.zamknijPolaczenie();
	}
	
	/**
	 * Pobranie jednego s�owa z bazy danych.
	 * @param id_slowo ID s�owa w bazie danych
	 * @return rekord klasy Slowo
	 */
	private Slowo pobierzSlowo(int id_slowo) {
		interfejsBD.otworzPolaczenie();
		List<Slowo>lista = new LinkedList<Slowo>();
		lista = interfejsBD.pobierzSlowo(id_slowo);
		interfejsBD.zamknijPolaczenie();
		return lista.get(0);
	}
	
	/**
	 * Wy�wietlenie listy sl�w.
	 * Do debugu.
	 */
	private void wyswietlSlowa() {
		for(int i = 0; i < slowa.size(); i++) {
			System.out.print(slowa.get(i));
		}
		System.out.println();
	}
	
	/**
	 * Sprawdza, czy pole "s�owo" i "t�umaczenie" maj� zawarto��.
	 * @return true - obydwa pola maj� zawarto��; false - jedno z p�l jest puste
	 */
	private boolean walidacjaCzyJestSlowoTlumaczenie() {
		if(!czyPolePelne(tfSlowo.getText()) || 
				!czyPolePelne(tfTlumaczenie.getText())) {
			JOptionPane.showMessageDialog(
					null,
					"Pole 'S�owo' lub 'T�umaczenie' jest puste!",
					"Uwaga",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Klasa wewn�trzna dodania s�owa.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class DodajSlowo implements ActionListener {
		/**
		 * Konstruktor klasy DodajSlowo.
		 */
		public DodajSlowo() {}
		
		/**
		 * Akcja dodania s�owa do bazy danych.
		 * Walidacja danych.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(walidacjaCzyJestSlowoTlumaczenie()) {
				interfejsBD.otworzPolaczenie();
				interfejsBD.dodajSlowo(
						id_grupa,
						tfSlowo.getText(),
						tfTlumaczenie.getText(),
						tfCzescMowy.getText(),
						false);
				interfejsBD.zamknijPolaczenie();
				
				JOptionPane.showMessageDialog(
						null,
						"Dodano s�owo",
						"Info",
						JOptionPane.INFORMATION_MESSAGE);
				
				tfSlowo.setText("");
				tfTlumaczenie.setText("");
				tfCzescMowy.setText("");
			}
		}
	}
	
	/**
	 * Klasa wewn�trzna modyfikacji s�owa.
	 * Do wywo�ywania akcji.
	 * @author MikiWojak (Miko�aj �arnowski)
	 */
	private class ModyfikujSlowo implements ActionListener {
		/**
		 * Konstruktor klasy ModyfikujSlowo. 
		 */
		public ModyfikujSlowo() {}
		
		/**
		 * Akcja modyfikacji s�owa w bazie danych.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			interfejsBD.otworzPolaczenie();
			interfejsBD.modyfikujSlowo(
					id_slowo,
					slowoPrzedMod.pobierzIdGrupy(),
					tfSlowo.getText(),
					tfTlumaczenie.getText(),
					tfCzescMowy.getText(),
					false);
			interfejsBD.zamknijPolaczenie();
			
			JOptionPane.showMessageDialog(
					null,
					"Zmodyfikowano s�owo",
					"Info",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
