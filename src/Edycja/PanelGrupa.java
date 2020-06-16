package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.slf4j.LoggerFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	}
	
	/**
	 * Modyfikuje komponenty na potrzeby edycji grupy.
	 */
	private void modyfikujKomponenty() {
		lblTytul.setText("Modyfikuj grup� o ID: " + id_grupa);
		btnPotwierdzAkcje.setText("Modyfikuj grup�");
		btnPotwierdzAkcje.addActionListener(new ModyfikujGrupe());
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
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Dodano grup�");
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
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Zmodyfikowano grup� o ID\t" + id_grupa);
		}
	}
}
