package Model;

public class Soccer extends Sport {

	public Soccer() {

	}

	@Override
	public int playGame(Player playerA, Player playerB) {
		int pointPlayerA = 0;
		int pointPlayerB = 0;
			for (int i = 0; i < 2; i++) {
				pointPlayerA += playerA.getResult().get(i);
				pointPlayerB += playerB.getResult().get(i);
			}
			if (pointPlayerA == pointPlayerB) {
				pointPlayerA += playerA.getResult().get(2);
				pointPlayerB += playerB.getResult().get(2);
			}
			if (pointPlayerA == pointPlayerB) {
				pointPlayerA += playerA.getResult().get(3);
				pointPlayerB += playerB.getResult().get(3);
			}
		if (pointPlayerA > pointPlayerB)
			return 1;
		else if (pointPlayerA < pointPlayerB)
			return -1;
		else
			return 0;
	}

}
