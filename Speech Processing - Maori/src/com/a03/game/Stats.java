package com.a03.game;

import java.util.List;

/**
 * This class encapsulates information regarding the display statistics of the
 * current game. Allows easy access to this information.
 * @author Matt
 *
 */
public class Stats {
	
	private int _correct, _incorrect, _level, _question, _attempt;
	public int getLevelNumber() {
		if(_question <= 10) {
			return _question;
		}
		return 10;
	}	

	public boolean getIsFinished() {
		return _question > 10;
	}

	public int getCorrect() {
		return _correct;
	}

	public int getWrong() {
		return _incorrect;
	}

	public int getLevelType() {

		return _level;

	}
	public int getAttempts() {
		return _attempt;
	}

	public void setLevelNumber(int levelNumber) {
		_question = levelNumber;
	}	

	public void setCorrect(int correct) {
		_correct = correct;
	}

	public void setWrong(int wrong) {
		_incorrect = wrong;
	}

	public void setLevelType(int type) {

		_level = type;

	}
	public void setAttempts(int attempts) {
		_attempt = attempts;
	}

	/*
	 * For a practise game, retrieves the information from the local
	 * game handler.
	 */
	public void getFromLocalGame() {
		List<Level> levelList = GameHandler.getInstance().levelList;
			for(Level l : levelList) {
				if(l.getPass()) {
					_correct++;
				}else {
					_incorrect++;
				}

			}

		_incorrect = _incorrect - 1;

		_question = levelList.size();

		_attempt = levelList.get(levelList.size()-1).getAttempt() ;
		_level = 0;
	}


	public String getAttemptString() {

		if( _attempt == 0 ) {
			return "First Attempt";
		}else {
			return "Final Attempt";
		}
	}

	@Override
	public String toString(){
		int q;
		if(_question <= 10){
			q = _question;
		}
		else{
			q = 10;
		}
		return ("Level: " + getLevelType() + "\nQuestion: " + q +"\n" + "Correct: " + getCorrect() + "\n"  + "Wrong: "+ getWrong() + "\n" + getAttemptString());

	}

}
