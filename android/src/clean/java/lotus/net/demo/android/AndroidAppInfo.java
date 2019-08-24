package lotus.net.demo.android;

import lotus.net.center.myclass.LGame;
import lotus.net.center.android.VAndroidLauncher;
import lotus.net.center.net.AdsType;

public class AndroidAppInfo {
    public AndroidAppInfo(LGame game, VAndroidLauncher androidLauncher){
        game.info.game_Address = androidLauncher.getAppProcessName(androidLauncher);
        game.info.is_Add_New = false;
        //广告
        game.info.app_ad_id = "1101152570";
        game.info.banner_ad_id = "9079537218417626401";
        game.info.interstitial_ad_id = "8575134060152130849";
        game.info.rewardedVideo_ad_id = "2090845242931421";
        game.info.setInland(true);
        game.info.setInlandAddress("www.baidu.com");
        game.info.interstitial_ad_condition_num = 4;
        game.info.setAdsType(AdsType.gdt);
        game.info.getOwnTypes().add(AdsType.gdt,AdsType.baidu);
    }
}


