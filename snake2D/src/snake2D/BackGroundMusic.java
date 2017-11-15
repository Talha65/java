package snake2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import sun.audio.*;

public class BackGroundMusic {
	


public static AudioStream sound;

//public static ContinuousAudioDataStream loop=null;	

	public static void ballMusic(){
		
		try{
			sound=new AudioStream(new FileInputStream("cartoon012.wav"));
			AudioPlayer.player.start(sound);
			
		}
		catch(IOException e){
			
		}
		
		
	}
	public static void snakeMusic(){
		try{
			sound=new AudioStream(new FileInputStream("Kid Giggle 2-SoundBible.com-847618472.wav"));
			AudioPlayer.player.start(sound);
			
		}
		catch(IOException e){
			
		}
		
	}
	public static void startingMusic(){
		try{
			sound=new AudioStream(new FileInputStream("POL-macaron-island-short.wav"));
			AudioPlayer.player.start(sound);
			
		}
		catch(IOException e){
			
		}
	}

}
