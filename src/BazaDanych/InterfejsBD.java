package BazaDanych;

import java.sql.Connection;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * G³ówny interfejs komunikacji z baz¹ danych
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class InterfejsBD {
	/**
	 * Sterownik bazy danych
	 */
	public static final String DRIVER = "org.sqlite.JDBC";
	/**
	 * Lokalizacja pliku bazy danych.
	 * W tym samym folderze, co program.
	 */
	public static final String DB_URL = "jdbc:sqlite:slowka.db";
	
	/**
	 * Po³¹czenie z baz¹ danych.
	 */
	private Connection connection;
	/**
	 * Instrukcje dla bazy danych.
	 * Cokolwiek to znaczy.
	 */
	private Statement statement;
	/**
	 * Generowanie logów.
	 */
	private Logger log;
	
	/**
	 * Konstruktor dla klasy InterfejsBD.
	 * Inicjuje pola.
	 * Wczytuje sterownik.
	 * Nawi¹zuje po³¹czenie z baz¹ danych.
	 * Tworzy tabele, jeœli to konieczne.
	 */
	public InterfejsBD() {
		/**
		 * Inicjacja pól
		 */
		log = LoggerFactory.getLogger(InterfejsBD.class); 
		/**
		 * Wczytanie sterownika bazy danych
		 */
		try {
			Class.forName(InterfejsBD.DRIVER);
			log.debug("Wczytano sterownik.");
		} catch (ClassNotFoundException e) {
			log.debug("ERROR! Brak sterownika JDBC!");
			System.err.println("ERROR! Brak sterownika JDBC!");
			e.printStackTrace();
		}
	}
}
