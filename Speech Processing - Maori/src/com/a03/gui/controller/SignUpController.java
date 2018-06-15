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
import com.a03.gui.view.LoginScreen;
import com.a03.server.LoginHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

/**
 * Handles sign up and validation screen, prevents invalid input.
 * @author Matt
 *
 */
public class SignUpController {
	LoginHandler l = new LoginHandler();
	boolean userValid = false;

	@FXML
	Pane pane;

	@FXML
	JFXSpinner spinner;

	@FXML
	JFXButton signUp;

	@FXML
	JFXTextField username;

	@FXML
	Label validText;

	@FXML
	Label alertText;

	@FXML
	JFXPasswordField password;

	@FXML
	JFXPasswordField confirmPass;

	@FXML
	JFXButton back;

	/*
	 * When the username text is changed, checks if the name already exists.
	 */
	@FXML
	private void checkUser(){
		userValid = false;
		signUp.setDisable(true);
		validText.setText("X");
		alertText.setText("Username invalid or already in use");
		final Task<Boolean> t = new Task<Boolean>(){

			@Override
			protected Boolean call() throws Exception {
				String name = username.getText();
				return (!l.checkUsernameConflict(name) && name.length() > 0) && name.length() < 18;

			}
		};
		t.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent arg0) {
				userValid = t.getValue();
				if(userValid){
					validText.setText("");
					alertText.setText("");
				}else{
					validText.setText("X");
					alertText.setText("Username invalid or already in use");
				}

				if(userValid && passCheck()){

					signUp.setDisable(false);
				}else{

					signUp.setDisable(true);
				}
			}

		});
		Thread thr = new Thread(t);
		thr.start();

	}

	/*
	 * When password is edited, checks that the confirm password matches the
	 * other password.
	 */
	private boolean passCheck(){

		String pass = password.getText();
		String confirm = confirmPass.getText();


		if(pass.compareTo(confirm) == 0 && pass.length() > 0){
			alertText.setText("");
			return true;

		}else{
			alertText.setText("Passwords do not match");
			return false;
		}

	}

	/*
	 * Sends a request to the server to sign up the player.
	 */
	@FXML
	private void signUp(){
		spinner.setVisible(true);
		username.setDisable(true);
		password.setDisable(true);
		confirmPass.setDisable(true);
		signUp.setDisable(true);
		final Task<Void> t = new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				try {
					l.register(username.getText(), password.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		};
		t.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			public void handle(WorkerStateEvent arg0) {
		signUp.setText("Done!");
		fadeOut();
		spinner.setVisible(false);
		username.setDisable(false);
		password.setDisable(false);
		confirmPass.setDisable(false);
		signUp.setDisable(false);
			}
		});
		Thread thr = new Thread(t);
		thr.start();
	}

	@FXML
	private void checkPass(){
		signUp.setDisable(!passCheck() || !userValid);

	}

	@FXML
	private void back(){
		fadeOut();
	}

	@FXML
	private void initialize(){
		spinner.setVisible(false);
		alertText.setText("");
		signUp.setDisable(true);
	}

	private void fadeOut(){
		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(-600);
		f.setOnFinished(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				LoginScreen l = new LoginScreen(Main.getMainApp().getStage());

			}

		});
		f.play();
	}


}
