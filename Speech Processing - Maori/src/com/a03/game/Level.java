package com.a03.game;

import java.util.ArrayList;
import java.util.List;

import com.a03.speech.VoiceData;

/**
 * Data structure for the level info; contains all relevant level info
 * and allows for practise levels to be played
 * @author Matthew
 *
 */
public class Level {

	// todo: change MaoriNumbers to allow for 1-99.
	private int _attempts = 0, _number;
	private boolean pass = false;
	private List<MaoriNumbers> m;
	private String equationString;
	private boolean _add = true,
			_minus= true,
			_multiply= true,
			_divide= true;

	/*
	 * This constructor is for regular games, not practise games.
	 * determines whether to start an easy or hard level.
	 */
	public Level(int i){
		_add = true;
		_minus= true;
		_multiply= true;
		_divide= true;
		_number = i;
		m = MaoriNumberFactory.createNumber(_number);

		if( i < 10 ) {
			equationString = easyEquation();
		}else {
			equationString = hardEquation();
		}

	}
	
	/*
	 * This constructor is for practise levels, allows customization of the
	 * math operations.
	 */
	public Level(int start, int finish, boolean add, boolean minus, boolean multiply, boolean divide){
		_add = add;
		_minus = minus;
		_multiply = multiply;
		_divide = divide;
		_number = (int)((finish - start)*Math.random())+ start;
		m = MaoriNumberFactory.createNumber(_number);

		equationString = hardEquation();
		

	}

	/*
	 * converts a boolean to an int (1 = true, 0 = false)
	 */
	int boolToInt( boolean b ){
		if ( b ) 
			return 1;

		return 0;
	}
	
	public enum Operation {
	    ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
	}

	/*
	 * This defines logic for producing a practice level with
	 * a changing number of operations being played.
	 * The number of operations being played is summed, and then 
	 * 1 / the sum provides the probability bracket for each 
	 * operation being generated.
	 */
	private String hardEquation() {		
		
		ArrayList<Operation> operations = new ArrayList<Operation>();
		int num;
		
		//Adding relevant possible operations to operations list
		if(_add) {
			operations.add(Operation.ADDITION);
		}
		if(_minus) {
			operations.add(Operation.SUBTRACTION);
		}
		if( !isPrime(_number) && _multiply) {
			//Does not try to multiply to primes
			//Double weighting to counter conditions
			operations.add(Operation.MULTIPLICATION);
			operations.add(Operation.MULTIPLICATION);
		}
		if( _number < 60 && _divide ) {
			//Does not try to divide to large numbers
			//Double weighting to counter conditions
			operations.add(Operation.DIVISION);
			operations.add(Operation.DIVISION);
		}
		
		Operation operator = operations.get(	(int)	(	Math.random()*(	(double)operations.size()	)	)	);
		//Choosing a random operation
		
		//Logic for generating an equation string from the given operation
		switch (operator) {
		case ADDITION:
			num = ((int)(Math.random()*100))%_number;
			return Integer.toString(_number - num) + " + " + Integer.toString(num);
			
		case SUBTRACTION:
			num = ((int)(Math.random()*100))%(1000-_number);
			return Integer.toString(_number + num) + " - " + Integer.toString(num);
	
		case MULTIPLICATION:
			List<Integer> factorList = findFactors(_number);
			num = factorList.get( ((int)((factorList.size()*Math.random())))  );
			return Integer.toString(_number / num) + " x " + Integer.toString(num);
			
		case DIVISION:
			
			num = (int)(Math.random()* ( 150.0 / ((double)_number)) + 1);
			return Integer.toString(_number * num) + " / " + Integer.toString(num);

		}

		return "" + _number; 
		//Otherwise return lone number
	
	}


	/*
	 * defines easy level generation - 50/50 chance of producing a 
	 * subtraction or addition question
	 */
	private String easyEquation() {

		double rand1 = Math.random();
		double deciderNum = Math.random();

		if( deciderNum < 0.5) {

			//Addition question
			int num = ((int)(rand1*_number));
			return Integer.toString(_number - num) + " + " + Integer.toString(num);
		}else {
			//Subtraction question
			int num = ((int)(rand1*_number));
			return Integer.toString(_number + num) + " - " + Integer.toString(num);
		}

	}

	public List<MaoriNumbers> getWord(){
		return m;
	}
	
	public void passed() {
		pass = true;
	}

	public boolean getPass(){
		return pass;
	}

	public int getAttempt(){
		if(_attempts == 2) {
			_attempts = 0;
			return 2;
		}
		return _attempts;
	}
	
	public int getNumber(){
		return _number;
	}
	
	public void attempted() {
		_attempts++;
	}
	
	public String getEquationString() {
		return equationString;
	}
	
	/*
	 * used for find factors for multiplication questions
	 */
	public List<Integer> findFactors(int n){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for( int i = 1; i <= n; i ++) {
			
			if( n % i == 0 ) {
				list.add(i);
			}
			
		}
		
		return list;
	}
	
	/*
	 * Avoids prime numbers from becoming multiplication questions.
	 */
	private boolean isPrime(int num) {
		if (num % 2 == 0) return false;
		for (int i = 3; i * i < num; i += 2)
			if (num % i == 0) return false;
		return true;
	}

}



