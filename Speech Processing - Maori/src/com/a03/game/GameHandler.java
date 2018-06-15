package com.a03.game;


import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;

import com.a03.server.Session;
import com.a03.speech.IOHandler;
import com.a03.speech.VoiceConverter;

/**
 * This class is a singleton, and encapsulates all information about
 * the current game you are playing. Since only one game can be played at once,
 * it makes sense to be a singleton.
 * @author Matt
 *
 */
public class GameHandler {
	public List<Level> levelList = new ArrayList<Level>();
	private IOHandler h = IOHandler.getInstance();	
	private Session sess = Session.getInstance();
	
	private boolean practise = false,
			_add = true,
			_minus= true,
			_multiply= true,
			_divide= true;	
	private int _start,_finish;		

	private static GameHandler g;
	private GameHandler(){};
	private int gameType;

	
	/*
	 * Singleton method
	 */
	public static GameHandler getInstance(){

		if (g == null){
			g = new GameHandler();
			return g;
		}
		return g;
	}

	/*
	 * Returns the current Maori word that is the correct answer for the level
	 * you are on
	 */
	public String getWord(){

		String out = "";
		for(MaoriNumbers m : levelList.get(levelList.size()-1).getWord()){
			out = out + m.getName() + " ";
		}
		return out;
	}

	/*
	 * Allows the controller to tell the game to record audio
	 */
	public void record(){

		Task<Void> t = new Task<Void>(){

			@Override
			protected Void call(){
				h.recordVoice();
				return null;

			}

		};
		Thread thr = new Thread(t);
		thr.start();

	}

	/*
	 * allows the controller to tell the game to play back auduio
	 */
	public void play(){
		h.play();
	}

	/*
	 * tells the game handler to verify the answer, and return whether it is correct
	 * or not
	 */
	private boolean checkAnswer() {
		return sess.checkAnswer(VoiceConverter.toByte(), levelList.get(levelList.size() - 1).getWord());
	}

	/*
	 * logic for when the submit button is pressed
	 */
	public boolean submit(){

		boolean pass = checkAnswer();
		levelList.get(levelList.size() - 1).attempted();
		if(!practise) {
			if(pass){
				levelList.get(levelList.size() - 1).passed();
				nextLevel();
			}else if(levelList.get(levelList.size() -1).getAttempt() == 2){
				nextLevel();			
			}	
		}else {
			if(pass){
				nextPractiseLevel();
			}else if(levelList.get(levelList.size() -1).getAttempt() == 2){
				nextPractiseLevel();

			}
		}
		return pass;
	}


	/*
	 * gets the current Maori number's numerical value
	 */
	public int getNumber(){

		return levelList.get(levelList.size()-1).getNumber();
	}

	public String getEquationString() {

		return levelList.get(levelList.size()-1).getEquationString();

	}

	public void start9Game(){
		levelList = new ArrayList<Level>();
		practise = false;

		gameType = 1;

		levelList.add(new Level(sess.startNewGame(1)));



	}

	/*
	 * starts a practise module with the selected mathematical operations.
	 */
	public void startPractiseGame(int start, int finish, boolean add, boolean minus, boolean multiply, boolean divide) {
		practise = true; 
		levelList = new ArrayList<Level>();
		_start = start;
		_finish = finish;
		_add = add;
		_minus = minus;
		_multiply = multiply;
		_divide = divide;

		levelList.add(new Level(start,finish,add,minus,multiply,divide));

	}

	public void start99Game(){
		levelList = new ArrayList<Level>();
		practise = false;
		gameType = 2;

		levelList.add(new Level(sess.startNewGame(2)));

	}
	private void nextLevel() {
		levelList.add(new Level(sess.startNewLevel(gameType)));
	}
	private void nextPractiseLevel() {
		levelList.add(new Level(_start,_finish,_add,_minus,_multiply,_divide));
	}

}
