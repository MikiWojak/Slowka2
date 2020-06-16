package Edycja;

import java.awt.Dimension;


import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BazaDanych.InterfejsBD;

/**
 * G³ówny panel okienka. Zawiera podstawowe informacje i z niego bêd¹ dziedziczyæ panele odpowiedzialne za funkcje.
 * Klasa abstrakcyjna - s³u¿y tylko jako podstawa dla innych paneli.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public abstract class Panel extends JPanel {
	
	/**
	 * Sta³a - szerokoœæ panelu.
	 */
	protected final int PANEL_SZEROKOSC = 800;
	/**
	 * Sta³a - wysokoœæ panelu.
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
	 * Konstruktor klasy abstrakcyjne Panel.
	 */
	public Panel() {
		setPreferredSize(new Dimension(PANEL_SZEROKOSC, PANEL_WYSOKOSC));
		setLayout(null);
		inicjujPola();
	}
	
	/**
	 * Inicjowanie pól w klasie.
	 */
	private void inicjujPola() {
		interfejsBD = new InterfejsBD();
		interfejsBD.zamknijPolaczenie();
		log = LoggerFactory.getLogger(Panel.class);
	}
	
	/**
	 * Sprawdza, czy podane pole ma zawartoœæ.
	 * @param zawartoscPola zawartoœæ pola do sprawdzenia
	 * @return true - pole ma zawartoœæ; false - pole jest puste
	 */
	protected boolean czyPolePelne(String zawartoscPola) {
		if(zawartoscPola.equals("")) { return false; }
		return true;
	}

}
