/**
 * Klasy zw. z baz¹ danych - modele, interfejs, itp.
 */
package BazaDanych;

/**
 * Rekord pobrany z bazy danych z tabeli 'slowa'
 * @author MikiWojak (Miko³aj ¯arnowski)
 *
 */
public class Slowo {
	/**
	 * ID s³owa.
	 * Klucz podstawowy
	 */
	private int id_slowo;
	/**
	 * ID grupy.
	 * Klucz obcy
	 */
	private int id_grupa;
	/**
	 * S³owo lub fraza w jêzyku obcym
	 */
	private String slowo;
	/**
	 * T³umaczenie s³owa lub frazy w jêzyku ojczystym 
	 */
	private String tlumaczenie;
	/**
	 * Informacja dot. czêœæi mowy s³owa lub frazy
	 */
	private String czesc_mowy;
	/**
	 * Flaga - czy wyra¿enie jest zapamiêtane (podane prawid³owo)
	 */
	private boolean czy_zapamietane;
	
	/**
	 * Pusty konstruktor - domyœlny
	 */
	public Slowo( ) {}
	
	/**
	 * Konstruktor z parametrami - danymi dot. rekordu z bazy danych
	 * @param id_slowo ID s³owa
	 * @param id_grupa ID grupy
	 * @param slowo s³owo lub fraza
	 * @param tlumaczenie t³umaczenie s³owa lub frazy
	 * @param czesc_mowy czêœæ mowy s³owa lub frazy
	 * @param czy_zapamietane czy wyra¿enie jest zapamiêtane
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
	 * Pobranie ID s³owa z rekordu
	 * @return ID s³owa
	 */
	public int pobierzIdSlowo() { return id_slowo; }
	
	/**
	 * Pobranie ID grupy z rekordu
	 * @return ID grupy
	 */
	public int pobierzIdGrupy() { return id_grupa; }
	
	/**
	 * Pobranie s³owa lub frazy z rekordu
	 * @return s³owo lub fraza
	 */
	public String pobierzSlowo() { return slowo; }
	
	/**
	 * Pobranie t³umaczenia z rekordu
	 * @return t³umaczenie
	 */
	public String pobierzTlumaczenie() { return tlumaczenie; }
	
	/**
	 * Pobranie czêœci mowy z rekordu
	 * @return czêœæ mowy
	 */
	public String pobierzCzescMowy() { return czesc_mowy; }
	
	/**
	 * Pobranie flagi 'czy zapamiêtane' z rekordu
	 * @return flaga czy zapamiêtane (s³owo)
	 */
	public boolean pobierzCzyZapamietane() { return czy_zapamietane; }
	
}
