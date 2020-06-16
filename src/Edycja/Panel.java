package Edycja;

import java.awt.Dimension;


import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BazaDanych.InterfejsBD;

/**
 * G��wny panel okienka. Zawiera podstawowe informacje i z niego b�d� dziedziczy� panele odpowiedzialne za funkcje.
 * Klasa abstrakcyjna - s�u�y tylko jako podstawa dla innych paneli.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public abstract class Panel extends JPanel {
	
	/**
	 * Sta�a - szeroko�� panelu.
	 */
	protected final int PANEL_SZEROKOSC = 800;
	/**
	 * Sta�a - wysoko�� panelu.
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
	 * Konstruktor klasy abstrakcyjne Panel.
	 */
	public Panel() {
		setPreferredSize(new Dimension(PANEL_SZEROKOSC, PANEL_WYSOKOSC));
		setLayout(null);
		inicjujPola();
	}
	
	/**
	 * Inicjowanie p�l w klasie.
	 */
	private void inicjujPola() {
		interfejsBD = new InterfejsBD();
		interfejsBD.zamknijPolaczenie();
		log = LoggerFactory.getLogger(Panel.class);
	}
	
	/**
	 * Sprawdza, czy podane pole ma zawarto��.
	 * @param zawartoscPola zawarto�� pola do sprawdzenia
	 * @return true - pole ma zawarto��; false - pole jest puste
	 */
	protected boolean czyPolePelne(String zawartoscPola) {
		if(zawartoscPola.equals("")) { return false; }
		return true;
	}

}
