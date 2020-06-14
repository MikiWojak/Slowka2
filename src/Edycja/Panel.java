package Edycja;

import java.awt.Dimension;

import javax.swing.JPanel;

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
	 * Konstruktor klasy abstrakcyjne Panel.
	 */
	public Panel() {
		setPreferredSize(new Dimension(PANEL_SZEROKOSC, PANEL_WYSOKOSC));
		setLayout(null);
	}

}
