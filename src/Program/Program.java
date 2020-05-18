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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * G³owne okno programu. W nim bêd¹ zawarte g³ówne funkcje programu
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Program extends JFrame {
	private final int OKNO_SZEROKOSC = 1200;
	private final int OKNO_WYSOKOSC = 600;
	
	private JMenuBar mnBarMenu;
	private JMenu mnWidok;
	private JMenu mnTest;
	private JLayeredPane warstwy;
	
	private Panel widok;
	private Panel test;
	/**
	 * Utworzenie g³ównego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		//operacje pocz¹tkowe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 0, 0);
		
		//komponenty
		mnBarMenu = new JMenuBar();
		setJMenuBar(mnBarMenu);
		
		mnWidok = new JMenu("Widok");
		mnWidok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				zmienPanel(test);
			}
		});
		mnBarMenu.add(mnWidok);
		
		mnTest = new JMenu("Test");
		mnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				zmienPanel(widok);
			}
		});
		mnBarMenu.add(mnTest);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		warstwy = new JLayeredPane();
		getContentPane().add(warstwy);
		warstwy.setPreferredSize(new Dimension(OKNO_SZEROKOSC, OKNO_WYSOKOSC));
		warstwy.setLayout(null);
		
		widok = new Widok();
		warstwy.add(widok);
		
		test = new Test();
		warstwy.add(test);
		
		//operacje koñcowe
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	private void zmienPanel(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}
}
