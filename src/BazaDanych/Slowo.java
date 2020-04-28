/**
 * Klasy zw. z baz� danych - modele, interfejs, itp.
 */
package BazaDanych;

/**
 * Rekord pobrany z bazy danych z tabeli 'slowa'
 * @author MikiWojak (Miko�aj �arnowski)
 *
 */
public class Slowo {
	/**
	 * ID s�owa.
	 * Klucz podstawowy
	 */
	private int id_slowo;
	/**
	 * ID grupy.
	 * Klucz obcy
	 */
	private int id_grupa;
	/**
	 * S�owo lub fraza w j�zyku obcym
	 */
	private String slowo;
	/**
	 * T�umaczenie s�owa lub frazy w j�zyku ojczystym 
	 */
	private String tlumaczenie;
	/**
	 * Informacja dot. cz��i mowy s�owa lub frazy
	 */
	private String czesc_mowy;
	/**
	 * Flaga - czy wyra�enie jest zapami�tane (podane prawid�owo)
	 */
	private boolean czy_zapamietane;
	
	/**
	 * Pusty konstruktor - domy�lny
	 */
	public Slowo( ) {}
	
	/**
	 * Konstruktor z parametrami - danymi dot. rekordu z bazy danych
	 * @param id_slowo ID s�owa
	 * @param id_grupa ID grupy
	 * @param slowo s�owo lub fraza
	 * @param tlumaczenie t�umaczenie s�owa lub frazy
	 * @param czesc_mowy cz�� mowy s�owa lub frazy
	 * @param czy_zapamietane czy wyra�enie jest zapami�tane
	 */
	public Slowo(
			int id_slowo,
			int id_grupa,
			String slowo,
			String tlumaczenie,
			String czesc_mowy,
			boolean czy_zapamietane) {
		this.id_slowo = id_slowo;
		this.id_grupa = id_grupa;
		this.slowo = slowo;
		this.tlumaczenie = tlumaczenie;
		this.czesc_mowy = czesc_mowy;
		this.czy_zapamietane = czy_zapamietane;
	}
	
	/*
	 * Pobranie ID s�owa z rekordu
	 * @return ID s�owa
	 */
	public int pobierzIdSlowo() { return id_slowo; }
	
	/**
	 * Pobranie ID grupy z rekordu
	 * @return ID grupy
	 */
	public int pobierzIdGrupy() { return id_grupa; }
	
	/**
	 * Pobranie s�owa lub frazy z rekordu
	 * @return s�owo lub fraza
	 */
	public String pobierzSlowo() { return slowo; }
	
	/**
	 * Pobranie t�umaczenia z rekordu
	 * @return t�umaczenie
	 */
	public String pobierzTlumaczenie() { return tlumaczenie; }
	
	/**
	 * Pobranie cz�ci mowy z rekordu
	 * @return cz�� mowy
	 */
	public String pobierzCzescMowy() { return czesc_mowy; }
	
	/**
	 * Pobranie flagi 'czy zapami�tane' z rekordu
	 * @return flaga czy zapami�tane (s�owo)
	 */
	public boolean pobierzCzyZapamietane() { return czy_zapamietane; }
	
}
