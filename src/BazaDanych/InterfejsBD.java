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
		otworzPolaczenie();
		//Utworzenie tabel.
		utworzTabele();
	}
	
	/**
	 * Otwarcie po��czenia z baz� danych
	 */
	public void otworzPolaczenie() {
		try {
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
			log.debug("Otwarto po��czenie.");
		} catch (SQLException e) {
			log.debug("ERROR! Problem z otwarciem po��czenia.");
			System.err.println("ERROR! Problem z otwarciem po��czenia.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Utworzenie tabel, je�li te nie istniej�.
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
			log.debug("ERROR! B��d przy dodawaniu rekordu do tabeli 'grupy'!");
			System.err.println("ERROR! B��d przy dodawaniu rekordu do tabeli 'grupy'!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Wstawienie s�owa lub frazy do bazy danych.
	 * @param id_grupa ID grupy, do kt�rej przypisane jest s�owo
	 * @param slowo s�owo lub fraza
	 * @param tlumaczenie t�umaczenie s�owa
	 * @param czesc_mowy cz�� mowy s�owa
	 * @param czy_zapamietane flaga, czy dane s�owo zosta�o zapami�tane
	 * @return status wstawienia s�owa do bazy danych
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
			log.debug("ERROR! B��d przy dodawaniu rekordu do tabeli 'zadania'!");
			System.err.println("ERROR! B��d przy dodawaniu rekordu do tabeli 'zadania'!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Baza do pobierania danych z tabeli 'grupy'.
	 * @param zapytanie tre�� zapytania do pobrania rekord�w z tabeli 'grupy'
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
			log.debug("ERROR! B��d podczas pobierania danych z tabeli 'grupy'!");
			e.printStackTrace();
			return null;
		}
		log.debug("Pobrano dane z tabeli 'grupy' zgodnie z zapytaniem.");
		return grupy;
	}
	
	/**
	 * Pobranie <b>wszystkich</b> rekord�w z tabeli 'grupy'.
	 * Rekordy nie s� sortowane.
	 * @return lista z rekordami z tabeli 'grupy'
	 */
	public List<Grupa> pobierzGrupyWszystkie() {
		String zapytanie = "SELECT * FROM grupy";
		List<Grupa>grupy = pobierzGrupyBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'grupy' - wszystkie grupy, niesortowane.");
		return grupy;
	}
	
	/**
	 * Pobranie <b>wszystkich</b> rekord�w z tabeli 'grupy'.
	 * Rekordy s� posortowane alfabetycznie rosn�co.
	 * @return lista z rekordami z tabeli 'grupy'
	 */
	public List<Grupa> pobierzGrupyWszystkieAlfabetycznieRosnaco() {
		String zapytanie = "SELECT * FROM grupy ORDER BY nazwa_grupa";
		List<Grupa>grupy = pobierzGrupyBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'grupy' - wszystkie grupy, posortowane alfabetycznie rosn�co.");
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
	 * Baza do pobierania rekord�w z tabeli 's�owa'.
	 * @param zapytanie tre�� zapytania do pobrania rekord�w z tabeli 's�owa'
	 * @return lista z rekordami z tabeli 's�owa'
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
			log.debug("ERROR! B��d podczas pobierania danych z tabeli 'slowa'!");
			e.printStackTrace();
			return null;
		}
		log.debug("Pobrano dane z tabeli 'slowa' zgodnie z zapytaniem.");
		return slowa;
	}
	
	/**
	 * Pobranie <b>wszystkich</b> rekord�w z tabeli 's�owa'.
	 * Rekordy nie s� sortowane.
	 * @return lista z rekordami z tabeli 's�owa'
	 */
	public List<Slowo> pobierzSlowaWszystkie() {
		String zapytanie = "SELECT * FROM slowa";
		List<Slowo>slowa = pobierzSlowaBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'slowa' - wszystkie s�owa, niesortowane.");
		return slowa;
	}
	
	/**
	 * Pobranie rekord�w z tabeli s�owa nale��cych do <b>okre�lonej grupy</b>.
	 * Rekordy nie s� sortowane.
	 * @param id_grupa ID grupy, do kt�rej nale�� pobrane rekordy
	 * @return lista z rekordami z tabeli 's�owa'
	 */
	public List<Slowo> pobierzSlowaZGrupy(int id_grupa) {
		String zapytanie = "SELECT * FROM slowa WHERE id_grupa = " + id_grupa;
		List<Slowo>slowa = pobierzSlowaBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'slowa' - s�owa nale��ce do grupy o ID: " + id_grupa + ", niesortowane.");
		return slowa;
	}
	
	/**
	 * Pobranie <b>jednego</b> rekordu z tabeli 'slowa'.
	 * @param id_slowo ID s�owa
	 * @return lista z jednym rekordem z tabeli 'slowa'
	 */
	public List<Slowo> pobierzSlowo(int id_slowo) {
		String zapytanie = "SELECT * FROM slowa WHERE id_slowo = " + id_slowo;
		List<Slowo>slowa = pobierzSlowaBaza(zapytanie);
		log.debug("Pobrano dane z tabeli 'slowa' - jedno slowo o ID: " + id_slowo);
		return slowa;
	}
	
	/**
	 * Modyfikacja rekordu nale��cego do tabeli 'grupy' 
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
			log.debug("Zmodyfikowano grup� o ID: " + id_grupa);
		} catch (SQLException e) {
			log.debug("ERROR! B��d przy modyfikacji grupy o ID: " + id_grupa + "!");
			System.err.println("ERROR! B��d przy modyfikacji grupy!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Modyfikacja rekordu nale��cego do tabeli 'slowa'
	 * @param id_slowo ID s�owa do modyfikacji
	 * @param id_grupa nowe lub takie samo ID grupy
	 * @param slowo nowe lub takie samo s�owo
	 * @param tlumaczenie nowe lub takie samo t�umaczenie
	 * @param czesc_mowy nowa lub taka sama cz�� mowy
	 * @param czy_zapamietane zmieniona lub taka sama flaga 'czy zapami�tane'
	 * @return status modyfikacji s�owa
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
			log.debug("Zmodyfikowano s�owo o ID: " + id_slowo);
		} catch (SQLException e) {
			log.debug("ERROR! B��d przy modyfikacji s�owa o ID: " + id_slowo + "!");
			System.err.println("ERROR! B��d przy modyfikacji s�owa!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Usuni�cie grupy oraz powi�zanych z ni� s��w.
	 * @param id_grupa ID grupy do usuni�cia
	 * @return status usuni�cia grupy
	 */
	public boolean usunGrupe(int id_grupa) {
		try {
			PreparedStatement preparedStatement;
			//usuni�cie s��w powi�zanych z grup�
			preparedStatement = connection.prepareStatement(""
					+ "DELETE FROM slowa WHERE id_grupa = ?");
			preparedStatement.setInt(1, id_grupa);
			preparedStatement.execute();
			log.debug("Usuni�to s�owa nale��ce do grupy o ID: " + id_grupa);
			
			//usuni�cie grupy
			preparedStatement = connection.prepareStatement(""
					+ "DELETE FROM grupy WHERE id_grupa = ?");
			preparedStatement.setInt(1, id_grupa);
			preparedStatement.execute();
			log.debug("Usuni�to grup� o ID: " + id_grupa);
		} catch (SQLException e) {
			log.debug("ERROR! B��d przy pr�bie usuni�cia grupy o ID: " + id_grupa + " oraz powi�zanych s��w!");
			System.err.println("ERROR! B��d przy pr�bie usuni�cia grupy oraz powi�zanych s��w!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Usuni�cie s�owa lub frazy
	 * @param id_slowo ID s�owa do usuni�cia
	 * @return status usuni�cia s�owa
	 */
	public boolean usunSlowo(int id_slowo) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(""
					+ "DELETE FROM slowa WHERE id_slowo = ?");
			preparedStatement.setInt(1, id_slowo);
			preparedStatement.execute();
			log.debug("Usuni�to s�owo o ID: " + id_slowo);
		} catch (SQLException e) {
			log.debug("ERROR! B��d przy pr�bie usuni�cia s�owa o ID: " + id_slowo + "!");
			System.err.println("ERROR! B��d przy pr�bie usuni�cia s�owa!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Wyczyszczenie tabel z <b>ca�ej zawarto�ci</b>.
	 * G��wnie do test�w.
	 * @return status wyczyszczenia tabel.
	 */
	public boolean wyczyscTabele() {
		try {
			//grupy
			statement.execute("DELETE FROM grupy");
			log.debug("Wyczyszczono tabel� 'grupy'.");
			//slowa
			statement.execute("DELETE FROM slowa");
			log.debug("Wyczyszczono tabel� 'slowa'.");
		} catch (SQLException e) {
			log.debug("ERROR! B��d przy czyszczeniu tabel!");
			System.err.println("ERROR! B��d przy czyszczeniu tabel!");
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
