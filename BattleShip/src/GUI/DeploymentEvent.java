package GUI;

import java.util.EventObject;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja sprema podatke pri dogadjaju postavljanja brodova na plocu korisnika
 * 
 */

public class DeploymentEvent extends EventObject {
	
	private boolean[][] playerField;
	private String player;
	private final boolean flag=true;

	public DeploymentEvent(Object ob) {
		super(ob);
		playerField=new boolean[8][8];
		
	}

	public boolean[][] getPlayerField() {
		return playerField;
	}
	
	/*
	 * Metoda postavlja stanje polja na odabrano
	 * @param x
	 * 			tipa int odredjuje x koordinatu
	 * @param y 
	 * 			tipa int odredjuje y koordinatu
	 * @param value
	 * 			tipa boolean odredjuje vrijednost polja 
	 * 
	 */
	
	
	public void setPlayerField(int x,int y,boolean value) {
		playerField[x][y] = value;
	}
	
	public void setPlayer(String name) {
		
		player=name;
	}
	
	public String getPlayer() {
		
		return player;
	}
	
	public boolean getFlag() {
		
		return flag;
	}
	

}
