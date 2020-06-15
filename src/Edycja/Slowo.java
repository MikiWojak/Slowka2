package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * Panel do edycji s��w.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Slowo extends Panel {
	
	/**
	 * ID s�owa do modyfikacji.
	 */
	private int id_slowo;
	/**
	 * ID grupy, do kt�rej ma nale�e� s�owo.
	 */
	private int id_grupa;
	/**
	 * Napis informuj�cy czy nowe s�owo, czy edycja istniej�cego.
	 */
	private JLabel lblOpis;
	
	/**
	 * Konstruktor klasy Slowo - nowe s�owo.
	 * @param id_grupa ID grupy, do kt�rej ma nale�e� s�owo
	 */
	public Slowo(int id_grupa) {
		this.id_grupa = id_grupa;
		inicjujKomponenty();
		lblOpis.setText("Dodaj s�owo do grupy o ID: " + this.id_grupa);
	}
	
	/**
	 * Konstruktor klasy Slowo - modyfikacja s�owa.
	 * @param id_slowo ID s�owa do modyfikacji
	 * @param tryb true albo false - nie ma znaczenia; tylko do odr�nienia konstruktora
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
	 * Modyfikuje komponenty na potrzeby edycji s�owa.
	 */
	private void modyfikujKomponenty() {
		lblOpis.setText("Modyfikuj s�owo o ID: " + id_slowo);
	}
}
