package Program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BazaDanych.InterfejsBD;

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
 * G�owne okno programu. W nim b�d� zawarte g��wne funkcje programu
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Program extends JFrame {
	private final int OKNO_SZEROKOSC = 1200;
	private final int OKNO_WYSOKOSC = 600;
	
	private InterfejsBD interfejsBD;
	
	private JMenuBar mnBarMenu;
	private JMenu mnWidok;
	private JMenu mnTest;
	private JLayeredPane warstwy;
	
	private Panel widok;
	private Panel test;
	
	/**
	 * Utworzenie g��wnego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		//operacje pocz�tkowe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 0, 0);
		
		//inicjacje
		inicjujKomponenty();
		
		
		//operacje ko�cowe
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	private void inicjujKomponenty() {
		//mnBarMenu
		mnBarMenu = new JMenuBar();
		setJMenuBar(mnBarMenu);
		
		//mnWidok
		mnWidok = new JMenu("Widok");
		mnWidok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { zmienPanelNaWidok(); }
		});
		mnWidok.setEnabled(false);
		mnBarMenu.add(mnWidok);
		
		//mnTest
		mnTest = new JMenu("Test");
		mnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { zmienPanelNaTest(); }
		});
		mnBarMenu.add(mnTest);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//warstwy
		warstwy = new JLayeredPane();
		getContentPane().add(warstwy);
		warstwy.setPreferredSize(new Dimension(OKNO_SZEROKOSC, OKNO_WYSOKOSC));
		warstwy.setLayout(null);
		
		//widok
		widok = new Widok();
		warstwy.add(widok);
		
		//test
		test = new Test();
		warstwy.add(test);
	}
	
	private void inicjujZmienne() {
		interfejsBD = new InterfejsBD();
		interfejsBD.zamknijPolaczenie();
	}
	
	private void zmienPanel(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}
	
	private void zmienPanelNaWidok() {
		zmienPanel(widok);
		
		mnWidok.setEnabled(false);
		mnTest.setEnabled(true);
	}
	
	private void zmienPanelNaTest() {
		zmienPanel(test);			
		
		mnWidok.setEnabled(true);
		mnTest.setEnabled(false);
	}
}
