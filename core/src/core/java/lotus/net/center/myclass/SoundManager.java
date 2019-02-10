package lotus.net.center.myclass;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	private LGame game;
	public Music music;
	public SoundManager(LGame game){
		this.game = game;
		setSoundVolume(1);
	}
	private float soundVolume;
	public void getAndPlayMusic(String musicName,float soundVolume){
		if(music ==null){
			music = game.assetManager.get( musicName  , Music.class);
			music.play();
			music.setLooping(true);
		}else{
			if(music!=game.assetManager.get( musicName  , Music.class)){
				try {
					music.stop();
				} catch (Exception e) {
				}
				music = game.assetManager.get( musicName , Music.class);
				music.play();
				music.setLooping(true);
			}
		}
		music.setVolume(soundVolume);
		setMusicOnOrOff(game.info.MUSIC_ON_OFF);
	}
	public long getAndPlaySound(String soundName){
		return getAndPlaySound(soundName,soundVolume,false);
	}
	public long getAndPlaySound(String soundName,float soundVolume){
		return getAndPlaySound(soundName,soundVolume,false);
	}
	public long getAndPlaySound(String soundName,boolean isLoop){
		return getAndPlaySound(soundName,soundVolume,isLoop);
	}
	public long getAndPlaySound(String soundName,float soundVolume,boolean isLoop){
		if(!game.info.SOUND_ON_OFF)
			return 0;
		Sound sound = getSound(soundName);
		if(isLoop)
			return sound.loop(soundVolume);
		else
			return sound.play();
	}
	public Sound getSound(String soundName){
		Sound sound = game.assetManager.get( soundName, Sound.class);
		return sound;
	}
	public void setMusicOnOrOff(boolean on){
		game.info.MUSIC_ON_OFF = on;
		if(music == null)
			return;
		if(game.info.MUSIC_ON_OFF){
			if(!music.isPlaying())
				music.play();
		}else{
			music.stop();
		}
	}
	public void setSoundOnOrOff(boolean on){
		game.info.SOUND_ON_OFF = on;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
}
