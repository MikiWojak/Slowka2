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
	 * ID s³owa do modyfikacji.
	 */
	private int id_slowo;
	/**
	 * ID grupy, do której ma nale¿eæ s³owo.
	 */
	private int id_grupa;
	/**
	 * Napis informuj¹cy czy nowe s³owo, czy edycja istniej¹cego.
	 */
	private JLabel lblOpis;
	
	/**
	 * Konstruktor klasy Slowo - nowe s³owo.
	 * @param id_grupa ID grupy, do której ma nale¿eæ s³owo
	 */
	public Slowo(int id_grupa) {
		this.id_grupa = id_grupa;
		inicjujKomponenty();
		lblOpis.setText("Dodaj s³owo do grupy o ID: " + this.id_grupa);
	}
	
	/**
	 * Konstruktor klasy Slowo - modyfikacja s³owa.
	 * @param id_slowo ID s³owa do modyfikacji
	 * @param tryb true albo false - nie ma znaczenia; tylko do odró¿nienia konstruktora
	 */
	public Slowo(int id_slowo, boolean tryb) {
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
	 * Modyfikuje komponenty na potrzeby edycji s³owa.
	 */
	private void modyfikujKomponenty() {
		lblOpis.setText("Modyfikuj s³owo o ID: " + id_slowo);
	}
}
