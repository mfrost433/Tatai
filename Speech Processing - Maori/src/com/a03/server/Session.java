package com.a03.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.a03.game.Level;
import com.a03.game.MaoriNumbers;
import com.a03.game.Stats;
import com.a03.gamehistory.GameHistory;
import com.a03.gamehistory.GameHistoryUpdaterImpl;
import com.a03.gamehistory.GameHistoryUpdaterImplService;
import com.a03.gamehistory.GetGameHistoryImpl;
import com.a03.gamehistory.GetGameHistoryImplService;
import com.a03.leaderboard.GetLeaderboardImpl;
import com.a03.leaderboard.GetLeaderboardImplService;
import com.a03.leaderboard.LeaderboardData;
import com.a03.multiplayer.generated.GameInfoImpl;
import com.a03.multiplayer.generated.GameInfoImplService;
import com.a03.multiplayer.generated.NewEasyMultiplayerGameImpl;
import com.a03.multiplayer.generated.NewEasyMultiplayerGameImplService;
import com.a03.multiplayer.generated.NewHardMultiplayerGameImpl;
import com.a03.multiplayer.generated.NewHardMultiplayerGameImplService;
import com.a03.multiplayer.generated.NextMultiplayerLevelImpl;
import com.a03.multiplayer.generated.NextMultiplayerLevelImplService;
import com.a03.newlevel.NewLevel1To99Impl;
import com.a03.newlevel.NewLevel1To99ImplService;
import com.a03.newlevel.NewLevel1To9Impl;
import com.a03.newlevel.NewLevel1To9ImplService;
import com.a03.recieveaudio.RecieveImpl;
import com.a03.recieveaudio.RecieveImplService;
import com.a03.recievemulti.RecieveMultiplayerImpl;
import com.a03.recievemulti.RecieveMultiplayerImplService;
import com.a03.stats.StatsHandlerImpl;
import com.a03.stats.StatsHandlerImplService;
import com.google.gson.Gson;

import javafx.collections.ObservableList;

/**
 * Contains all information about current login including token, username, etc.
 * Directly connects to the server, requests information regarding game requests, 
 * answer validation, etc.
 * @author Matt
 *
 */
public class Session {
	
	private static Session instance = null;
	private String token;
	private String _user = "";
	private static Connection c = null;
	private String currentGameId = null;

	public static Session getInstance(){
		if(instance == null){
			try {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			instance = new Session();

		}
		return instance;
	}

	

	public void setUser(String user){
		_user = user;
	}

	public void setToken(String t) {
		token = t;
	}

	public String getUser(){
		return _user;
	}


	public int startNewLevel(int i) {
		if(i == 1) {
			NewLevel1To9ImplService service = new NewLevel1To9ImplService();
			NewLevel1To9Impl impl = service.getNewLevel1To9ImplPort();

			return impl.new1To9(token);

		}else {
			NewLevel1To99ImplService service = new NewLevel1To99ImplService();
			NewLevel1To99Impl impl = service.getNewLevel1To99ImplPort();

			return impl.new1To99(token);
		}

	}
	
	public int startNewGame(int i) {
		if(i == 1) {
			NewLevel1To9ImplService service = new NewLevel1To9ImplService();
			NewLevel1To9Impl impl = service.getNewLevel1To9ImplPort();

			return impl.newGame1To9(token);

		}else {
			NewLevel1To99ImplService service = new NewLevel1To99ImplService();
			NewLevel1To99Impl impl = service.getNewLevel1To99ImplPort();

			return impl.newGame1To99(token);
		}

	}

	/*
	 * Sends a .wav file to be checked for correctness in a byte array format.
	 */
	public boolean checkAnswer(byte[] b, List<MaoriNumbers> m ) {

		RecieveImplService serv = new RecieveImplService();
		RecieveImpl r = serv.getRecieveImplPort();

		return r.playAudio(b, m, token);
	}
	
	/*
	 * Checks a multiplayer answer in a similar fashion as above.
	 */
	public boolean checkMultiplayerAnswer(byte[] b, List<MaoriNumbers> m ) {

		RecieveMultiplayerImplService serv = new RecieveMultiplayerImplService();
		RecieveMultiplayerImpl r = serv.getRecieveMultiplayerImplPort();

		return r.recieveAudio(b, m, token, currentGameId);
	}

	public Stats getStats() {
		StatsHandlerImplService serv = new StatsHandlerImplService();
		StatsHandlerImpl impl = serv.getStatsHandlerImplPort();
		return impl.getStats(token);

	}


	public void updateGameHistory() {
		GameHistoryUpdaterImplService s = new GameHistoryUpdaterImplService();
		GameHistoryUpdaterImpl impl = s.getGameHistoryUpdaterImplPort();
		impl.updateHistory(token);
	}

	public GameHistory getGameHistory() {
		GetGameHistoryImplService s = new GetGameHistoryImplService();
		GetGameHistoryImpl impl = s.getGetGameHistoryImplPort();
		return new Gson().fromJson( impl.getGameHistory(_user), GameHistory.class );
	}

	/*
	 * Gets leaderboard info in JSON format.
	 */
	public JSONArray getLeaderboard(){
		
		GetLeaderboardImplService s = new GetLeaderboardImplService();
		GetLeaderboardImpl impl = s.getGetLeaderboardImplPort();
		
		try {
			return new JSONArray(impl.getLeaderBoardInfo());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Requests a multiplayer game, recieves a string containing game ID and challenger
	 * separated by a dot.
	 */
	public String startMultiplayerGame(int i, String challenged) {

		if(i == 1) {
			NewEasyMultiplayerGameImplService s = new NewEasyMultiplayerGameImplService();
			NewEasyMultiplayerGameImpl impl = s.getNewEasyMultiplayerGameImplPort();

			String[] out = seperateGameInfo(impl.newEasyMultiplayerGame(_user, challenged));
			currentGameId = out[0];
	
				return out[1];			
		}else {
			NewHardMultiplayerGameImplService s = new NewHardMultiplayerGameImplService();
			NewHardMultiplayerGameImpl impl = s.getNewHardMultiplayerGameImplPort();

			String[] out = seperateGameInfo(impl.newHardMultiplayerGame(_user, challenged));
			currentGameId = out[0];

				return out[1];			
		}
		
	}
	
	public boolean doesPlayerExist(String player) {
		GameInfoImplService s = new GameInfoImplService();
		GameInfoImpl impl = s.getGameInfoImplPort();
		return impl.userExists(player);
	}
	
	public String getGameInfo() {
		GameInfoImplService s = new GameInfoImplService();
		GameInfoImpl impl = s.getGameInfoImplPort();
		return impl.getCurrentGames(token);
	}
	
	private String[] seperateGameInfo(String s) {
		String[] out = s.split(Pattern.quote("."));
			return out;
		}
	
	/*
	 * When an active game is selected, it resumes the game
	 * based on the ID
	 */
	public String resume(String gameId) {

		currentGameId = gameId;
		NextMultiplayerLevelImplService s = new NextMultiplayerLevelImplService();
		NextMultiplayerLevelImpl impl =  s.getNextMultiplayerLevelImplPort();
		return impl.resumeGame(gameId, token);
	}
	
	public String nextMultiplayerLevel(int i) {
		NextMultiplayerLevelImplService s = new NextMultiplayerLevelImplService();
		NextMultiplayerLevelImpl impl =  s.getNextMultiplayerLevelImplPort();

		return impl.nextMultiplayerLevel(currentGameId,token).trim();
	}
	
	public JSONObject getMultiplayerStats() {
		GameInfoImplService s = new GameInfoImplService();
		GameInfoImpl impl = s.getGameInfoImplPort();
		String out = impl.getGameStats(_user,currentGameId);


		if(out== null) {
			return null;
		}
		try {
			return new JSONObject(out);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject getMultiplayerStats(String Id) {
		GameInfoImplService s = new GameInfoImplService();
		GameInfoImpl impl = s.getGameInfoImplPort();
		String out = impl.getGameStats(_user,Id);
		if(out == null) {
			return null;
		}
		try {
			return new JSONObject(out);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getEnemyScore(String Id, String username) {
		
		GameInfoImplService s = new GameInfoImplService();
		GameInfoImpl impl = s.getGameInfoImplPort();

		return impl.getEnemyScore(username,Id);
		


	}
}
