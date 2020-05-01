package Testy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BazaDanych.Slowo;

/**
 * Testy jednostkowe klasy Slowo
 * @author MikiWojak (Miko³aj ¯arnowski)
 *
 */
class TestOfSlowo {
	/**
	 * Obiekt klasy Slowo do testów odczytu
	 */
	private Slowo slowo1 = new Slowo(1, 2, "computer", "komputer", "n", false);
	/**
	 * Obiekt klasy Slowo do testów modyfikacji
	 */
	private Slowo slowo2 = new Slowo(3, 4, "haus", "cha³upa", "v", true);
	
	/**
	 * Test metody {@link BazaDanych.Slowo#pobierzIdSlowo()}.
	 * Test pobierania ID s³owa.
	 */
	@Test
	void testPobierzIdSlowo() {
		int oczekiwane = 1;
		int wynik = slowo1.pobierzIdSlowo();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#pobierzIdGrupy()}.
	 * Test pobierania ID grupy.
	 */
	@Test
	void testPobierzIdGrupy() {
		int oczekiwane = 2;
		int wynik = slowo1.pobierzIdGrupy();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#pobierzSlowo()}.
	 * Test pobierania s³owa.
	 */
	@Test
	void testPobierzSlowo() {
		String oczekiwane = "computer";
		String wynik = slowo1.pobierzSlowo();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#pobierzTlumaczenie()}.
	 * Test pobierania t³umaczenia.
	 */
	@Test
	void testPobierzTlumaczenie() {
		String oczekiwane = "komputer";
		String wynik = slowo1.pobierzTlumaczenie();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#pobierzCzescMowy()}.
	 * Test pobierania czêœci mowy.
	 */
	@Test
	void testPobierzCzescMowy() {
		String oczekiwane = "n";
		String wynik = slowo1.pobierzCzescMowy();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#pobierzCzyZapamietane()}.
	 * Test pobierania flagi 'czy zapamiêtane'
	 */
	@Test
	void testPobierzCzyZapamietane() {
		boolean oczekiwane = false;
		boolean wynik = slowo1.pobierzCzyZapamietane();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#ustawIdSlowa(int)}.
	 * Test ustawiania ID s³owa.
	 */
	@Test
	void testUstawIdSlowa() {
		int oczekiwane = 5;
		slowo2.ustawIdSlowa(oczekiwane);
		int wynik = slowo2.pobierzIdSlowo();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#ustawIdGrupy(int)}.
	 * Test ustawiania ID grupy.
	 */
	@Test
	void testUstawIdGrupy() {
		int oczekiwane = 6;
		slowo2.ustawIdGrupy(oczekiwane);
		int wynik = slowo2.pobierzIdGrupy();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#ustawSlowo(java.lang.String)}.
	 * Test ustawiania s³owa.
	 */
	@Test
	void testUstawSlowo() {
		String oczekiwane = "house";
		slowo2.ustawSlowo(oczekiwane);
		String wynik = slowo2.pobierzSlowo();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#ustawTlumaczenie(java.lang.String)}.
	 * Test ustawiania t³umaczenia.
	 */
	@Test
	void testUstawTlumaczenie() {
		String oczekiwane = "dom";
		slowo2.ustawTlumaczenie(oczekiwane);
		String wynik = slowo2.pobierzTlumaczenie();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#ustawCzescMowy(java.lang.String)}.
	 * Test ustawiania czêœci mowy.
	 */
	@Test
	void testUstawCzescMowy() {
		String oczekiwane = "n";
		slowo2.ustawCzescMowy(oczekiwane);
		String wynik = slowo2.pobierzCzescMowy();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.Slowo#ustawCzyZapamietane(boolean)}.
	 * Test ustawiania flagi 'czy zapamiêtane'.
	 */
	@Test
	void testUstawCzyZapamietane() {
		boolean oczekiwane = false;
		slowo2.ustawCzyZapamietane(oczekiwane);;
		boolean wynik = slowo2.pobierzCzyZapamietane();
		assertEquals(oczekiwane, wynik);
	}

}
