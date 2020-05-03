package Main;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BazaDanych.Grupa;
import BazaDanych.InterfejsBD;
import BazaDanych.Slowo;

/**
 * Klasa, kt�ra uruchamia g��wne okno programu
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Main {
	
	/**
	 * G��wna metoda. S�u�y do uruchomienia programu
	 * @param args argumenty niezb�dne do odpalenia programu
	 */
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Main.class);
		log.debug("Hello world!");
		InterfejsBD bd = new InterfejsBD();
		bd.zamknijPolaczenie();
		
		/*
		bd.otworzPolaczenie();
		bd.dodajGrupe("Szko�a",	"");
		bd.dodajGrupe("Dom", "");
		bd.dodajGrupe("�redniowiecze", "");
		bd.zamknijPolaczenie();
		*/
		
		/*
		List<Grupa>grupy = new LinkedList<Grupa>();
		bd.otworzPolaczenie();
		grupy = bd.pobierzGrupyWszystkieAlfabetycznieRosnaco();
		//grupy = bd.pobierzGrupyWszystkie();
		bd.zamknijPolaczenie();
		
		for(int i = 0; i < grupy.size(); i++) {
			System.out.print(grupy.get(i));
		}
		*/
		
		/*
		bd.otworzPolaczenie();
		bd.dodajSlowo(1, "computer", "komputer", "n", false);
		bd.dodajSlowo(1, "switch", "prze��cznik", "n", false);
		bd.dodajSlowo(1, "hack", "w�ama� si�", "v", true);
		bd.zamknijPolaczenie();
		*/
		
		bd.otworzPolaczenie();
		List<Slowo>slowa = bd.pobierzSlowaWszystkie();
		bd.zamknijPolaczenie();
		for(int i = 0; i < slowa.size(); i++) {
			System.out.print(slowa.get(i));
		}
	}
}
