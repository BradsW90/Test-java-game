package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sounds/16bs-door-2.wav");
		soundURL[1] = getClass().getResource("/sounds/16bs-collect-1.wav");
		soundURL[2] = getClass().getResource("/sounds/Bustling_City.wav");
		soundURL[3] = getClass().getResource("/sounds/16bs-powerup-1.wav");
		soundURL[4] = getClass().getResource("/sounds/16bs-door-1.wav");
		soundURL[5] = getClass().getResource("/sounds/Finish_Melody.wav");
		soundURL[6] = getClass().getResource("/sounds/16b-step-1.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
