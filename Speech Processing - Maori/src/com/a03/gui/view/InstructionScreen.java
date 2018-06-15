package com.a03.gui.view;

import com.a03.gui.Main;
import com.a03.gui.controller.InstructionScreenController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Class sets up the instruction screen, loads the FXML
 * @author Matt
 *
 */
public class InstructionScreen {
	
	private InstructionScreenController controller;
	private AnchorPane pane;
	private Scene instructionScreenScene;
	
	public InstructionScreen(Stage s) {	

		try {
			
			//Creating the game screen and its controller
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("instructionScreen.fxml"));
			pane = (AnchorPane) loader.load();
			
			//Getting controller specified in fxml file
			controller = loader.getController();		
			instructionScreenScene = new Scene(pane);
			s.setScene(instructionScreenScene);
			s.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public InstructionScreenController getController(){
		return controller;
	}

}
