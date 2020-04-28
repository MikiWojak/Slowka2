/**
 * Klasy zw. z baz¹ danych - modele, interfejs, itp.
 */
package BazaDanych;

/**
 * Rekord pobrany z bazy danych z tabeli 'grupy'
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Grupa {
	/**
	 * ID grupy.
	 * Klucz podstawowy
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
	 * @param id_grupa ID grupy
	 * @param nazwa_grupa nazwa grupy
	 * @param opis_grupa opis grupy
	 */
	public Grupa(
			int id_grupa,
			String nazwa_grupa,
			String opis_grupa) {
		this.id_grupa = id_grupa;
		this.nazwa_grupa = nazwa_grupa;
		this.opis_grupa = opis_grupa;
	}
	
	/**
	 * Pobranie ID grupy z rekordu
	 * @return ID grupy
	 */
	public int pobierzIdGrupa() { return id_grupa; }
	
	/**
	 * Pobranie nazwy grupy z rekordu
	 * @return nazwa grupy
	 */
	public String pobierzNazwaGrupy() { return nazwa_grupa; }
	
	/**
	 * Pobranie opisu grupy z rekordu
	 * @return opis grupy
	 */
	public String pobierzOpisGrupy() { return opis_grupa; }
	
	/**
	 * Ustawienie lub modyfikacja ID grupy
	 * @param id_grupa ID grupy
	 */
	public void ustawIdGrupy(int id_grupa) { this.id_grupa = id_grupa; }
	
	/**
	 * Ustawienie lub modyfikacja nazwy grupy
	 * @param nazwa_grupa nazwa grupy
	 */
	public void ustawNazweGrupy(String nazwa_grupa) { this.nazwa_grupa = nazwa_grupa; }
	
	/**
	 * Ustawienie lub modyfikacja opisu grupy
	 * @param opis_grupa opis grupy
	 */
	public void ustawOpisGrupy(String opis_grupa) { this.opis_grupa = opis_grupa; }
	
	/**
	 * Wyœwietlenie wszystkich danych z rekordu z tabeli 'grupy'
	 * @return napis z³o¿ony z: ID grupy, nazwy grupy i opisu grupy
	 */
	@Override
	public String toString() {
		return "[" + id_grupa + "]\t" + nazwa_grupa + "\t" + opis_grupa + "\n";
	}
}
