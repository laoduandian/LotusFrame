package lotus.net.center.screen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.LScreen;
import lotus.net.center.myclass.Tools;
import lotus.net.center.net.AddNew;
import lotus.net.center.net.AdsId;
import lotus.net.center.net.AppChannel;
import lotus.net.center.net.AppRestricted;
import lotus.net.center.net.LotusStudio;


public class LLogonScreen extends LScreen {
	private static LLogonScreen logonScreen;
	public static LLogonScreen getInstance(LGame game){
		if(logonScreen == null){
			logonScreen = new LLogonScreen();
			logonScreen.setGame(game);
		}
		return logonScreen;
	}
	private LotusStudio lotusStudio;
	@Override
	public void show() {
		super.show();
        init();
        game.app.initAD();
        game.app.removeRanners();
	}
	private void init() {
		time = 0;
		Image bgImage = new Image(game.getTexture("data/logobg.jpg"));
		
		bgImage.setSize(game.info.GAME_WIDTH, bgImage.getHeight()* game.info.GAME_WIDTH/bgImage.getWidth());
		bgImage.setPosition(game.info.GAME_WIDTH/2 - bgImage.getWidth()/2, game.info.GAME_HEIGHT/2 -bgImage.getHeight()/2);
		
		getStage().addActor(bgImage);
		//1:add;2:app;3:ads;
		lotusStudio = new LotusStudio();
		if(game.lotusStudioApp!=null){
			lotusStudio.setAdsId(game.lotusStudioApp.getAdsId());
			lotusStudio.setAppRestricted(game.lotusStudioApp.getAppRestricted());
			lotusStudio.setAddNew(game.lotusStudioApp.getAddNew());
		}
		getAddFromWap();//add
		getAppFromWap();//app
	}
	private void getAddFromWap(){
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		ApplicationType appType =  Gdx.app.getType();
		String url = "http://www.lotusstudio.top/ad/add/";
		if(appType == ApplicationType.Desktop)
			url = "https://www.lotusstudio.top/ad/add/";
		if(appType == ApplicationType.Android){
			if(game.info.isInland())
				url = String.format("%s%s",url,"inlandandroid.text");
			else
				url = String.format("%s%s",url,"android.text");
		}
		else
			url = String.format("%s%s",url,"ios.text");
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse (HttpResponse httpResponse) {
				String a = httpResponse.getResultAsString();
				AddNew addNew = game.json.fromJson(AddNew.class, a);
				if(lotusStudio.getAddNew() == null){
					lotusStudio.setAddNew(addNew);
					addNewAppFile();
				}else {
					if(addNew.getIndex() > lotusStudio.getAddNew().getIndex()){
						lotusStudio.setAddNew(addNew);
						addNewAppFile();
					}
				}
			}
			@Override
			public void failed (Throwable t) {
				Gdx.app.error("HttpRequestExample", "something went wrong", t);
			}
			@Override
			public void cancelled () {
				Gdx.app.log("HttpRequestExample", "cancelled");
			}
		});
	}
	private void getAppFromWap(){
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		ApplicationType appType =  Gdx.app.getType();
		String url = "http://www.lotusstudio.top/ad/app/";
		if(appType == ApplicationType.Desktop)
			url = "https://www.lotusstudio.top/ad/app/";
		url = String.format("%s%s%s",url,game.info.game_name,".text");
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse (HttpResponse httpResponse) {
				String a = httpResponse.getResultAsString();
				AppRestricted appRestricted = game.json.fromJson(AppRestricted.class, a);
				if(lotusStudio.getAppRestricted() == null){
					lotusStudio.setAppRestricted(appRestricted);
					addNewAppFile();
					getAdsFromWap();
				}else {
					if(appRestricted.getIndex() > lotusStudio.getAppRestricted().getIndex()){
						lotusStudio.setAppRestricted(appRestricted);
						addNewAppFile();
						getAdsFromWap();
					}else {
						if(lotusStudio.getAdsId() == null)
							getAdsFromWap();
					}
				}
			}
			@Override
			public void failed (Throwable t) {
				Gdx.app.error("HttpRequestExample", "something went wrong", t);
			}
			@Override
			public void cancelled () {
				Gdx.app.log("HttpRequestExample", "cancelled");
			}
		});
	}
	private void getAdsFromWap(){
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		ApplicationType appType =  Gdx.app.getType();
		String url = "http://www.lotusstudio.top/ad/ads/";
		if(appType == ApplicationType.Desktop)
			url = "https://www.lotusstudio.top/ad/ads/";
		if(game.info.getAppChannel() == AppChannel.ios)
			url = String.format("%s%s",url,lotusStudio.getAppRestricted().getAdsFileIos());
		else
	        url = String.format("%s%s",url,lotusStudio.getAppRestricted().getAdsFile());
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse (HttpResponse httpResponse) {
				String a = httpResponse.getResultAsString();
				AdsId adsId = game.json.fromJson(AdsId.class, Tools.decodeString(a));
				lotusStudio.setAdsId(adsId);
				addNewAppFile();
			}
			@Override
			public void failed (Throwable t) {
				Gdx.app.error("HttpRequestExample", "something went wrong", t);
			}
			@Override
			public void cancelled () {
				Gdx.app.log("HttpRequestExample", "cancelled");
			}
		});
	}

	private float time;
	@Override
	public void render(float delta) {
		super.render(delta);
		time+=delta;
		if(game.assetManager.update()&&!isTiaozhuan&&time>1){
			isTiaozhuan =true;
			game.setScreenshots(this.nextScreen);
		}
	}
	//把网上下载的信息存储到本地
	protected void addNewAppFile() {
		setInfo();
		game.per.putString("lotusStudio", game.json.toJson(lotusStudio));
		game.per.flush();
	}
	private void setInfo(){
		AppRestricted appRestricted = lotusStudio.getAppRestricted();
		if(appRestricted == null)
			return;
		if(appRestricted != null){
			game.info.is_Add_New = appRestricted.isAddNew();
			if(appRestricted.getShowTime()!= 0)
				game.info.interstitial_ad_condition_num = appRestricted.getShowTime();

		}
	}
	private void getWa(){
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl( "https://www.lotusstudio.top/wa.text");
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse (HttpResponse httpResponse) {
				String a = httpResponse.getResultAsString();
				System.out.println(a);
			}
			@Override
			public void failed (Throwable t) {
				Gdx.app.error("HttpRequestExample", "something went wrong", t);
			}
			@Override
			public void cancelled () {
				Gdx.app.log("HttpRequestExample", "cancelled");
			}
		});
	}
	@Override
	public void resume() {
		this.game.assetManager.load("data/logobg.jpg",Texture.class, game.textureParameter);
	}
	boolean isTiaozhuan = false;
	@Override
	public void dispose() {
	}
	@Override
	public void dobackJob() {
		
	}
	public LScreen nextScreen;
	public void setNextScreen(LScreen screen){
		this.nextScreen = screen;
	}
}
