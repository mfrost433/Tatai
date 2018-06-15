package com.a03.multiplayer.gamedata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.a03.leaderboard.LeaderboardData;
import com.a03.server.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class encapsulates information about the multiplayer games.
 * information recieved in JSON format is converted assigned to variables
 * and displayed in the in active games, and in finished games.
 * @author Matt
 *
 */
public class GameData {
	private String _gameId, _challenger, _challengee;
	private int _challengerLevel,  _challengeeLevel, _userCorrect, _enemyCorrect;
	private boolean finished = false;
	private GameData(String gameId, String challenger, String challengee, int challengerLevel, int challengeeLevel) {
		_gameId = gameId;
		_challenger = challenger;
		_challengee = challengee;
		_challengerLevel = challengerLevel;
		_challengeeLevel = challengeeLevel;
	}

	private GameData(String gameId, String challenger, String challengee, int challengerLevel, int challengeeLevel, int userCorrect,
			int enemyCorrect) {
		_gameId = gameId;
		_challenger = challenger;
		_challengee = challengee;
		_challengerLevel = challengerLevel;
		_challengeeLevel = challengeeLevel;
		_userCorrect = userCorrect;
		_enemyCorrect = enemyCorrect;
		finished = true;
	}

	public static ObservableList<GameData> getList() {
		JSONArray arr = null;
		try {
			arr = new JSONArray(Session.getInstance().getGameInfo());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ObservableList<GameData> out = FXCollections.observableArrayList();

		try {
			for(int i = 0; i < 	arr.length(); i++) {

				JSONObject o = arr.getJSONObject(i);
				String gameId = o.getString("GameId").trim();
				String challenger = o.getString("Challenger").trim();
				String challengee = o.getString("Challengee").trim();

				int challengerLevel = o.getInt("challengerLevel");
				int challengeeLevel = o.getInt("challengeeLevel");

				if(!new MultiplayerStats(gameId).getIsFinished()) {
					out.add(new GameData(gameId,challenger,challengee,challengerLevel,challengeeLevel));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return out;
	}
	public static ObservableList<GameData> getFinishedList() {

		JSONArray arr = null;
		try {
			arr = new JSONArray(Session.getInstance().getGameInfo());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ObservableList<GameData> out = FXCollections.observableArrayList();

		try {
			for(int i = 0; i < 	arr.length(); i++) {
				
				JSONObject o = arr.getJSONObject(i);
				String gameId = o.getString("GameId").trim();
				String challenger = o.getString("Challenger").trim();
				String challengee = o.getString("Challengee").trim();

				int challengerLevel = o.getInt("challengerLevel");
				int challengeeLevel = o.getInt("challengeeLevel");

				int userCorrect = new MultiplayerStats(gameId).getCorrect();
				int enemyCorrect = 0;
				if(Session.getInstance().getUser().compareTo(challenger.trim()) == 0) {
					
					enemyCorrect = Session.getInstance().getEnemyScore(gameId, challengee);
					
				}else{
					
					enemyCorrect = Session.getInstance().getEnemyScore(gameId, challenger);
					
				}

				if(new MultiplayerStats(gameId).getIsFinished()) {
					out.add(new GameData(gameId,challenger,challengee,challengerLevel,challengeeLevel, userCorrect, enemyCorrect));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return out;
	}
	
	public String getUsername() {
		return Session.getInstance().getUser();
	}
	
	public int getYourScore() {
		return _userCorrect;
	}
	
	public String getCompetitor() {
		String enemy = "";
		if(_challengee.compareTo(Session.getInstance().getUser()) != 0) {
			enemy =  _challengee;
		}else {
			enemy =  _challenger;
		}
		return enemy;
	}
	
	public String getCompetitorScore() {
		if(_enemyCorrect == -1) {
			return "?";
		}else {
			return "" + _enemyCorrect;
		}
	}
	
	public String getResult() {
		if(_enemyCorrect == -1) {
			return "N/A";
		}
		if(_userCorrect == _enemyCorrect) {
			return "DRAW" ;
		}else if(_userCorrect > _enemyCorrect) {
			return "WIN" ;
		}else {
			return "LOSS" ;
		}
	}

	@Override
	public String toString() {
		String enemy = "";
		if(_challengee.compareTo(Session.getInstance().getUser()) != 0) {
			enemy =  _challengee;
		}else {
			enemy =  _challenger;
		}
		if(finished) {
			if(_enemyCorrect == -1) {
				return Session.getInstance().getUser() + ": "+ _userCorrect + "/10 || " + enemy + ": " +"?/10" ;
			}
			if(_userCorrect == _enemyCorrect) {
				return Session.getInstance().getUser() + ": "+ _userCorrect + "/10 || " + enemy + ": " +_enemyCorrect + "/10 || DRAW" ;
			}else if(_userCorrect > _enemyCorrect) {
				return Session.getInstance().getUser() + ": "+ _userCorrect + "/10 || " + enemy + ": " +_enemyCorrect + "/10 || WIN" ;
			}else {
				return Session.getInstance().getUser() + ": "+ _userCorrect + "/10 || " + enemy + ": " +_enemyCorrect + "/10 || LOSS" ;
			}
		}
		if(_challengee.compareTo(Session.getInstance().getUser()) != 0) {
			return "You challenged: " + enemy;
		}else {
			return enemy + ": challenged you!";
		}
	}

	public String getGameId() {
		return _gameId;
	}

	public String getChallengerId() {
		if(_challengee.compareTo(Session.getInstance().getUser()) != 0) {
			return  _challengee;
		}else {
			return _challenger ;
		}
	}

}
