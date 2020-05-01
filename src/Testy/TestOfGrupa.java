package Testy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BazaDanych.Grupa;

/**
 * Testy jednostkowe klasy Grupa
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
class TestOfGrupa {
	/**
	 * Obiekt klasy Grupa do testów odczytu
	 */
	private Grupa grupa1 = new Grupa(1, "Dom", "Pokoje, meble, itp.");
	/**
	 * Obiekt klasy Grupa do testów modyfikacji
	 */
	private Grupa grupa2 = new Grupa(2, "Dom", "Pokoje, meble, itp.");
	
	/**
	 * Test metody {@link BazaDanych.Grupa#pobierzIdGrupa()}.
	 * Test pobierania ID grupy.
	 */
	@Test
	void testPobierzIdGrupa() {
		int oczekiwany = 1;
		int wynik = grupa1.pobierzIdGrupa();
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test metody {@link BazaDanych.Grupa#pobierzNazwaGrupy()}
	 * Test pobierania nazwy grupy.
	 */
	@Test
	void testPobierzNazwaGrupy() {
		String oczekiwany = "Dom";
		String wynik = grupa1.pobierzNazwaGrupy();
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test metody {@link BazaDanych.Grupa#pobierzOpisGrupy()}
	 * Test pobierania opisu grupy.
	 */
	@Test
	void testPobierzOpisGrupy() {
		String oczekiwany = "Pokoje, meble, itp.";
		String wynik = grupa1.pobierzOpisGrupy();
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test metody {@link BazaDanych.Grupa#ustawIdGrupy(int)}.
	 * Test ustawiania ID grupy.
	 */
	@Test
	void testUstawIdGrupy() {
		int oczekiwany = 3;
		grupa2.ustawIdGrupy(oczekiwany);
		int wynik = grupa2.pobierzIdGrupa();
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test metody {@link BazaDanych.Grupa#ustawNazweGrupy(java.lang.String)}.
	 * Test ustawiania nazwy grupy.
	 */
	@Test
	void testUstawNazweGrupy() {
		String oczekiwany = "Szko³a";
		grupa2.ustawNazweGrupy(oczekiwany);
		String wynik = grupa2.pobierzNazwaGrupy();
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test metody {@link BazaDanych.Grupa#ustawOpisGrupy(java.lang.String)}.
	 * Test ustawiania opisu grupy.
	 */
	@Test
	void testUstawOpisGrupy() {
		String oczekiwany = "¯ycie szkolne, zajêcia, pomieszczenia, itp.";
		grupa2.ustawOpisGrupy(oczekiwany);
		String wynik = grupa2.pobierzOpisGrupy();
		assertEquals(oczekiwany, wynik);
	}
}
