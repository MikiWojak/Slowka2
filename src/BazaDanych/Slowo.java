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
	 * Informacja dot. cz��i mowy s�owa lub wyra�enia
	 */
	private String czesc_mowy;
	/**
	 * Flaga - czy wyra�enie jest zapami�tane (podane prawid�owo)
	 */
	private boolean czy_zapamietane;
}
