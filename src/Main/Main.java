package Main;

import BazaDanych.Grupa;

/**
 * Klasa, która uruchamia g³ówne okno programu
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Main {
	
	/**
	 * G³ówna metoda. S³u¿y do uruchomienia programu
	 * @param args argumenty niezbêdne do odpalenia programu
	 */
	public static void main(String[] args) {
		Grupa grupa1 = new Grupa(1, "Dom", "Pokoje, meble, itp.");
		System.out.println(grupa1);
		
	}
}
