package com.a03.gui.controller;

import com.jfoenix.controls.JFXButton;

import audio.Audio;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.a03.gui.Main;
import com.a03.gui.view.HomeScreen;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Controller for instruction screen - handles button presses, animations
 * and other specific features.
 * @author Matt
 *
 */
public class InstructionScreenController {
	
	@FXML
	Pane pane;

	MediaPlayer mediaPlayer = null;

	boolean p1 = true;

	@FXML
	AnchorPane pane2;

	@FXML
	Pane pronouncePane;

	@FXML
	AnchorPane root;

	@FXML
	ImageView mic;

	@FXML
	Tooltip micTip;

	@FXML
	JFXButton recordButton;

	@FXML
	ImageView speaker;

	@FXML
	ImageView tick;

	@FXML
	JFXButton returnButton;

	@FXML
	private void initialize() {
	}
	
	@FXML
	public void exit() {
		fadeOut(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				HomeScreen h = new HomeScreen(Main.getMainApp().getStage());

			}

		});
	}

	@FXML
	private void right() {
		swapPane(-1);

	}

	@FXML
	public void back() {
		swapPane(1);

	}

	/*
	 * Exit with animaton back to the home screen
	 */
	private void fadeOut(EventHandler<ActionEvent> e){
		if(p1) {
			TranslateTransition f = new TranslateTransition();
			f.setNode(pane2);
			f.setByX(600);

			f.setOnFinished(e);
			f.play();
		}else {
			TranslateTransition f = new TranslateTransition();
			f.setNode(pane);
			f.setByX(-600);

			f.setOnFinished(e);
			f.play();
		}
	}

	/*
	 * Slides the center pane to the right or left.
	 */
	private void swapPane(int d) {

		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(800 * d);

		TranslateTransition f2 = new TranslateTransition();
		f2.setNode(pane2);
		f2.setByX(555 * d);


		TranslateTransition f3 = new TranslateTransition();
		f3.setNode(pronouncePane);
		f3.setByX(701 * d);

		f.play();
		f2.play();
		f3.play();
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
		//tick.setImage(new Image(Main.class.getResource("speakerblue.png").getPath()));
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

	/*
	 * These functions play the audio when pressing the buttons.
	 */
	
	@FXML
	private void one() {
		play("tahi.wav");
	}

	@FXML
	private void two() {
		play("rua.wav");
	}

	@FXML
	private void three() {
		play("toru.wav");
	}

	@FXML
	private void four() {
		play("wha.wav");
	}

	@FXML
	private void five() {
		play("rimaII.wav");
	}

	@FXML
	private void six() {
		play("ono.wav");
	}

	@FXML
	private void seven() {
		play("whituIII.wav");
	}
	@FXML
	private void eight() {
		play("waruII.wav");
	}

	@FXML
	private void nine() {
		play("iwa.wav");
	}

	@FXML
	private void ten() {
		play("tekau.wav");
	}


	@FXML
	private void toExample() {
		swapPane(-1);
	}

	@FXML
	private void toPronunciations() {
		swapPane(1);
	}

	/*
	 * Defines Java IO method for playing the .wav audio files.
	 */
	private void play(String audios) {
		URL u = Audio.class.getResource(audios);

		try {
			Clip c = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(u);
	        DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
	        c = (Clip)AudioSystem.getLine(info);
	        c.open(inputStream);

			c.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
