package Model;


public class Round<T extends Sport> {

	public enum eType {
		Tennis, Soccer, BasketBall
	};
	
	public eType type;
	
	public Sport typeGame;


	public Player[] players = new Player[2];
	public Player theWinner;

	public Round(Player playerA, Player playerB , Sport sportType) {
		players[0] = playerA;
		players[1] = playerB;
		typeGame=sportType;
	}
	
	public int theWinner(Player playerA, Player playerB) {
		return typeGame.playGame(playerA, playerB);
	}

	public void setWinner(Player otherPlayer) {
		theWinner = otherPlayer;
	}
	
	public void setType(eType otherType) {
		type=otherType;
	}
	
	public eType getType() {
		return type;
	}
	
	public Player getPlayerA() {
		return players[0];
	}
	
	public Player getPlayerB() {
		return players[1];
	}

}
