package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira panel s ledendom kako izgleda koji brod 
 * i koliko treba postaviti brodova odredjenog tipa na plocu
 * 
 */
public class Ships extends JPanel {

	private JButton[] airCarrier;
	private JButton[] battleShip;
	private JButton[] cruiser;
	private JButton[] submarine;
	private JPanel airCarrierPanel;
	private JPanel battleShipPanel;
	private JPanel cruiserPanel;
	private JPanel submarinePanel;
	
	
	
	private int acSize = 4;
	private int bsSize = 3;
	private int crSize = 2;
	private int smSize = 1;

	public Ships() {
		
		
		createRun();
		layoutRun();

	}
	/*
	 * Metoda inicijalizira objekte klase
	 */
	private void createRun() {

		airCarrierPanel = new JPanel();
		battleShipPanel = new JPanel();
		cruiserPanel = new JPanel();
		submarinePanel = new JPanel();
		airCarrier = new JButton[acSize];
		battleShip = new JButton[bsSize];
		cruiser = new JButton[crSize];
		submarine = new JButton[smSize];

	}
	/*
	 * Metoda postavlja izgled panela, objekata i raspored polja
	 * 
	 */
	private void layoutRun() {
		
		setBackground(Color.BLACK);
		setLayout(new GridBagLayout());
		borderRun(this, "Fleet");

		airCarrierPanel.setLayout(new GridLayout(1, 4));
		airCarrierPanel.setBackground(Color.BLACK);
		borderRun(airCarrierPanel, "Carrier x 1");
		battleShipPanel.setLayout(new GridLayout(1, 3));
		battleShipPanel.setBackground(Color.BLACK);
		borderRun(battleShipPanel, "Battleship x 2");
		cruiserPanel.setLayout(new GridLayout(1, 3));
		cruiserPanel.setBackground(Color.BLACK);
		borderRun(cruiserPanel, "Cruiser x 3");
		submarinePanel.setLayout(new GridLayout(1, 4));
		submarinePanel.setBackground(Color.BLACK);
		borderRun(submarinePanel, "Submarine x 3");
		
		for (int f = 0; f < acSize; f++) {
			
			airCarrier[f]=new JButton();
			airCarrierPanel.add(airCarrier[f]);
			airCarrier[f].setEnabled(false);
			

		}

		for (int f = 0; f < bsSize; f++) {
			
			battleShip[f]=new JButton();
			battleShipPanel.add(battleShip[f]);
			battleShip[f].setEnabled(false);

		}

		for (int f = 0; f < crSize; f++) {
			
			cruiser[f]=new JButton();
			cruiserPanel.add(cruiser[f]);
			cruiser[f].setEnabled(false);

		}
		cruiserPanel.add(new JLabel("   "));

		for (int f = 0; f < smSize; f++) {
			
			submarine[f]=new JButton();
			submarinePanel.add(submarine[f]);
			submarine[f].setEnabled(false);
		}
		submarinePanel.add(new JLabel("   "));
		submarinePanel.add(new JLabel("   "));
		
		GridBagConstraints gc=new GridBagConstraints();
		
		gc.gridx=0;
		gc.gridy=0;
		
		gc.ipady=25;
		gc.anchor=gc.LINE_START;
		add(airCarrierPanel,gc);
		gc.gridy=1;
		add(battleShipPanel,gc);
		gc.gridy=2;
		add(cruiserPanel,gc);
		gc.gridy=3;
		gc.weightx=1.0;
		add(submarinePanel,gc);
		

	}

	/*
	 * Metoda kreira okvir objekta
	 *@param panel Objekt tipa Jpanel na koji treba dodati okvir
	 *@param name Objekt tipa String odredjuje naslov ovira 
	 */
	private void borderRun(JPanel panel, String name) {

		TitledBorder top = BorderFactory.createTitledBorder(name);
		Border bott = BorderFactory.createEmptyBorder(0, 20, 5, 20);
		
		top.setTitleColor(Color.GRAY);
		top.setTitleFont(new Font("Arial",Font.BOLD, 20));
		
		panel.setBorder(BorderFactory.createCompoundBorder(top, bott));

	}

}
