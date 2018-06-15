package com.a03.gui.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.a03.game.GameHandler;
import com.a03.gui.Main;
import com.a03.gui.view.HomeScreen;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for practise lobby. Allows you to customise your 
 * practise game and begin it.
 * @author Matt
 *
 */
public class PractiseLoaderController {
	@FXML
	Pane pane;
	GameScreenController gameController;

	@FXML
	JFXButton startButton;

	@FXML 
	JFXTextField startRange;

	@FXML 
	JFXTextField endRange;

	@FXML
	ToggleButton plus;

	@FXML
	Label alertText;

	@FXML
	ToggleButton minus;

	@FXML
	ToggleButton multiply;

	@FXML
	ToggleButton divide;

	String startText = "-1";
	String endText = "-1";

	/*
	 * Sets up a change listener for the number fields, so that you cannot
	 * select a number above 99, and it must contain numbers.
	 */
	@FXML
	private void initialize() {
		plus.selectedProperty();
		alertText.setVisible(false);
		startButton.setDisable(true);
		startRange.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				alertText.setVisible(true);
				startButton.setDisable(true);
				if (!newValue.matches("\\d*")) {
					startRange.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if ( newValue.length() > 2) {
					startRange.setText(oldValue);
				}
				if(newValue.matches("\\d*") && newValue.compareTo("") != 0) {
					startText = newValue;

					if(Integer.parseInt(newValue) > 99 || Integer.parseInt(newValue) < 0 || Integer.parseInt(newValue) > Integer.parseInt(endText)){
						alertText.setVisible(true);
						startButton.setDisable(true);
					}else if(Integer.parseInt(endText) < 99 && Integer.parseInt(endText) > 0){
						alertText.setVisible(false);
						startButton.setDisable(false);
					}
				}
			}


		});
		endRange.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				alertText.setVisible(true);
				startButton.setDisable(true);
				if ( !newValue.matches("\\d*")) {
					endRange.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if(newValue.length() > 2) {
					endRange.setText(oldValue);
				}
				if(newValue.matches("\\d*") && newValue.compareTo("") != 0) {
					endText = newValue;


					if(Integer.parseInt(newValue) > 99 || Integer.parseInt(newValue) < 0 || Integer.parseInt(startText) > Integer.parseInt(endText)){
						alertText.setVisible(true);
						startButton.setDisable(true);
					}else if(Integer.parseInt(startText) < 99 && Integer.parseInt(startText) > 0){
						alertText.setVisible(false);
						startButton.setDisable(false);
					}
				}

			}


		});
	}

	/*
	 * When the play button is pressed, it enters the game screen
	 * and tells the game handler to begin the custom practise game.
	 */
	@FXML
	private void start() {
		final int start = (int)Integer.parseInt(startRange.getText());
		final int end = (int)Integer.parseInt(endRange.getText());
		final boolean addb = plus.selectedProperty().get();

		final boolean minusb = minus.selectedProperty().get();
		final boolean multiplyb = multiply.selectedProperty().get();
		final boolean divideb = divide.selectedProperty().get();
		final Task<AnchorPane> loaderTask = new Task<AnchorPane>() {

			@Override
			protected AnchorPane call(){
				GameHandler.getInstance().startPractiseGame(start,end,addb, minusb, multiplyb, divideb);
				AnchorPane pane1 = null;
				FXMLLoader loader = new FXMLLoader();
				gameController = new GameScreenController();
				loader.setLocation(Main.class.getResource("gamescreen.fxml"));
				loader.setController(gameController);


				try {
					pane1 = (AnchorPane) loader.load();


					return pane1;
				}
				catch (IOException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return pane1;
				//Getting controller specified in fxml file


			}
		};

		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent arg0) {
				fadeOut(new EventHandler<ActionEvent>(){

					public void handle(ActionEvent arg0) {

						Scene gameScene;
						try {

							gameScene = new Scene(loaderTask.get());

							Main.getMainApp().getStage().setScene(gameScene);
							gameController.start(3);

						} catch (InterruptedException e) {

							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {

							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, 1);
			}

		});
		Thread thr = new Thread(loaderTask);
		thr.start();
	}

	@FXML
	private void back() {
		fadeOut(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				Stage s = Main.getMainApp().getStage();
				HomeScreen h = new HomeScreen(s);

			}

		}, -1);

	}
	private void fadeOut(EventHandler<ActionEvent> eh, int d){

		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(600 * d);

		f.setOnFinished(eh);
		f.play();
	}
}
