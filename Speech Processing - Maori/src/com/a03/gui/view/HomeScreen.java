package com.a03.gui.view;

import com.a03.gui.Main;
import com.a03.gui.controller.MainController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * Class sets up the home screen, loads the FXML
 * @author Matt
 *
 */
public class HomeScreen {
	private MainController controller;
	private AnchorPane pane;
	private Scene loginScene;
	private Stage stage;
	private String _username;
	private static HomeScreen homeScreenRef;
	
	public HomeScreen(Stage s){
		load(s);
		homeScreenRef = this;
		stage = s;
	}
	
	private void load(Stage s){
		try {

			//Creating the game screen and its controller
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("mainscreen.fxml"));
			pane = (AnchorPane) loader.load();

			//Getting controller specified in fxml file
			controller = loader.getController();		
			loginScene = new Scene(pane);
			s.setScene(loginScene);

			s.show();


		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static public HomeScreen getHomeScreen() {
		return homeScreenRef;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Scene getScene() {
		return loginScene;
	}

}
