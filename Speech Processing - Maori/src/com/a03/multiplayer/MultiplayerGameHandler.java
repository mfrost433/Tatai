package com.a03.multiplayer;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.a03.multiplayer.gamedata.MultiplayerStats;
import com.a03.server.Session;
import com.a03.speech.IOHandler;
import com.a03.speech.VoiceConverter;

import javafx.concurrent.Task;

/**
 * Class is similar to GameHandler, but interfaces with the server to generate
 * and play multiplayer games, rather than single player games.
 * @author Matt
 *
 */
public class MultiplayerGameHandler {
	private List<MultiplayerLevel> l = new ArrayList<MultiplayerLevel>();
	private IOHandler h = IOHandler.getInstance();	
	private Session sess = Session.getInstance();
	private static MultiplayerGameHandler instance;
	
	private String enemy;
	private int _diff;

	public static MultiplayerGameHandler getInstance() {
		if(instance == null) {
			instance = new MultiplayerGameHandler();
			return instance;
		}
		return instance;
	}
	private MultiplayerGameHandler() {}


	/*
	 * String from server recieved is a math equation - 
	 * solves this equation to find an integer to store.
	 */
	public void startGame(String challenged, int diff) {

		enemy = challenged;
		_diff = diff;

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String foo = sess.startMultiplayerGame(diff, challenged);
		String eq = foo.replace("x", "*");

		try {
			l.add(new MultiplayerLevel((Integer)engine.eval(eq), foo, challenged));

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEquation() {
		return l.get(l.size() - 1).getEquation().trim();
	}

	public void resumeGame(String gameId, String challenged) {

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		String foo = sess.resume(gameId);
		foo = foo.trim();

		
		try {
			l.add(new MultiplayerLevel((Integer)engine.eval(foo), foo, challenged));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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

	public void play(){
		h.play();
	}
	
	private boolean checkAnswer() {
		return sess.checkMultiplayerAnswer(VoiceConverter.toByte(), l.get(l.size() - 1).getWord());
	}
	
	public boolean submit(){

		boolean pass = checkAnswer();
		l.get(l.size() - 1).attempted();

		if(pass){
			if(!new MultiplayerStats().getIsFinished()) {
				l.get(l.size() - 1).passed();
				nextLevel();	
			}	

		}else if(l.get(l.size() -1).getAttempt() == 2){
			if(!new MultiplayerStats().getIsFinished()) {
				nextLevel();	

			}	
		}

		return pass;
	}

	private void nextLevel() {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String foo = sess.nextMultiplayerLevel(_diff);


		try {

			l.add(new MultiplayerLevel((Integer)engine.eval(foo), foo, enemy));

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
