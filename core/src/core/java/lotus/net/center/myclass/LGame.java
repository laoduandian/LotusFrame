package lotus.net.center.myclass;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.lang.reflect.Field;
import lotus.net.center.freefont.FreeFont;
import lotus.net.center.net.LotusStudio;
import lotus.net.center.screen.LoadingScreen;
import lotus.net.center.uieditor.EditorInto;


public class LGame extends Game {
	public Screen nextScreen;
	public InputMultiplexer multiplexer;
	public AssetManager assetManager;
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
	private TextureAtlas atlas;
	public TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
	public void setApp(App app) {
		this.app = app;
	}

	@Override
	public void create() {
        textureParameter.minFilter = Texture.TextureFilter.Linear;
        textureParameter.magFilter = Texture.TextureFilter.Linear;
		json = new Json();
		per = Gdx.app.getPreferences(info.game_name);
		lotusStudioApp = json.fromJson(LotusStudio.class,per.getString("lotusStudioApp"));
		into = new EditorInto(this);
		Gdx.app.setLogLevel(Logger.DEBUG);
		creatTexture();
		isScreenshots = false;
		font = new FreeFont(this);
		assetManager = new AssetManager();
		soundManager = new SoundManager(this);
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
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
	/**
	 * 加载一类资源
	 */
	public <T> void load(Class<T> type,String... paths){
		for (String path: paths) {
			assetManager.load(path,type);
		}
	}
	/**
	 * 加载一类资源
	 */
	public <T> void load(Class<T> type,AssetLoaderParameters<T> parameter,String... paths){
		for (String path: paths) {
			assetManager.load(path,type,parameter);
		}
	}


	/**
	 * 加载文件夹下的资源
	 * @param L_Classs
	 */
	public void loadFolder(Class<?>... L_Classs) {
		for (Class<?> lClass : L_Classs) {
			Class innerClazz[] = lClass.getDeclaredClasses();
			if (innerClazz.length > 0)loadFolder(innerClazz);
			Field[] fields = lClass.getDeclaredFields();
			for (Field field : fields) {
				loadField(lClass,field);
			}
		}
	}
	private void loadField(Class<?> lClass,Field field){
		String class_name = lClass.getSimpleName();
		try {
			String path= (String) field.get(lClass);
			switch (class_name){
				case "image":
					this.load(Texture.class,textureParameter,path);
					break;
				case "Music":
					this.load(Music.class,path);
					break;
				case "Sound":
					this.load(Sound.class,path);
					break;
				case "particle":
					ParticleEffectLoader.ParticleEffectParameter parameter = new ParticleEffectLoader.ParticleEffectParameter();
//					parameter.atlasFile = L.data.pack.menu;
//					FileHandle fileHandle = new FileHandle(path);
//					fileHandle.parent();
//					parameter.imagesDir = new FileHandle(path).parent();
//					this.load(ParticleEffect.class,parameter,path);
					break;
				case "pack":
					this.load(TextureAtlas.class,path);
					break;
				case "font":
					BitmapFontLoader.BitmapFontParameter bitmapFontParameter = new BitmapFontLoader.BitmapFontParameter();
					bitmapFontParameter.magFilter = Texture.TextureFilter.Linear;
					bitmapFontParameter.minFilter = Texture.TextureFilter.Linear;
					this.load(BitmapFont.class,bitmapFontParameter,path);
					break;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public Texture getTexture(String texturePath){
	    if(!assetManager.isLoaded(texturePath)){
            load(Texture.class,textureParameter,texturePath);
            Gdx.app.error(this.getClass().getName(),"没有加载："+texturePath);
            assetManager.finishLoading();
        }
        return assetManager.get(texturePath);
    }
    public TextureRegion getTextureRegion(String name){
	    return getAtlas().findRegion(name);

    }
    public TextureAtlas getAtlas() {
	    if(atlas == null)
	        atlas = new TextureAtlas();
        return atlas;
    }
    public TextureAtlas addAtlas(String atlasPath){
		if(!assetManager.isLoaded(atlasPath)){
			load(TextureAtlas.class,atlasPath);
			Gdx.app.error(this.getClass().getName(),"没有加载："+atlasPath);
			assetManager.finishLoading();
		}
		TextureAtlas addAtlas = this.assetManager.get(atlasPath);
		for (TextureAtlas.AtlasRegion region :addAtlas.getRegions()){
			if(getAtlas().findRegion(region.name)!=null)
				Gdx.app.error(this.getClass().getName(),"重复资源名称："+region.name);
			getAtlas().addRegion(region.name,region);
		}
		return atlas;
	}
    public ParticleEffect getParticleEffect(String effectPath, String imagesDir){
        if(!assetManager.isLoaded(imagesDir)){
            load(Texture.class,textureParameter,imagesDir);
            Gdx.app.error(this.getClass().getName(),"没有加载："+imagesDir);
            assetManager.finishLoading();
        }
        String imageName = new File(imagesDir.replace('\\', '/')).getName();
        int lastDotIndex = imageName.lastIndexOf('.');
        if (lastDotIndex != -1) imageName = imageName.substring(0, lastDotIndex);
        if(getAtlas().findRegion(imageName)==null){
            TextureRegion region = new TextureRegion(assetManager.get(imagesDir,Texture.class));
            getAtlas().addRegion(imageName,region);
        }
        ParticleEffect effect = new ParticleEffect();
        effect.load(new FileHandle(effectPath),getAtlas());
        return  effect;
    }
    public ParticleEffect getParticleEffect(String effectPath){
        ParticleEffect effect = new ParticleEffect();
        effect.loadEmitters(new FileHandle(effectPath));
        Array<ParticleEmitter> emitters = effect.getEmitters();
        for (int i = 0, n = emitters.size; i < n; i++) {
            ParticleEmitter emitter = emitters.get(i);
            for (String imagePath : emitter.getImagePaths()) {
                String imageName = new File(imagePath.replace('\\', '/')).getName();
                int lastDotIndex = imageName.lastIndexOf('.');
                if (lastDotIndex != -1) imageName = imageName.substring(0, lastDotIndex);
                TextureRegion region = getAtlas().findRegion(imageName);
                if (region == null) throw new IllegalArgumentException("粒子效果缺少图片: " + imageName);
            }
        }
        effect.loadEmitterImages(getAtlas());
        return  effect;
    }
}