package com.a03.gui;


import java.io.File;

import com.a03.gui.view.LoginScreen;
import com.a03.server.Session;
import com.a03.speech.IOHandler;

import audio.Audio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for JavaFX application. Initialises everything and opens 
 * login screen
 * @author Matt
 *
 */

public class Main extends Application{

	IOHandler l = IOHandler.getInstance();



	private Stage primaryStage;
	private Scene scene;

	private static Main mainApp;
	public static void main(String[] args){
		
		System.out.println("STARTING, PLEASE WAIT...");
		launch(args);
	}

	@Override
	public void start(Stage primary) throws Exception {
		
		primary.setResizable(false);
		mainApp = this;
		
		this.primaryStage = primary;
		this.primaryStage.setTitle("Taitai!");

		initApp();

	}

	private void initApp() {		
		LoginScreen l = new LoginScreen(primaryStage);
	}


	public Stage getStage() {
		return primaryStage;
	}

	public Scene getScene() {
		return scene;
	}

	public static Main getMainApp() {
		return mainApp;
	}


}
