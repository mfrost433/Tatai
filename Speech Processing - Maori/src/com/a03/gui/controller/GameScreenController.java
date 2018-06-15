package com.a03.gui.controller;

import java.io.File;
import java.util.concurrent.ExecutionException;

import com.a03.game.GameHandler;
import com.a03.game.Stats;
import com.a03.gui.Main;
import com.a03.gui.view.GameScreen;
import com.a03.gui.view.HomeScreen;
import com.a03.server.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * This controls all interaction with the Game Screen.
 * This involves animations, button interaction, and 
 * making requests to game handler.
 * @author Matt
 *
 */
public class GameScreenController{
	private Session session = Session.getInstance();
	private Timeline time = new Timeline();
	private Color col = new Color(33.0/255, 150.0/255, 243.0/255, 1.0);
	GameHandler _g;
	
	private double x,y;
	private int defx = 1;
	private int defy = 1;
	private int level;
	
	private boolean practise = false;

	
	@FXML
	ProgressBar recordBar;
	
	@FXML
	Pane pane;

	@FXML
	ImageView mic;

	@FXML
	ImageView speaker;

	@FXML
	ImageView tick;

	@FXML
	private ImageView correctImage;

	@FXML
	private ImageView incorrectImage;

	@FXML
	private ImageView feedbackImage;

	@FXML
	private Label userText;
	
	@FXML
	private Label user;	
	
	@FXML
	private Label vs;	
	
	@FXML
	private Label enemy;

	@FXML
	private JFXButton recordButton;

	@FXML
	private JFXButton tryAgain;

	@FXML
	private JFXButton nextLevel;

	@FXML
	private JFXButton exitButton;

	@FXML
	private JFXButton playButton;

	@FXML
	private JFXButton submitButton;

	@FXML
	JFXSpinner spinner;

	@FXML
	private Label mainText;

	@FXML
	private Label sideText;

	@FXML
	private Label numberText;


	//record button pressed

	/*
	 * Starts a get ready timer bar. When that is done,
	 * calls record and starts a recording timer bar.
	 */
	@FXML
	private void record(){

		
		timer();
		mainText.setText("GET READY");
		Task<Void> sleeper = new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				Thread.sleep(2100);

				return null;
			}

		};

		Thread thr = new Thread(sleeper);
		thr.start();

		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent arg0) {
				mainText.setText("RECORDING");
				_g.record();

				timer();


			}

		});


	}

	/*
	 * Tells game handler to play audio, and puts on timer.
	 */
	@FXML
	private void play(){

		_g.play();
		timer();

	}

	@FXML
	private void tryAgain(){

		GameScreen game = new GameScreen(level,Main.getMainApp().getStage());

	}

	@FXML
	private void nextLevel(){

		GameScreen game = new GameScreen(level%2 + 1,Main.getMainApp().getStage());

	}

	/*
	 * When the game ends, disable buttons and print final score 
	 * and display corresponding buttons.
	 */
	private void end(Stats s){

		recordButton.setDisable(true);
		playButton.setDisable(true);
		submitButton.setDisable(true);

		mainText.setMinWidth(Region.USE_PREF_SIZE);
		mainText.setText(s.getCorrect() + " out of 10 correct");
		numberText.setText("");

		if(s.getCorrect() >= 8 && s.getLevelType() == 1) {
			nextLevel.setVisible(true);
		}


		tryAgain.setVisible(true);

	}

	private boolean isFinished() {

		Stats s = new Stats();
		s.getFromLocalGame();
		return s.getIsFinished();

	}

	
	/*
	 * Whenever action is taken, this function is called. It makes sure that
	 * The GUI stays updated with information from the server about stats and
	 * current level.
	 */
	private void update() {
		Stats stats;
		if(!practise) {
			stats = session.getStats();
		}else {
			
			stats = new Stats();
			stats.getFromLocalGame();
		}
		sideText.setText(stats.toString());

		if(isFinished()){
			if(!practise) {
				session.updateGameHistory();
			}
			end(stats);

		}else{

			//numberText.setText("" + _g.getNumber());
			numberText.setText("" + _g.getEquationString());
		}



	}


	/*
	 * This method displays a progress bar timer for 2 seconds.
	 */
	private void timer(){

		recordButton.setDisable(true);
		playButton.setDisable(true);
		submitButton.setDisable(true);
		recordBar.setVisible(true);

		time = new Timeline();
		recordBar.setProgress(0);
		time.setCycleCount(Timeline.INDEFINITE);

		KeyFrame f = new KeyFrame(Duration.seconds(0.005), new EventHandler<ActionEvent>(){

			public void handle(ActionEvent e) {
				
				recordBar.setProgress(recordBar.getProgress() + 0.0025);
				
				if(recordBar.getProgress() > 1){
					time.stop();
					mainText.setText(null);
					
					recordButton.setDisable(false);
					playButton.setDisable(false);
					submitButton.setDisable(false);
					recordBar.setVisible(false);
					
					mainText.setText("");

				}	
			}		
		});

		time.getKeyFrames().add(f);
		time.playFromStart();
	}


	/*
	 * When submit pressed, tell game handler to validate answer with server,
	 * do in thread to prevent freezing.
	 */
	@FXML
	private void submit(){
		spinner.setVisible(true);
		recordButton.setDisable(true);
		playButton.setDisable(true);
		submitButton.setDisable(true);
		final Task<Boolean> t = new Task<Boolean>(){

			@Override
			protected Boolean call() throws Exception {

				return _g.submit();
			}

		};
		t.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent arg0) {
				recordButton.setDisable(false);
				playButton.setDisable(false);
				submitButton.setDisable(false);
				spinner.setVisible(false);
				try {
					if (t.get()) {
						correctFeedback();

					}else {

						incorrectFeedback();

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			}

		});

		Thread thr = new Thread(t);
		thr.start();

	}

	/*
	 * Displays correct icon when correct.
	 */
	private void correctFeedback(){

		correctImage.setVisible(true);
		numberText.setTextFill(Color.LIGHTGREEN);
		
		Task<Void> sleeper = new Task<Void>(){
			@Override
			protected Void call() throws Exception {
				Thread.sleep(1200);
				return null;
			}
		};

		Thread thr = new Thread(sleeper);
		thr.start();

		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
			public void handle(WorkerStateEvent arg0) {
				update();
				numberText.setTextFill(col);
				correctImage.setVisible(false);
			}
		});
	}

	/*
	 * Displays incorrect icon when incorrect.
	 */
	public void incorrectFeedback(){

		incorrectImage.setVisible(true);
		numberText.setTextFill(Color.RED);
		Timeline xtime = new Timeline(new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				numberText.setLayoutX(x + Math.random()*2*defx + 2*defx);
				numberText.setLayoutY(y + Math.random()*2*defy + 2*defy);

				if(Math.random()>0.5){
					defx = -defx;
				}
				if(Math.random()>0.5){
					defy = -defy;
				}

			}

		}));

		xtime.setOnFinished(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				update();
				numberText.setTextFill(col);
				numberText.setLayoutX(x);
				numberText.setLayoutY(y);

				incorrectImage.setVisible(false);

			}

		});

		xtime.setCycleCount(25);
		xtime.setAutoReverse(false);
		xtime.play();

		Task<Void> sleeper = new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				Thread.sleep(1500);

				return null;
			}

		};

		Thread thr = new Thread(sleeper);
		thr.start();

	}



	public void exit(){
		fadeOut();
	}


	/*
	 * Method is called on the Controller to initialize all the relevant
	 * values.
	 */
	public void start(int game){

		correctImage.setVisible(false);
		incorrectImage.setVisible(false);

		level = game;
		_g = GameHandler.getInstance();

		if(game == 1){
			practise = false;
			_g.start9Game();
		}else if(game == 2){
			practise = false;
			_g.start99Game();
		}else {
			practise = true;
		}

		nextLevel.setVisible(false);
		tryAgain.setVisible(false);

		update();
	}


	@FXML
	private void initialize(){
		
		user.setVisible(false);
		enemy.setVisible(false);
		vs.setVisible(false);		
		spinner.setVisible(false);
		recordBar.setVisible(false);
		
		submitButton.setDisable(true);
		playButton.setDisable(true);
		
		numberText.setTextFill(col);
		
		x = numberText.getLayoutX();
		y = numberText.getLayoutY();
		
		userText.setText("Logged in as: " + session.getUser());
		
	}

	
	/*
	 * Displays transition animation back to home.
	 */
	private void fadeOut(){
		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(-800);

		f.setOnFinished(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				Stage s = Main.getMainApp().getStage();
				HomeScreen h = new HomeScreen(s);

			}

		});
		
		f.play();
	}

	@FXML
	private void mouseOverTick() {
		tick.setImage(new Image(Main.class.getResource("Yesblue.png").toString()));
	}

	@FXML
	private void mouseOverMic() {
		mic.setImage(new Image(Main.class.getResource("microphoneblue.png").toString()));
	}
	@FXML
	private void mouseOverSpeaker() {
		speaker.setImage(new Image(Main.class.getResource("speakerblue.png").toString()));
		
	}
	@FXML
	private void mouseExitTick() {
		tick.setImage(new Image(Main.class.getResource("Yes-512.png").toString()));
	}

	@FXML
	private void mouseExitMic() {
		mic.setImage(new Image(Main.class.getResource("microphone1600.png").toString()));
	}
	@FXML
	private void mouseExitSpeaker() {
		speaker.setImage(new Image(Main.class.getResource("speaker1600.png").toString()));
	}






}
