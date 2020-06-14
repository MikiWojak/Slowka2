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
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Grupa extends Panel{
	
	/**
	 * Nr trybu:
	 * <ul>
	 * <li>0 - dodaj grupê</li>
	 * <li>0 - modyfikuj grupê</li>
	 * </ul>
	 */
	private int tryb;
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
	
	/**
	 * Konstruktor klasy Grupa - nowa grupa.
	 */
	public Grupa() {
		tryb = 0;
		inicjujKomponenty();
	}
	
	/**
	 * Konstruktor klasy Grupa - edycja grupy.
	 * @param id_grupa ID grupy do modyfikacji
	 */
	public Grupa(int id_grupa) {
		tryb = 1;
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
		btnPotwierdzAkcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(tryb) {
				case 0:
					System.out.println("Dodano grupê");
					break;
				case 1:
					System.out.println("Zmodyfikowano grupê");
					break;
				default:
					break;
				}
			}
		});
		btnPotwierdzAkcje.setBounds(0, 370, 150, 30);
		add(btnPotwierdzAkcje);
	}
	
	/**
	 * Modyfikuje komponenty na potrzeby edycji grupy.
	 */
	private void modyfikujKomponenty() {
		lblOpis.setText("Modyfikuj grupê o ID: " + id_grupa);
		btnPotwierdzAkcje.setText("Modyfikuj grupê");
	}
}
