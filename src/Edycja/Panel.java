package Edycja;

import java.awt.Dimension;

import javax.swing.JPanel;

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
	 * Konstruktor klasy abstrakcyjne Panel.
	 */
	public Panel() {
		setPreferredSize(new Dimension(PANEL_SZEROKOSC, PANEL_WYSOKOSC));
		setLayout(null);
	}

}
