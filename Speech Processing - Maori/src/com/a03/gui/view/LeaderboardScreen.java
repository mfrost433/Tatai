package com.a03.gui.view;

import com.a03.gui.Main;
import com.a03.gui.controller.InstructionScreenController;
import com.a03.gui.controller.LeaderboardController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * Class sets up the leaderboard screen, loads the FXML
 * @author Matt
 *
 */
public class LeaderboardScreen {
	private LeaderboardController controller;
	private AnchorPane pane;
	private Stage _s;
	private Scene instructionScreenScene;
	public LeaderboardScreen(Stage s) {
		try {
			_s = s;
			//Creating the game screen and its controller
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("leaderboardScreen.fxml"));
			pane = (AnchorPane) loader.load();

			//Getting controller specified in fxml file
			controller = loader.getController();		
			instructionScreenScene = new Scene(pane);

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public void show() {
		_s.setScene(instructionScreenScene);
		_s.show();
	}
}
