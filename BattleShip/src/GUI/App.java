package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controler.Controler;

public class App {
	
	/*
	 * Klasa koja pokrece program.
	 * @author Venci Ivos
	 * @since rujan, 2018.
	 * 
	 */
	

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new StartFrame();
			}
		});

	}

}
