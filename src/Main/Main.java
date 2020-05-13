package Main;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BazaDanych.Grupa;
import BazaDanych.InterfejsBD;
import BazaDanych.Slowo;
import Program.Program;

/**
 * Klasa, kt�ra uruchamia g��wne okno programu
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Main {
	
	/**
	 * Generowanie log�w.
	 */
	private static Logger log;
	/**
	 * G��wne okno programu.
	 */
	private static Program program;
	
	/**
	 * G��wna metoda. S�u�y do uruchomienia programu
	 * @param args argumenty niezb�dne do odpalenia programu
	 */
	public static void main(String[] args) {
		log = LoggerFactory.getLogger(Main.class);
		
		//Uruchomienie programu
		try {
			program = new Program();
			log.debug("Uruchomiono program");
		} catch (Exception e) {
			log.debug("ERROR! B��d podczas uruchamiania programu!");
			e.printStackTrace();
		}
		
	}
}
