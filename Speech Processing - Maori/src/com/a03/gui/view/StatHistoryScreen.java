package com.a03.gui.view;

import com.a03.gamehistory.GameHistory;
import com.a03.gui.Main;
import com.a03.gui.controller.GameScreenController;
import com.a03.gui.controller.MainController;
import com.a03.gui.controller.StatHistoryScreenController;
import com.a03.server.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Class sets up the statistics history screen, loads the FXML
 * @author Matt
 *
 */
public class StatHistoryScreen {
	
	private StatHistoryScreenController controller;
	private AnchorPane pane;
	private Scene statHistoryScene;
	

	public StatHistoryScreen(Stage s) {	

		try {
						
			//Creating the stats screen and its controller
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("statHistoryScreen.fxml"));
			pane = (AnchorPane) loader.load();
			
			//Getting controller specified in fxml file
			controller = loader.getController();	
			controller.init();
			statHistoryScene = new Scene(pane);
			s.setScene(statHistoryScene);
			s.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	public StatHistoryScreenController getController(){
		return controller;
	}

}
