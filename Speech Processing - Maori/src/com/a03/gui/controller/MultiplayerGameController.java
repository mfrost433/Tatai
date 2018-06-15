package com.a03.gui.controller;

import java.util.concurrent.ExecutionException;

import com.a03.game.GameHandler;
import com.a03.game.Stats;
import com.a03.gui.Main;
import com.a03.gui.view.GameScreen;
import com.a03.gui.view.HomeScreen;
import com.a03.multiplayer.MultiplayerGameHandler;
import com.a03.multiplayer.gamedata.MultiplayerStats;
import com.a03.server.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Specifies another controller for the game screen, but specifically for
 * the multiplayer mode. Uses a different part of the database/server.
 * @author Matt
 *
 */
public class MultiplayerGameController {
	
	MultiplayerGameHandler _g = MultiplayerGameHandler.getInstance();
	double x;
	double y;

	int defx = 1;
	int defy = 1;
	boolean practise = false;
	private Session session = Session.getInstance();
	private int seconds, level;
	private Timeline time = new Timeline();
	boolean lastTurn  = false;

	private Color col = new Color(33.0/255, 150.0/255, 243.0/255, 1.0);
	
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
	
	@FXML
	private Label user;	
	
	@FXML
	private Label vs;	
	
	@FXML
	private Label enemy;


	//record button pressed


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
	
	

	//play button pressed
	@FXML
	private void play(){

		_g.play();
		timer();

	}

	// FIX THIS METHOD

	@FXML
	private void tryAgain(){

		GameScreen game = new GameScreen(level,Main.getMainApp().getStage());

	}

	@FXML
	private void nextLevel(){

		GameScreen game = new GameScreen(level%2 + 1,Main.getMainApp().getStage());


	}

	private void end(){
		MultiplayerStats s = new MultiplayerStats();
		recordButton.setDisable(true);
		playButton.setDisable(true);
		submitButton.setDisable(true);

		mainText.setMinWidth(Region.USE_PREF_SIZE);
		mainText.setText(s.getCorrect() + " out of 10 correct");
		numberText.setText("");


	}

	// submit button pressed

	private void update() {
		sideText.setText(new MultiplayerStats().toString());

		if(new MultiplayerStats().getIsFinished()){
			end();

		}else{


			numberText.setText("" + _g.getEquation());
		}



	}

	// method used by multiple other methods to display a 2 second countdown timer
	private void timer(){

		recordButton.setDisable(true);
		playButton.setDisable(true);
		submitButton.setDisable(true);
		recordBar.setVisible(true);

		//mainText.setText( message +  Integer.toString(seconds));

		time = new Timeline();
		recordBar.setProgress(0);
		time.setCycleCount(Timeline.INDEFINITE);

		KeyFrame f = new KeyFrame(Duration.seconds(0.005), new EventHandler<ActionEvent>(){

			public void handle(ActionEvent e) {
				recordBar.setProgress(recordBar.getProgress() + 0.0025);
				seconds--;
				//mainText.setText( message  + Integer.toString(seconds));

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

	// submit button pressed
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

					}t.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		//need to add mainText messages based on result
		Thread thr = new Thread(t);
		thr.start();



	}




	private void correctFeedback(){

		//mainText.setText("Correct!");
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
				//mainText.setText("");
				correctImage.setVisible(false);
			}

		});
	}

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


		//mainText.setText("Incorrect");
		Task<Void> sleeper = new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				Thread.sleep(1500);

				return null;
			}

		};

		Thread thr = new Thread(sleeper);
		thr.start();

		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent arg0) {

				//incorrectImage.setVisible(false);
				//mainText.setText("");
			}

		});


	}



	public void exit(){
		fadeOut();
		}


	@FXML
	private void initialize(){
		user.setVisible(true);
		vs.setVisible(true);
		enemy.setVisible(true);
		user.setText(Session.getInstance().getUser());
		enemy.setText(new MultiplayerStats().getChallengee());
		//multiplayerText.setText(Session.getInstance().getUser() + "\n   VS   \n" + new MultiplayerStats().getChallengee());

		correctImage.setVisible(false);
		incorrectImage.setVisible(false);
		spinner.setVisible(false);
		recordBar.setVisible(false);
		
		submitButton.setDisable(true);
		playButton.setDisable(true);

		
		Session s = Session.getInstance();
		
		numberText.setTextFill(col);
		
		x = numberText.getLayoutX();
		y = numberText.getLayoutY();
		
		userText.setText("Logged in as: " + session.getUser());
		nextLevel.setVisible(false);
		tryAgain.setVisible(false);

		update();

		
	}

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
