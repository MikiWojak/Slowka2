package Program;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * G�owne okno programu. W nim b�d� zawarte g��wne funkcje programu
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Program extends JFrame {
	
	/**
	 * Pojemnik na kilka paneli.
	 * Panele te b�d� w��czane w zalezno�ci od potrzeb.
	 */
	private JLayeredPane warstwy;
	
	/**
	 * Panel widoku grup i s��wek.
	 */
	private Panel widok;
	
	/**
	 * Panel testu s��wek.
	 */
	private Panel test;
	
	/**
	 * Utworzenie g��wnego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		inicjujKomponenty();
		//zmiana panelu
		//zmienPanel(test);
	}
	
	/**
	 * Inicjowanie komponent�w w programie.
	 */
	private void inicjujKomponenty() {
		//podstawowe parametry
		setBounds(100, 100, 0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("S��wka 2");
		
		//komponenty
		warstwy = new JLayeredPane();
		warstwy.setLayout(new CardLayout());
		getContentPane().add(warstwy);
		
		widok = new Widok();
		warstwy.add(widok);
		
		test = new Test();
		warstwy.add(test);
		
		pack();
		
		//ko�cowe parametry
		setVisible(true);
	}
	
	/**
	 * Zmiana panelu.
	 * Zmiana na widok lub test.
	 * @param panel panel, jaki ma by� wczytany
	 */
	public void zmienPanel(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}

}
