package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Panel z widokiem na grupy i s³owa.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Widok extends Panel {
	/**
	 * Napis na nazwê wybranej grupy.
	 */
	private JLabel lblGrupa;
	/**
	 * Tabela s³ów wybranej grupy.
	 */
	private JTable tableSlowa;
	/**
	 * Scroll do listy grup.
	 */
	private JScrollPane scrollGrupy;
	/**
	 * Scroll do tabeli s³ów.
	 */
	private JScrollPane scrollSlowa;
	/**
	 * Lista grup ze s³owami.
	 */
	private JList listGrupy;
	
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
	 * Inicjacja elementów panelu.
	 * G³ównie elementów z biblioteki Swing.
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
		listGrupy.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollGrupy.setViewportView(listGrupy);
		//grupy do listy
		pobierzGrupyBD();
		listGrupy.setModel(utworzListeGrup());
		
		scrollSlowa = new JScrollPane();
		scrollSlowa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSlowa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSlowa.setBounds(400, 50, 800, 500);
		add(scrollSlowa);
		
		tableSlowa = new JTable();
		scrollSlowa.setViewportView(tableSlowa);
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Widok.class);
	}
	
	/**
	 * Pobranie wszystkich grup z bazy danych. Grupy nie s¹ sortowane.
	 */
	private void pobierzGrupyBD() {
		interfejsBD.otworzPolaczenie();
		grupy = interfejsBD.pobierzGrupyWszystkie();
		interfejsBD.zamknijPolaczenie();
	}
	
	/**
	 * Utworzenie lub aktualizacja listy grup ze s³owami.
	 * Nale¿y u¿yæ po funkcji: pobierzGrupyBD.
	 * @return grupy do umieszczenia w liœcie grup
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
}
