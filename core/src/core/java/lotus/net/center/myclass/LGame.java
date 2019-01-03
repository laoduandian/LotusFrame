package lotus.net.center.myclass;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import lotus.net.center.freefont.FreeFont;
import lotus.net.center.net.LotusStudio;
import lotus.net.center.screen.LoadingScreen;
import lotus.net.center.uieditor.EditorInto;


public class LGame extends Game {
	public Screen nextScreen;
	public InputMultiplexer multiplexer;
	public LAssetManager assetManager;
	public SoundManager soundManager;
	private boolean isScreenshots;// 截图
	public App app;
	public FreeFont font;
	public Texture texture;
	public EditorInto into;
	public GameInfo info;
	public Preferences per;
	public Json json;
	public LoadingScreen loadingScreen;
	public LotusStudio lotusStudioApp;
	public HashMap<String,LScreen> screenPool = new HashMap<>();

	public void setApp(App app) {
		this.app = app;
	}

	@Override
	public void create() {
		json = new Json();
		setGameInfo();
		per = Gdx.app.getPreferences(info.game_name);
		lotusStudioApp = json.fromJson(LotusStudio.class,per.getString("lotusStudioApp"));
		into = new EditorInto(this);
		Gdx.app.setLogLevel(Logger.DEBUG);
		creatTexture();
		isScreenshots = false;
		font = new FreeFont(this);
		soundManager = new SoundManager(this);
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	private void setGameInfo(){
		if(Gdx.app.getType()== Application.ApplicationType.Android){
			info.app_ad_id = "ca-app-pub-2887861689802805~5485762576";
			info.banner_ad_id = "ca-app-pub-9276668028949645/9537230211";
			info.interstitial_ad_id = "ca-app-pub-2887861689802805/6437458573";
			info.rewardedVideo_ad_id = "ca-app-pub-2887861689802805/3587152459";
		}
	}

	@Override
	public void render() {
		super.render();
	}
	public void over_Skip(){
        setScreen(nextScreen);
        loadingScreen.dispose();
    }

	public void doSkip(TextureRegion fullTextrueRegion) {
		getScreen().dispose();
		nextScreen.resume();
		loadingScreen.setFullTextrueRegion(fullTextrueRegion);
		setScreen(loadingScreen);
		multiplexer.clear();
		isScreenshots = false;
	}

	private void doBackjob() {
		if (getScreen() instanceof LScreen) {
			LScreen screen = (LScreen) getScreen();
			screen.dobackJob();
		}
	}
	public void showMovie_return(int id) {

	}
	public boolean isScreenshots() {
		return isScreenshots;
	}

	public void setScreenshots(Screen nextScreen) {
		this.nextScreen = nextScreen;
		this.isScreenshots = true;
	}

	private void creatTexture() {
		Pixmap pixmap = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE); // White
		pixmap.drawRectangle(0, 0, 20, 20);
		pixmap.fill();
		texture  = new Texture(20, 20, Pixmap.Format.RGBA8888);
		texture.draw(pixmap, 0, 0);
		pixmap.dispose();
	}

	public Pixmap getPixmap(String name) {
		return new Pixmap(Gdx.files.internal(name));
	}


	private Texture fullTextrue;
	/**
	 * 截图
	 */
	public TextureRegion getFullTextrueRegion() {
		Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(fullTextrue !=null){
			fullTextrue.dispose();
			fullTextrue = null;
		}
		fullTextrue = new Texture(pixmap);
		TextureRegion region = new TextureRegion(fullTextrue);
		region.flip(false, true);
		pixmap.dispose();
		return region;
	}

}