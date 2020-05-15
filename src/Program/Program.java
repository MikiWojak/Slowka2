package Program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;


/**
 * G³owne okno programu. W nim bêd¹ zawarte g³ówne funkcje programu
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Program extends JFrame {
	private JMenuBar mnBarMenu;
	private JMenu mnWidok;
	private JMenu mnTest;
	private JLayeredPane warstwy;
	
	/**
	 * Utworzenie g³ównego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		mnBarMenu = new JMenuBar();
		setJMenuBar(mnBarMenu);
		
		mnWidok = new JMenu("Widok");
		mnBarMenu.add(mnWidok);
		
		mnTest = new JMenu("Test");
		mnBarMenu.add(mnTest);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		warstwy = new JLayeredPane();
		getContentPane().add(warstwy);
		warstwy.setPreferredSize(new Dimension(1200, 600));
		warstwy.setLayout(null);
		
		
		
		pack();
		
		setVisible(true);
	}
}
