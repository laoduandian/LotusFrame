package lotus.net.demo.android;

import android.os.Bundle;
import com.badlogic.gdx.Gdx;
import lotus.net.center.android.VAndroidLauncher;
import lotus.net.demo.MyGame;

public class AndroidLauncher extends VAndroidLauncher {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGame game = MyGame.getInstance();
        game.info.game_Address = this.getAppProcessName(this);
        game.info.is_Add_New = true;
        //广告
        game.info.app_ad_id = "ca-app-pub-2887861689802805~5485762576";
        game.info.banner_ad_id = "ca-app-pub-2887861689802805/6437458573";
        game.info.interstitial_ad_id = "ca-app-pub-2887861689802805/9390924976";
        game.info.rewardedVideo_ad_id = "ca-app-pub-2887861689802805/3587152459";
        game.info.interstitial_ad_condition_num = 4;
//        game.info.setAndroid_Test_Ads();
        init(game);
    }

    @Override
    public void addBanners(boolean isHead) {
        super.addBanners(isHead);
        Gdx.app.log(getClass().getName(),String.format("%s_%s_%s","显示广告条",this.game.info.app_ad_id,this.game.info.banner_ad_id));
    }
}
