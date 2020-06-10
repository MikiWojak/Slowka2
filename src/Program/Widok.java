package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * Panel z widokiem na grupy i s�owa.
 * @author MikiWojak (Miko�aj �arnowski)
 */
public class Widok extends Panel {
	/**
	 * Napis na nazw� wybranej grupy.
	 */
	private JLabel lblGrupa;
	/**
	 * Tabela s��w wybranej grupy.
	 */
	private JTable tableSlowa;
	/**
	 * Scroll do listy grup.
	 */
	private JScrollPane scrollGrupy;
	/**
	 * Scroll do tabeli s��w.
	 */
	private JScrollPane scrollSlowa;
	/**
	 * Lista grup ze s�owami.
	 */
	private JList listGrupy;
	/**
	 * Wektor z nazwami kolumn do tabeli s��w.
	 */
	private Vector<String>nazwyKolumnTabelaSlow;
	private Vector<Vector<Object>>daneTabelaSlow;
	/**
	 * Utworzenie panelu z widokiem.
	 * Konstruktor klasy Widok.
	 */
	public Widok() {
		inicjujPola();
		inicjujKomponenty();
		
		log.debug("Utworzono obiekt klasy Widok.");
	}
	
	/**
	 * Inicjacja element�w panelu.
	 * G��wnie element�w z biblioteki Swing.
	 */
	private void  inicjujKomponenty() {
		lblGrupa = new JLabel("Wybierz grup\u0119 po lewej");
		lblGrupa.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupa.setFont(new Font("Arial", Font.BOLD, 16));
		lblGrupa.setBounds(400, 0, 800, 50);
		add(lblGrupa);
		
		scrollGrupy = new JScrollPane();
		scrollGrupy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollGrupy.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollGrupy.setBounds(0, 0, 350, 550);
		add(scrollGrupy);
		
		listGrupy = new JList();
		listGrupy.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				zmienGrupe();
			}
		});
		listGrupy.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollGrupy.setViewportView(listGrupy);
		pobierzGrupyBD();																			//grupy do listy
		listGrupy.setModel(utworzListeGrup());
		
		scrollSlowa = new JScrollPane();
		scrollSlowa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSlowa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSlowa.setBounds(400, 50, 800, 500);
		add(scrollSlowa);
		
		utworzNazwyKolumnTabebaSlow();																//nazwy kolumn
		tableSlowa = new JTable(daneTabelaSlow, nazwyKolumnTabelaSlow);
		scrollSlowa.setViewportView(tableSlowa);
	}
	
	/**
	 * Inicjacja p�l klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
		//wektory
		nazwyKolumnTabelaSlow = new Vector<String>();
		daneTabelaSlow = new Vector<Vector<Object>>();
	}
	
	/**
	 * Pobranie wszystkich grup z bazy danych. Grupy nie s� sortowane.
	 */
	private void pobierzGrupyBD() {
		interfejsBD.otworzPolaczenie();
		grupy = interfejsBD.pobierzGrupyWszystkie();
		interfejsBD.zamknijPolaczenie();
	}
	
	/**
	 * Utworzenie lub aktualizacja listy grup ze s�owami.
	 * Nale�y u�y� po funkcji: pobierzGrupyBD.
	 * @return grupy do umieszczenia w li�cie grup
	 */
	private DefaultListModel<Object>utworzListeGrup() {
		DefaultListModel<Object>lista_grup = new DefaultListModel<Object>();
		try {
			for(int i = 0; i < grupy.size(); i++) {
				lista_grup.addElement(grupy.get(i).pobierzNazwaGrupy());
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
		return lista_grup;
	}
	
	/**
	 * Zmiana aktualnie wy�wietlanej grupy. Do zastosowania z listenerem listy grup.
	 */
	private void zmienGrupe() {
		int indexLista = listGrupy.getSelectedIndex();
		//nag��wek listy s��w
		lblGrupa.setText(grupy.get(indexLista).pobierzNazwaGrupy());
	}
	
	/**
	 * Dodanie nazw kolumn do wektora. Utworzenie kolumn w tabeli.
	 */
	private void utworzNazwyKolumnTabebaSlow() {
		nazwyKolumnTabelaSlow.add("S�owo");
		nazwyKolumnTabelaSlow.add("T�umaczenie");
		nazwyKolumnTabelaSlow.add("Cz�� mowy");
	}
}
