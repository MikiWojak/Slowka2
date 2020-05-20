package Program;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import BazaDanych.InterfejsBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * G��wny panel programu. Zawiera podstawowe informacje i z niego b�d� dziedziczy� panele odpowiedzialne za funkcje.
 * Klasa abstrakcyjna - s�u�y tylko jako podstawa dla innych paneli.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public abstract class Panel extends JPanel {
	
	/**
	 * Szeroko�� panelu.
	 */
	protected final int PANEL_SZEROKOSC = 1200;
	/**
	 * Wysoko�� panelu.
	 */
	protected final int PANEL_WYSOKOSC = 600;
	/**
	 * Interfejs bazy danych.
	 */
	protected InterfejsBD interfejsBD;
	/**
	 * Generowanie log�w.
	 */
	protected Logger log;
	
	/**
	 * Utworzenie panelu g��wnego.
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
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//interfejsBD
		interfejsBD = new InterfejsBD();
		interfejsBD.zamknijPolaczenie();
		//log
		log = LoggerFactory.getLogger(Panel.class);
	}

}
