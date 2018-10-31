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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira mrezu za rasporedjivanje flote.
 */
public class DeploymentBattleField extends JPanel {

	private final int chkCounter=19;
	private int counter;
	private final int gridSize = 8;
	private JToggleButton[][] btn;
	private String name;
	private Font lblFont;
	private Font btnFont;
	private boolean btnState;
	private DeploymentListener deployLst;
	

	public DeploymentBattleField() {

		// setVisible(true);

		createRun();
		layoutRun();

	}
	
	public void setListener(DeploymentListener dl) {
		
		deployLst=dl;
	}
	/*
	 * Metoda za inicijalizaciju objekata
	 */
	private void createRun() {

		btn = new JToggleButton[gridSize][gridSize];
		btnState=true;
		lblFont=new Font("Arial", Font.BOLD, 15);
		btnFont=new Font("Arial", Font.PLAIN, 1);
		name = "Game";
		counter=0;

	}
	/*
	 * Metoda za raspored, inicijalizaciju botuna i podesavanje
	 * ActionListenera za svaki botun
	 */
	private void layoutRun() {

		setBackground(Color.BLACK);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		borderRun();

		for (int x = 0; x < gridSize; x++) {


			String ch = Character.toString((char) (x + 65));
			gc.gridx = x + 1;
			gc.gridy = 0;
			JLabel tempLabel2 = new JLabel(ch, JLabel.CENTER);
			tempLabel2.setForeground(Color.LIGHT_GRAY);
			tempLabel2.setFont(lblFont);

			add(tempLabel2, gc);
		}

		for (int x = 0; x < gridSize; x++) {		

			
				gc.gridx = 0;
				gc.gridy = x+1;
				
				JLabel tempLabel = new JLabel(" " + (x+1), JLabel.CENTER);
				tempLabel.setForeground(Color.LIGHT_GRAY);
				tempLabel.setFont(lblFont);
				add(tempLabel, gc);
			
			
			for (int y = 0; y < gridSize; y++) {
				
				
				gc.gridx = x + 1;
				gc.gridy = y + 1;
				gc.weightx=0.5;
				gc.weighty=0.5;
				gc.fill=gc.BOTH;
				
				btn[x][y] = new JToggleButton(x + "," + y);
				btn[x][y].setFont(btnFont);
				btn[x][y].setFocusable(false);
				buttonColor(x, y);
				
				add(btn[x][y], gc);

				btn[x][y].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent value) {

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
						if(btn[x][y].isSelected()) {
							clickedButtonColor(x, y);
							counter++;
							
						}else {
							buttonColor(x, y);
							counter--;
						}
						
						if(counter==chkCounter) {
							
							deployLst.deploymentStatus(true);
						}else {
							
							deployLst.deploymentStatus(false);
						}
						

					}
				});

			}

		}

	}
	/*
	 * Metoda za postavljanje default boje botuna
	 */
	private void buttonColor(int x,int y) {
		
		btn[x][y].setBackground(Color.BLACK);
		btn[x][y].setForeground(Color.BLACK);
	}
	/*
	 * Metoda zapostavljanje boje odabranog botuna 
	 */
	private void clickedButtonColor(int x,int y) {
		
		btn[x][y].setBackground(Color.LIGHT_GRAY);
		btn[x][y].setForeground(Color.LIGHT_GRAY);
	}
	
	/*
	 * Metoda za resetiranje botuna
	 */
	public void resetButtons() {

		for (int x = 0; x < gridSize; x++) {

			for (int y = 0; y < gridSize; y++) {
				
				btn[x][y].setSelected(false);
				buttonColor(x, y);

			}
		}
	}
	
	/*
	 * Metoda koja dohvaca koordinatu botuna
	 * @param value Objekt tipa String je tekst botuna gdje je 
	 * 				upisana njegova koordinata
	 * @return parametar tipa int[] koji vraca koordinatu botuna
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
	 * Metoda koja provjerava dali je botun na odredjenoj koordinati odabran
	 * @param x tipa int odredjuje x vrijednost tocke
	 * @param y tipa int odredjuje y vrijednost tocke
	 * @return parametar tipa boolean 
	 */
	public boolean getButton(int x,int y) {
		
		return btn[x][y].isSelected();
	}
	
	/*
	 * Metoda koja kreira okvir objekta
	 */
	private void borderRun() {

		TitledBorder top = BorderFactory.createTitledBorder(name);
		Border bott = BorderFactory.createEmptyBorder(10, 0, 10, 5);

		top.setTitleColor(Color.GRAY);
		top.setTitleFont(new Font("Arial", Font.BOLD, 20));
		setBorder(BorderFactory.createCompoundBorder(top, bott));
	}

}
