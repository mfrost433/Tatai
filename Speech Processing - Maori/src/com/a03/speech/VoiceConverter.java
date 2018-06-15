package com.a03.speech;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Important class - converts audio to a byte array to send via SOAP
 * to the server for validation.
 * @author Matt
 *
 */
public class VoiceConverter {
	
	public static byte[] toByte() {
		String fs = File.separator;
		File f = new File("Audio"+fs+"foo.wav");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		byte[] data = new byte[(int) f.length()];

		FileInputStream inp;

		try {
			inp = new FileInputStream(f);
			inp.read(data);
			inp.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;

	}
}
