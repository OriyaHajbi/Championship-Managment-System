package Listener;

import java.util.ArrayList;

import Exception.ExceptionNotDigit;
import Exception.ExceptionPlayerExist;
import Exception.ExceptionTeco;

public interface ViewListenable {

	void addNewPlayer(String playerName) throws ExceptionPlayerExist;

	boolean playGame(String name, String text, String text2, int numberPlay) throws ExceptionTeco;

	void setResults(String text, String text2, ArrayList<String> resultsPlayerA, ArrayList<String> resultsPlayerB)
			throws ExceptionNotDigit;

	String getWinner(int i) throws ExceptionTeco;

	void setTypeGame(String sportName);

	int numPlayers();

	void removePlayer();

}
