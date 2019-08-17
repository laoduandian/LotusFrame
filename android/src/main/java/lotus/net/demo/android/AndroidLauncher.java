package lotus.net.demo.android;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import lotus.net.center.android.VAndroidLauncher;
import lotus.net.demo.MyGame;

public class AndroidLauncher extends VAndroidLauncher {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyGame game = MyGame.getInstance();

        new AndroidAppInfo(game,this);

//        game.info.setAndroid_Test_Ads();
        init(game);
        showSomething("主：：：："+getAppProcessName(this)+"————————————"+getChannel());

    }


    private String getChannel(){
        String channel = null;
        try {
            ApplicationInfo appInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);

            channel = appInfo.metaData.getString("UMENG_CHANNEL");

            Log.i("TAG","UMENG_CHANNEL_VALUE=" + channel);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return channel;
    }

}
