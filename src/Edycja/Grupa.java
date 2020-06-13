/**
 * 
 */
package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Panel do edycji grup.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Grupa extends Panel{
	
	/**
	 * ID grupy do modyfikacji. 
	 */
	private int id_grupa;
	/**
	 * Napis informuj¹cy czy nowa grupa, czy edycja istniej¹cej.
	 */
	private JLabel lblOpis;
	
	/**
	 * Konstruktor klasy Grupa - nowa grupa.
	 */
	public Grupa() {
		inicjujKomponenty();
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
		setLayout(null);
		
		lblOpis = new JLabel("Dodaj grup\u0119");
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setFont(new Font("Arial", Font.BOLD, 16));
		lblOpis.setBounds(0, 0, 800, 25);
		add(lblOpis);
	}
	
	/**
	 * Modyfikuje komponenty na potrzeby edycji grupy.
	 */
	private void modyfikujKomponenty() {
		lblOpis.setText("Modyfikuj grupê o ID: " + id_grupa);
	}
}
