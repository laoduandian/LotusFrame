package lotus.net.demo.android;

import lotus.net.center.myclass.LGame;
import lotus.net.center.android.VAndroidLauncher;
import lotus.net.center.net.AdsType;

public class AndroidAppInfo {
    public AndroidAppInfo(LGame game, VAndroidLauncher androidLauncher){
        game.info.game_Address = androidLauncher.getAppProcessName(androidLauncher);
        game.info.is_Add_New = true;
        //广告
        game.info.app_ad_id = "1101152570";
        game.info.banner_ad_id = "4080052898050840";
        game.info.interstitial_ad_id = "3040652898151811";
        game.info.rewardedVideo_ad_id = "2090845242931421";
        game.info.setInland(true);
        game.info.setInlandAddress("www.baidu.com");
        game.info.interstitial_ad_condition_num = 4;
        game.info.getOwnTypes().add(AdsType.gdt,AdsType.baidu,AdsType.csj);
        game.info.setAdsType(AdsType.gdt);
    }
}


