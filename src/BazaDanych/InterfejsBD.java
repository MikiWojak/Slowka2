package BazaDanych;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * G��wny interfejs komunikacji z baz� danych
 * @author MikiWojak (Miko�aj �arnowski)
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
	 * Po��czenie z baz� danych.
	 */
	private Connection connection;
	/**
	 * Instrukcje dla bazy danych.
	 * Cokolwiek to znaczy.
	 */
	private Statement statement;
	/**
	 * Generowanie log�w.
	 */
	private Logger log;
	
	/**
	 * Konstruktor dla klasy InterfejsBD.
	 * Inicjuje pola.
	 * Wczytuje sterownik.
	 * Otwiera po��czenie z baz� danych.
	 * Tworzy tabele, je�li to konieczne.
	 */
	public InterfejsBD() {
		//Inicjacja p�l
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
		
		//Otwarcie po��czenia z baz� danych
		try {
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
			log.debug("Otwarto po��czenie.");
		} catch (SQLException e) {
			log.debug("ERROR! Problem z otwarciem po��czenia.");
			System.err.println("ERROR! Problem z otwarciem po��czenia.");
			e.printStackTrace();
		}
		
		//Utworzenie tabel.
		utworzTabele();
	}
	
	/**
	 * Utworzenie tabel, je�li te nie istniej�.
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
		
		//Wykonanie zapyta�
		try {
			statement.execute(utworzGrupy);
			log.debug("Utworzono tabel� 'grupy', je�li nie istnieje.");
			statement.execute(utworzSlowa);
			log.debug("Utworzono tabel� 'slowa', je�li nie istnieje.");
		} catch (SQLException e) {
			log.debug("ERROR! B��d przy tworzeniu tabel!");
			System.err.println("ERROR! B��d przy tworzeniu tabel!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Zamkni�cie po��czenia z baz� danych.
	 */
	public void zamknijPolaczenie() {
		try {
			connection.close();
			log.debug("Zamkni�to po��czenie.");
		} catch (SQLException e) {
			log.debug("ERROR! Problem z zamkni�ciem po��czenia!");
			System.err.println("ERROR! Problem z zamkni�ciem po��czenia!");
			e.printStackTrace();
		}
	}
}
