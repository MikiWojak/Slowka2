package Program;

import java.awt.Color;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Panel testu ze znajomoœci s³ówek.
 * @author MikiWojak (Miko³aj ¯arnowski)
 */
public class Test extends Panel {
	private JLabel lblTest;
	private JRadioButton rdbtnNaObcy;
	private JRadioButton rdbtnNaNatywny;
	private ButtonGroup btngrTryby;
	private JTextField textField;
	private JTextField textField_1;

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
	 * Inicjacja elementów panelu.
	 * G³ównie elementów z biblioteki Swing.
	 */
	private void inicjujKomponenty() {
		lblTest = new JLabel("Test");
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblTest.setFont(new Font("Arial", Font.BOLD, 16));
		lblTest.setBounds(0, 13, 1200, 20);
		add(lblTest);
		
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
		
		JLabel label = new JLabel("S\u0142owo");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Arial", Font.ITALIC, 16));
		label.setBounds(10, 123, 120, 25);
		add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(145, 123, 1040, 25);
		add(textField);
		
		JLabel label_1 = new JLabel("T\u0142umaczenie");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Arial", Font.ITALIC, 16));
		label_1.setBounds(10, 164, 120, 25);
		add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(145, 164, 1040, 25);
		add(textField_1);
	}
	
	/**
	 * Inicjacja pól klasy.
	 */
	private void inicjujPola() {
		//log
		log = LoggerFactory.getLogger(Test.class);
	}
}
