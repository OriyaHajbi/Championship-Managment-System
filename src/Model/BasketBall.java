package Model;

public class BasketBall extends Sport {
	
	public BasketBall() {
		
	}

	@Override
	public int playGame(Player playerA, Player playerB) {
		int pointPlayerA = 0;
		int pointPlayerB = 0;
			for (int i = 0; i < playerB.getResult().size()-1 ; i++) {
				pointPlayerA += playerA.getResult().get(i);
				pointPlayerB += playerB.getResult().get(i);
			}

		if (pointPlayerA > pointPlayerB)
			return 1;
		else if (pointPlayerA < pointPlayerB)
			return -1;
		else
			return 0;
	}

}
