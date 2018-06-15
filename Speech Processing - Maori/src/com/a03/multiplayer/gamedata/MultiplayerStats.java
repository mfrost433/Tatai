package com.a03.multiplayer.gamedata;

import org.json.JSONException;
import org.json.JSONObject;

import com.a03.server.Session;

/**
 * COntains information about the current game to be displayed in the 
 * game screen, in multiplayer.
 * @author Matt
 *
 */
public class MultiplayerStats {
	private int _correct, _incorrect, _question, _attempt;
	private final String _level = "Multiplayer";
	private String _challengee = "";
	
	/*
	 * Constructs itself with information retrieved from server in JSON,
	 * if no information is recieved defaults to 0 values.
	 */
	public MultiplayerStats() {
		JSONObject obj = Session.getInstance().getMultiplayerStats();
		if(obj.isNull("Incorrect")) {
			setWrong(0);
			setCorrect(0);
			setAttempts(0);
			setChallengee("");
		}
		try {			
			setWrong(obj.getInt("Incorrect"));
			setCorrect(obj.getInt("Correct"));
			setAttempts(obj.getInt("Attempt") + 1);
			
			setChallengee(obj.getString("Challenger").trim());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Gets JSON for game by ID - used for loading a currently active game.
	 */
	public MultiplayerStats(String iD) {
		JSONObject obj = Session.getInstance().getMultiplayerStats(iD);
		if(obj == null) {
			setWrong(0);
			setCorrect(0);
			setAttempts(0);
			setChallengee("");
			return;
		}
		try {
			setWrong(obj.getInt("Incorrect"));
			setCorrect(obj.getInt("Correct"));
			setAttempts(obj.getInt("Attempt") + 1);
			setChallengee(obj.getString("Challenger").trim());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public int getLevelNumber() {
		if(_question < 10) {
			return _question;
		}else {
			return 10;
		}
	}	

	public int getCorrect() {
		return _correct;
	}

	public int getWrong() {
		return _incorrect;
	}

	public String getLevelType() {

		return _level;

	}
	
	public void setChallengee(String c) {

		_challengee = c;

	}
	
	public String getChallengee() {

		return _challengee;

	}
	public int getAttempts() {
		return _attempt;
	}

	public void setCorrect(int correct) {
		_correct = correct;
		_question = _correct + _incorrect + 1;
	}

	public void setWrong(int wrong) {
		_incorrect = wrong;
		_question = _correct + _incorrect + 1;
	}
	
	public void setAttempts(int attempts) {
		_attempt = attempts;
	}


	public String getAttemptString() {

		if( _attempt == 1 ) {
			return "First Attempt";
		}else {
			return "Final Attempt";
		}
	}

	@Override
	public String toString(){
		return ("Level: " + getLevelType() + "\nQuestion: " + getLevelNumber() +"\n" + "Correct: " + getCorrect() + "\n"  + "Wrong: "+ getWrong() + "\n" + getAttemptString());
	}


	public boolean getIsFinished() {
			return _question == 11;


	}

}
