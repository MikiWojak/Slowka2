package Edycja;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Panel do edycji s³ów.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class PanelSlowo extends Panel {
	
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
	 * Pole na s³owo w jêzyku obcym.
	 */
	private JTextField tfSlowo;
	/**
	 * Pole na t³umaczenie s³owa.
	 */
	private JTextField tfTlumaczenie;
	/**
	 * Pole na czêœæ mowy s³owa.
	 */
	private JTextField tfCzescMowy;
	/**
	 * Opis pola na s³owo w jêzyku obcym.
	 */
	private JLabel lblSlowo;
	/**
	 * Opis pola na t³umaczenie s³owa.
	 */
	private JLabel lblTlumaczenie;
	/**
	 * Opis pola na czêœæ mowy s³owa.
	 */
	private JLabel lblCzescMowy;
	/**
	 * Przycisk do dodawania lub modyfikacji s³owa.
	 */
	private JButton btnPotwierdzAkcje;
	
	/**
	 * Konstruktor klasy Slowo - nowe s³owo.
	 * @param id_grupa ID grupy, do której ma nale¿eæ s³owo
	 * @wbp.parser.constructor
	 */
	public PanelSlowo(int id_grupa) {
		this.id_grupa = id_grupa;
		inicjujKomponenty();
		dodajSlowoKomponenty();
	}
	
	/**
	 * Konstruktor klasy Slowo - modyfikacja s³owa.
	 * @param id_slowo ID s³owa do modyfikacji
	 * @param tryb true albo false - nie ma znaczenia; tylko do odró¿nienia konstruktora
	 */
	public PanelSlowo(int id_slowo, boolean tryb) {
		this.id_slowo = id_slowo;
		inicjujKomponenty();
		modyfikujSlowoKomponenty();
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
	 * Zmiany w komponentach na potrzeby dodawania s³owa.
	 */
	private void dodajSlowoKomponenty() {
		lblOpis.setText("Dodaj s³owo do grupy");
		btnPotwierdzAkcje.setText("Dodaj s³owo");
	}
	/**
	 * Zmiany w komponentach na potrzeby modyfikacji s³owa.
	 */
	private void modyfikujSlowoKomponenty() {
		lblOpis.setText("Modyfikuj s³owo");
		btnPotwierdzAkcje.setText("Modyfikuj s³owo");
	}
	
	/**
	 * Klasa wewnêtrzna dodania s³owa.
	 * Do wywo³ywania akcji.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class DodajSlowo implements ActionListener {
		
		public DodajSlowo() {}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/**
	 * Klasa wewnêtrzna modyfikacji s³owa.
	 * Do wywo³ywania akcji.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class ModyfikujSlowo implements ActionListener {
		
		public ModyfikujSlowo() {}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
