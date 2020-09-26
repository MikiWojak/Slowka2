package Program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BazaDanych.InterfejsBD;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
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
 * G³owne okno programu. W nim bêd¹ zawarte g³ówne funkcje programu.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Program extends JFrame {
	
	/**
	 * Szerokosæ okna - pojemnika na panele.
	 */
	private final int OKNO_SZEROKOSC = 1200;
	/**
	 * Wysokoœæ okna - pojemnika na panele.
	 */
	private final int OKNO_WYSOKOSC = 600;
	/**
	 * Interfejs bazy danych.
	 */
	private InterfejsBD interfejsBD;
	/**
	 * Generowanie logów.
	 */
	private Logger log;
	/**
	 * Pasek menu.
	 */
	private JMenuBar mnBarMenu;
	/**
	 * Opcja widok.
	 * Prze³¹czenie programu na panel widok.
	 */
	private JMenu mnWidok;
	/**
	 * Opcja test.
	 * Prze³¹czenie programu na panel test.
	 */
	private JMenu mnTest;
	/**
	 * Pojemnik na panele.
	 * Umo¿liwia prze³¹czanie siê miêdzy kilkoma panelami.
	 */
	private JLayeredPane warstwy;
	/**
	 * Panel widok.
	 * Lista grup i s³ów.
	 */
	private Widok widok;
	/**
	 * Panel test.
	 * Nauka s³ów metod¹ wielokrotnych powtórzeñ.
	 */
	private Test test;
	/**
	 * Zmiana panelu na widok.
	 * Rozszerzenie klasy MouseListener.
	 */
	private PanelWidok listenerWidok;
	/**
	 * Zmiana panelu na test.
	 * Rozszerzenie klasy MouseListener.
	 */
	private PanelTest listenerTest;
	
	/**
	 * Utworzenie g³ównego programu.
	 * Konstruktor klasy Program.
	 */
	public Program() {
		//operacje pocz¹tkowe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 0, 0);
		
		//inicjacje
		inicjujPola();
		inicjujKomponenty();
		
		//operacje koñcowe
		pack();
		setResizable(false);
		setVisible(true);
		
		log.debug("Utworzono obiekt klasy Program.");
	}
	
	/**
	 * Inicjacja elementów okna.
	 * G³ównie elementów z biblioteki Swing.
	 */
	private void inicjujKomponenty() {
		//mnBarMenu
		mnBarMenu = new JMenuBar();
		setJMenuBar(mnBarMenu);
		
		//mnWidok
		mnWidok = new JMenu("Widok");
		mnWidok.setEnabled(false);
		mnBarMenu.add(mnWidok);
		
		//mnTest
		mnTest = new JMenu("Test");
		mnTest.addMouseListener(listenerTest);
		mnBarMenu.add(mnTest);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//warstwy
		warstwy = new JLayeredPane();
		getContentPane().add(warstwy);
		warstwy.setPreferredSize(new Dimension(OKNO_SZEROKOSC, OKNO_WYSOKOSC));
		warstwy.setLayout(null);
		
		/*
		 * Inicjacja: najpierw test, potem Widok.
		 * Prze³¹czenie na widok.
		 * Usuniêcie przek³amañ w interfejsie Widok.
		 */
		//test
		test = new Test();
		warstwy.add(test);
		
		//widok
		widok = new Widok();
		warstwy.add(widok);
		
		zmienPanel(widok);
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//interfejsBD
		interfejsBD = new InterfejsBD();
		interfejsBD.zamknijPolaczenie();
		//log
		log = LoggerFactory.getLogger(Program.class);
		//listenery
		listenerWidok = new PanelWidok();
		listenerTest = new PanelTest();
	}
	
	/**
	 * Zmiana panelu.
	 * @param panel panel, jaki ma zostaæ wyœwietlony
	 */
	private void zmienPanel(JPanel panel) {
		warstwy.removeAll();
		warstwy.add(panel);
		warstwy.repaint();
		warstwy.revalidate();
	}
	
	/**
	 * Klasa wewnêtrzna pokazania panelu Widok.
	 * Do wywo³ania akcji.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class PanelWidok extends MouseAdapter {
		/**
		 * Konstruktor klasy PanelWidok
		 */
		public  PanelWidok() {}
		
		/**
		 * Zmiana panelu na Widok.
		 */
		@Override
		public void mouseClicked(MouseEvent e) { 
			zmienPanel(widok);
			widok.zmienGrupe(widok.pobierzIndexGrupa());
			
			mnWidok.removeMouseListener(listenerWidok);
			mnWidok.setEnabled(false);
			mnTest.addMouseListener(listenerTest);
			mnTest.setEnabled(true);
			
			log.debug("Zmiana panelu na 'widok'.");
		}
	}
	
	/**
	 * Klasa wewnêtrzna pokazania panelu Test.
	 * Do wywo³ania akcji.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class PanelTest extends MouseAdapter {
		/**
		 * Konstruktor klasy PanelTest
		 */
		public PanelTest() {}
		
		/**
		 * Zmiana panelu na Test.
		 * Sprawdzenie, czy zosta³a wybrana grupa i czy w grupie s¹ s³owa.
		 * Uruchomienie trybu testu.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!widok.czyWybranoGrupe()) {
				JOptionPane.showMessageDialog(
						null,
						"Nie wybrano grupy!\n"
						+ "Nie mo¿na uruchomiæ testu!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
			} else if(!widok.czySaSlowaWGrupie()){
				JOptionPane.showMessageDialog(
						null,
						"Brak s³ów w wybranej grupie!\n"
						+ "Nie mo¿na uruchomiæ testu!",
						"Uwaga",
						JOptionPane.WARNING_MESSAGE);
			} else {
				zmienPanel(test);
				test.przygotujTest(widok.pobierzIdGrupa());
				
				mnWidok.addMouseListener(listenerWidok);
				mnWidok.setEnabled(true);
				mnTest.removeMouseListener(listenerTest);
				mnTest.setEnabled(false);
				
				log.debug("Zmiana panelu na 'test'.");
			}
		}
	}
}
