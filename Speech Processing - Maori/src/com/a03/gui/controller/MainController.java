package com.a03.gui.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.a03.gui.Main;

import com.a03.gui.view.InstructionScreen;
import com.a03.gui.view.LeaderboardScreen;
import com.a03.gui.view.LoginScreen;
import com.a03.gui.view.PractiseLoaderScreen;
import com.a03.gui.view.StatHistoryScreen;
import com.a03.server.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/*
 * Home screen controller, allows access to all other features of the app.
 */
public class MainController {
	private Session session = Session.getInstance();

	@FXML
	Pane pane;

	@FXML
	JFXSpinner spinner;

	@FXML
	JFXButton leaderboardButton;

	@FXML
	private Label userText;

	@FXML
	private JFXButton toNineButton;

	@FXML
	private JFXButton instructionsButton;

	@FXML
	private JFXButton practiseButton;

	@FXML
	private JFXButton multiplayer;

	@FXML
	private JFXButton statsButton;

	@FXML
	private JFXButton toNinetyNineButton;

	@FXML
	private void initialize() {
		userText.setText("Logged in as: " + session.getUser());
		spinner.setVisible(false);
	}

	@FXML
	private void practisePressed() {
		fadeOut(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				PractiseLoaderScreen p = new PractiseLoaderScreen(Main.getMainApp().getStage());
			}
		});
	}

	@FXML
	private void logout() {
		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(-600);

		f.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				LoginScreen l = new LoginScreen(Main.getMainApp().getStage());

			}

		});
		f.play();
	}

	/*
	 * Loads multiplayer data from server, waits for response using thread.
	 */
	@FXML
	private void multiplayerPressed() {
		final Task<Pane> loaderTask = new Task<Pane>() {

			@Override
			protected Pane call(){
				disableButtons();
				spinner.setVisible(true);
				LobbyController controller;
				AnchorPane pane;
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("multiplayerLobby.fxml"));
				try {
					pane = (AnchorPane) loader.load();
					controller = loader.getController();
					return pane;
				} catch (IOException e) {

					e.printStackTrace();
				}
				return null;

			}
		};

		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				fadeOut(new EventHandler<ActionEvent>(){

					public void handle(ActionEvent arg0) {					

						fadeOut(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent arg0) {	
								Scene scene;
								try {
									scene = new Scene(loaderTask.get());
									Main.getMainApp().getStage().setScene(scene);

									spinner.setVisible(false);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ExecutionException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							}
						});
					}
				});
			}
		});		

		Thread thr = new Thread(loaderTask);
		thr.start();
	}


	LeaderboardScreen l;
	
	@FXML
	private void leaderboardPressed() {
		disableButtons();
		spinner.setVisible(true);
		final Task<Void> loaderTask = new Task<Void>() {

			@Override
			protected Void call(){
				l = new LeaderboardScreen(Main.getMainApp().getStage());
				return null;
			}
		};
		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				fadeOut(new EventHandler<ActionEvent>(){

					public void handle(ActionEvent arg0) {

						l.show();
						spinner.setVisible(false);
					}
				});
			}
		});

		Thread thr = new Thread(loaderTask);
		thr.start();
	}

	/*
	 * When clicking easy or hard, it loads the game screen and calls 
	 * the controller to start the corresponding level.
	 */
	private void startGame(final int i) {
		disableButtons();
		spinner.setVisible(true);

		final Task<AnchorPane> loaderTask = new Task<AnchorPane>() {

			@Override
			protected AnchorPane call(){
				AnchorPane pane1;
				FXMLLoader loader = new FXMLLoader();
				GameScreenController gameController = new GameScreenController();
				loader.setLocation(Main.class.getResource("gamescreen.fxml"));
				loader.setController(gameController);
				try {
					pane1 = (AnchorPane) loader.load();
					gameController.start(i);
					return pane1;
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				//Getting controller specified in fxml file


			}
		};

		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				fadeOut(new EventHandler<ActionEvent>(){

					public void handle(ActionEvent arg0) {
						Scene gameScene;
						try {
							gameScene = new Scene(loaderTask.get());
							Main.getMainApp().getStage().setScene(gameScene);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
			}

		});
		Thread thr = new Thread(loaderTask);
		thr.start();
	}

	@FXML
	private void playToNine() {
		startGame(1);

	}

	private void disableButtons() {
		toNineButton.setDisable(true);

		leaderboardButton.setDisable(true);

		practiseButton.setDisable(true);

		instructionsButton.setDisable(true);

		statsButton.setDisable(true);

		toNinetyNineButton.setDisable(true);
		multiplayer.setDisable(true);
	}


	private void enableButtons() {
		leaderboardButton.setDisable(false);
		toNineButton.setDisable(false);
		multiplayer.setDisable(false);
		practiseButton.setDisable(false);

		instructionsButton.setDisable(false);

		statsButton.setDisable(false);

		toNinetyNineButton.setDisable(false);
	}

	@FXML
	private void playToNinetyNine() {
		startGame(2);
	}	

	@FXML
	private void statsPressed() {
		disableButtons();
		spinner.setVisible(true);
		final Task<AnchorPane> loaderTask = new Task<AnchorPane>() {

			@Override
			protected AnchorPane call() throws Exception {
				AnchorPane pane1;
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("statHistoryScreen.fxml"));
				pane1 = (AnchorPane) loader.load();
				StatHistoryScreenController controller = loader.getController();	
				controller.init();
				//Getting controller specified in fxml file
				return pane1;
			}

		};

		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				fadeOut(new EventHandler<ActionEvent>(){

					public void handle(ActionEvent arg0) {
						Scene gameScene;
						try {
							gameScene = new Scene(loaderTask.get());
							Main.getMainApp().getStage().setScene(gameScene);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
			}

		});
		Thread thr = new Thread(loaderTask);
		thr.start();
	}

	@FXML
	private void instructionsPressed() {
		fadeOut(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				InstructionScreen instructionScreen = new InstructionScreen(Main.getMainApp().getStage());

			}

		});

	}	

	private void fadeOut(EventHandler<ActionEvent> eh){
		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(600);

		f.setOnFinished(eh);
		f.play();
		enableButtons();
	}

}
