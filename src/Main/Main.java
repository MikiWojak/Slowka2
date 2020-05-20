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
	 * G��wna metoda. S�u�y do uruchomienia programu
	 * @param args argumenty niezb�dne do odpalenia programu
	 */
	public static void main(String[] args) {
		//logi
		Logger log = LoggerFactory.getLogger(Main.class);
		log.debug("Witaj �wiecie!");
		
		//program
		try {
			Program program = new Program();
			log.debug("Uruchomiono program");
		} catch (Exception e) {
			log.debug("ERROR! Problem z uruchomieniem programu!");
			e.printStackTrace();
		}
		
	}
}
