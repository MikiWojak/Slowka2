package Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BazaDanych.Grupa;
import BazaDanych.InterfejsBD;
import BazaDanych.Slowo;

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
		Logger log = LoggerFactory.getLogger(Main.class);
		log.debug("Hello world!");
		InterfejsBD bd = new InterfejsBD();
		bd.zamknijPolaczenie();
		
		bd.otworzPolaczenie();
		bd.dodajGrupe("Dom", "");
		bd.zamknijPolaczenie();
		
		bd.otworzPolaczenie();
		bd.dodajSlowo(1, "house", "dom", "n", false);
		bd.zamknijPolaczenie();
	}
}
