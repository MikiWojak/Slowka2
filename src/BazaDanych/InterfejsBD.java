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
	 * Struktura tabel w bazie danych.
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
	 * Baza do pobierania danych z tabeli 'grupy'.
	 * @param zapytanie treœæ zapytania do pobrania rekordów z tabeli 'grupy'
	 * @return lista z rekordami z tabeli 'grupy'
	 */
	private List<Grupa> pobierzGrupyBaza(String zapytanie) {
		List<Grupa>grupy = new LinkedList<Grupa>();
		try {
			ResultSet wynik = statement.executeQuery(zapytanie);
			int id_grupa;
			String nazwa_grupa;
			String opis_grupa;
			
			while(wynik.next()) {
				id_grupa = wynik.getInt("id_grupa");
				nazwa_grupa = wynik.getString("nazwa_grupa");
				opis_grupa = wynik.getString("opis_grupa");
				grupy.add(new Grupa(id_grupa, nazwa_grupa, opis_grupa));
			}
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d podczas pobierania danych z tabeli 'grupy'!");
			e.printStackTrace();
			return null;
		}
		log.debug("Pobrano dane z tabeli 'grupy' zgodnie z zapytaniem.");
		return grupy;
	}
	
	/**
	 * Pobranie <b>wszystkich</b> rekordów z tabeli 'grupy'.
	 * Rekordy nie s¹ sortowane.
	 * @return lista z rekordami z tabeli 'grupy'
	 */
	public List<Grupa> pobierzGrupyWszystkie() {
		String zapytanie = "SELECT * FROM grupy";
		List<Grupa>grupy = pobierzGrupyBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'grupy' - wszystkie grupy, niesortowane.");
		return grupy;
	}
	
	/**
	 * Pobranie <b>wszystkich</b> rekordów z tabeli 'grupy'.
	 * Rekordy s¹ posortowane alfabetycznie rosn¹co.
	 * @return lista z rekordami z tabeli 'grupy'
	 */
	public List<Grupa> pobierzGrupyWszystkieAlfabetycznieRosnaco() {
		String zapytanie = "SELECT * FROM grupy ORDER BY nazwa_grupa";
		List<Grupa>grupy = pobierzGrupyBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'grupy' - wszystkie grupy, posortowane alfabetycznie rosn¹co.");
		return grupy;
	}
	
	/**
	 * Pobranie <b>jednego</b> rekordu z tabeli 'grupy'.
	 * @param id_grupa ID grupy
	 * @return lista z jednym rekordem z tabeli 'grupy'
	 */
	public List<Grupa> pobierzGrupe(int id_grupa) {
		String zapytanie = "SELECT * FROM grupy WHERE id_grupa = " + id_grupa;
		List<Grupa> grupy = pobierzGrupyBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'grupy' - jedna grupa o ID: " + id_grupa);
		return grupy;
	}
	
	/**
	 * Baza do pobierania rekordów z tabeli 's³owa'.
	 * @param zapytanie treœæ zapytania do pobrania rekordów z tabeli 's³owa'
	 * @return lista z rekordami z tabeli 's³owa'
	 */
	private List<Slowo> pobierzSlowaBaza(String zapytanie) {
		List<Slowo>slowa = new LinkedList<Slowo>();
		try {
			ResultSet wynik = statement.executeQuery(zapytanie);
			int id_slowo;
			int id_grupa;
			String slowo;
			String tlumaczenie;
			String czesc_mowy;
			boolean czy_zapamietane;
			while(wynik.next()) {
				id_slowo = wynik.getInt("id_slowo");
				id_grupa = wynik.getInt("id_grupa");
				slowo = wynik.getString("slowo");
				tlumaczenie = wynik.getString("tlumaczenie");
				czesc_mowy = wynik.getString("czesc_mowy");
				czy_zapamietane = wynik.getBoolean("czy_zapamietane");
				slowa.add(new Slowo(id_slowo, id_grupa, slowo, tlumaczenie, czesc_mowy, czy_zapamietane));
			}
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d podczas pobierania danych z tabeli 'slowa'!");
			e.printStackTrace();
			return null;
		}
		log.debug("Pobrano dane z tabeli 'slowa' zgodnie z zapytaniem.");
		return slowa;
	}
	
	/**
	 * Pobranie <b>wszystkich</b> rekordów z tabeli 's³owa'.
	 * Rekordy nie s¹ sortowane.
	 * @return lista z rekordami z tabeli 's³owa'
	 */
	public List<Slowo> pobierzSlowaWszystkie() {
		String zapytanie = "SELECT * FROM slowa";
		List<Slowo>slowa = pobierzSlowaBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'slowa' - wszystkie s³owa, niesortowane.");
		return slowa;
	}
	
	/**
	 * Pobranie rekordów z tabeli s³owa nale¿¹cych do <b>okreœlonej grupy</b>.
	 * Rekordy nie s¹ sortowane.
	 * @param id_grupa ID grupy, do której nale¿¹ pobrane rekordy
	 * @return lista z rekordami z tabeli 's³owa'
	 */
	public List<Slowo> pobierzSlowaZGrupy(int id_grupa) {
		String zapytanie = "SELECT * FROM slowa WHERE id_grupa = " + id_grupa;
		List<Slowo>slowa = pobierzSlowaBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'slowa' - s³owa nale¿¹ce do grupy o ID: " + id_grupa + ", niesortowane.");
		return slowa;
	}
	
	/**
	 * Pobranie <b>jednego</b> rekordu z tabeli 'slowa'.
	 * @param id_slowo ID s³owa
	 * @return lista z jednym rekordem z tabeli 'slowa'
	 */
	public List<Slowo> pobierzSlowo(int id_slowo) {
		String zapytanie = "SELECT * FROM slowa WHERE id_slowo = " + id_slowo;
		List<Slowo>slowa = pobierzSlowaBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'slowa' - jedno slowo o ID: " + id_slowo);
		return slowa;
	}
	
	/**
	 * Modyfikacja rekordu nale¿¹cego do tabeli 'grupy' 
	 * @param id_grupa ID grupy do zmodyfikowania
	 * @param nazwa_grupa nowa lub taka sama nazwa grupy
	 * @param opis_grupa nowy lub taki sam opis grupy
	 * @return status modyfikacji grupy
	 */
	public boolean modyfikujGrupe(
			int id_grupa,
			String nazwa_grupa,
			String opis_grupa) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(""
					+ "UPDATE grupy SET "
					+ "nazwa_grupa = ?, "
					+ "opis_grupa = ? "
					+ "WHERE id_grupa = ?");
			preparedStatement.setString(1, nazwa_grupa);
			preparedStatement.setString(2, opis_grupa);
			preparedStatement.setInt(3, id_grupa);
			preparedStatement.execute();
			log.debug("Zmodyfikowano grupê o ID: " + id_grupa);
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy modyfikacji grupy o ID: " + id_grupa + "!");
			System.err.println("ERROR! B³¹d przy modyfikacji grupy!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Modyfikacja rekordu nale¿¹cego do tabeli 'slowa'
	 * @param id_slowo ID s³owa do modyfikacji
	 * @param id_grupa nowe lub takie samo ID grupy
	 * @param slowo nowe lub takie samo s³owo
	 * @param tlumaczenie nowe lub takie samo t³umaczenie
	 * @param czesc_mowy nowa lub taka sama czêœæ mowy
	 * @param czy_zapamietane zmieniona lub taka sama flaga 'czy zapamiêtane'
	 * @return status modyfikacji s³owa
	 */
	public boolean modyfikujSlowo(
			int id_slowo,
			int id_grupa,
			String slowo,
			String tlumaczenie,
			String czesc_mowy,
			boolean czy_zapamietane) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(""
					+ "UPDATE slowa SET "
					+ "id_grupa = ?, "
					+ "slowo = ?, "
					+ "tlumaczenie = ?, "
					+ "czesc_mowy = ?, "
					+ "czy_zapamietane = ? "
					+ "WHERE id_slowo = ?");
			preparedStatement.setInt(1, id_grupa);
			preparedStatement.setString(2, slowo);
			preparedStatement.setString(3, tlumaczenie);
			preparedStatement.setString(4, czesc_mowy);
			preparedStatement.setBoolean(5, czy_zapamietane);
			preparedStatement.setInt(6, id_slowo);
			preparedStatement.execute();
			log.debug("Zmodyfikowano s³owo o ID: " + id_slowo);
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy modyfikacji s³owa o ID: " + id_slowo + "!");
			System.err.println("ERROR! B³¹d przy modyfikacji s³owa!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Usuniêcie grupy oraz powi¹zanych z ni¹ s³ów.
	 * @param id_grupa ID grupy do usuniêcia
	 * @return status usuniêcia grupy
	 */
	public boolean usunGrupe(int id_grupa) {
		try {
			PreparedStatement preparedStatement;
			//usuniêcie s³ów powi¹zanych z grup¹
			preparedStatement = connection.prepareStatement(""
					+ "DELETE FROM slowa WHERE id_grupa = ?");
			preparedStatement.setInt(1, id_grupa);
			preparedStatement.execute();
			log.debug("Usuniêto s³owa nale¿¹ce do grupy o ID: " + id_grupa);
			
			//usuniêcie grupy
			preparedStatement = connection.prepareStatement(""
					+ "DELETE FROM grupy WHERE id_grupa = ?");
			preparedStatement.setInt(1, id_grupa);
			preparedStatement.execute();
			log.debug("Usuniêto grupê o ID: " + id_grupa);
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy próbie usuniêcia grupy o ID: " + id_grupa + " oraz powi¹zanych s³ów!");
			System.err.println("ERROR! B³¹d przy próbie usuniêcia grupy oraz powi¹zanych s³ów!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Usuniêcie s³owa lub frazy
	 * @param id_slowo ID s³owa do usuniêcia
	 * @return status usuniêcia s³owa
	 */
	public boolean usunSlowo(int id_slowo) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(""
					+ "DELETE FROM slowa WHERE id_slowo = ?");
			preparedStatement.setInt(1, id_slowo);
			preparedStatement.execute();
			log.debug("Usuniêto s³owo o ID: " + id_slowo);
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy próbie usuniêcia s³owa o ID: " + id_slowo + "!");
			System.err.println("ERROR! B³¹d przy próbie usuniêcia s³owa!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Wyczyszczenie tabel z <b>ca³ej zawartoœci</b>.
	 * G³ównie do testów.
	 * @return status wyczyszczenia tabel.
	 */
	public boolean wyczyscTabele() {
		try {
			//grupy
			statement.execute("DELETE FROM grupy");
			log.debug("Wyczyszczono tabelê 'grupy'.");
			//slowa
			statement.execute("DELETE FROM slowa");
			log.debug("Wyczyszczono tabelê 'slowa'.");
		} catch (SQLException e) {
			log.debug("ERROR! B³¹d przy czyszczeniu tabel!");
			System.err.println("ERROR! B³¹d przy czyszczeniu tabel!");
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
