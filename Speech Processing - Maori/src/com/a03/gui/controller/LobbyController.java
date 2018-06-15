package com.a03.gui.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.a03.gui.Main;
import com.a03.gui.view.HomeScreen;
import com.a03.leaderboard.LeaderboardData;
import com.a03.multiplayer.MultiplayerGameHandler;
import com.a03.multiplayer.gamedata.GameData;
import com.a03.server.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * This controller handles the multiplayer lobby. Has 3 main screens -
 * active games, lobby and finished games. Can start a game, accept a challenge 
 * and look at game results.
 * @author Matt
 *
 */
public class LobbyController {

	@FXML
	Pane pane;

	@FXML
	Pane finishedPane;

	@FXML
	ToggleButton hard;

	@FXML
	ToggleButton easy;

	@FXML
	ToggleButton random;

	@FXML
	Pane pane1;

	@FXML
	JFXTextField userField;	

	@FXML
	JFXSpinner spinner;

	@FXML
	JFXSpinner spinner2;

	@FXML
	JFXListView<GameData> gameList;

	@FXML
	TableView<GameData> finishedTable;

	@FXML
	JFXButton play1;

	@FXML
	JFXButton finished;

	@FXML
	JFXButton active;

	@FXML
	JFXButton back;

	@FXML
	Label userText;

	@FXML
	Label userText1;

	@FXML
	Label userText2;

	@FXML
	TableColumn<GameData, Integer> yourScore;

	@FXML
	TableColumn<GameData, String> competitor;

	@FXML
	TableColumn<GameData, String> compScore;

	@FXML
	TableColumn<GameData, String> result;
	

	MultiplayerGameHandler gh = MultiplayerGameHandler.getInstance();

	@FXML
	private void initialize() {
		yourScore.setCellValueFactory(new PropertyValueFactory<GameData,Integer>("YourScore"));
		competitor.setCellValueFactory(new PropertyValueFactory<GameData,String>("Competitor"));
		compScore.setCellValueFactory(new PropertyValueFactory<GameData,String>("CompetitorScore"));
		result.setCellValueFactory(new PropertyValueFactory<GameData,String>("Result"));
		spinner2.setVisible(false);

		spinner.setVisible(false);
		userText.setText("Logged in as: " + Session.getInstance().getUser());

		userText1.setText("Logged in as: " + Session.getInstance().getUser());

		userText2.setText("Logged in as: " + Session.getInstance().getUser());
		finishedTable.getColumns().addListener(new ListChangeListener<Object>() {

			public void onChanged(Change<?> change) {
				 change.next();
		         change.reset();

			}

	    });
	}

	private void disableButtons() {
		back.setDisable(true);
		play1.setDisable(true);
		finished.setDisable(true);
		active.setDisable(true);
	}

	private void enableButtons() {
		back.setDisable(false);
		play1.setDisable(false);
		finished.setDisable(false);
		active.setDisable(false);
	}
	/*
	 * Moves to the left or right screen
	 */
	private void switchScreens(int d) {

		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(482 * d);

		TranslateTransition f3 = new TranslateTransition();
		f3.setNode(finishedPane);
		f3.setByX(558 * d);

		TranslateTransition f2 = new TranslateTransition();
		f2.setNode(pane1);
		f2.setByX(482 * d);

		f.play();
		f3.play();
		f2.play();


	}

	@FXML
	private void toActiveGames() {
		disableButtons();
		spinner.setVisible(true);
		final Task<Void> loaderTask = new Task<Void>() {

			@Override
			protected Void call(){
				gameList.setItems(GameData.getList());

				return null;
			}
		};
		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				spinner.setVisible(false);
				enableButtons();
				switchScreens(-1);
			}
		});
		Thread thr = new Thread(loaderTask);
		thr.start();

	}

	@FXML
	private void toFinishedGames() {
		disableButtons();
		spinner.setVisible(true);
		final Task<Void> loaderTask = new Task<Void>() {

			@Override
			protected Void call(){
				finishedTable.setItems(GameData.getFinishedList());
				return null;
			}
		};

		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				spinner.setVisible(false);
				enableButtons();
				switchScreens(1);
			}
		});
		Thread thr = new Thread(loaderTask);
		thr.start();

	}

	/*
	 * Begins an active game from where you left it.
	 * Does nothing if there is no selection.
	 */
	@FXML
	private void playActive() {
		if(gameList.getSelectionModel().getSelectedIndex() != -1) {
			spinner2.setVisible(true);
			final Task<Void> loaderTask = new Task<Void>() {

				@Override
				protected Void call(){
					gameList.getSelectionModel().getSelectedIndex();
					ObservableList<GameData> data = gameList.getItems();
					GameData d = data.get(gameList.getSelectionModel().getSelectedIndex());
					gh.resumeGame(d.getGameId(), d.getChallengerId());

					return null;
				}
			};
			loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

				public void handle(WorkerStateEvent event) {
					startGame(-1);
				}
			});
			
			Thread thr = new Thread(loaderTask);
			thr.start();
		}

	}

	@FXML
	private void backToLobby() {
		switchScreens(1);
	}

	@FXML
	private void backToLobby2() {
		switchScreens(-1);
	}
	@FXML
	private void playEasy() {
		if( hard.selectedProperty().get()) {
			hard.setSelected(false);
		}
	}
	@FXML
	private void playHard() {
		if( easy.selectedProperty().get()) {
			easy.setSelected(false);
		}
	}

	@FXML
	private void randomPressed() {
		if(random.selectedProperty().get()) {
			userField.setDisable(true);
		}else {
			userField.setDisable(false);
		}
	}



	@FXML
	private void go() {
		disableButtons();
		spinner.setVisible(true);


		if((! easy.selectedProperty().get() && !hard.selectedProperty().get()) || (!random.selectedProperty().get() && userField.getText().isEmpty())){
			spinner.setVisible(false);
			enableButtons();
			return;
		}

		if(!userField.isDisable() && !Session.getInstance().doesPlayerExist(userField.getText())) {
			spinner.setVisible(false);
			enableButtons();
			return;
		}
		if(userField.getText().compareTo(Session.getInstance().getUser()) == 0) {
			spinner.setVisible(false);
			enableButtons();
			return;
		}

		if(easy.selectedProperty().get()) {

			if(random.selectedProperty().get()) {			

				initializeGame(null, 1);

			}else{

				initializeGame(userField.getText(), 1);
			}


		}else {
			if(random.selectedProperty().get()) {

				initializeGame(null, 2);

			}else {
				initializeGame(userField.getText(), 2);
			}
		}



	}

	private void initializeGame(final String s, final int i) {

		final Task<Void> loaderTask = new Task<Void>() {

			@Override
			protected Void call(){
				gh.startGame(s, i);
				return null;
			}
		};
		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				startGame(1);

			}
		});

		Thread thr = new Thread(loaderTask);
		thr.start();


	}

	@FXML
	private void back() {

		fadeOut(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				HomeScreen h = new HomeScreen(Main.getMainApp().getStage());

			}

		}, -1);

	}

	private void fadeOut(EventHandler<ActionEvent> e , int d){
		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(d*600);

		f.setOnFinished(e);
		f.play();

	}

	private void startGame(final int i) {


		final Task<AnchorPane> loaderTask = new Task<AnchorPane>() {

			@Override
			protected AnchorPane call(){
				AnchorPane pane1;
				FXMLLoader loader = new FXMLLoader();
				MultiplayerGameController gameController = new MultiplayerGameController();
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
				return null;
				//Getting controller specified in fxml file


			}
		};

		loaderTask.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent event) {
				enableButtons();
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
				}, i);
			}

		});
		Thread thr = new Thread(loaderTask);
		thr.start();
	}



}
