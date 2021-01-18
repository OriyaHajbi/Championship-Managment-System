package Model;

import java.util.ArrayList;
import Exception.ExceptionNotDigit;
import Exception.ExceptionPlayerExist;
import Exception.ExceptionTeco;
import Listener.ModelListenable;
import Model.Round.eType;

public class Championship {

	public ArrayList<Player> players;

	public Sport typeGame;

	public Player[] winners;
	public static int winnerCounter = 0;

	public ArrayList<ModelListenable> allListeners;

	public Championship() {

		players = new ArrayList<Player>();
		allListeners = new ArrayList<ModelListenable>();
		winners = new Player[7];

	}

	public void setType(String type) {
		if (type.equalsIgnoreCase("Soccer"))
			typeGame = new Soccer();
		if (type.equalsIgnoreCase("Tennis"))
			typeGame = new Tennis();
		if (type.equalsIgnoreCase("BasketBall"))
			typeGame = new BasketBall();
	}

	public Player searchPlayer(String playerName) {

		Player p = null;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getName().equalsIgnoreCase(playerName))
				p = players.get(i);
		}
		return p;
	}

	/*public boolean addPlayer(String playerName) throws ExceptionPlayerExist {
		Player player = new Player(playerName);

		if (players.contains(searchPlayer(playerName)))
			throw new ExceptionPlayerExist("this player exist, please rename");

		if (players.size() < 8) {
			players.add(player);
			return true;
		}
		return false;
	} */

	public boolean addPlayer(String playerName) throws ExceptionPlayerExist {

		if (players.size() == 8) {
			for (ModelListenable l : allListeners) {
				l.maxPlayers();
			}
			return false;
		}
		
		if (players.contains(searchPlayer(playerName))) {
			throw new ExceptionPlayerExist("this player exist, please rename");
		}

		players.add(new Player(playerName));
		for (ModelListenable l : allListeners) {
			l.updatePlayers(playerName);;
		}
		return true;
	} 


	public void removePlayer() {

		if (players.size() != 0) {
			players.remove(players.size()-1);
			if(players.size()<8) 
				for (ModelListenable l : allListeners) {
					l.updateTeamIsNotFull();
			}
		}
	}


	public void setResult(String playerA, String playerB, ArrayList<String> resultsPlayerA,
			ArrayList<String> resultsPlayerB) throws ExceptionNotDigit {
		Player aPlayer = searchPlayer(playerA);
		Player bPlayer = searchPlayer(playerB);

		ArrayList<Integer> aResult = new ArrayList<Integer>();
		ArrayList<Integer> bResult = new ArrayList<Integer>();
		int num1;
		int num2;

		for (int i = 0; i < 5; i++) {

			if (resultsPlayerA.get(i).equalsIgnoreCase("")) {
				num1 = 0;
			} else if (!isDigit(resultsPlayerA.get(i))) {
				throw new ExceptionNotDigit("enter only digit, please enter again the result");
			} else {
				num1 = Integer.parseInt(resultsPlayerA.get(i));
			}

			if (resultsPlayerB.get(i).equalsIgnoreCase("")) {
				num2 = 0;
			} else if (!isDigit(resultsPlayerB.get(i))) {
				throw new ExceptionNotDigit("enter only digit, please enter again the result");
			} else {
				num2 = Integer.parseInt(resultsPlayerB.get(i));
			}

			aResult.add(num1);
			bResult.add(num2);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).equals(searchPlayer(playerA)))
				players.get(i).setResult(aResult);

			if (players.get(i).equals(searchPlayer(playerB)))
				players.get(i).setResult(bResult);
		}
	}

	private boolean isDigit(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!(Character.isDigit(string.charAt(i))))
				return false;
		}
		return true;
	}

	public boolean theWinnerIs(String sportType, String aPlayer, String bPlayer, int numberPlay) throws ExceptionTeco { /// to
		Round r = null;
		Player playerA = searchPlayer(aPlayer);
		Player playerB = searchPlayer(bPlayer);

		if (sportType.equalsIgnoreCase("Tennis")) { // Tennis
			r = new Round<Tennis>(playerA, playerB, typeGame);
			r.setType(eType.Tennis);
		} else if (sportType.equalsIgnoreCase("Soccer")) { /// Soccer
			r = new Round<Soccer>(playerA, playerB, typeGame);
			r.setType(eType.Soccer);
		} else if (sportType.equalsIgnoreCase("BasketBall")) { // BasketBall
			r = new Round<BasketBall>(playerA, playerB, typeGame);
			r.setType(eType.BasketBall);
		}

		int playerWinner = r.theWinner(playerA, playerB);

		switch (playerWinner) {
		case 1: // player a winner
			winners[numberPlay] = playerA;
			return true;
		case -1: // player b winner
			winners[numberPlay] = playerB;
			return true;
		case 0: // no winners ---> teco
			throw new ExceptionTeco("Téhere is no winner / There is a teco \nPlease enter again the result");
		}
		return false;
	}

	public void registerListener(ModelListenable l) {
		allListeners.add(l);
	}

	public String getWinner(int i) {
		if (winners[i] == null)
			return "";
		return winners[i].getName();

	}

	public int getCountPlayers() {
		return players.size();
	}

}
