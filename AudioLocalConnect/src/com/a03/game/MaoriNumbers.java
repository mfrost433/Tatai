package com.a03.game;

/**
 * Enum represents the maori word with a string ands its numerical value.
 * @author Matthew
 *
 */
public enum MaoriNumbers {

	TAHI(1, "tahi"), RUA(2, "rua"), TORU(3, "toru"), WHA(4, "whaa"),
	RIMA(5, "rima"), ONO(6, "ono"), WHITU(7, "whitu"), WARU(8, "waru"), IWA(9, "iwa"), TEKAU(10, "tekau"), MAA("maa");

	private int _value;
	private String _word;

	private MaoriNumbers(int value, String word){
		_value = value;
		_word = word;
	}
	private MaoriNumbers(String word){
		_word = word;
	}


	public String getName(){
		return _word;
	}

	public int getValue(){
		return _value;
	}
}
