package BazaDanych;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

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
		otworzPolaczenie();
		//Utworzenie tabel.
		utworzTabele();
	}
	
	/**
	 * Otwarcie po³¹czenia z baz¹ danych
	 */
	public void otworzPolaczenie() {
		try {
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
			log.debug("Otwarto po³¹czenie.");
		} catch (SQLException e) {
			log.debug("ERROR! Problem z otwarciem po³¹czenia.");
			System.err.println("ERROR! Problem z otwarciem po³¹czenia.");
			e.printStackTrace();
		}
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
	 * Wstawienie grupy do bazy danych.
	 * @param nazwa_grupa nazwa grupy
	 * @param opis_grupa opis grupy
	 * @return status wstawienia grupy do bazy danych
	 */
	public boolean dodajGrupe(
			String nazwa_grupa, 
			String opis_grupa) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO grupy VALUES (NULL, ?, ?)");
			preparedStatement.setString(1, nazwa_grupa);
			preparedStatement.setString(2, opis_grupa);
			preparedStatement.execute();
			log.debug("Dodano rekord do tabeli 'grupy': " + nazwa_grupa);
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy dodawaniu rekordu do tabeli 'grupy'!");
			System.err.println("ERROR! B³¹d przy dodawaniu rekordu do tabeli 'grupy'!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Wstawienie s³owa lub frazy do bazy danych.
	 * @param id_grupa ID grupy, do której przypisane jest s³owo
	 * @param slowo s³owo lub fraza
	 * @param tlumaczenie t³umaczenie s³owa
	 * @param czesc_mowy czêœæ mowy s³owa
	 * @param czy_zapamietane flaga, czy dane s³owo zosta³o zapamiêtane
	 * @return status wstawienia s³owa do bazy danych
	 */
	public boolean dodajSlowo(
			int id_grupa,
			String slowo,
			String tlumaczenie,
			String czesc_mowy,
			boolean czy_zapamietane) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO slowa VALUES (NULL, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, id_grupa);
			preparedStatement.setString(2, slowo);
			preparedStatement.setString(3, tlumaczenie);
			preparedStatement.setString(4, czesc_mowy);
			preparedStatement.setBoolean(5, czy_zapamietane);
			preparedStatement.execute();
			log.debug("Dodano rekord do tabeli 'slowa': " + slowo + "\t" + tlumaczenie);
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy dodawaniu rekordu do tabeli 'zadania'!");
			System.err.println("ERROR! B³¹d przy dodawaniu rekordu do tabeli 'zadania'!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Pobranie <b>wszystkich</b> rekordów z tabeli 'grupy'. Rekordy nie s¹ sortowane
	 * @return lista z rekordami z tabeli 'grupy'
	 */
	public List<Grupa> pobierzGrupy() {
		List<Grupa>grupy = new LinkedList<Grupa>();
		try {
			String zapytanie = "SELECT * FROM grupy";
			ResultSet wynik = statement.executeQuery(zapytanie);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
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
