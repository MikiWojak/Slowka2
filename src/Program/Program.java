package Program;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * G³owne okno programu. W nim bêd¹ zawarte g³ówne funkcje programu
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Program extends JFrame {
	
	/**
	 * Pojemnik na kilka paneli.
	 * Panele te bêd¹ w³¹czane w zaleznoœci od potrzeb.
	 */
	private JLayeredPane warstwy;
	
	/**
	 * Panel widoku grup i s³ówek.
	 */
	private Panel widok;
	
	/**
	 * Panel testu s³ówek.
	 */
	private Panel test;
	
	/**
	 * Utworzenie g³ównego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		inicjujKomponenty();
		//zmiana panelu
		//zmienPanel(test);
	}
	
	/**
	 * Inicjowanie komponentów w programie.
	 */
	private void inicjujKomponenty() {
		//podstawowe parametry
		setBounds(100, 100, 0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("S³ówka 2");
		
		//komponenty
		warstwy = new JLayeredPane();
		warstwy.setLayout(new CardLayout());
		getContentPane().add(warstwy);
		
		widok = new Widok();
		warstwy.add(widok);
		
		test = new Test();
		warstwy.add(test);
		
		pack();
		
		//koñcowe parametry
		setVisible(true);
	}
	
	/**
	 * Zmiana panelu.
	 * Zmiana na widok lub test.
	 * @param panel panel, jaki ma byæ wczytany
	 */
	public void zmienPanel(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}

}
