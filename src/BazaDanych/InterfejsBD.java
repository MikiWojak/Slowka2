package BazaDanych;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	 * Otwiera po³¹czenie z baz¹ danych.
	 * Tworzy tabele, jeœli to konieczne.
	 */
	public InterfejsBD() {
		//Inicjacja pól
		log = LoggerFactory.getLogger(InterfejsBD.class); 
		
		//Wczytanie sterownika bazy danych
		try {
			Class.forName(InterfejsBD.DRIVER);
			log.debug("Wczytano sterownik JDBC.");
		} catch (ClassNotFoundException e) {
			log.debug("ERROR! Brak sterownika JDBC!");
			System.err.println("ERROR! Brak sterownika JDBC!");
			e.printStackTrace();
		}
		
		//Otwarcie po³¹czenia z baz¹ danych
		try {
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
			log.debug("Otwarto po³¹czenie.");
		} catch (SQLException e) {
			log.debug("ERROR! Problem z otwarciem po³¹czenia.");
			System.err.println("ERROR! Problem z otwarciem po³¹czenia.");
			e.printStackTrace();
		}
		
		//Utworzenie tabel.
		utworzTabele();
	}
	
	/**
	 * Utworzenie tabel, jeœli te nie istniej¹.
	 * @return status utworzenia tabel 
	 */
	private boolean utworzTabele() {
		//Zapytania do tworzenia tabel
		String utworzGrupy = ""
				+ "CREATE TABLE IF NOT EXISTS grupy("
				+ "id_grupa INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nazwa_grupa VARCHAR(255),"
				+ "opis_grupa TEXT)";
		String utworzSlowa = ""
				+ "CREATE TABLE IF NOT EXISTS slowa("
				+ "id_slowo INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "id_grupa INT,"
				+ "slowo VARCHAR(255),"
				+ "tlumaczenie VARCHAR(255),"
				+ "czesc_mowy VARCHAR(255),"
				+ "czy_zapamietane TINYINT(1))";
		
		//Wykonanie zapytañ
		try {
			statement.execute(utworzGrupy);
			log.debug("Utworzono tabelê 'grupy', jeœli nie istnieje.");
			statement.execute(utworzSlowa);
			log.debug("Utworzono tabelê 'slowa', jeœli nie istnieje.");
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy tworzeniu tabel!");
			System.err.println("ERROR! B³¹d przy tworzeniu tabel!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Zamkniêcie po³¹czenia z baz¹ danych.
	 */
	public void zamknijPolaczenie() {
		try {
			connection.close();
			log.debug("Zamkniêto po³¹czenie.");
		} catch (SQLException e) {
			log.debug("ERROR! Problem z zamkniêciem po³¹czenia!");
			System.err.println("ERROR! Problem z zamkniêciem po³¹czenia!");
			e.printStackTrace();
		}
	}
}
