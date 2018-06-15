package com.a03.leaderboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.a03.server.Session;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeaderboardData {
	private SimpleIntegerProperty rID;
	private SimpleStringProperty _username;
	private SimpleIntegerProperty _correct;
	private SimpleIntegerProperty _incorrect;
	private SimpleStringProperty _percentage;

	public LeaderboardData(String username, int correct, int incorrect, String percentage) {
		_username = new SimpleStringProperty(username);
		_correct = new SimpleIntegerProperty(correct);
		_incorrect = new SimpleIntegerProperty(incorrect);
		_percentage = new SimpleStringProperty(percentage);
	}

	public Integer getRID() {
		return rID.get();
	}

	public void setRID(int i) {
		rID.set(i);
	}

	public String getUsername() {
		return _username.get();
	}

	public void setUsername(String s) {
		_username.set(s);
	}

	public Integer getCorrect() {
		return _correct.get();
	}

	public void setCorrect(int i) {
		_correct.set(i);
	}

	public Integer getIncorrect() {
		return _incorrect.get();
	}

	public void setIncorrect(int i) {
		_incorrect.set(i);
	}

	public String getPercentage() {
		return _percentage.get();
	}

	public void setPercentage(String s) {
		_percentage.set(s);
	}


	public static ObservableList<LeaderboardData> decodeLeaderboard() {
		ObservableList<LeaderboardData> out = FXCollections.observableArrayList();
		JSONArray arr = null;
			arr = Session.getInstance().getLeaderboard();


		String name;
		try {
			for(int i = 0; i < 	arr.length(); i++) {
				JSONObject o = arr.getJSONObject(i);
				name = o.getString("Username").trim();
				Integer correct = o.getInt("Correct");
				Integer incorrect = o.getInt("Incorrect");
				
				double p = Double.parseDouble(o.getString("Percent")) * 100;
				String percentage = "" + Math.round(p)/100.0;
				out.add(new LeaderboardData(name,correct,incorrect,percentage));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}


		return out;

	}

}
