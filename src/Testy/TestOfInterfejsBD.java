package Testy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import BazaDanych.Grupa;
import BazaDanych.InterfejsBD;
import BazaDanych.Slowo;

/**
 * Pierwszy test jednostkowy klasy InterfejsBD.
 * Nowa baza danych.
 * Testy jednostkowe g³ównego interfejsu bazy danych.
 * <b>Przed wykonaniem usun¹æ plik bazy danych z folderu g³ównego!</b>
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
class TestOfInterfejsBD {
	/**
	 * Obiekt klasy InterfejsBD do testów.
	 */
	private InterfejsBD interfejs = new InterfejsBD();
	/**
	 * Lista grup jako wyniki do testów.
	 */
	private List<Grupa>grupy = new LinkedList<Grupa>();
	/**
	 * Lista slow jako wyniki do testów.
	 */
	private List<Slowo>slowa = new LinkedList<Slowo>();

	/**
	 * Test metody {@link BazaDanych.InterfejsBD#dodajGrupe(java.lang.String, java.lang.String)}.
	 * Test dodawania grup do bazy danych.
	 */
	@Test
	void testDodajGrupe() {
		//czyszczenie BD
		interfejs.wyczyscTabele();
		//dodanie grup
		interfejs.dodajGrupe("Komputer", "Podzespo³y komputerowe");
		interfejs.dodajGrupe("Broñ bia³a", "Sieczna, obuchowa i drzewcowa");
		interfejs.dodajGrupe("Zbroje", "Lekkie i ciê¿kie");
		int oczekiwane = 3;
		
		//int wynik = 
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#dodajSlowo(int, java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	void testDodajSlowo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#pobierzGrupyWszystkie()}.
	 */
	@Test
	void testPobierzGrupyWszystkie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#pobierzGrupyWszystkieAlfabetycznieRosnaco()}.
	 */
	@Test
	void testPobierzGrupyWszystkieAlfabetycznieRosnaco() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#pobierzSlowaWszystkie()}.
	 */
	@Test
	void testPobierzSlowaWszystkie() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#pobierzSlowaZGrupy(int)}.
	 */
	@Test
	void testPobierzSlowaZGrupy() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#modyfikujGrupe(int, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testModyfikujGrupe() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#modyfikujSlowo(int, int, java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	void testModyfikujSlowo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#usunGrupe(int)}.
	 */
	@Test
	void testUsunGrupe() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#usunSlowo(int)}.
	 */
	@Test
	void testUsunSlowo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#wyczyscTabele()}.
	 */
	@Test
	void testWyczyscTabele() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BazaDanych.InterfejsBD#zamknijPolaczenie()}.
	 */
	@Test
	void testZamknijPolaczenie() {
		fail("Not yet implemented");
	}

}
