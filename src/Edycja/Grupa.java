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
 * @author MikiWojak (Mikołaj Żarnowski)
 */
public class Grupa extends Panel{
	/**
	 * Napis informujący czy nowa grupa, czy edycja istniejącej.
	 */
	private JLabel lblOpis;
	
	/**
	 * Konstruktor klasy Grupa.
	 */
	public Grupa() {
		setPreferredSize(new Dimension(800, 400));
		setLayout(null);
		
		inicjujKomponenty();
	}
	
	/**
	 * Inicjuje komponenty.
	 */
	private void inicjujKomponenty() {
		lblOpis = new JLabel("Dodaj grup\u0119");
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOpis.setBounds(0, 0, 800, 25);
		add(lblOpis);
	}
}
