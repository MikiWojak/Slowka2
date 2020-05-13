package Program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * G�owne okno programu. W nim b�d� zawarte g��wne funkcje programu
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Program extends JFrame {
	
	/**
	 * Utworzenie g��wnego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		setBounds(100, 100, 0, 0);
		add(new Panel());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("S��wka 2");
		setVisible(true);
	}

}
