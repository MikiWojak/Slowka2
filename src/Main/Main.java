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
		List<Grupa>grupy = bd.pobierzGrupyWszystkie();
		bd.zamknijPolaczenie();
		for(int i = 0; i < grupy.size(); i++) {
			System.out.print(grupy.get(i));
		}
		
		/*
		bd.otworzPolaczenie();
		//bd.modyfikujGrupa(2, "Mi�o��", "S�owa zw. z �yciem uczuciowym, podrywem, itp.");
		//grupy = bd.pobierzGrupyWszystkie();
		bd.zamknijPolaczenie();
		for(int i = 0; i < grupy.size(); i++) {
			System.out.print(grupy.get(i));
		}
		*/
	}
}
