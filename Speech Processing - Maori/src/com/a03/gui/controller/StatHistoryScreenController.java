package com.a03.gui.controller;

import java.util.Arrays;
import java.util.List;

import com.a03.gamehistory.GameHistory;
import com.a03.gui.Main;
import com.a03.gui.view.HomeScreen;
import com.a03.server.Session;
import com.jfoenix.controls.JFXButton;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Controls the screen that contains individual player statistics.
 * @author Matt
 *
 */
public class StatHistoryScreenController {
	
	private GameHistory gameHistory;
	
	@FXML
	Pane pane;
	
	@FXML
	JFXButton returnButton;
	
	@FXML
	StackedBarChart chart;
	
	@FXML
	CategoryAxis xAxis;
	
	@FXML
	NumberAxis yAxis;
	
	@FXML
	Label statLabel;
	
	//String labels for graph data
	final private String n1 = "1";
	final private String n2 = "2";
	final private String n3 = "3";
	final private String n4 = "4";
	final private String n5 = "5";
	final private String n6 = "6";
	final private String n7 = "7";
	final private String n8 = "8";
	final private String n9 = "9";
	
	private int correctNum = 0;
	private int incorrectNum = 0;
	private int totalNum = 0;
	
	private String statCountString;

	
	public void init() {
		
		gameHistory = Session.getInstance().getGameHistory();
		
		//Creating two series, one for correct questions and one for incorrect questions
		final XYChart.Series<String, Number> correctSeries = new XYChart.Series<String, Number>();
		final XYChart.Series<String, Number> incorrectSeries = new XYChart.Series<String, Number>();
		
		//Setting categories 1 to 9 on the x axis
		List<String> stringList = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9);
		xAxis.setCategories(FXCollections.<String>observableArrayList(stringList));
		
		//Putting the % correct and incorrect into each series
		for( int i = 0; i < stringList.size(); i++ ) {
			int percent = gameHistory.getPercentageCorrectForValue(i + 1);
			correctSeries.getData().add(new XYChart.Data<String, Number>(stringList.get(i), percent));
			incorrectSeries.getData().add(new XYChart.Data<String, Number>(stringList.get(i), 100 - percent));
			
			//Summing total correct/incorrect
			correctNum += gameHistory.getNumberCorrectForValue(i + 1);
			incorrectNum += gameHistory.getNumberIncorrectForValue(i + 1);
			
		}
		
		totalNum = correctNum + incorrectNum;
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
		
		
		//Adding series, title, labels etc to chart
		list.add(correctSeries);
		list.add(incorrectSeries);
		
		chart.setTitle("Number Proficiency");
		chart.setData(list);
		chart.setLegendVisible(false);
		chart.getStylesheets().add(getClass().getResource("chartSheet.css").toExternalForm());
		
		yAxis.setLabel("Percentage");
		chart.getYAxis()
	       .lookup(".axis-label")
	       .setStyle("-fx-label-padding: -20 0 0 0;");
		
		//Setting labels
		statCountString = "Correct Answers:	" + correctNum +"\nIncorrect Answers:	" + incorrectNum + "\nTotal Answered:	" + totalNum;
		
		statLabel.setText(statCountString);
		
	}
	
	@FXML
	public void returnToMenu(){
		fadeOut();
		
		
	}
	private void fadeOut(){
		TranslateTransition f = new TranslateTransition();
		f.setNode(pane);
		f.setByX(-800);

		f.setOnFinished(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				HomeScreen h = new HomeScreen(Main.getMainApp().getStage());
				
			}
			
		});
		f.play();
	}

}
