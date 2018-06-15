package com.a03.multiplayer;

import java.util.List;

import com.a03.game.MaoriNumberFactory;
import com.a03.game.MaoriNumbers;

/**
 * Similar to the single player level, this encapsulates all useful
 * info about the current level. A list of these is stored in the gamehandler.
 * @author Matt
 *
 */
public class MultiplayerLevel {
	
	private int number;
	private String _equation;
	private String _enemy;
	private int attempts = 0;
	private boolean pass = false;
	private List<MaoriNumbers> m;
	
	public MultiplayerLevel(int n, String equation, String enemy) {
		number = n;
		_equation = equation;
		_enemy = enemy;		
		m = MaoriNumberFactory.createNumber(n);
		
	}

	
	public List<MaoriNumbers> getWord() {
		return m;
	}
	
	public void passed() {
		pass = true;
	}
	
	public int getAttempt() {
		if(attempts == 2) {
			attempts = 0;
			return 2;
		}
		return attempts;
	}
	
	public void attempted() {
		attempts++;
		
	}
	
	public int getNumber() {
		return number;
		
	}
	
	public String getEquation() {
		return _equation;
		
	}
	
	public String getEnemy() {
		return _enemy;
		
	}
	
	

}
