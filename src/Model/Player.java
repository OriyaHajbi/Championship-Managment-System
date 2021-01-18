package Model;

import java.util.ArrayList;

public class Player {
	
	public String playerName;
	public boolean isWinner = false;
	protected ArrayList<Integer>  resultGames = new ArrayList<>();

	
	public Player(String player) {
		playerName=player;
	}
	
	public void setWinner() {
		isWinner=true;
	}
	
	public String getName() {
		return playerName;
	}
	
	public void setResult(ArrayList<Integer> result) {
		resultGames = result;
	}
	
	public ArrayList<Integer> getResult(){
		return resultGames;
	}
	
	public String toString() {
		return "The player: " + playerName;
	}

}
