package Model;

import java.io.Serializable;
/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira objekt Igrac
 * 
 */

public class Player implements Serializable{
	
	private String playerName;
	private int lvl;
	private int gameWon;
	private int gameLost;
	private int shotsFired;
	private int shotsHit;
	private double accuracy;
	
	/*
	 * Konstruktor klase
	 * @param playerName Objekt tipa String jer ime igraca
	 */
	public Player(String playerName) {
		
		
		this.playerName = playerName;
		this.lvl = 0;
		this.gameWon = 0;
		this.gameLost = 0;
		this.shotsFired = 0;
		this.shotsHit = 0;
		this.accuracy = 0;
	}
	public String getPlayerName() {
		return playerName;
	}
	
	public int getLvl() {
		return lvl;
	}
	public void setLvl() {
		this.lvl++;
	}
	public int getGameWon() {
		return gameWon;
	}
	/*
	 * Metoda postavlja broj dobivenih igara
	 * i inkrementira level igraca svakih 5 pobjeda
	 */
	public void setGameWon() {
		this.gameWon++;
		if(gameWon%5==0) {
			setLvl();
		}
	}
	public int getGameLost() {
		return gameLost;
	}
	public void setGameLost() {
		this.gameLost++;
	}
	public int getShotsFired() {
		return shotsFired;
	}
	public void setShotsFired(int shotsFired) {
		this.shotsFired += shotsFired;
	}
	public int getShotsHit() {
		return shotsHit;
	}
	public void setShotsHit(int shotsHit) {
		this.shotsHit += shotsHit;
	}
	public double getAccuracy() {
		return accuracy = shotsFired/shotsHit;
	}
	
	

}
