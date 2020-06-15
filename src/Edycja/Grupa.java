package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel do edycji grup.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Grupa extends Panel{
	
	/**
	 * ID grupy do modyfikacji. 
	 */
	private int id_grupa;
	/**
	 * Napis informuj�cy czy nowa grupa, czy edycja istniej�cej.
	 */
	private JLabel lblOpis;
	/**
	 * Przycisk potwierdzaj�cy dodanie lub modyfikacj� grupy.
	 */
	private JButton btnPotwierdzAkcje;
	
	/**
	 * Konstruktor klasy Grupa - nowa grupa.
	 */
	public Grupa() {
		inicjujKomponenty();
		btnPotwierdzAkcje.addActionListener(new DodajGrupe());
	}
	
	/**
	 * Konstruktor klasy Grupa - edycja grupy.
	 * @param id_grupa ID grupy do modyfikacji
	 */
	public Grupa(int id_grupa) {
		this.id_grupa = id_grupa;
		inicjujKomponenty();
		modyfikujKomponenty();
	}
	
	/**
	 * Inicjuje komponenty na potrzeby nowej grupy.
	 */
	private void inicjujKomponenty() {
		setPreferredSize(new Dimension(800, 400));
		
		lblOpis = new JLabel("Dodaj grup\u0119");
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setFont(new Font("Arial", Font.BOLD, 16));
		lblOpis.setBounds(0, 0, 800, 25);
		add(lblOpis);
		
		btnPotwierdzAkcje = new JButton("Dodaj grup\u0119");
		btnPotwierdzAkcje.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPotwierdzAkcje.setBounds(0, 370, 150, 30);
		add(btnPotwierdzAkcje);
	}
	
	/**
	 * Modyfikuje komponenty na potrzeby edycji grupy.
	 */
	private void modyfikujKomponenty() {
		lblOpis.setText("Modyfikuj grup� o ID: " + id_grupa);
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
