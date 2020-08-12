package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import BazaDanych.Grupa;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
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
 * Panel testu ze znajomo�ci s��wek.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Test extends Panel {
	/**
	 * Do pobrania s��w z grupy do testu.
	 * ID grupy w BD.
	 * Domy�lnie -1, co oznacza brak wybranej BD.
	 */
	private int id_grupa;
	/**
	 * Rekord z bazy danych reprezentuj�cy grup�.
	 */
	private Grupa grupa;
	/**
	 * Flaga - test b�dzie aktywny, je�li s� jakie� s�owa na li�cie.
	 * W przypadku, gdy sprawdzanie przy prze��czaniu na tryb testu nie wychwyci b��du.
	 */
	private boolean czySaSlowa;
	/**
	 * Lista s��w, kt�re s� do przerobienia.
	 * Indexy do listy s��w.
	 */
	private List<Integer> indexyDoZrobienia;
	/**
	 * Lista s��w, kt�re s� poprawne.
	 * Indexy do listy s��w.
	 */
	private List<Integer> indexyPoprawne;
	/**
	 * Lista s��w, kt�re s� b��dne.
	 * Indexy do listy s��w.
	 */
	private List<Integer> indexyBledne;
	
	/**
	 * Napis z informacj� o trybach testu.
	 */
	private JLabel lblTryby;
	/**
	 * Tryb t�umaczenia z j. natywnego na j. obcy. Przycisk radio.
	 */
	private JRadioButton rdbtnNaObcy;
	/**
	 * Tryb t�umaczenia z j. obcego na j. natywny. Przycisk radio.
	 */
	private JRadioButton rdbtnNaNatywny;
	/**
	 * Grupa tryb�w t�umaczenia. Grupa przycisk�w na przyciski radio. 
	 */
	private ButtonGroup btngrTryby;
	/**
	 * Pole tekstowe na s�owo w j. obcym lub j. natywnym.
	 */
	private JTextField tfSlowo;
	/**
	 * Pole tekstowe na t�umaczenie s�owa.
	 */
	private JTextField tfTlumaczenie;
	/**
	 * Opis pola tekstowego na s�owo w j. obcym lub j. natywnym.
	 */
	private JLabel lblSlowo;
	/**
	 * Opis pola tekstowego na t�umaczenie s�owa.
	 */
	private JLabel lblTlumaczenie;
	/**
	 * Przycisk przej�cia do nast�pnego s�owa.
	 */
	private JButton btnDalej;
	/**
	 * Informacja o wyniku - czy odpowied� jest poprawna, czy nie.
	 */
	private JLabel lblWynk;
	/**
	 * Wy�wietla poprawn� odpowied�, je�li podana nie by�a poprawna.
	 */
	private JLabel lblPoprawnaOdp;
	/**
	 * Napis z informacj�, �e to test.
	 */
	private JLabel lblTest;
	/**
	 * Informacja o pasku post�pu obrazuj�cym odsetek zaliczonych s��w.
	 */
	private JLabel lblPoprawne;
	/**
	 * Infomacja o pasku post�pu obrazuj�cym skuteczno�� w te�cie.
	 */
	private JLabel lblSkutecznosc;
	/**
	 * Informacja o odsetku zaliczonych s��w.
	 * Pasek post�pu.
	 */
	private JProgressBar pbPoprawne;
	/**
	 * Informacja o stosunku poprawnych odpowiedzi do udzielonych odpowiedzi.
	 * Pasek post�pu.
	 * Na start wype�niony ca�kowicie. Maleje po ka�dej b��dnej odpowiedzi.
	 */
	private JProgressBar pbSkutecznosc;
	/**
	 * Informacja o odsetku b��dnych odpowiedzi.
	 */
	private JLabel lblBledne;
	/**
	 * Informacja o stosunku b��dnych odpowiedzi do puli odpowiedzi.
	 * Pasek post�pu.
	 * Resetuje si�, kiedy pula b��dnych odpowiedzi jest ponownie puszczana.
	 */
	private JProgressBar pbBledne;

	/**
	 * Utworzenie panelu z testem.
	 * Konstruktor klasy Test.
	 */
	public Test() {
		inicjujKomponenty();
		inicjujPola();
		
		log.debug("Utworzono obiekt klasy Test.");
	}
	
	/**
	 * Inicjacja element�w panelu.
	 * G��wnie element�w z biblioteki Swing.
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
		
		btnDalej = new JButton("Dalej");
		btnDalej.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDalej.setBounds(1085, 268, 100, 30);
		add(btnDalej);
		
		lblWynk = new JLabel("");
		lblWynk.setHorizontalAlignment(SwingConstants.CENTER);
		lblWynk.setFont(new Font("Arial", Font.PLAIN, 16));
		lblWynk.setBounds(0, 314, 1200, 20);
		add(lblWynk);
		
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
		
		lblPoprawne = new JLabel("Poprawne");
		lblPoprawne.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPoprawne.setFont(new Font("Arial", Font.ITALIC, 16));
		lblPoprawne.setBounds(10, 477, 120, 25);
		add(lblPoprawne);
		
		lblSkutecznosc = new JLabel("Skuteczno\u015B\u0107");
		lblSkutecznosc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSkutecznosc.setFont(new Font("Arial", Font.ITALIC, 16));
		lblSkutecznosc.setBounds(10, 559, 120, 25);
		add(lblSkutecznosc);
		
		pbPoprawne = new JProgressBar();
		pbPoprawne.setForeground(Color.GREEN);
		pbPoprawne.setStringPainted(true);
		pbPoprawne.setBounds(145, 477, 1040, 25);
		add(pbPoprawne);
		
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
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//W�asne
		id_grupa = -1;
		indexyDoZrobienia = new ArrayList<Integer>();
		indexyPoprawne = new ArrayList<Integer>();
		indexyBledne = new ArrayList<Integer>();
		
		//Odziedziczone
		//log
		log = LoggerFactory.getLogger(Test.class);
	}
	
	/**
	 * Przygotowanie testu s��wek. Do wywo�ania przy zmianie panelu na test.
	 * Ustawienie ID grupy w BD do uzyskania rekord�w z po��danej grupy.
	 * Pobranie listy s��w z danej grupy z BD.
	 * @param id_grupa ID grupy w BD
	 */
	public void przygotujTest(int id_grupa) {
		this.id_grupa = id_grupa;
		//Przygotowanie listy s��w
		pobierzSlowaZBazyDanych();
		pobierzGrupeZBazyDanych();
		
		wyczyscListy();
		oddzielPrzerobione();
		
		
		/**DEBUG
		System.out.println("ID grupy:\t" + id_grupa);
		System.out.println("Do zrobienia");
		debugListaSlow(indexyDoZrobienia);
		System.out.println("Poprawne");
		debugListaSlow(indexyPoprawne);
		*/
	}
	
	/**
	 * Pobranie s��w z podanej grupy z bazy danych.
	 * Sprawdzenie, czy lista zawiera jakie� s�owa.
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
	 * Pobranie z BD grupy, na kt�rej b�dzie przeprowadzony test.
	 * Wy�wietlenie komunikatu, �e na danej grupie jest test.
	 * Je�li dana grupa nie istnieje lub nie zawiera danych, wy�wietlony zostanie odpowiedni komunikat.
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
			lblTest.setText("Problem z wczytaniem grupy s��w!");
		}
	}
	
	/**
	 * S�owa, kt�re s� przerobione, daje na inn� list�.
	 * Lista index�w.
	 */
	private void oddzielPrzerobione() {
		for(int i = 0; i < slowa.size(); i++) {
			if(slowa.get(i).pobierzCzyZapamietane()) {
				indexyPoprawne.add(i);
			} else {
				indexyDoZrobienia.add(i);
			}
		}
	}
	
	/**
	 * Czy�ci listy index�w z zawarto�ci.
	 * Zapobiega zapychaniu si� list.
	 */
	private void wyczyscListy() {
		indexyDoZrobienia.clear();
		indexyPoprawne.clear();
		indexyBledne.clear();
	}
	
	/**
	 * Generowanie pseudolosowych list index�w do zrobienia i poprawnych.
	 */
	private void losowanie() {
		int iloscSlow = slowa.size();
		
		for(int i = 0; i < iloscSlow; i++) {
			
		}
	}
	
	private boolean czyWylosowana() {
		return true;
	}
	
	/**
	 * Wyswietlanie listy pobranych s��w.
	 * Do debugu.
	 * @param lista index�w (do zrobienia, poprawne, b��dne)
	 */
	private void debugListaSlow(List<Integer>lista) {
		if(czySaSlowa) {
			System.out.println("Ilo�� rekord�w\t" + lista.size());
			
			for(int i = 0; i < lista.size(); i++) {
				System.out.print(slowa.get(lista.get(i)));
			}
		}
	}
}
