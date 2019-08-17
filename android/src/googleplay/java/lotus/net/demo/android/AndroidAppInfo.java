package lotus.net.demo.android;

import lotus.net.center.android.VAndroidLauncher;
import lotus.net.center.myclass.LGame;
import lotus.net.center.net.AdsType;
import lotus.net.center.net.AdsValue;

public class AndroidAppInfo {
    public AndroidAppInfo(LGame game, VAndroidLauncher androidLauncher){
        game.info.game_Address = androidLauncher.getAppProcessName(androidLauncher);
        game.info.is_Add_New = false;
        //广告
        game.info.app_ad_id = "ca-app-pub-2887861689802805~6924520015";
        game.info.banner_ad_id = "ca-app-pub-2887861689802805/8046029991";
        game.info.interstitial_ad_id = "ca-app-pub-2887861689802805/6541376638";
        game.info.rewardedVideo_ad_id = "ca-app-pub-2887861689802805/2400797557";
        game.info.interstitial_ad_condition_num = 4;
        game.info.getOwnTypes().add(AdsType.admob);
        game.info.setAdsType(AdsType.admob);

    }
}
