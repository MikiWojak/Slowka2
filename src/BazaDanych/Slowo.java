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
	 * Grupa, do kt�rej jest przypisane s�owo.
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
	
	/**
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
	
	/**
	 * Ustawienie lub modyfikacja ID s�owa
	 * @param id_slowo ID s�owa
	 */
	public void ustawIdSlowa(int id_slowo) { this.id_slowo = id_slowo; }
	
	/**
	 * Ustawienie lub modyfikacja ID grupy
	 * @param id_grupa ID grupy
	 */
	public void ustawIdGrupy(int id_grupa) { this.id_grupa = id_grupa; }
	
	/**
	 * Ustawienie lub modyfikacja s�owa lub frazy
	 * @param slowo s�owo lub fraza
	 */
	public void ustawSlowo(String slowo) { this.slowo = slowo; }
	
	/**
	 * Ustawienie lub modyfikacja t�umaczenia s�owa lub frazy
	 * @param tlumaczenie t�umaczenie
	 */
	public void ustawTlumaczenie(String tlumaczenie) { this.tlumaczenie = tlumaczenie; }
	
	/**
	 * Ustawienie lub modyfikacja cz�ci mowy s�owa lub frazy
	 * @param czesc_mowy cz�� mowy
	 */
	public void ustawCzescMowy(String czesc_mowy) { this.czesc_mowy = czesc_mowy; }
	
	/**
	 * Ustawienie lub modyfikacja flagi 'czy zapami�tane' (s�owo)
	 * @param czy_zapamietane flaga 'czy zapami�tane' (s�owo)
	 */
	public void ustawCzyZapamietane(boolean czy_zapamietane) { this.czy_zapamietane = czy_zapamietane; }
	
	/**
	 * Wy�wietlenie wszystkich danych rekordu z tabeli 'slowa'
	 * @return napis z�o�ony z: ID s�owa, ID grupy, s�owa, t�umaczenia, cz�ci mowy i flagi 'czy zapami�tane'
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
