package lotus.net.center.screen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import lotus.net.center.myclass.LScreen;
import lotus.net.center.myclass.Tools;
import lotus.net.center.net.AppRestricted;
import lotus.net.center.net.LotusStudio;
import lotus.net.demo.MyGame;


public class LogonScreen extends LScreen {
	public LogonScreen(MyGame game) {
		super(game);
	}
	@Override
	public void show() {
		super.show();
        init();
	}
	private void init() {
		time = 0;
		Texture texture = game.assetManager.get("data/logobg.jpg");
		Image bgImage = new Image(Tools.setTextureFilter(texture));
		
		bgImage.setSize(game.info.GAME_WIDTH, bgImage.getHeight()* game.info.GAME_WIDTH/bgImage.getWidth());
		bgImage.setPosition(game.info.GAME_WIDTH/2 - bgImage.getWidth()/2, game.info.GAME_HEIGHT/2 -bgImage.getHeight()/2);
		
		getStage().addActor(bgImage);

		HttpRequest request = new HttpRequest(HttpMethods.GET);
		ApplicationType appType =  Gdx.app.getType();
		String url = "http://www.lotusstudio.top/";
		if(appType == ApplicationType.Desktop)
			url = "https://www.lotusstudio.top/";
		if(appType == ApplicationType.Android)
			url = String.format("%s%s",url,"android.text");
		else
			url = String.format("%s%s",url,"ios.text");
		request.setUrl(url);
		setInfo();
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse (HttpResponse httpResponse) {
				LotusStudio lotusStudio = game.json.fromJson(LotusStudio.class, httpResponse.getResultAsString());
				if(game.lotusStudioApp == null){
					addNewAppFile(lotusStudio);
				}else {
					if(lotusStudio.getIndex() > game.lotusStudioApp.getIndex())
						addNewAppFile(lotusStudio);
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
	protected void addNewAppFile(LotusStudio lotusStudio) {
		game.lotusStudioApp = lotusStudio;
		setInfo();
		game.per.putString("lotusStudioApp", game.json.toJson(lotusStudio));
		game.per.flush();
	}
	private void setInfo(){
		if(game.lotusStudioApp == null)
			return;
		Array<AppRestricted> appRestricteds = game.lotusStudioApp.getAppRestricteds();
		if(appRestricteds == null)
			return;
		AppRestricted appRestricted = null;
		for (AppRestricted app : appRestricteds){
			if(app.getAddress().equals(game.info.game_Address)){
				appRestricted = app;
				break;
			}
		}
		if(appRestricted != null){
			game.info.is_Add_New = appRestricted.isAddNew();

			//广告
			if(appRestricted.getApp_ad_id()!=null)
				game.info.app_ad_id = appRestricted.getApp_ad_id();
			if(appRestricted.getBanner_ad_id()!=null)
				game.info.banner_ad_id = appRestricted.getBanner_ad_id();
			if(appRestricted.getInterstitial_ad_id()!=null)
				game.info.interstitial_ad_id = appRestricted.getInterstitial_ad_id();
			if(appRestricted.getRewardedVideo_ad_id()!=null)
				game.info.rewardedVideo_ad_id = appRestricted.getRewardedVideo_ad_id();
			if(appRestricted.getShowTime()!= 0)
				game.info.interstitial_ad_condition_num = appRestricted.getShowTime();

		}
	}
	@Override
	public void resume() {
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
