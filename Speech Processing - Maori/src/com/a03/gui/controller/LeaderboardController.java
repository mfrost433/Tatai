package com.a03.gui.controller;

import java.util.List;
import java.util.function.Predicate;

import com.a03.gui.Main;
import com.a03.gui.view.HomeScreen;
import com.a03.leaderboard.LeaderboardData;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class is a controller for the leaderboard. It's main function 
 * is to set up the list of players by assigning relevant data.
 * @author Matt
 *
 */
public class LeaderboardController {
	
	@FXML
	Pane pane;
	
	@FXML
	TableView<LeaderboardData> table;
	
	@FXML
	JFXTextField searchBar;
	
	@FXML
	TableColumn<LeaderboardData, String> username;
	
	@FXML
	TableColumn<LeaderboardData, Integer> correct;
	
	@FXML
	TableColumn<LeaderboardData, Integer> incorrect;
	
	@FXML
	TableColumn<LeaderboardData, String> percent;
	
	ObservableList<LeaderboardData> data = FXCollections.observableArrayList();
	
	FilteredList<LeaderboardData> f = new FilteredList<LeaderboardData>(data);
	
	/*
	 * Filters the list data by username based on your search in the top field
	 */
	@FXML
	private void search() {
		final String text = searchBar.getText();
		f.setPredicate(new Predicate<LeaderboardData>() {
			public boolean test(LeaderboardData t) {
				if(text.isEmpty() || text == null) {
					return true;
				}
				if(t.getUsername().contains(text)) {
					return true;
				}
				return false;
			}			
		});		
		
		table.setItems(f);		
	}
	
	/*
	 * Sets up ListView with all data from server.
	 */
	@FXML
	private void initialize() {
		
		table.setEditable(false);
		
		username.setCellValueFactory(new PropertyValueFactory<LeaderboardData,String>("Username"));
		correct.setCellValueFactory(new PropertyValueFactory<LeaderboardData,Integer>("Correct"));
		incorrect.setCellValueFactory(new PropertyValueFactory<LeaderboardData,Integer>("Incorrect"));
		percent.setCellValueFactory(new PropertyValueFactory<LeaderboardData,String>("Percentage"));
		
		List<LeaderboardData> l = LeaderboardData.decodeLeaderboard();

		for(int i = 0; i < l.size(); i++) {
			data.add(l.get(i));
		}
		
		table.setItems(data);
		table.getColumns().addListener(new ListChangeListener<Object>() {

			public void onChanged(Change<?> change) {

		               change.reset();

			}

	    });
	}
	
	@FXML
	private void back() {
		fadeOut();
	}
	
	/*
	 * animates back to main screen
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
	
}
