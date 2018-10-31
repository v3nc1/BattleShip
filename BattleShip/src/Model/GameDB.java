package Model;

import java.util.ArrayList;
/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira objekte za spremanje podataka 
 * 
 */
public class GameDB {
	
	private ArrayList<Game> gameDB;
	private ArrayList<Player> playerDB;
	
	
	public GameDB() {
		
		gameDB=new ArrayList<>();
		playerDB=new ArrayList<>();
		
	}


	public ArrayList<Game> getGameDB() {
		return gameDB;
	}



	public ArrayList<Player> getPlayerDB() {
		return playerDB;
	}
	
	public void addPlayer(Player player) {
		
		playerDB.add(player);
	}
	
	public void addGame(Game game) {
		
		gameDB.add(game);
	}
	
	public void setGameDB(ArrayList<Game> gameDB) {
		this.gameDB = gameDB;
	}

	public void setPlayerDB(ArrayList<Player> playerDB) {
		this.playerDB = playerDB;
	}
	
	

}
