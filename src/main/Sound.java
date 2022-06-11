package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
	private Clip clip;
	private URL soundURL[] = new URL[30];
	
	
	public Sound()
	{
		soundURL[0] = getClass().getResource("/sound/test.wav");
		soundURL[1] = getClass().getResource("/sound/damageSound.wav");
		soundURL[2] = getClass().getResource("/sound/pickup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/pickupNail.wav");
		soundURL[5] = getClass().getResource("/sound/nailHit.wav");
		soundURL[6] = getClass().getResource("/sound/ZaWarudo.wav");
		soundURL[7] = getClass().getResource("/sound/equipNail.wav");
		soundURL[8] = getClass().getResource("/sound/unequipNail.wav");
	}
	
	public void setFile(int i)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch(Exception e)
		{
			
		}
	}
	public void play()
	{
		clip.start();
	}
	@SuppressWarnings("static-access")
	public void loop()
	{
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void stop()
	{
		clip.stop();
	}
}
