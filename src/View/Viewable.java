package View;

import Listener.ViewListenable;

public interface Viewable {
	
	void registerListener(ViewListenable l);

	void updatePlayers(String playerName);
	
	void maxPlayers(String string);

	void messageException(String message);

	void removePlayer();

	void updateTeamIsNotFull();
		
	

}
