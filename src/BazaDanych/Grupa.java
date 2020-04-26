/**
 * 
 */
package BazaDanych;

/**
 * @author MikiWojak (Miko³aj ¯arnowski)
 * Rekord pobrany z bazy danych z tabeli 'grupy'
 */
public class Grupa {
	/**
	 * ID grupy
	 */
	private int id_grupa;
	/**
	 * Nazwa grupy
	 */
	private String nazwa_grupa;
	/**
	 * Opis grupy
	 */
	private String opis_grupa;
	
	/**
	 * Pusty konstruktor - domyœlny
	 */
	public Grupa() {}
	
	/**
	 * Konstruktor z parametrami - danymi dot. rekordu z bazy danych 
	 */
	public Grupa(
			int id_grupa,
			String nazwa_grupa,
			String opis_grupa) {
		
	}
}
