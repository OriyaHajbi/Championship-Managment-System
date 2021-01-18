package View;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import Exception.ExceptionNotDigit;
import Exception.ExceptionPlayerExist;
import Exception.ExceptionTeco;
import Listener.ViewListenable;
import javafx.animation.Animation;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewJavaFx implements Viewable {
	private ArrayList<ViewListenable> allListeners = new ArrayList<ViewListenable>();

	private BorderPane bpRoot;
	private ArrayList<TextField> allPlayers;
	private ArrayList<Button> btPlays;

	public static int counterPlayers = 0;

	///////////////////////////
	HBox hPlayers = new HBox();
	Label lblPlayerName = new Label("Player name: ");
	TextField tfPlayerName = new TextField();
	Button btAddPlayer = new Button("Add Participan");
	Button btRemovePlayer = new Button("Remove Participan");
	Button btStartGame = new Button("Start Championship");
	Label lblSportType = new Label("Sport Type: ");
	ToggleGroup tglSport = new ToggleGroup();
	RadioButton rdTennis = new RadioButton("Tennis");
	RadioButton rdSoccer = new RadioButton("Soccer");
	RadioButton rdBasketball = new RadioButton("Basketball");
	/////////////////////////////

	private pnResult pnGame;
	private Stage stage;

	private Pane pnChampion = new Pane();

	Random random = new Random();

	///////////////////////////////
	private final int FIRST_GAME = 0;
	private final int SECOND_GAME = 1;
	private final int THIRD_GAME = 2;
	private final int FOURTH_GAME = 3;
	private final int FIFTH_GAME = 4;
	private final int SIXTH_GAME = 5;
	private final int SEVENTH_GAME = 6;
	///////////////////////////////

	public ViewJavaFx(Stage theStage) {
		bpRoot = new BorderPane();
		bpRoot.setPadding(new Insets(20));
		bpRoot.setTop(VBoxTop(createText("Championship")));

		bpRoot.setLeft(createPlayers());
		bpRoot.setBottom(addPlayers());

		btStartGame.setDisable(true);
		btAddPlayer.setDisable(true);
		btRemovePlayer.setDisable(true);
		tfPlayerName.setDisable(true);

		rdSoccer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openAddPlayer();
			}
		});

		rdTennis.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openAddPlayer();
			}
		});

		rdBasketball.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openAddPlayer();

			}
		});

		btRemovePlayer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				allListeners.get(0).removePlayer();
				if (allListeners.get(0).numPlayers() == 0)
					btRemovePlayer.setDisable(true);
			}
		});

		btAddPlayer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String playerName = tfPlayerName.getText();
				tfPlayerName.clear();
				if (!playerName.equals(""))
					try {
						allListeners.get(0).addNewPlayer(playerName);
					} catch (ExceptionPlayerExist e) {
						messageException(e.getMessage());
					}
				else
					JOptionPane.showMessageDialog(null, "You must enter a name");

				if (allListeners.get(0).numPlayers() == 8) {
					btStartGame.setDisable(false);
					btAddPlayer.setDisable(true);
					tfPlayerName.setDisable(true);
				}
				if (allListeners.get(0).numPlayers() != 0)
					btRemovePlayer.setDisable(false);
				else
					btRemovePlayer.setDisable(true);

			}
		});

		btStartGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				btRemovePlayer.setDisable(true);
				btStartGame.setDisable(true);
				rdBasketball.setDisable(true);
				rdSoccer.setDisable(true);
				rdTennis.setDisable(true);

				allListeners.get(0).setTypeGame(getSportName());

				bpRoot.setCenter(startGame2());
				bpRoot.setTop(VBoxTop(createText(getSportName() + " tournament")));

				for (int i = 4; i < 7; i++) {
					btPlays.get(i).setDisable(true);
				}

				btPlays.get(FIRST_GAME).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						showPopUp(0, 1, 0, 8, 0, 0);
					}
				});

				btPlays.get(SECOND_GAME).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						showPopUp(2, 3, 1, 9, 1, 1);
					}
				});

				btPlays.get(THIRD_GAME).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						showPopUp(4, 5, 2, 10, 2, 2);
					}
				});

				btPlays.get(FOURTH_GAME).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						showPopUp(6, 7, 3, 11, 3, 3);
					}
				});

				btPlays.get(FIFTH_GAME).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						showPopUp(8, 9, 4, 12, 4, 4);
					}
				});

				btPlays.get(SIXTH_GAME).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						showPopUp(10, 11, 5, 13, 5, 5);
					}
				});

				btPlays.get(SEVENTH_GAME).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						showPopUp(12, 13, 6, 14, 6, 6);
					}
				});
			}

		});

		Scene scene = new Scene(bpRoot, 800, 600);
		theStage.setScene(scene);
		theStage.setTitle("Championship");
		theStage.show();
	}

	private void semifinals() {
		if (!allPlayers.get(12).getText().equalsIgnoreCase("") && !allPlayers.get(13).getText().equalsIgnoreCase("")) {
			btPlays.get(6).setDisable(false);
		}
	}

	private void quarterfinal() {
		if (!allPlayers.get(8).getText().equalsIgnoreCase("") && !allPlayers.get(9).getText().equalsIgnoreCase("")
				&& !allPlayers.get(10).getText().equalsIgnoreCase("")
				&& !allPlayers.get(11).getText().equalsIgnoreCase("")) {
			btPlays.get(4).setDisable(false);
			btPlays.get(5).setDisable(false);
		}
	}

	public void show() throws FileNotFoundException {
		AnchorPane root = new AnchorPane();
		Circle c[] = new Circle[2000];
/*		FileInputStream inputstream = new FileInputStream(
				"C:\\Users\\Oriya Hajbi\\eclipse-workspace\\Championship\\winner.jpg");
		ImageView background = new ImageView(new Image(inputstream));  
		background.setFitHeight(500);
		background.setFitWidth(870);
				root.getChildren().add(background);  */
		  
		

		int[][] colors = { { 255, 255, 0 }, { 255, 127, 0 }, { 250, 0, 38 }, { 226, 29, 139 }, { 118, 42, 145 },
				{ 31, 120, 180 } };

		for (int i = 0; i < 2000; i++) {
			c[i] = new Circle(1, 1, 1);
			c[i].setRadius(random.nextDouble() * 3);
			int colorIndex = random.nextInt(colors.length - 1);
			Color color = Color.rgb(colors[colorIndex][0], colors[colorIndex][1], colors[colorIndex][2],
					random.nextDouble());

			c[i].setFill(color);
			bpRoot.getChildren().add(c[i]);
			Raining(c[i]);
		}
		//		bpRoot.setLeft(null);
		//		bpRoot.setBottom(null);
		bpRoot.setTop(VBoxTop(createText("The winner of the competition is: " + allPlayers.get(14).getText())));
		//		bpRoot.setCenter(root);
	}

	private void Raining(final Circle c) {
		c.setCenterX(random.nextInt(900));// Window width = 800
		int time = random.nextInt(50);
		@SuppressWarnings("deprecation")
		Animation fall = TranslateTransitionBuilder.create().node(c).fromY(-200).toY(600 + 200) // WIndow height = 600
		.toX(random.nextDouble() * c.getCenterX()).duration(Duration.seconds(time))
		.onFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Raining(c);
			}
		}).build();
		fall.play();
	}

	private void openAddPlayer() {
		btAddPlayer.setDisable(false);
		tfPlayerName.setDisable(false);

	}

	private VBox VBoxTop(Text lblTop) {
		VBox vTop = new VBox();
		vTop.setPrefHeight(100);
		vTop.getChildren().add(lblTop);
		return vTop;
	}

	private Pane startGame2() {

		btPlays = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			btPlays.add(new Button("Play"));
			allPlayers.add(i + 8, shadowTextField());
			pnChampion.getChildren().addAll(btPlays.get(i), allPlayers.get(i + 8));
		}
		setLines();
		setButtonsLocation();
		return pnChampion;
	}

	private void setLines() {
		pnChampion.getChildren().add(new Line(0, 10, 75, 10));
		pnChampion.getChildren().add(new Line(75, 10, 75, 20));
		pnChampion.getChildren().add(new Line(0, 60, 75, 60));
		pnChampion.getChildren().add(new Line(75, 60, 75, 47));

		pnChampion.getChildren().add(new Line(0, 105, 75, 105));
		pnChampion.getChildren().add(new Line(75, 105, 75, 113));
		pnChampion.getChildren().add(new Line(0, 150, 75, 150));
		pnChampion.getChildren().add(new Line(75, 150, 75, 138));

		pnChampion.getChildren().add(new Line(0, 192, 75, 192));
		pnChampion.getChildren().add(new Line(75, 192, 75, 205));
		pnChampion.getChildren().add(new Line(0, 239, 75, 239));
		pnChampion.getChildren().add(new Line(75, 239, 75, 231));

		pnChampion.getChildren().add(new Line(0, 280, 75, 280));
		pnChampion.getChildren().add(new Line(75, 280, 75, 290));
		pnChampion.getChildren().add(new Line(0, 330, 75, 330));
		pnChampion.getChildren().add(new Line(75, 330, 75, 317));

		pnChampion.getChildren().add(new Line(225, 30, 261, 30));
		pnChampion.getChildren().add(new Line(261, 30, 261, 70));
		pnChampion.getChildren().add(new Line(225, 128, 261, 128));
		pnChampion.getChildren().add(new Line(261, 128, 261, 95));

		pnChampion.getChildren().add(new Line(225, 220, 263, 220));
		pnChampion.getChildren().add(new Line(263, 220, 263, 250));
		pnChampion.getChildren().add(new Line(225, 300, 263, 300));
		pnChampion.getChildren().add(new Line(263, 300, 263, 274));

		pnChampion.getChildren().add(new Line(400, 80, 455, 80));
		pnChampion.getChildren().add(new Line(455, 80, 455, 160));
		pnChampion.getChildren().add(new Line(400, 265, 455, 265));
		pnChampion.getChildren().add(new Line(455, 265, 455, 185));

	}

	private void setButtonsLocation() {
		setButtonLocation(0, 8, 60, 100, 20);
		setButtonLocation(1, 9, 60, 100, 115);
		setButtonLocation(2, 10, 60, 100, 205);
		setButtonLocation(3, 11, 60, 100, 290);
		setButtonLocation(4, 12, 240, 280, 70);
		setButtonLocation(5, 13, 240, 280, 250);
		setButtonLocation(6, 14, 440, 480, 160);
	}

	private void setButtonLocation(int btPlay, int btPlayer, int xPlay, int xPlayer, int y) {
		btPlays.get(btPlay).setLayoutX(xPlay);
		btPlays.get(btPlay).setLayoutY(y);
		allPlayers.get(btPlayer).setLayoutX(xPlayer);
		allPlayers.get(btPlayer).setLayoutY(y);
	}

	public Text createText(String string) {

		Text text = new Text(string);
		text.setFill(Color.DIMGRAY);
		text.setTextOrigin(VPos.CENTER);
		text.setFont(Font.font("Cooper Black", FontWeight.BOLD, 40));
		return text;
	}

	public Button getBtStartGame() {
		return btStartGame;
	}

	private String getSportName() {
		String str = null;
		if (rdBasketball.isSelected())
			str = rdBasketball.getText();
		else if (rdSoccer.isSelected())
			str = rdSoccer.getText();
		else
			str = rdTennis.getText();
		return str;
	}

	private HBox addPlayers() {

		hPlayers.setPrefHeight(70);
		hPlayers.setPrefWidth(70);
		hPlayers.setSpacing(25);

		VBox vbRadio = new VBox();
		rdTennis.setToggleGroup(tglSport);
		rdSoccer.setToggleGroup(tglSport);
		rdBasketball.setToggleGroup(tglSport);
		vbRadio.getChildren().addAll(rdTennis, rdSoccer, rdBasketball);

		if (rdBasketball.isSelected() || rdSoccer.isSelected() || rdTennis.isSelected())
			btAddPlayer.setDisable(false);
		tfPlayerName.setPromptText("Enter player name");
		VBox vbAddRemove = new VBox(btAddPlayer, btRemovePlayer);
		btAddPlayer.setTextFill(Color.BLUE);
		btAddPlayer.setPrefWidth(120);
		btRemovePlayer.setPrefWidth(120);
		btRemovePlayer.setTextFill(Color.RED);
		vbAddRemove.setPrefWidth(120);
		vbAddRemove.setSpacing(20);
		vbAddRemove.setAlignment(Pos.TOP_LEFT);

		btStartGame.setPrefSize(130, 70);
		btStartGame.setTextFill(Color.BLUE);
		hPlayers.getChildren().addAll(lblSportType, vbRadio, lblPlayerName, tfPlayerName, vbAddRemove, btStartGame);

		return hPlayers;
	}

	public TextField shadowTextField() {
		TextField tfPlayer = new TextField();
		tfPlayer.setEditable(false);
		DropShadow dShadow = new DropShadow(10, Color.BLACK);
		dShadow.setOffsetX(3);
		dShadow.setOffsetY(3);
		tfPlayer.setEffect(dShadow);
		tfPlayer.setPrefWidth(120);
		return tfPlayer;
	}

	public void setWinnerColor(TextField tf) {
		DropShadow dShadow = new DropShadow(10, Color.LAWNGREEN);
		tf.setEffect(dShadow);
	}

	public void setLoserColor(TextField tf) {
		DropShadow dShadow = new DropShadow(10, Color.RED);
		tf.setEffect(dShadow);
	}

	private VBox createPlayers() {

		allPlayers = new ArrayList<TextField>();
		VBox vPlayers = new VBox();
		for (int i = 0; i < 8; i++) {
			TextField tfPlayer = shadowTextField();
			allPlayers.add(tfPlayer);
		}

		vPlayers.setPrefWidth(120);
		vPlayers.setSpacing(20);
		vPlayers.setAlignment(Pos.TOP_LEFT);
		vPlayers.getChildren().addAll(allPlayers);

		return vPlayers;
	}

	@Override
	public void registerListener(ViewListenable l) {
		allListeners.add(l);
	}

	public void updatePlayers(String playerName) {
//		if (counterPlayers != 8)
			allPlayers.get(counterPlayers++).setText(playerName);
	}

	@Override
	public void maxPlayers(String string) {
		JOptionPane.showMessageDialog(null, string);
	}

	@Override
	public void messageException(String message) {
		JOptionPane.showMessageDialog(null, message);

	}

	public void showPopUp(final int playerA, final int playerB, final int winner, final int winnerTf,

			final int thisButton, final int numberPlay) {
		// i know that i can unite all the ints (winner , this button and numberPlay) 
		// because  all the function they are the same but i don't know if its smart because like this its more obviously 

		stage = new Stage();
		pnGame = new pnResult(allListeners.get(0), getSportName(), allPlayers.get(playerA).getText(),
				allPlayers.get(playerB).getText());
		pnGame.getButtonDone().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				for (int i = 0; i < pnGame.getTfPlayerA().size(); i++) {
					pnGame.getResultsPlayerA().add(pnGame.getTfPlayerA().get(i).getText());
					pnGame.getResultsPlayerB().add(pnGame.getTfPlayerB().get(i).getText());
				}
				String winnerName = null;

				try {
					allListeners.get(0).setResults(allPlayers.get(playerA).getText(), allPlayers.get(playerB).getText(),
							pnGame.getResultsPlayerA(), pnGame.getResultsPlayerB());
					allListeners.get(0).playGame(getSportName(), allPlayers.get(playerA).getText(),
							allPlayers.get(playerB).getText(), numberPlay);
					winnerName = allListeners.get(0).getWinner(winner);
					allPlayers.get(winnerTf).setText(winnerName);
				} catch (ExceptionNotDigit e) {
					messageException(e.getMessage());
				} catch (ExceptionTeco e) {
					messageException(e.getMessage());
				} finally {
					stage.close();
				}

				if (!allPlayers.get(winnerTf).getText().equalsIgnoreCase("")) {
					btPlays.get(thisButton).setDisable(true);
					stage.close();

					if (winnerName.equalsIgnoreCase(allPlayers.get(playerA).getText())) {
						setWinnerColor(allPlayers.get(playerA));
						setLoserColor(allPlayers.get(playerB));
					} else {
						setWinnerColor(allPlayers.get(playerB));
						setLoserColor(allPlayers.get(playerA));
					}
					if (numberPlay == 6) {
						setWinnerColor(allPlayers.get(14));
						try {
							show();
						} catch (FileNotFoundException e) {
							messageException(e.getMessage());
						}
					}

				} else {
					stage.close();
				}
				if (thisButton < 4)
					quarterfinal();

				if (thisButton > 3)
					semifinals();

			}
		});

		Scene scene = new Scene(pnGame, 300, 300);
		stage.setScene(scene);
		stage.setTitle(getSportName() + " Game");
		stage.show();
	}

	@Override
	public void removePlayer() {
		allPlayers.get(--counterPlayers).setText(null);
	}
	
	public void updateTeamIsNotFull() {
		openAddPlayer();
		btStartGame.setDisable(true);
	}
}

