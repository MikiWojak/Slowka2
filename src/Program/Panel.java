package Program;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import BazaDanych.InterfejsBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * G³ówny panel programu. Zawiera podstawowe informacje i z niego bêd¹ dziedziczyæ panele odpowiedzialne za funkcje.
 * Klasa abstrakcyjna - s³u¿y tylko jako podstawa dla innych paneli.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public abstract class Panel extends JPanel {
	
	/**
	 * Szerokoœæ panelu.
	 */
	protected final int PANEL_SZEROKOSC = 1200;
	/**
	 * Wysokoœæ panelu.
	 */
	protected final int PANEL_WYSOKOSC = 600;
	/**
	 * Interfejs bazy danych.
	 */
	protected InterfejsBD interfejsBD;
	/**
	 * Generowanie logów.
	 */
	protected Logger log;
	
	/**
	 * Utworzenie panelu g³ównego.
	 * Konstruktor klasy Panel.
	 */
	protected Panel() {
		setBounds(0, 0, PANEL_SZEROKOSC, PANEL_WYSOKOSC);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		inicjujPola();
		
		log.debug("Utworzono obiekt klasy Panel.");
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//interfejsBD
		interfejsBD = new InterfejsBD();
		interfejsBD.zamknijPolaczenie();
		//log
		log = LoggerFactory.getLogger(Panel.class);
	}

}
