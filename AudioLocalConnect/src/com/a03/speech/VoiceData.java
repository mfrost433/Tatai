package com.a03.speech;


import java.util.ArrayList;
import java.util.List;

import com.a03.game.MaoriNumberFactory;
import com.a03.game.MaoriNumbers;


/**
 * Data structure for the voice information. Contains all words that HTK believes have
 * been spoken.
 * @author Matthew
 *
 */

public class VoiceData {
	private List<String> _maoriWords;
	private List<MaoriNumbers> m = new ArrayList<MaoriNumbers>();

	public VoiceData(List<String> words){
		_maoriWords = words;
		
		for(String s:_maoriWords){
			m.add(MaoriNumberFactory.createNumber(s));
		}
		
	}

	public MaoriNumbers getWord(int i){
		return m.get(i);
	}
	
	public int size(){
		return m.size();
	}
}
