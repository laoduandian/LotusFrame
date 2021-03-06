package com.lotus.game.demo;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.badlogic.gdx.backends.iosrobovm.IOSDevice;

import org.robovm.apple.adsupport.ASIdentifierManager;
import org.robovm.apple.corefoundation.CFUUID;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import lotus.net.center.ios.VIOSLauncher;
import lotus.net.center.net.AdsType;
import lotus.net.center.net.AppChannel;
import lotus.net.demo.MyGame;


public class IOSLauncher extends VIOSLauncher {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        this.setGame(MyGame.getInstance());
        this.getGame().info.game_Address = "1175680497";
        this.getGame().info.is_Add_New = true;
        //广告
        this.getGame().info.app_ad_id ="ca-app-pub-2887861689802805~1172421223";
        this.getGame().info.banner_ad_id = "ca-app-pub-2887861689802805/4931702831";
        this.getGame().info.interstitial_ad_id = "ca-app-pub-2887861689802805/7845640785";
        this.getGame().info.rewardedVideo_ad_id = "ca-app-pub-2887861689802805/4418721033";
        this.getGame().info.interstitial_ad_condition_num = 4;
        this.getGame().info.setAppChannel(AppChannel.ios);
        this.getGame().info.getOwnTypes().add(AdsType.admob);
        this.getGame().info.setAdsType(AdsType.admob);
//        this.getGame().info.seIOS_Test_Ads();
        return new IOSApplication(this.getGame(), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

}