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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * G�owne okno programu. W nim b�d� zawarte g��wne funkcje programu.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Program extends JFrame {
	
	/**
	 * Szerokos� okna - pojemnika na panele.
	 */
	private final int OKNO_SZEROKOSC = 1200;
	/**
	 * Wysoko�� okna - pojemnika na panele.
	 */
	private final int OKNO_WYSOKOSC = 600;
	/**
	 * Interfejs bazy danych.
	 */
	private InterfejsBD interfejsBD;
	/**
	 * Generowanie log�w.
	 */
	private Logger log;
	/**
	 * Pasek menu.
	 */
	private JMenuBar mnBarMenu;
	/**
	 * Opcja widok.
	 * Prze��czenie programu na panel widok.
	 */
	private JMenu mnWidok;
	/**
	 * Opcja test.
	 * Prze��czenie programu na panel test.
	 */
	private JMenu mnTest;
	/**
	 * Pojemnik na panele.
	 * Umo�liwia prze��czanie si� mi�dzy kilkoma panelami.
	 */
	private JLayeredPane warstwy;
	/**
	 * Panel widok.
	 * Lista grup i s��w.
	 */
	private Panel widok;
	/**
	 * Panel test.
	 * Nauka s��w metod� wielokrotnych powt�rze�.
	 */
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
		inicjujPola();
		
		//operacje ko�cowe
		pack();
		setResizable(false);
		setVisible(true);
		
		log.debug("Utworzono obiekt klasy Program.");
	}
	
	/**
	 * Inicjacja element�w okna.
	 * G��wnie element�w z biblioteki Swing.
	 */
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
	
	/**
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//interfejsBD
		interfejsBD = new InterfejsBD();
		interfejsBD.zamknijPolaczenie();
		//log
		log = LoggerFactory.getLogger(Program.class);
	}
	
	/**
	 * Zmiana panelu.
	 * @param panel panel, jaki ma zosta� wy�wietlony
	 */
	private void zmienPanel(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}
	
	/**
	 * Zmiana panelu na widok.
	 * Do wykorzystania w opcji Widok oraz w skr�tach klawiszowych.
	 */
	private void zmienPanelNaWidok() {
		zmienPanel(widok);
		mnWidok.setEnabled(false);
		mnTest.setEnabled(true);
		log.debug("Zmiana panelu na 'widok'.");
	}
	
	/**
	 * Zmiana panelu na test.
	 * Do wykorzystania w opcji Widok oraz w skr�tach klawiszowych.
	 */
	private void zmienPanelNaTest() {
		zmienPanel(test);			
		mnWidok.setEnabled(true);
		mnTest.setEnabled(false);
		log.debug("Zmiana panelu na 'test'.");
	}
}
