package Main;

import BazaDanych.Grupa;

/**
 * Klasa, kt�ra uruchamia g��wne okno programu
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Main {
	
	/**
	 * G��wna metoda. S�u�y do uruchomienia programu
	 * @param args argumenty niezb�dne do odpalenia programu
	 */
	public static void main(String[] args) {
		Grupa grupa1 = new Grupa(1, "Dom", "Pokoje, meble, itp.");
		System.out.println(grupa1);
		
	}
}
