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
	 * Grupa, do której jest przypisane s³owo.
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
	
	/**
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
	
	/**
	 * Ustawienie lub modyfikacja ID s³owa
	 * @param id_slowo ID s³owa
	 */
	public void ustawIdSlowa(int id_slowo) { this.id_slowo = id_slowo; }
	
	/**
	 * Ustawienie lub modyfikacja ID grupy
	 * @param id_grupa ID grupy
	 */
	public void ustawIdGrupy(int id_grupa) { this.id_grupa = id_grupa; }
	
	/**
	 * Ustawienie lub modyfikacja s³owa lub frazy
	 * @param slowo s³owo lub fraza
	 */
	public void ustawSlowo(String slowo) { this.slowo = slowo; }
	
	/**
	 * Ustawienie lub modyfikacja t³umaczenia s³owa lub frazy
	 * @param tlumaczenie t³umaczenie
	 */
	public void ustawTlumaczenie(String tlumaczenie) { this.tlumaczenie = tlumaczenie; }
	
	/**
	 * Ustawienie lub modyfikacja czêœci mowy s³owa lub frazy
	 * @param czesc_mowy czêœæ mowy
	 */
	public void ustawCzescMowy(String czesc_mowy) { this.czesc_mowy = czesc_mowy; }
	
	/**
	 * Ustawienie lub modyfikacja flagi 'czy zapamiêtane' (s³owo)
	 * @param czy_zapamietane flaga 'czy zapamiêtane' (s³owo)
	 */
	public void ustawCzyZapamietane(boolean czy_zapamietane) { this.czy_zapamietane = czy_zapamietane; }
	
	/**
	 * Wyœwietlenie wszystkich danych rekordu z tabeli 'slowa'
	 * @return napis z³o¿ony z: ID s³owa, ID grupy, s³owa, t³umaczenia, czêœci mowy i flagi 'czy zapamiêtane'
	 */
	@Override
	public String toString() {
		return "[" + id_slowo + "]\t"
				+ id_grupa + "\t" 
				+ slowo + "\t" 
				+ tlumaczenie + "\t" 
				+ czesc_mowy + "\t" 
				+ czy_zapamietane + "\n";
	}
	
}
