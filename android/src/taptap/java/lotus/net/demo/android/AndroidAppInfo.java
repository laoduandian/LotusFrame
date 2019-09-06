package lotus.net.demo.android;

import lotus.net.center.myclass.LGame;
import lotus.net.center.android.VAndroidLauncher;
import lotus.net.center.net.AdsType;

public class AndroidAppInfo {
    public AndroidAppInfo(LGame game, VAndroidLauncher androidLauncher){
        game.info.game_Address = androidLauncher.getAppProcessName(androidLauncher);
        game.info.is_Add_New = true;
        //广告
        game.info.app_ad_id = "1109770423";
        game.info.banner_ad_id = "8070080373269573";
        game.info.interstitial_ad_id = "2050881303361548";
        game.info.rewardedVideo_ad_id = "7060089343663673";
        game.info.setInland(true);
        game.info.setInlandAddress("www.baidu.com");
        game.info.interstitial_ad_condition_num = 4;
        game.info.getOwnTypes().add(AdsType.gdt,AdsType.baidu,AdsType.csj);
        game.info.setAdsType(AdsType.gdt);
    }
}


