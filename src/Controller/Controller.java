package Controller;

import java.util.ArrayList;

import Exception.ExceptionNotDigit;
import Exception.ExceptionPlayerExist;
import Exception.ExceptionTeco;
import Listener.ModelListenable;
import Listener.ViewListenable;
import Model.Championship;
import View.Viewable;

public class Controller implements ModelListenable, ViewListenable {

	private Championship model;
	private Viewable view;

	public Controller(Championship model, Viewable view) {
		this.model = model;
		this.view = view;

		model.registerListener(this);
		view.registerListener(this);
	}

	@Override
	public void addNewPlayer(String playerName) throws ExceptionPlayerExist {
			model.addPlayer(playerName);
		}	

	@Override
	public boolean playGame(String sportType, String namePlayerA, String namePlayerB, int numberPlay) throws ExceptionTeco
			 {
		return model.theWinnerIs(sportType, namePlayerA, namePlayerB, numberPlay);

	}

	@Override
	public void setResults(String namePlayerA, String namePlayerB, ArrayList<String> resultsPlayerA,
			ArrayList<String> resultsPlayerB) throws ExceptionNotDigit {
		model.setResult(namePlayerA, namePlayerB, resultsPlayerA, resultsPlayerB);

	}

	@Override
	public String getWinner(int i) throws ExceptionTeco {
		String string = model.getWinner(i);
		if (string.equalsIgnoreCase(""))
			throw new ExceptionTeco("doesnt have a winner");
		return model.getWinner(i);
	}

	@Override
	public void setTypeGame(String sportName) {
		model.setType(sportName);
	}

	@Override
	public int numPlayers() {
		return model.getCountPlayers();
	}

	@Override
	public void removePlayer() {
		model.removePlayer();
		view.removePlayer();
		
	}

	@Override
	public void updatePlayers(String playerName) {
		view.updatePlayers(playerName);		
	}

	@Override
	public void maxPlayers() {
		view.maxPlayers("The place is full");		
	}

	@Override
	public void updateTeamIsNotFull() {
		view.updateTeamIsNotFull();
		
	}

}
