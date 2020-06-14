package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * Panel do edycji s堯w.
 * @author MikiWojak (Miko豉j 畝rnowski)
 */
public class Slowo extends Panel {
	
	/**
	 * ID s這wa do modyfikacji.
	 */
	private int id_slowo;
	/**
	 * Napis informuj鉍y czy nowe s這wo, czy edycja istniej鉍ego.
	 */
	private JLabel lblOpis;
	
	/**
	 * Konstruktor klasy Slowo - nowe s這wo.
	 */
	public Slowo() {
		inicjujKomponenty();
	}
	
	/**
	 * Konstruktor klasy Slowo - edycja s這wa.
	 * @param id_slowo ID s這wa do modyfikacji.
	 */
	public Slowo(int id_slowo) {
		this.id_slowo = id_slowo;
		inicjujKomponenty();
		modyfikujKomponenty();
	}

	/**
	 * Inicjuje komponenty.
	 */
	private void inicjujKomponenty() {
		setPreferredSize(new Dimension(800, 600));
		
		lblOpis = new JLabel("Dodaj s\u0142owo");
		lblOpis.setFont(new Font("Arial", Font.BOLD, 16));
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setBounds(0, 0, 800, 25);
		add(lblOpis);
	}
	
	/**
	 * Modyfikuje komponenty na potrzeby edycji s這wa.
	 */
	private void modyfikujKomponenty() {
		lblOpis.setText("Modyfikuj s這wo o ID: " + id_slowo);
	}
}
