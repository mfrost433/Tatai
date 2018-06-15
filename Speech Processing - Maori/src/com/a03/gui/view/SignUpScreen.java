package com.a03.gui.view;

import com.a03.gui.Main;
import com.a03.gui.controller.SignUpController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * Class sets up the signup screen, loads the FXML
 * @author Matt
 *
 */
public class SignUpScreen {
	AnchorPane pane = null;
	SignUpController l;
	Scene registerScene;
	public SignUpScreen(Stage s){
		try {

			//Creating the game screen and its controller
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("signupscreen.fxml"));
			pane = (AnchorPane) loader.load();

			//Getting controller specified in fxml file
			l = loader.getController();		
			registerScene = new Scene(pane);
			s.setScene(registerScene);
			s.show();

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
