package Main;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BazaDanych.Grupa;
import BazaDanych.InterfejsBD;
import BazaDanych.Slowo;

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
		Logger log = LoggerFactory.getLogger(Main.class);
		log.debug("Hello world!");
		InterfejsBD bd = new InterfejsBD();
		bd.zamknijPolaczenie();
		
	
		bd.otworzPolaczenie();
		List<Slowo>slowa = bd.pobierzSlowaWszystkie();
		bd.zamknijPolaczenie();
		System.out.println("\nGrupa ID: 1");
		for(int i = 0; i < slowa.size(); i++) {
			System.out.print(slowa.get(i));
		}
		
		bd.otworzPolaczenie();
		//bd.modyfikujSlowo(5, 1, "padded armour", "przeszywanica", "n", true);
		slowa = bd.pobierzSlowaWszystkie();
		bd.zamknijPolaczenie();
		System.out.println("\nGrupa ID: 1");
		for(int i = 0; i < slowa.size(); i++) {
			System.out.print(slowa.get(i));
		}
	}
}
