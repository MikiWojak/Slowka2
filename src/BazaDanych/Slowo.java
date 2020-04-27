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
	 * Informacja dot. czêœæi mowy s³owa lub wyra¿enia
	 */
	private String czesc_mowy;
	/**
	 * Flaga - czy wyra¿enie jest zapamiêtane (podane prawid³owo)
	 */
	private boolean czy_zapamietane;
}
