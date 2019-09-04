package lotus.net.center.myclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
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
	protected static LGame game;
	private Stage stage;

	protected static void setGame(LGame game) {
		LScreen.game = game;
	}
	@Override
	public void show() {
		stage = new Stage();
		stage.setViewport(new StretchViewport(game.info.GAME_WIDTH, game.info.GAME_HEIGHT));
		game.multiplexer.clear();
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
	public abstract void dobackJob();

	private HttpRequest httpRequest;
	private Texture texture;
	private Group adsGroup;
	private AppItem appItem;

	public void add(){
		LotusStudio lotusStudioApp = game.lotusStudioApp;
		if(lotusStudioApp == null||lotusStudioApp.getAddNew() == null)
			return;
		game.info.is_Add_New = false;
		loadAdsGroup(getDifferentApp(lotusStudioApp));
	}
	private AppItem getDifferentApp(LotusStudio lotusStudioApp){
		AppItem appItem = randomAppItem(lotusStudioApp);
		while (appItem.getAppAddress().equals(game.info.game_Address)){
			appItem = randomAppItem(lotusStudioApp);
		}
		return appItem;
	}
	private AppItem randomAppItem(LotusStudio lotusStudioApp) {
		float random = MathUtils.random(1f);
		int index = 0;
		for (int i = 0; i < lotusStudioApp.getAddNew().getApps().size; i++) {
			if(getId(random, lotusStudioApp, i))
				index = i;
			else
				break;
		}
		return lotusStudioApp.getAddNew().getApps().get(index);
	}
	private boolean getId(float random,LotusStudio lotusStudioApp,int index) {
		float sum = 0;
		for (int i = 0; i < index; i++) {
			sum += lotusStudioApp.getAddNew().getApps().get(i).getAppProportion();
		}
		return random > sum;
	}
	String addName="";
	private void loadAdsGroup(AppItem appItem){
		this.appItem = appItem;
		adsGroup = new Group();
		if(game.info.GAME_WIDTH>game.info.GAME_HEIGHT)
			addName = "heng/";
		final FileHandle png = Gdx.files.local("data/"+addName+appItem.getAppAdImageName());
		if(png.exists()){
			texture = new Texture(png);
			addAdsGroup(texture);
		}else{
//			game.app.showSomething("下载图片");
			String httpMethod = Net.HttpMethods.GET;
			httpRequest = new HttpRequest(httpMethod);
			httpRequest.setUrl("http://www.lotusstudio.top/games/"+addName+appItem.getAppAdImageName());
//			httpRequest.setUrl("http://b270.photo.store.qq.com/psb?/82a3c959-5cd6-427e-8453-7b23acdd781f/2Ekgg5*N0RsRj1qc.SLT6r7BPXvUzGSyOYlVsAGHJRY!/b/dNdt.aC8FgAA&bo=WAIgA9ACwAMBGFc!&rf=viewer_4");
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
					png.writeBytes(rawImageBytes,false);
				}

				@Override
				public void failed(Throwable t) {
//					game.app.showSomething("下载失败"+t.getMessage());
				}

				@Override
				public void cancelled() {
//					game.app.showSomething("下载取消");
				}
			});
		}
	}
	private void addAdsGroup(Texture texture){
		Image adsImage = new Image(Tools.setTextureFilter(texture));
		Image image = new Image(texture);
		Image chaImage = new Image(new Texture(Gdx.files.internal("data/cha.png")));
		final Image gImage = new Image(new Texture(Gdx.files.internal("data/g.png")));

		image.setSize(game.info.GAME_WIDTH, game.info.GAME_HEIGHT);
		image.setColor(0,0,0,0.7f);
		float scale = Math.max(game.info.GAME_WIDTH,game.info.GAME_HEIGHT)/800f;//480，800
		if(game.info.GAME_WIDTH < game.info.GAME_HEIGHT)
			adsImage.setSize(400*scale, 600*scale);
		else
			adsImage.setSize(600*scale, 400*scale);
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
//				if(game.info.isInland()&&appItem.getInlandAddress()!=null)
//					Gdx.net.openURI("https://"+appItem.getInlandAddress());
//				else
				game.app.newgame(appItem);
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
				if(game.info.isInland()&&appItem.getInlandAddress()!=null)
					Gdx.net.openURI("https://"+appItem.getInlandAddress());
				else
					game.app.newgame(appItem);
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
