package GUI;

import java.util.EventObject;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja sprema podatke pri dogadjaju pritisnutog dugmeta na polju.
 */

public class ButtonEvent extends EventObject {

	private int[] xy;

	public ButtonEvent(Object arg0) {
		super(arg0);
		xy=new int[2];

	}
	
	/*
	 * Metoda preko koje se spremaju koordinate dugmeta
	 * 
	 * @param x 
	 * 				vrijednost tipa int odredjuje tocku x koordinate
	 * @param y
	 * 				vrijednost tipa int odredjuje tocku y koordinate
	 * 
	 */

	public void setXY(int x, int y) {

		xy[0] = x;
		xy[1] = y;

	}
	
	public int[] getXY() {
		
		return xy;
	}

}
