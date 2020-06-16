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
	private JLabel lblOpis;
	/**
	 * Przycisk potwierdzaj¹cy dodanie lub modyfikacjê grupy.
	 */
	private JButton btnPotwierdzAkcje;
	private JTextField textField;
	
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
		
		lblOpis = new JLabel("Dodaj grup\u0119");
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setFont(new Font("Arial", Font.BOLD, 16));
		lblOpis.setBounds(0, 15, 800, 25);
		add(lblOpis);
		
		btnPotwierdzAkcje = new JButton("Dodaj grup\u0119");
		btnPotwierdzAkcje.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPotwierdzAkcje.setBounds(630, 260, 150, 30);
		add(btnPotwierdzAkcje);
		
		JLabel lblNewLabel = new JLabel("Nazwa");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 16));
		lblNewLabel.setBounds(15, 55, 80, 25);
		add(lblNewLabel);
		
		JLabel lblOpis_1 = new JLabel("Opis");
		lblOpis_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpis_1.setFont(new Font("Arial", Font.ITALIC, 16));
		lblOpis_1.setBounds(15, 95, 85, 150);
		add(lblOpis_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(115, 55, 665, 25);
		add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(115, 95, 665, 150);
		add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setFont(new Font("Arial", Font.PLAIN, 16));
	}
	
	/**
	 * Inicjowanie pól w klasie
	 */
	private void inicjujPola() {
		log = LoggerFactory.getLogger(PanelGrupa.class);
	}
	
	/**
	 * Modyfikuje komponenty na potrzeby edycji grupy.
	 */
	private void modyfikujKomponenty() {
		lblOpis.setText("Modyfikuj grupê o ID: " + id_grupa);
		btnPotwierdzAkcje.setText("Modyfikuj grupê");
		btnPotwierdzAkcje.addActionListener(new ModyfikujGrupe());
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
			System.out.println("Dodano grupê");
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
			System.out.println("Zmodyfikowano grupê o ID\t" + id_grupa);
		}
	}
}
