package lotus.net.demo.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
//import android.support.multidex.MultiDex;
import android.util.Log;
import lotus.net.center.android.VAndroidLauncher;
import lotus.net.demo.MyGame;

public class AndroidLauncher extends VAndroidLauncher {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGame game = MyGame.getInstance();
//        MultiDex.install(this);
        new AndroidAppInfo(game,this);

//        game.info.setAndroid_Test_Ads();
        init(game);
        showSomething("主：：：："+getFileProviderName(this)+"————————————"+getChannel());

    }



}
