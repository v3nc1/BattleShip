package Controler;
/*
 * 
 * Kontroler komunicira s bazom podataka koja predstavlja 
 * skup objekata tipa Player i Game
 * 
 * @author Venci Ivos
 * @since rujan, 2018.
 
 *
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Game;
import Model.GameDB;
import Model.Player;

public class Controler {

	private Player player;
	private GameDB gameDB = new GameDB();
	private Game game;
	
	/*
	 * Metoda koja kreira objekt tipa Player
	 * i dodaje ga u bazu
	 * @param name je objekt tipa String i odredjuje
	 * 		ime igraca
	 */
	public void newPlayer(String name) {

		player = new Player(name);

		gameDB.addPlayer(player);

	}
	
	/*
	 * Metoda koja kreira objekt tipa Game i dodaje 
	 * ga u bazu igara
	 */
	public void newGame(Player player1, Player player2,int name) {

		game = new Game(player1, player2,name);

		gameDB.addGame(game);
	}
	/*
	 * Metoda koja dohvaca objekt Player
	 * po zadanom parametru
	 * @param name je objekt tipa String ime igraca
	 * @return objekt tipa Player
	 */
	public Player getPlayer(String name) {

		for (Player p : gameDB.getPlayerDB()) {

			if (name.equals(p.getPlayerName())) {

				return p;
			}

		}

		

		return null;

	}
	/*
	 * Metoda koja dohvaca objekt Game
	 * @param name tipa int jew ime igre
	 * @return objekt tipa Game
	 */
	public Game getGame(int name) {

		for (Game p : gameDB.getGameDB()) {

			if (name==(p.getGameName(name))) {

				return p;
			}

		}

	

		return null;

	}
	
	
	public ArrayList<Player> getPlayerDB() {

		return gameDB.getPlayerDB();
	}

	/*
	 * Player Geters and Seters
	 */
	
	public String getPlayerName(Player pl) {
		
		return pl.getPlayerName();
	}
	
	public int getPlayerLvl(Player pl) {
		
		return pl.getLvl();
	}
	
	public void setHit(Player pl1, int pl1Hits, Player pl2, int pl2Hits) {

		pl1.setShotsHit(pl1Hits);
		pl2.setShotsHit(pl2Hits);

	}

	public void setShots(Player pl1, int pl1Shot, Player pl2, int pl2Shot) {

		pl1.setShotsFired(pl1Shot);
		pl2.setShotsHit(pl2Shot);

	}

	public double gameAccuracy(int shots, int hits) {
		
		if(hits==0) {
			return 0;
		}else {
		return ((hits*100) / shots);
		}
	}

	public int getGameWon(Player pl) {
		
		return pl.getGameWon();
	}
	
	public int getGameLost(Player pl) {
		
		return pl.getGameLost();
	}

	public void gameWon(Player pl1, Player pl2) {

		pl1.setGameWon();
		pl2.setGameLost();
	}

	

	public double getPlayerAccuracy(Player pl) {

		return pl.getAccuracy();
	}

	/*
	 * Game Geters and Seters
	 */

	public Player getP1(Game game) {

		return game.getPlayer1();
	}

	public Player getP2(Game game) {

		return game.getPlayer2();
	}

	public void setP1Boards(Game game, int[] field) {

		game.setPlayer1Board(field);
	}

	public void setP2Boards(Game game, int[] field) {

		game.setPlayer2Board(field);
	}

	public boolean getPl1Field(Game game, int[] field) {

		return game.getPl1Field(field);
	}

	public boolean getPl2Field(Game game, int[] field) {

		return game.getPl2Field(field);
	}

	public void setPlayerHit(Game game, boolean flag) {

		if (flag) {
			game.setPl1Hit();
		} else {
			game.setPl2Hit();
		}
	}

	public int getPlayerHit(Game game, boolean flag) {

		if (flag) {
			return game.getPl1Hit();
		} else {
			return game.getPl2Hit();
		}

	}

	public void setPlayerShots(Game game, boolean flag) {

		if (flag) {
			game.setP1Shots();
		} else {
			game.setP2Shots();
		}
	}

	public int getPlayerShots(Game game, boolean flag) {

		if (flag) {
			return game.getP1Shots();
		} else {
			return game.getP2Shots();
		}
	}

	public void setPlayerDemage(Game game, boolean flag) {

		if (flag) {
			game.setP1Demage();
		} else {
			game.setP2Demage();
		}
	}

	public int getPlayerDemage(Game game, boolean flag) {

		if (flag) {
			return game.getP1Demage();
		} else {
			return game.getP2Demage();
		}
	}

	public void doTurn(Game game) {

		game.setTurn();
	}
	public int getTurn(Game game) {

		return game.getTurn();
	}
	
	/*
	 * Metoda koja sprema podatke o igracima u datoteku
	 */
	public void saveUsers() {

		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File(".\\users.db")))) {

			os.writeObject(gameDB.getPlayerDB());
			
			os.close();

		} catch (IOException e) {

			System.out.println("Users saved succesfully!");
		}
		

	}
	/*
	 * Metoda koja ucitava podatke o igracima iz datoteke
	 */
	public void openUsers() {

		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(new File(".\\users.db")))) {

			gameDB.setPlayerDB((ArrayList<Player>) is.readObject());
			
			is.close();

		} catch (IOException e) {

			System.out.println("No user file found...");
		} catch (ClassNotFoundException c) {

			System.out.println("Class error..");
		}

	}

}
