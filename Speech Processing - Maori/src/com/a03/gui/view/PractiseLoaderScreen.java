package com.a03.gui.view;

import com.a03.gui.Main;
import com.a03.gui.controller.LoginController;
import com.a03.gui.controller.PractiseLoaderController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * Class sets up the practise  creation screen, loads the FXML
 * @author Matt
 *
 */
public class PractiseLoaderScreen {
	private PractiseLoaderController controller;
	private AnchorPane pane;
	private Scene loginScene;
	public PractiseLoaderScreen(Stage s){
		try {

			//Creating the game screen and its controller
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("practiseLoaderScreen.fxml"));
			pane = (AnchorPane) loader.load();

			//Getting controller specified in fxml file
			controller = loader.getController();		
			loginScene = new Scene(pane);
			s.setScene(loginScene);
			s.setWidth(680.0);
			s.setHeight(504.0);
			s.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
