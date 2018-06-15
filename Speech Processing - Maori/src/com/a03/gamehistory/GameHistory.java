package com.a03.gamehistory;

/**
 * This class encapsulates information relating to the game history of 
 * the logged in player. Allows easy access and presentation on a graph.
 * @author Matt
 *
 */
public class GameHistory {
	
	private int[] correctTotalForNumber = new int[9];
	private int[] totalForNumber = new int[9];
	
	public void setCorrectTotalForNumber(int[] i) {
		correctTotalForNumber = i;
	}
	public void setTotalForNumber(int[] i) {
		totalForNumber = i;
	}
	
	public int getPercentageCorrectForValue(int j) {
		if(j > 9) {			
			return 0;			
		}


		return (int)((((double)correctTotalForNumber[j-1])/((double)totalForNumber[j-1]))*100);

	}
	
	public int getNumberCorrectForValue(int i) {
		if(i > 9) {
			return 0;
		}
		
		return correctTotalForNumber[i-1];

	}
	
	public int getNumberIncorrectForValue(int i) {
		if(i > 9) {
			return 0;
		}		
		return totalForNumber[i-1] - correctTotalForNumber[i-1];

	}
	
	
}
