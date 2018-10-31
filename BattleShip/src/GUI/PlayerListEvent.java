package GUI;

import java.util.ArrayList;
import java.util.EventObject;

import Model.Player;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja sprema Listu korisnika pri dogadjaju
 */
public class PlayerListEvent extends EventObject {
	
	private ArrayList<Player> playerList;

	public PlayerListEvent(Object ob) {
		super(ob);
		
	}
	
	public void setPlayerList(ArrayList<Player> list) {
		
		playerList=list;
		
	}
	
	public ArrayList<Player> getPlayerList(){
		
		return playerList;
	}

	

}
