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
 * Klasa, która uruchamia g³ówne okno programu
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Main {
	
	/**
	 * G³ówna metoda. S³u¿y do uruchomienia programu
	 * @param args argumenty niezbêdne do odpalenia programu
	 */
	public static void main(String[] args) {
		//logi
		Logger log = LoggerFactory.getLogger(Main.class);
		log.debug("Witaj œwiecie!");
		
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
