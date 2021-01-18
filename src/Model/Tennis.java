package Model;


public class Tennis extends Sport {

	public Tennis() {

	}

	@Override
	public int playGame(Player playerA, Player playerB) {
		int pointPlayerA = 0;
		int pointPlayerB = 0;
		for (int i = 0; i < playerA.getResult().size(); i++) {
			if (playerA.getResult().get(i) > playerB.getResult().get(i))
				pointPlayerA++;
			if (playerA.getResult().get(i) < playerB.getResult().get(i))
				pointPlayerB++;
		}		
		int res = pointPlayerA - pointPlayerB;
		
		if ( res == 3 || res == 1 )
			return 1;
		else if (res == -3 || res == -1)
			return -1;
		else
			return 0;
	}

}
