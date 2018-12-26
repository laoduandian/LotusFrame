package lotus.net.center.myclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import lotus.net.center.myclass.helper.BasicButton;
import lotus.net.center.net.AppItem;
import lotus.net.center.net.LotusStudio;

public abstract class LScreen implements Screen{
	public LGame game;
	private Stage stage;
	private TextureAtlas modeAtlas;
	private TextureAtlas publicAtlas;
	public LScreen(LGame game) {
		this.game = game;
	}
	@Override
	public void show() {
		stage = new Stage();
		stage.setViewport(new StretchViewport(game.info.GAME_WIDTH, game.info.GAME_HEIGHT));
        game.multiplexer.addProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(game.multiplexer);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act(delta);
		if(game.isScreenshots()){
			game.doSkip(game.getFullTextrueRegion());
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void pause() {
		
	}
	@Override
	public abstract void resume();

	@Override
	public void hide() {
	}

	@Override
	public abstract void dispose();
	public Stage getStage() {
		return stage;
	}
	public TextureRegion getModeTextureRegion(String regionName){
		return modeAtlas.findRegion(regionName);
	}
	public void setModeAtlas(TextureAtlas modeAtlas) {
		this.modeAtlas = modeAtlas;
	}
	public void setModeAtlas(String string) {
		this.modeAtlas = game.assetManager.get(string);
		for (Texture texture : modeAtlas.getTextures()) {
			Tools.setTextureFilter(texture);
		}
	}
	public TextureAtlas getModeAtlas() {
		return modeAtlas;
	}
	
	public TextureRegion getPublicTextureRegion(String regionName){
		return publicAtlas.findRegion(regionName);
	}
	public void setPublicAtlas(String string) {
		this.publicAtlas = game.assetManager.get(string);
		for (Texture texture : publicAtlas.getTextures()) {
			Tools.setTextureFilter(texture);
		}
	}
	public TextureAtlas getPublicAtlas() {
		return publicAtlas;
	}
	public abstract void dobackJob();

	private HttpRequest httpRequest;
	private Texture texture;
	private Group adsGroup;
	private AppItem appItem;

	public void add(){
		LotusStudio lotusStudioApp = game.lotusStudioApp;
		if(lotusStudioApp == null)
			return;
		game.info.is_Add_New = false;
		loadAdsGroup(randomAppItem(lotusStudioApp));
	}
	private AppItem randomAppItem(LotusStudio lotusStudioApp) {
		float random = MathUtils.random(1f);
		int index = 0;
		for (int i = 0; i < lotusStudioApp.getApps().size; i++) {
			if(getId(random, lotusStudioApp, i))
				index = i;
			else
				break;
		}
		return lotusStudioApp.getApps().get(index);
	}
	private boolean getId(float random,LotusStudio lotusStudioApp,int index) {
		float sum = 0;
		for (int i = 0; i < index; i++) {
			sum += lotusStudioApp.getApps().get(i).getAppProportion();
		}
		return random > sum;
	}
	private void loadAdsGroup(AppItem appItem){
		this.appItem = appItem;
		adsGroup = new Group();
		FileHandle png = Gdx.files.absolute(Gdx.files.getExternalStoragePath()+"data/"+appItem.getAppAdImageName());
		if(png.exists()){
			texture = new Texture(png);
			addAdsGroup(texture);
		}else{
			String httpMethod = Net.HttpMethods.GET;
			httpRequest = new HttpRequest(httpMethod);
			httpRequest.setUrl("http://www.lotusstudio.top/games/"+appItem.getAppAdImageName());
			Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
				@Override
				public void handleHttpResponse(Net.HttpResponse httpResponse) {
					final byte[] rawImageBytes = httpResponse.getResult();
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
							texture = new Texture(pixmap);
							addAdsGroup(texture);
						}
					});
					try {
						File sf=new File(Gdx.files.getExternalStoragePath()+"data");
						if(!sf.exists()){
							sf.mkdirs();
						}
						OutputStream os = new FileOutputStream(Gdx.files.getExternalStoragePath()+"data/"+LScreen.this.appItem.getAppAdImageName());
						os.write(rawImageBytes, 0, rawImageBytes.length);
						os.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void failed(Throwable t) {

				}

				@Override
				public void cancelled() {

				}
			});
		}
	}
	private void addAdsGroup(Texture texture){
		Image adsImage = new Image(Tools.setTextureFilter(texture));
		Image image = new Image(new Texture(Gdx.files.internal("data/0.jpg")));
		Image chaImage = new Image(new Texture(Gdx.files.internal("data/cha.png")));
		Image gImage = new Image(new Texture(Gdx.files.internal("data/g.png")));

		image.setSize(game.info.GAME_WIDTH, game.info.GAME_HEIGHT);
		image.setColor(0,0,0,0.7f);
		float math = Math.max(480,800);
		float scale = game.info.GAME_HEIGHT/math;//480，800
		adsImage.setSize(427*scale, 600*scale);
		adsImage.setPosition(game.info.GAME_WIDTH/2 - adsImage.getWidth()/2, game.info.GAME_HEIGHT/2- adsImage.getHeight()/2);
		chaImage.setPosition(game.info.GAME_WIDTH/2 - adsImage.getWidth()/2, game.info.GAME_HEIGHT/2- adsImage.getHeight()/2);
		gImage.setPosition(game.info.GAME_WIDTH/2 + adsImage.getWidth()/2 - gImage.getWidth(), game.info.GAME_HEIGHT/2- adsImage.getHeight()/2);

		adsGroup.addActor(image);
		adsGroup.addActor(adsImage);
		adsGroup.addActor(chaImage);
		adsGroup.addActor(gImage);
		getStage().addActor(adsGroup);
		gImage.addListener(new BasicButton(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
								int pointer, int button) {
				if(pointer!=0||!isTouch())
					return;
				game.app.newgame(appItem.getAppAddress());
				adsGroup.remove();
				super.touchUp(event, x, y, pointer, button);
			}
		});
		adsImage.addListener(new BasicButton(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
								int pointer, int button) {
				if(pointer!=0||!isTouch())
					return;
				game.app.newgame(appItem.getAppAddress());
				adsGroup.remove();
				super.touchUp(event, x, y, pointer, button);
			}
		});
		chaImage.addListener(new BasicButton(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
								int pointer, int button) {
				if(pointer!=0||!isTouch())
					return;
				adsGroup.remove();
				super.touchUp(event, x, y, pointer, button);
			}
		});

	}

}
