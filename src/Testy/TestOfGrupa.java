package Testy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BazaDanych.Grupa;

/**
 * Testy jednostkowe klasy Grupa
 */
class TestOfGrupa {
	/**
	 * Obiekt klasy Grupa do testów
	 */
	private Grupa grupa1 = new Grupa(1, "Dom", "Pokoje, meble, itp.");
	
	/*
	@Test
	void testGrupa() {
		fail("Not yet implemented");
	}
	*/
	
	/*
	@Test
	void testGrupaIntStringString() {
		fail("Not yet implemented");
	}
	*/
	
	/**
	 * Test pobierania ID grupy.
	 * Test metody pobierzIdGrupa()
	 */
	@Test
	void testPobierzIdGrupa() {
		int oczekiwany = 1;
		int wynik = grupa1.pobierzIdGrupa();
		assertEquals(oczekiwany, wynik);
	}
	
	/**
	 * Test pobierania nazwy grupy
	 * Test metody pobierzNazwaGrupy()
	 */
	@Test
	void testPobierzNazwaGrupy() {
		String oczekiwany = "Dom";
		String wynik = grupa1.pobierzNazwaGrupy();
		assertEquals(oczekiwany, wynik);
	}

	@Test
	void testPobierzOpisGrupy() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testUstawIdGrupy() {
		fail("Not yet implemented");
	}

	@Test
	void testUstawNazweGrupy() {
		fail("Not yet implemented");
	}

	@Test
	void testUstawOpisGrupy() {
		fail("Not yet implemented");
	}


}
