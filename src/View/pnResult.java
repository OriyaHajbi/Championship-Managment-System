package View;

import java.util.ArrayList;

import Listener.ViewListenable;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class pnResult extends Pane {
	public ArrayList<TextField> tfPlayerA = new ArrayList<>();
	public ArrayList<TextField> tfPlayerB = new ArrayList<>();

	public ArrayList<String> resultsPlayerA = new ArrayList<>();
	public ArrayList<String> resultsPlayerB = new ArrayList<>();
	public Button btnDone = new Button("Done");

	public pnResult(ViewListenable l, String sportName, String playerA, String playerB) {

		Pane hTop = new Pane();
		Text lblSportType = createText(sportName + " Game");
		lblSportType.setLayoutX(30);
		lblSportType.setLayoutY(35);

		Label lblNamePlayerA = new Label(playerA);
		lblNamePlayerA.setLayoutX(20);
		lblNamePlayerA.setLayoutY(85);

		Label lblNamePlayerB = new Label(playerB);
		lblNamePlayerB.setLayoutX(20);
		lblNamePlayerB.setLayoutY(125);

		hTop.getChildren().addAll(lblSportType, lblNamePlayerA, lblNamePlayerB, btnDone);

		for (int i = 0; i < 5; i++) {
			tfPlayerA.add(new TextField());
			tfPlayerB.add(new TextField());
			tfPlayerA.get(i).setPrefWidth(40);
			tfPlayerB.get(i).setPrefWidth(40);
			hTop.getChildren().addAll(tfPlayerA.get(i), tfPlayerB.get(i));
		}
		tfPlayerA.get(0).setLayoutX(50);
		tfPlayerA.get(0).setLayoutY(80);

		tfPlayerA.get(1).setLayoutX(100);
		tfPlayerA.get(1).setLayoutY(80);

		tfPlayerA.get(2).setLayoutX(150);
		tfPlayerA.get(2).setLayoutY(80);

		tfPlayerA.get(3).setLayoutX(200);
		tfPlayerA.get(3).setLayoutY(80);

		tfPlayerA.get(4).setLayoutX(250);
		tfPlayerA.get(4).setLayoutY(80);

		tfPlayerB.get(0).setLayoutX(50);
		tfPlayerB.get(0).setLayoutY(120);

		tfPlayerB.get(1).setLayoutX(100);
		tfPlayerB.get(1).setLayoutY(120);

		tfPlayerB.get(2).setLayoutX(150);
		tfPlayerB.get(2).setLayoutY(120);

		tfPlayerB.get(3).setLayoutX(200);
		tfPlayerB.get(3).setLayoutY(120);

		tfPlayerB.get(4).setLayoutX(250);
		tfPlayerB.get(4).setLayoutY(120);

		btnDone.setLayoutX(120);
		btnDone.setLayoutY(220);

		this.getChildren().add(hTop);

	}

	private Text createText(String string) {
		Text text = new Text(string);
		text.setFill(Color.DIMGRAY);
		text.setTextOrigin(VPos.CENTER);
		text.setFont(Font.font("Cooper Black", FontWeight.BOLD, 40));
		return text;
	}

	public ArrayList<TextField> getTfPlayerB() {
		return tfPlayerB;
	}

	public ArrayList<TextField> getTfPlayerA() {
		return tfPlayerA;
	}

	public ArrayList<String> getResultsPlayerA() {
		return resultsPlayerA;
	}

	public ArrayList<String> getResultsPlayerB() {
		return resultsPlayerB;
	}

	public Button getButtonDone() {
		return btnDone;
	}

}
