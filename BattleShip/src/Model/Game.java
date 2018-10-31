package Model;

/*
 * Klasa koja kreira objekt igra
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 *  
 */

public class Game {
	
	private int gameName;
	private Player player1;
	private boolean[][] pl1Board;
	private int pl1Hit;
	private int p1Demage;
	private int p1Shot;
	private Player player2;
	private boolean[][] pl2Board;
	private int pl2Hit;
	private int p2Demage;
	private int p2Shot;

	private int turn;
	
	/*
	 * Konstruktor klase 
	 * @param player1 objekt tipa Player
	 * @param player2 objekt tipa Player
	 * @param gameName parametar tipa int ime igre
	 */
	public Game(Player player1, Player player2,int gameName) {
		
		this.gameName=gameName;
		this.player1 = player1;
		pl1Board = new boolean[8][8];
		pl1Hit = 0;
		p1Demage = 0;
		p1Shot = 0;
		this.player2 = player2;
		pl2Board = new boolean[8][8];
		pl2Hit = 0;
		p2Demage = 0;
		p2Shot = 0;

		turn = 1;

	}
	
	public int getGameName(int name) {
		
		return gameName;
	}

	public int getPl1Hit() {
		return pl1Hit;
	}

	public void setPl1Hit() {
		pl1Hit++;
	}

	public int getP1Demage() {
		return p1Demage;
	}

	public void setP1Demage() {
		p1Demage++;
	}
	
	public int getP1Shots() {
		return p1Shot;
	}
	public void setP1Shots() {

		p1Shot++;
	}

	public int getPl2Hit() {
		return pl2Hit;
	}

	public void setPl2Hit() {
		pl2Hit++;
	}

	public int getP2Demage() {
		return p2Demage;
	}

	public void setP2Demage() {
		p2Demage++;
	}
	public int getP2Shots() {
		return p2Shot;
	}
	

	public void setP2Shots() {

		p2Shot++;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn() {
		turn++;
	}

	public void setPlayer1Board(int field[]) {

		pl1Board[field[0]][field[1]] = true;

	}

	public void setPlayer2Board(int field[]) {

		pl2Board[field[0]][field[1]] = true;

	}

	public boolean getPl1Field(int field[]) {
		return pl1Board[field[0]][field[1]];
	}

	public boolean getPl2Field(int field[]) {
		return pl2Board[field[0]][field[1]];
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

}
