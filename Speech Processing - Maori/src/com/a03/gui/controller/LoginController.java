package com.a03.gui.controller;

import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;

import com.a03.gui.Main;
import com.a03.gui.view.HomeScreen;
import com.a03.gui.view.SignUpScreen;
import com.a03.server.LoginHandler;
import com.a03.server.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

/**
 * Handles user login, prevents illegal entry of user info.
 * @author Matt
 *
 */
public class LoginController {
	
	private Session s = Session.getInstance();
	private LoginHandler l = new LoginHandler();
	@FXML
	JFXButton signUp;
	
	@FXML
	JFXSpinner spinner;
	
	@FXML
	Pane pane;
	
	@FXML
	Label alertText;

	@FXML
	JFXButton login;

	@FXML
	JFXTextField username;

	@FXML
	JFXPasswordField password;

	@FXML
	private void signUpPressed(){
		fadeOut(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				SignUpScreen l = new SignUpScreen(Main.getMainApp().getStage());
				
			}
			
		});
		

	}

	/*
	 * Validates whether details were correct or not. Recieve a session token if
	 * successful, which allows you to play games.
	 */
	@FXML
	private void loginPressed(){
		spinner.setVisible(true);
		login.setDisable(true);
		signUp.setDisable(true);
		final String user = username.getText();
		final String pass = password.getText();
		
		final Task<Boolean> t = new Task<Boolean>(){

			@Override
			protected Boolean call() throws Exception {
				try {
					if(l.login(user, pass)){
						return true;
					}else{
						return false;
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
				return false;
			}
	
		};
		
		t.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent w) {
				boolean success = t.getValue();
				login.setDisable(false);
				spinner.setVisible(false);
				signUp.setDisable(false);
				if(success){
					
					s.setUser(user);
					fadeOut(new EventHandler<ActionEvent>(){

						public void handle(ActionEvent arg0) {
							HomeScreen h = new HomeScreen(Main.getMainApp().getStage());
							
						}
						
					});
					
				}else{
					alertText.setText("Invalid username or password");
				}
				
			}

		});
		
		Thread thr = new Thread(t);
	
		thr.start();
		
	}
	
	@FXML
	private void initialize(){
		alertText.setText("");
		spinner.setVisible(false);
	}
	/*
	 * animates to main screen
	 */
	private void fadeOut(EventHandler<ActionEvent> e){
		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(600);

		f.setOnFinished(e);
		f.play();
	}

	
}
