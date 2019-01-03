package lotus.net.center.myclass;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	private LGame game;
	public Music music;
	public Map<Long,Sound> soundMap = new HashMap<Long, Sound>();
	public SoundManager(LGame game){
		this.game = game;
	}
	public void getAndPlayMusic(String musicName,float volume){
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
		music.setVolume(volume);
		setMusicOnOrOff(game.info.MUSIC_ON_OFF);
	}
	public void getAndPlaySound(String soundName){
		if(!game.info.SOUND_ON_OFF)
			return;
		getSound(soundName).play();
	}
	/**
	 * 全拼
	 * @param soundName
	 */
	public void getAndPlaySound_All(String soundName){
		if(!game.info.SOUND_ON_OFF)
			return;
		game.assetManager.get(soundName, Sound.class).play();
	}
	public void getAndPlaySound(String soundName,float volume){
		if(!game.info.SOUND_ON_OFF)
			return;
		Sound sound = getSound(soundName);
		sound.play(volume);
	}
	public long getAndPlaySound(String soundName,boolean isLoop){
		if(!game.info.SOUND_ON_OFF)
			return 0;
		Sound sound = getSound(soundName);
		long a = sound.loop();
		soundMap.put(a, sound);
		return a;
	}
	private Sound getSound(String soundName){
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
}
