package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import BazaDanych.Grupa;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

/**
 * Panel testu ze znajomoœci s³ówek.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Test extends Panel {
	/**
	 * Do pobrania s³ów z grupy do testu.
	 * ID grupy w BD.
	 * Domyœlnie -1, co oznacza brak wybranej BD.
	 */
	private int id_grupa;
	/**
	 * Rekord z bazy danych reprezentuj¹cy grupê.
	 */
	private Grupa grupa;
	/**
	 * Flaga - test bêdzie aktywny, jeœli s¹ jakieœ s³owa na liœcie.
	 * W przypadku, gdy sprawdzanie przy prze³¹czaniu na tryb testu nie wychwyci b³êdu.
	 */
	private boolean czySaSlowa;
	/**
	 * Lista s³ów, które s¹ do przerobienia.
	 * Indexy do listy s³ów.
	 */
	private List<Integer> indexyDoZrobienia;
	/**
	 * Lista s³ów, które s¹ zaliczone.
	 * Indexy do listy s³ów.
	 */
	private List<Integer> indexyZaliczone;
	/**
	 * Lista s³ów, które s¹ b³êdne.
	 * Indexy do listy s³ów.
	 */
	private List<Integer> indexyBledne;
	/**
	 * £¹czna iloœæ prób odgadniêcia s³owa przy aktualnym podejœciu.
	 */
	private int proby;
	/**
	 * Obecnie przetwarzany index z listy indexów do zrobienia.
	 */
	private int index;
	/**
	 * Obiekt do wywo³ania akcji sprawdzenia t³umaczenia s³owa.
	 * Obiekt nas³uchuj¹cy - ActionListener
	 */
	private AkcjaSprawdz akcjaSprawdz;
	/**
	 * Obiekt do wywo³ania akcji przejœcia dalej w teœcie.
	 * Obiekt nas³uchuj¹cy - ActionListener
	 */
	private AkcjaDalej akcjaDalej;
	/**
	 * Napis z informacj¹ o trybach testu.
	 */
	private JLabel lblTryby;
	/**
	 * Tryb t³umaczenia z j. natywnego na j. obcy. Przycisk radio.
	 */
	private JRadioButton rdbtnNaObcy;
	/**
	 * Tryb t³umaczenia z j. obcego na j. natywny. Przycisk radio.
	 */
	private JRadioButton rdbtnNaNatywny;
	/**
	 * Grupa trybów t³umaczenia. Grupa przycisków na przyciski radio. 
	 */
	private ButtonGroup btngrTryby;
	/**
	 * Pole tekstowe na s³owo w j. obcym lub j. natywnym.
	 */
	private JTextField tfSlowo;
	/**
	 * Pole tekstowe na t³umaczenie s³owa.
	 */
	private JTextField tfTlumaczenie;
	/**
	 * Opis pola tekstowego na s³owo w j. obcym lub j. natywnym.
	 */
	private JLabel lblSlowo;
	/**
	 * Opis pola tekstowego na t³umaczenie s³owa.
	 */
	private JLabel lblTlumaczenie;
	/**
	 * Przycisk przejœcia do nastêpnego s³owa.
	 */
	private JButton btnDalej;
	/**
	 * Informacja o wyniku - czy odpowiedŸ jest poprawna, czy nie.
	 */
	private JLabel lblWynik;
	/**
	 * Wyœwietla poprawn¹ odpowiedŸ, jeœli podana nie by³a poprawna.
	 */
	private JLabel lblPoprawnaOdp;
	/**
	 * Napis z informacj¹, ¿e to test.
	 */
	private JLabel lblTest;
	/**
	 * Informacja o pasku postêpu obrazuj¹cym odsetek zaliczonych s³ów.
	 */
	private JLabel lblZaliczone;
	/**
	 * Infomacja o pasku postêpu obrazuj¹cym skutecznoœæ w teœcie.
	 */
	private JLabel lblSkutecznosc;
	/**
	 * Informacja o odsetku zaliczonych s³ów.
	 * Pasek postêpu.
	 */
	private JProgressBar pbZaliczone;
	/**
	 * Informacja o stosunku poprawnych odpowiedzi do udzielonych odpowiedzi.
	 * Pasek postêpu.
	 * Na start wype³niony ca³kowicie. Maleje po ka¿dej b³êdnej odpowiedzi.
	 */
	private JProgressBar pbSkutecznosc;
	/**
	 * Informacja o odsetku b³êdnych odpowiedzi.
	 */
	private JLabel lblBledne;
	/**
	 * Informacja o stosunku b³êdnych odpowiedzi do puli odpowiedzi.
	 * Pasek postêpu.
	 * Resetuje siê, kiedy pula b³êdnych odpowiedzi jest ponownie puszczana.
	 */
	private JProgressBar pbBledne;

	/**
	 * Utworzenie panelu z testem.
	 * Konstruktor klasy Test.
	 */
	public Test() {
		inicjujPola();
		inicjujKomponenty();
		
		log.debug("Utworzono obiekt klasy Test.");
	}
	
	/**
	 * Inicjacja elementów panelu.
	 * G³ównie elementów z biblioteki Swing.
	 */
	private void inicjujKomponenty() {
		lblTryby = new JLabel("Tryb testu");
		lblTryby.setHorizontalAlignment(SwingConstants.LEFT);
		lblTryby.setFont(new Font("Arial", Font.BOLD, 16));
		lblTryby.setBounds(10, 13, 1180, 20);
		add(lblTryby);
		
		rdbtnNaObcy = new JRadioButton("Na j\u0119zyk obcy");
		rdbtnNaObcy.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnNaObcy.setBounds(10, 45, 1179, 29);
		rdbtnNaObcy.setSelected(true);
		add(rdbtnNaObcy);
		
		rdbtnNaNatywny = new JRadioButton("Na j\u0119zyk natywny");
		rdbtnNaNatywny.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnNaNatywny.setBounds(10, 82, 1179, 29);
		add(rdbtnNaNatywny);
		
		btngrTryby = new ButtonGroup();
		btngrTryby.add(rdbtnNaObcy);
		btngrTryby.add(rdbtnNaNatywny);
		
		lblSlowo = new JLabel("S\u0142owo");
		lblSlowo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSlowo.setFont(new Font("Arial", Font.ITALIC, 16));
		lblSlowo.setBounds(10, 186, 120, 25);
		add(lblSlowo);
		
		tfSlowo = new JTextField();
		tfSlowo.setEditable(false);
		tfSlowo.setFont(new Font("Arial", Font.PLAIN, 16));
		tfSlowo.setColumns(10);
		tfSlowo.setBounds(145, 186, 1040, 25);
		add(tfSlowo);
		
		lblTlumaczenie = new JLabel("T\u0142umaczenie");
		lblTlumaczenie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTlumaczenie.setFont(new Font("Arial", Font.ITALIC, 16));
		lblTlumaczenie.setBounds(10, 227, 120, 25);
		add(lblTlumaczenie);
		
		tfTlumaczenie = new JTextField();
		tfTlumaczenie.setFont(new Font("Arial", Font.PLAIN, 16));
		tfTlumaczenie.setColumns(10);
		tfTlumaczenie.setBounds(145, 227, 1040, 25);
		add(tfTlumaczenie);
		
		btnDalej = new JButton("Sprawd\u017A");
		btnDalej.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDalej.setBounds(1085, 268, 100, 30);
		add(btnDalej);
		
		lblWynik = new JLabel("");
		lblWynik.setHorizontalAlignment(SwingConstants.CENTER);
		lblWynik.setFont(new Font("Arial", Font.PLAIN, 16));
		lblWynik.setBounds(0, 314, 1200, 20);
		add(lblWynik);
		
		lblPoprawnaOdp = new JLabel("");
		lblPoprawnaOdp.setForeground(Color.BLUE);
		lblPoprawnaOdp.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoprawnaOdp.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPoprawnaOdp.setBounds(0, 350, 1200, 20);
		add(lblPoprawnaOdp);
		
		lblTest = new JLabel("Test");
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblTest.setFont(new Font("Arial", Font.BOLD, 16));
		lblTest.setBounds(0, 150, 1200, 20);
		add(lblTest);
		
		lblZaliczone = new JLabel("Zaliczone");
		lblZaliczone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZaliczone.setFont(new Font("Arial", Font.ITALIC, 16));
		lblZaliczone.setBounds(10, 477, 120, 25);
		add(lblZaliczone);
		
		lblSkutecznosc = new JLabel("Skuteczno\u015B\u0107");
		lblSkutecznosc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSkutecznosc.setFont(new Font("Arial", Font.ITALIC, 16));
		lblSkutecznosc.setBounds(10, 559, 120, 25);
		add(lblSkutecznosc);
		
		pbZaliczone = new JProgressBar();
		pbZaliczone.setForeground(Color.GREEN);
		pbZaliczone.setStringPainted(true);
		pbZaliczone.setBounds(145, 477, 1040, 25);
		add(pbZaliczone);
		
		pbSkutecznosc = new JProgressBar();
		pbSkutecznosc.setStringPainted(true);
		pbSkutecznosc.setForeground(Color.BLUE);
		pbSkutecznosc.setBounds(145, 559, 1040, 25);
		add(pbSkutecznosc);
		
		lblBledne = new JLabel("B\u0142\u0119dne");
		lblBledne.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBledne.setFont(new Font("Arial", Font.ITALIC, 16));
		lblBledne.setBounds(10, 518, 120, 25);
		add(lblBledne);
		
		pbBledne = new JProgressBar();
		pbBledne.setForeground(Color.RED);
		pbBledne.setStringPainted(true);
		pbBledne.setBounds(145, 518, 1040, 25);
		add(pbBledne);
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//W³asne
		id_grupa = -1;
		indexyDoZrobienia = new ArrayList<Integer>();
		indexyZaliczone = new ArrayList<Integer>();
		indexyBledne = new ArrayList<Integer>();
		
		akcjaSprawdz = new AkcjaSprawdz();
		akcjaDalej = new AkcjaDalej();
		
		//Odziedziczone
		//log
		log = LoggerFactory.getLogger(Test.class);
	}
	
	/**
	 * Przygotowanie testu s³ówek. Do wywo³ania przy zmianie panelu na test.
	 * Ustawienie ID grupy w BD do uzyskania rekordów z po¿¹danej grupy.
	 * Pobranie listy s³ów z danej grupy z BD.
	 * @param id_grupa ID grupy w BD
	 */
	public void przygotujTest(int id_grupa) {
		//Ustawienie ID grupy
		this.id_grupa = id_grupa;
		
		//Przygotowanie listy s³ów i grupy
		pobierzSlowaZBazyDanych();
		pobierzGrupeZBazyDanych();
		
		//Przygotowanie list indexów do zrobienia i zaliczonych.
		wyczyscListy();
		losowanie();
		
		//Ustawienie wartoœci dla zmiennych.
		proby = indexyZaliczone.size();		//Iloœæ prób - iloœæ ju¿ zaliczonych s³ów lub 0.
		index = 0;
		
		//Przypisanie akcji do przycisków lub resetowanie.
		btnDalej.removeActionListener(akcjaSprawdz);
		btnDalej.removeActionListener(akcjaDalej);
		btnDalej.addActionListener(akcjaSprawdz);
		btnDalej.setText("SprawdŸ");
		
		//Ustawienie wartoœci dla pasków postêpu.
		ustawWartosciPaskiPostepu();
		
		//Ustawienie pierwszego s³owa.
		tfSlowo.setText(slowa.get(indexyDoZrobienia.get(index)).pobierzTlumaczenie());
		tfTlumaczenie.setText("");
		tfTlumaczenie.setEditable(true);
		
		//Czyszczenie zawartoœci pól wynikowych.
		lblWynik.setForeground(Color.BLACK);
		lblWynik.setText("");
		lblPoprawnaOdp.setForeground(Color.BLACK);
		lblPoprawnaOdp.setText("");
		
		//DEBUG
		//debugStanListyIndexow();
		//debugListaListenerow();
	}
	
	/**
	 * Pobranie s³ów z podanej grupy z bazy danych.
	 * Sprawdzenie, czy lista zawiera jakieœ s³owa.
	 */
	private void pobierzSlowaZBazyDanych() {
		interfejsBD.otworzPolaczenie();
		slowa = interfejsBD.pobierzSlowaZGrupy(id_grupa);
		interfejsBD.zamknijPolaczenie();
		if(slowa.size() > 0) { 
			czySaSlowa = true;
		} else {
			czySaSlowa = false;
		}
	}
	
	/**
	 * Pobranie z BD grupy, na której bêdzie przeprowadzony test.
	 * Wyœwietlenie komunikatu, ¿e na danej grupie jest test.
	 * Jeœli dana grupa nie istnieje lub nie zawiera danych, wyœwietlony zostanie odpowiedni komunikat.
	 */
	private void pobierzGrupeZBazyDanych() {
		if(czySaSlowa) {
			interfejsBD.otworzPolaczenie();
			List<Grupa>listaGrup = interfejsBD.pobierzGrupe(id_grupa);
			grupa = listaGrup.get(0);
			
			lblTest.setForeground(Color.BLACK);
			lblTest.setText(grupa.pobierzNazwaGrupy() + " - test");
		} else {
			lblTest.setForeground(Color.RED);
			lblTest.setText("Problem z wczytaniem grupy s³ów!");
		}
	}
	
	/**
	 * S³owa, które s¹ przerobione, daje na inn¹ listê.
	 * Lista indexów.
	 * @deprecated
	 */
	private void oddzielPrzerobione() {
		for(int i = 0; i < slowa.size(); i++) {
			if(slowa.get(i).pobierzCzyZapamietane()) {
				indexyZaliczone.add(i);
			} else {
				indexyDoZrobienia.add(i);
			}
		}
	}
	
	/**
	 * Czyœci listy indexów z zawartoœci.
	 * Zapobiega zapychaniu siê list.
	 */
	private void wyczyscListy() {
		indexyDoZrobienia.clear();
		indexyZaliczone.clear();
		indexyBledne.clear();
	}
	
	/**
	 * Generowanie pseudolosowych list indexów do zrobienia i poprawnych.
	 */
	private void losowanie() {
		int iloscSlow = slowa.size();
		int wylosowane;
		
		for(int i = 0; i < iloscSlow; i++) {
			do {
				wylosowane = (int)(Math.random() * iloscSlow);
			} while(czyWylosowana(wylosowane, this.indexyDoZrobienia) || 
					czyWylosowana(wylosowane, this.indexyZaliczone));
			
			if(slowa.get(wylosowane).pobierzCzyZapamietane()) {
				indexyZaliczone.add(wylosowane);
			} else {
				indexyDoZrobienia.add(wylosowane);
			}
			
		}
	}
	
	/**
	 * Sprawdza, czy podana liczba jest ju¿ w danej liœcie.
	 * @param liczba poszukiwana liczba
	 * @param lista lista liczb, która ma zostaæ przeszukana
	 * @return true, jeœli <code>liczba</code> jest ju¿ w liœcie 
	 */
	private boolean czyWylosowana(int liczba, List<Integer> lista) {
		for(int i = 0; i < lista.size(); i++) {
			if(liczba == lista.get(i)) { return true; }
		}
		return false;
	}
	
	/**
	 * Przeliczenie stosunku jednego parametru do drugiego.
	 * Do ustawienia wartoœci dla pasków postêpu
	 * @param stosunekCzego wartoœæ do stosunku
	 * @param stosunekDoCzego punkt odniesienia
	 * @return wartoœæ stosunku w procentach; 0, jeœci punkt odniesienia jest równy zero
	 */
	private int przeliczStosunekProcentowy(int stosunekCzego, int stosunekDoCzego) {
		if(stosunekDoCzego == 0) { return 0; }
		else {
			float stosunekFloat = stosunekCzego * 100f / stosunekDoCzego;
			int stosunekInt = Math.round(stosunekFloat);
			//System.out.println(stosunekFloat + "\t" + stosunekInt);
			return stosunekInt;
		}
	}
	
	/**
	 * Ustawienie wartoœci w paskach postêpu.
	 */
	private void ustawWartosciPaskiPostepu() {
		pbZaliczone.setValue(przeliczStosunekProcentowy(
								indexyZaliczone.size(),
								slowa.size()));
		pbBledne.setValue(przeliczStosunekProcentowy(
								indexyBledne.size(), 
								indexyDoZrobienia.size()));
		pbSkutecznosc.setValue(przeliczStosunekProcentowy(
								indexyZaliczone.size(),
								proby));
	}
	
	/**
	 * Wyswietlanie listy pobranych s³ów.
	 * Do debugu.
	 * @param lista indexów (do zrobienia, poprawne, b³êdne)
	 */
	private void debugListaSlow(List<Integer>lista) {
		if(czySaSlowa) {
			System.out.println("Iloœæ rekordów\t" + lista.size());
			
			for(int i = 0; i < lista.size(); i++) {
				System.out.print(slowa.get(lista.get(i)));
			}
		}
	}
	
	/**
	 * Generuje listê indexów do s³ów.
	 * Do debugu.
	 * @param lista lista indexów (do zrobienia, poprawne, b³êdne) 
	 */
	private void debugListaIndexow(List<Integer>lista) {
		System.out.println("Rozmiar:\t" + lista.size());
		for(int i = 0; i < lista.size(); i++) {
			System.out.print(lista.get(i) + "\t");
		}
		System.out.println();
	}
	

	/**
	 * Ustawienie wartoœci dla pasków postêpu.
	 * Do debugu
	 */
	private void debugUstawienieStatystyk() {
		System.out.println("Zaliczone:\t" +
					przeliczStosunekProcentowy(
							indexyZaliczone.size(),
							slowa.size()));
		System.out.println("B³êdne:\t" +
						przeliczStosunekProcentowy(
								indexyBledne.size(), 
								indexyDoZrobienia.size()));
		System.out.println("Skutecznoœæ:\t" +
						przeliczStosunekProcentowy(
								indexyZaliczone.size(),
								proby));
	}
	
	/**
	 * Akcja zw. ze sprawdzeniem t³umaczenia s³owa w teœcie.
	 * Klasa wewnêtrzna.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class AkcjaSprawdz implements ActionListener {
		/**
		 * Konstruktor klasy AkcjaSprawdz.
		 */
		public AkcjaSprawdz() {}
		
		/**
		 * Akcja sprawdzenia t³umaczenia s³owa w teœcie.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//Pobranie poprawnej odpowiedzi
			int indexSlowa = indexyDoZrobienia.get(index);
			String poprawnaOdp = slowa.get(indexSlowa).pobierzSlowo();
			
			//Sprawdzanie
			tfTlumaczenie.setEditable(false);
			if(czyOdpowiedzPoprawna(tfTlumaczenie.getText(), poprawnaOdp)) {
				lblWynik.setForeground(Color.GREEN);
				lblWynik.setText("Dobra odpowiedŸ!");
				
				//Dodanie indexu do zaliczonych
				indexyZaliczone.add(indexSlowa);
			}
			else {
				lblWynik.setForeground(Color.RED);
				lblWynik.setText("Z³a odpowiedŸ!");
				
				lblPoprawnaOdp.setForeground(Color.BLUE);
				lblPoprawnaOdp.setText("Poprawna odpowiedŸ: " + poprawnaOdp);
				
				//Dodanie indexu do b³êdnych
				indexyBledne.add(indexSlowa);
			}
			
			//Aktualizacja indexu i statystyk
			proby++;
			ustawWartosciPaskiPostepu();
			
			//Zmiana zachowania przycisku na przejœcie dalej.
			btnDalej.removeActionListener(akcjaSprawdz);
			btnDalej.addActionListener(akcjaDalej);
			btnDalej.setText("Dalej");
			
			//DEBUG
			debugListaListenerow();
			
			// TODO Auto-generated method stub
		}
		
		/**
		 * Sprawdza, czy podane t³umaczenie jest poprawne.
		 * Na podstawie listy t³umaczeñ.
		 * @param tlumaczenie podana przez u¿ytkownika odpowiedŸ
		 * @param odpowiedz prawid³owa odpowiedŸ
		 * @return true, jeœli podana przez u¿ytkownika odpowiedŸ by³a poprawna
		 */
		private boolean czyOdpowiedzPoprawna(
				String tlumaczenie,
				String odpowiedz) {
			if(tlumaczenie.equals(odpowiedz)) { return true; }
			return false;
		}
		
	}
	
	/**
	 * Akcja zw. z przejœciem dalej w teœcie s³ówek.
	 * Klasa wewnêtrzna.
	 * @author MikiWojak (Miko³aj ¯arnowski)
	 */
	private class AkcjaDalej implements ActionListener {
		/**
		 * Konstuktor klasy AkcjaDalej.
		 */
		public AkcjaDalej() {}
		
		/**
		 * Akcja przejœcia dalej w teœcie. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//Aktualizacja indexu i ew. listy do zrobienia.
			index++;
			if(index < indexyDoZrobienia.size()) {
				tfSlowo.setText(slowa.get(indexyDoZrobienia.get(index)).pobierzTlumaczenie());
			}
			else {
				if(indexyBledne.size() > 0) {
					index = 0;
					indexyDoZrobienia.clear();
					indexyDoZrobienia.addAll(indexyBledne);
					indexyBledne.clear();
					
					tfSlowo.setText(slowa.get(indexyDoZrobienia.get(index)).pobierzTlumaczenie());
				}
				else {
					JOptionPane.showMessageDialog(
							null,
							"Koniec testu",
							"Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//DEBUG
			debugStanListyIndexow();
			debugListaListenerow();
			
			//Wyczyszczenie zawartoœci informacji
			tfTlumaczenie.setText("");
			tfTlumaczenie.setEditable(true);
			
			lblWynik.setForeground(Color.BLACK);
			lblWynik.setText("");
			
			lblPoprawnaOdp.setForeground(Color.BLUE);
			lblPoprawnaOdp.setText("");
			
			//Aktualizacja statustyk
			ustawWartosciPaskiPostepu();
			
			//Zmiana zachowania przycisku na sprawdzenie.
			btnDalej.removeActionListener(akcjaDalej);
			btnDalej.addActionListener(akcjaSprawdz);
			btnDalej.setText("SprawdŸ");
			
			// TODO Auto-generated method stub
		}
	}
	
	/**
	 * Wyœwietla obecny stan listy indexow i innych danych.
	 * Do debugu. 
	 */
	private void debugStanListyIndexow() {
		System.err.println("Próba:\t" + proby);
		System.out.println("Index:\t" + index + "\t\t" + indexyDoZrobienia.get(index));
		System.out.print(slowa.get(indexyDoZrobienia.get(index)));
		System.out.println("Do zrobienia:");
		debugListaIndexow(indexyDoZrobienia);
		System.out.println("Zaliczone:");
		debugListaIndexow(indexyZaliczone);
		System.out.println("B³êdne:");
		debugListaIndexow(indexyBledne);
		System.out.println();
	}
	
	/**
	 * Wyœwietla obecny stan przypisanych listenerów do przycisku.
	 * Do debugu.
	 */
	private void debugListaListenerow() {
		Object lista[] = btnDalej.getActionListeners();
		try {
			for(int i = 0; i < 5; i ++) {
				System.out.println(lista[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println();
	}
}
