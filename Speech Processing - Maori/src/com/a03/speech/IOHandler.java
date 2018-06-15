package com.a03.speech;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.ProcessBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.SwingWorker;



/**
 * This class handles IO with the host operating system. Records audio,
 * plays audio.
 * @author Matthew
 *
 */
public class IOHandler {
	private TargetDataLine line;
	private String workspace = "HTK/MaoriNumbers/";
	private ProcessBuilder pb;
	private String fs = File.separator;
	private File audioFold = new File("Audio");
	private File audio = new File("Audio" + fs +"foo.wav");
	private static IOHandler instance;

	private IOHandler(){
		makeDirectory();
	};


	// Singleton method
	public static IOHandler getInstance(){
		if(instance == null){
			instance = new IOHandler();

		}
		return instance;
	}

	private void makeDirectory(){

		File f = new File("Audio");
		if(f.exists()) {
			File audio = new File(f, "foo.wav");
			audio.delete();
		}else {
			f.mkdir();		

		}

	}

	private AudioFormat getAudioFormat() {
		float sampleRate = 22050;
		int sampleSizeInBits = 16;
		int channels = 2;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
				channels, signed, bigEndian);
		return format;
	}

	// Runs the script file, minus the printing of the recout.mlf
	public void recordVoice(){

		File f = new File("Audio"  + fs +"foo.wav");
		Task<Void> t = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Thread.sleep(2000);
				return null;			
			}
		};
		Thread thr = new Thread(t);
		t.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			public void handle(WorkerStateEvent arg0) {
				line.stop();
		        line.close();
				
			}
			
		});

		try {
			
			AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format is an AudioFormat object
			if (!AudioSystem.isLineSupported(info)) {
				// Handle the error ... 

			}
			// Obtain and open the line.
			try {
				line = (TargetDataLine) AudioSystem.getLine(info);
				line.open(format);
				line.start(); 
				thr.start();// start capturing

				System.out.println("Start capturing...");

				AudioInputStream ais = new AudioInputStream(line);

				System.out.println("Start recording...");

				// start recording
				AudioSystem.write(ais, fileType, f);
			} catch (LineUnavailableException ex) {

				// Handle the error ... 
			}
		}catch (Exception e) {

		}

	}
	MediaPlayer mediaPlayer = null;
	public void play(){
		Media hit = new Media(audio.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
}
