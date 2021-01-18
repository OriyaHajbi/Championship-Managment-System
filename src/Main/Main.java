package Main;

import Controller.Controller;

import Model.Championship;
import View.ViewJavaFx;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Championship championship = new Championship();
		ViewJavaFx championView = new ViewJavaFx(primaryStage);
		Controller c = new Controller(championship, championView);
	}

}
