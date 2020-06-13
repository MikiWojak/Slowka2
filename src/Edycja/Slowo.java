package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * Panel do edycji s³ów.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Slowo extends Panel {
	/**
	 * Napis informuj¹cy czy nowe s³owo, czy edycja istniej¹cego.
	 */
	private JLabel lblOpis;
	
	/**
	 * Konstruktor klasy Slowo.
	 */
	public Slowo() {
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		
		inicjujKomponenty();
	}
	
	/**
	 * Inicjuje komponenty.
	 */
	private void inicjujKomponenty() {
		lblOpis = new JLabel("Dodaj s\u0142owo");
		lblOpis.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setBounds(0, 0, 800, 25);
		add(lblOpis);
	}
}
