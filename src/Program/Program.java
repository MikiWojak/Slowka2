package Program;

import java.awt.BorderLayout;
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
	 * Utworzenie g³ównego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		inicjujKomponenty();
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
		add(new Panel());
		pack();
		
		//koñcowe parametry
		setVisible(true);
	}

}
