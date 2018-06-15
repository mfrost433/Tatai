package com.a03.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory creates a MaoriNumber enum based on the integer input,
 * and returns it
 * @author Matthew
 *
 */
public class MaoriNumberFactory {

	/*
	 * creates a List of maori number enums based on an integer input
	 */
	public static List<MaoriNumbers> createNumber(int i){

		List<MaoriNumbers> l = new ArrayList<MaoriNumbers>();
		// Grabs all digits from the input number seperated into array.
		String num = i + "";
		int[] digits = new int[num.length()];

		int count = -1;


		while(i > 0){
			count++;
			digits[count] = i%10;
			i = i / 10;

		}

		// Iterates through all items in array, and compares to
		// possible enums, find one with matching value, adds to list to return.
		for(;count >= 0; count--){
			for(MaoriNumbers m : MaoriNumbers.values()){
				if(digits[count] == m.getValue()){
					l.add(m);
				}

			}
		}
		if(digits.length == 2){
			if(l.get(0) == MaoriNumbers.TAHI){
				l.set(0,  MaoriNumbers.TEKAU);
			}else{
				l.add(1,MaoriNumbers.TEKAU);
				l.add(2, MaoriNumbers.MAA);
				return l;
			}
			l.add(1, MaoriNumbers.MAA);
			
		}
		return l;

	}
	
	/*
	 * finds the corresponding Maori number enum based on a name string
	 */
	public static MaoriNumbers createNumber(String name){

		for(MaoriNumbers m : MaoriNumbers.values()){
			if(name.contentEquals(m.getName())){
				return m;
			}

		}
		return null;
	}
	private MaoriNumberFactory(){};

}
