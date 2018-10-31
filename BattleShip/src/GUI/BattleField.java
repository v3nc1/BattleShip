package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira mrezu botuna za igru
 * 
 */
public class BattleField extends JPanel {

	private final int size = 8;
	private JButton[][] btn;
	private String name;
	private Font lblFont;
	private Font btnFont;
	private ButtonListener bl;

	public BattleField(JPanel panel,String title) {
		
		super();

		// setVisible(true);
		name=title;
		createRun();
		layoutRun();

	}
	/*
	 * Metoda koja inicijalizira objekte klase
	 */
	private void createRun() {

		btn = new JButton[size][size];
		lblFont=new Font("Arial", Font.BOLD, 15);
		btnFont=new Font("Arial", Font.PLAIN, 1);
		

	}
	/*
	 * Metoda koja kreira mrezu inicijalizacijom objekata ,rasporedom 
	 * i postavljanjem ActionListenera na objekt
	 */
	private void layoutRun() {

		setBackground(Color.BLACK);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		borderRun();

		for (int x = 0; x < size; x++) {

			String ch = Character.toString((char) (x + 65));
			gc.gridx = x + 1;
			gc.gridy = 0;
			JLabel tempLabel2 = new JLabel(ch, JLabel.CENTER);
			tempLabel2.setForeground(Color.LIGHT_GRAY);
			tempLabel2.setFont(lblFont);

			add(tempLabel2, gc);

		}

		for (int x = 0; x < size; x++) {

			gc.gridx = 0;
			gc.gridy = x + 1;
			gc.weightx=1.0;
			gc.weighty=1.0;
			gc.ipadx=10;
			gc.ipady=25;
			gc.fill=gc.BOTH;

			JLabel tempLabel = new JLabel(" " + (x + 1), JLabel.CENTER);
			tempLabel.setForeground(Color.LIGHT_GRAY);
			tempLabel.setFont(lblFont);
			add(tempLabel, gc);

			for (int y = 0; y < size; y++) {

				gc.gridx = x + 1;
				gc.gridy = y + 1;
				
				
				btn[x][y] = new JButton(x + "," + y);
				btn[x][y].setFont(btnFont);
				btn[x][y].setFocusable(false);
				setButtonColor(x, y);
				add(btn[x][y], gc);

				btn[x][y].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent value) {

						ButtonEvent be = new ButtonEvent(this);
						int x = 0;
						int y = 0;
						boolean br = true;

						for (int n : getCoords(value.getActionCommand())) {

							if (br) {
								x = n;
								br = false;
							} else {
								y = n;
							}

						}
						be.setXY(x, y);

						bl.buttonClicked(be);

					}
				});

			}

		}

	}
	
	/*
	 * Metoda koja zakljicava mrezu
	 */
	public void lockButtons() {
		
		for (int x = 0; x < size; x++) {

			for (int y = 0; y < size; y++) {
				
				btn[x][y].setEnabled(false);
				
				
				

			}
		}
		
		
	}
	/*
	 * Metoda koja otkljucava mrezu
	 */
	public void unlockButtons() {
		
		
			
			for (int x = 0; x < size; x++) {

				for (int y = 0; y < size; y++) {

					btn[x][y].setEnabled(true);
					

				}
			}	
	}
	
	/*
	 * Metoda koja resetira stanje svih botuna
	 */
	public void resetButtons() {

		for (int x = 0; x < size; x++) {

			for (int y = 0; y < size; y++) {

				setButtonColor(x, y);
				

			}
		}
	}
	
	/*
	 * Metoda koja postavlja default boju polja
	 */
	
	public void setButtonColor(int x,int y) {
		
		btn[x][y].setBackground(Color.BLACK);
		btn[x][y].setForeground(Color.BLACK);
		
	}
	/*
	 * Metoda koja postavlja boju polja u slucaju pogotka
	 */
	public void setButtonHit(int[] field) {

		btn[field[0]][field[1]].setBackground(Color.red);
		btn[field[0]][field[1]].setForeground(Color.red);
	}
	/*
	 * Metoda koja postavlja boju polja u slucaju promasaja
	 */
	
	public void setButtonMiss(int[] field) {

		btn[field[0]][field[1]].setBackground(Color.blue);
		btn[field[0]][field[1]].setForeground(Color.blue);
	}

	public void setListener(ButtonListener bl) {

		this.bl = bl;
	}
	
	/*
	 * Metoda koja odredjuje koordinate botuna
	 * 
	 * @param value Objekt tipa String je tekst botuna 
	 * 					gdje su upisane koordinate
	 * @return parametar tipa int[] koji sadrzi koordinatu 
	 */
	private int[] getCoords(String value) {

		int br = 0;
		String[] spliter;
		int xy[] = new int[2];

		spliter = value.split(",");

		for (String n : spliter) {

			xy[br] = Integer.parseInt(n);
			br++;
		}

		return xy;
	}
	/*
	 * Metoda za postavljanje okvira objekta
	 */
	private void borderRun() {

		TitledBorder top = BorderFactory.createTitledBorder(name);
		Border bott = BorderFactory.createEmptyBorder(10, 0, 10, 5);

		top.setTitleColor(Color.GRAY);
		top.setTitleFont(new Font("Arial", Font.BOLD, 20));
		setBorder(BorderFactory.createCompoundBorder(top, bott));
	}

}
