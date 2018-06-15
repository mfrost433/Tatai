package com.a03.gui.view;


import com.a03.gui.Main;
import com.a03.gui.controller.GameScreenController;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * Class sets up the game screen, loads the FXML
 * @author Matt
 *
 */
public class GameScreen {

	private AnchorPane pane;
	private Scene gameScene;
	private GameScreenController gameController;

	public GameScreen(final int _level, final Stage s) {	
		

			Task<Void> loaderTask = new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					try {
					//Creating the game screen and its controller
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("gamescreen.fxml"));
					gameController = new GameScreenController();
					loader.setController(gameController);
					pane = (AnchorPane) loader.load();
					gameController = loader.getController();

					gameController.start(_level);
					}catch(Exception e) {
						e.printStackTrace();
					}
					return null;	
				}
			};
			loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

				public void handle(WorkerStateEvent event) {

					gameScene = new Scene(pane);
					s.setScene(gameScene);
				}

			});
			Thread thr = new Thread(loaderTask);
			thr.start();



			s.show();

	

	}

	public GameScreenController getController(){
		return gameController;
	}







}
