package Testy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import BazaDanych.Grupa;
import BazaDanych.InterfejsBD;
import BazaDanych.Slowo;

/**
 * <b>Przed wykonaniem usun¹æ plik bazy danych z folderu g³ównego!</b>
 * Pierwszy test jednostkowy klasy InterfejsBD.
 * Testy jednostkowe g³ównego interfejsu bazy danych.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */

@TestMethodOrder(OrderAnnotation.class)
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
	@Order(1)
	void testDodajGrupe() {
		//czyszczenie BD
		interfejs.wyczyscTabele();
		//dodanie grup
		interfejs.dodajGrupe("Komputer", "Podzespo³y komputerowe");
		interfejs.dodajGrupe("Broñ bia³a", "Sieczna, obuchowa i drzewcowa");
		interfejs.dodajGrupe("Zbroje", "Lekkie i ciê¿kie");
		//Test - iloœæ grup
		int oczekiwane = 3;
		grupy.clear();
		grupy = interfejs.pobierzGrupyWszystkie();
		int wynik = grupy.size();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.InterfejsBD#dodajSlowo(int, java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 * Test dodawania s³ów do bazy danych.
	 */
	@Test
	@Order(2)
	void testDodajSlowo() {
		//Dodawanie s³ów
		interfejs.dodajSlowo(1, "computer", "komputer", "n", false);
		interfejs.dodajSlowo(1, "legacy", "przestarza³y", "adj", false);
		interfejs.dodajSlowo(1, "processor", "procesor", "n", false);
		interfejs.dodajSlowo(2, "mace", "buzdygan", "n", false);
		interfejs.dodajSlowo(2, "sword", "miecz", "n", true);
		interfejs.dodajSlowo(2, "lance", "kopia rycerska", "n", true);
		interfejs.dodajSlowo(3, "padded", "przeszywanica", "n", true);
		interfejs.dodajSlowo(3, "chainmail", "kolczuga", "n", true);
		interfejs.dodajSlowo(3, "coat of plates", "pancerz typu p³aty", "n", true);
		
		interfejs.dodajSlowo(1, "graphic card", "karta graficzna", "n", false);
		interfejs.dodajSlowo(1, "fix", "naprawiaæ (komputer)", "v", false);
		interfejs.dodajSlowo(1, "motherboard", "p³yta g³ówna", "n", false);
		interfejs.dodajSlowo(2, "warhammer", "m³ot rycerski", "n", false);
		interfejs.dodajSlowo(2, "dagger", "sztylet", "n", true);
		interfejs.dodajSlowo(3, "full plate", "zbroja p³ytowa", "n", true);
		
		//Test - iloœæ s³ów
		int oczekiwane = 15;
		slowa.clear();
		slowa = interfejs.pobierzSlowaWszystkie();
		int wynik = slowa.size();
		assertEquals(oczekiwane, wynik);
	}

	/**
	 * Test metody {@link BazaDanych.InterfejsBD#pobierzGrupyWszystkie()}.
	 * Test pobierania wszystkich grup (niesortowane).
	 */
	@Test
	@Order(3)
	void testPobierzGrupyWszystkie() {
		int oczekiwane[] = {1, 2, 3};
		grupy.clear();
		grupy = interfejs.pobierzGrupyWszystkie();
		
		//Test - po³o¿enie rekordów (po ID)
		for(int i = 0; i < grupy.size(); i++) {
			assertEquals(oczekiwane[i], grupy.get(i).pobierzIdGrupa());
		}
	}

	/**
	 * Test metody {@link BazaDanych.InterfejsBD#pobierzGrupyWszystkieAlfabetycznieRosnaco()}.
	 * Test pobierania wszystkich grup posortowanych alfabetycznie rosn¹co.
	 */
	@Test
	@Order(4)
	void testPobierzGrupyWszystkieAlfabetycznieRosnaco() {
		int oczekiwane[] = {2, 1, 3};
		grupy.clear();
		grupy = interfejs.pobierzGrupyWszystkieAlfabetycznieRosnaco();
		
		//Test - po³o¿enie rekordów (po ID)
		for(int i = 0; i < grupy.size(); i++) {
			assertEquals(oczekiwane[i], grupy.get(i).pobierzIdGrupa());
		}
	}
	
	/**
	 * Test metody {@link BazaDanych.InterfejsBD#pobierzGrupe(int)}.
	 * Test pobrania jedej grupy.
	 */
	@Test
	@Order(5)
	void testPobierzGrupe() {
		int oczekiwane = 3;
		grupy.clear();
		grupy = interfejs.pobierzGrupe(3);
		
		//Test 
		assertEquals(oczekiwane, grupy.get(0).pobierzIdGrupa());
	}

	/**
	 * Test metody {@link BazaDanych.InterfejsBD#pobierzSlowaWszystkie()}.
	 * Test pobierania wszystkich s³ów niezale¿nie od grupy i nieposortowanych.
	 */
	@Test
	@Order(6)
	void testPobierzSlowaWszystkie() {
		int oczekiwane[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		slowa.clear();
		slowa = interfejs.pobierzSlowaWszystkie();
		
		//Test - po³o¿enie rekordów (po ID)
		for(int i = 0; i < slowa.size(); i++) {
			assertEquals(oczekiwane[i], slowa.get(i).pobierzIdSlowo());
		}
	}

	/**
	 * Test metody {@link BazaDanych.InterfejsBD#pobierzSlowaZGrupy(int)}.
	 * Test pobierania s³ów nale¿¹cych do danej grupy, nieposortowanych.
	 */
	@Test
	@Order(7)
	void testPobierzSlowaZGrupy() {
		int oczekiwane[] = {4, 5, 6, 13, 14};
		slowa.clear();
		slowa = interfejs.pobierzSlowaZGrupy(2);
		
		//Test - po³o¿enie rekordów (po ID)
		for(int i = 0; i < slowa.size(); i++) {
			assertEquals(oczekiwane[i], slowa.get(i).pobierzIdSlowo());
		}
	}
	
	/**
	 * Test metody {@link BazaDanych.InterfejsBD#pobierzSlowo(int)}.
	 * Test pobrania jedengo slowa.
	 */
	@Test
	@Order(8)
	void testPobierzSlowo() {
		int oczekiwane = 4;
		slowa.clear();
		slowa = interfejs.pobierzSlowo(4);
		
		//Test
		assertEquals(oczekiwane, slowa.get(0).pobierzIdSlowo());
	}

	/**
	 * Test metody {@link BazaDanych.InterfejsBD#modyfikujGrupe(int, java.lang.String, java.lang.String)}.
	 */
	@Test
	@Order(8)
	void testModyfikujGrupe() {
		int id = 1;
		String nazwa = "Podzespo³y komputerowe";
		String opis = "Czêœci komputerowe";
		
		interfejs.modyfikujGrupe(id, nazwa, opis);
		grupy.clear();
		grupy = interfejs.pobierzGrupe(1);
		
		//Test
		assertEquals(id, grupy.get(0).pobierzIdGrupa());
		assertEquals(nazwa, grupy.get(0).pobierzNazwaGrupy());
		assertEquals(opis, grupy.get(0).pobierzOpisGrupy());
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

}
