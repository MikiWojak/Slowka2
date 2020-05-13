package Program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * G³owne okno programu. W nim bêd¹ zawarte g³ówne funkcje programu
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Program extends JFrame {
	
	/**
	 * Utworzenie g³ównego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		setBounds(100, 100, 0, 0);
		add(new Panel());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("S³ówka 2");
		setVisible(true);
	}

}
