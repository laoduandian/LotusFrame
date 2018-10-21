package lotus.net.demo.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import lotus.net.center.android.VAndroidLauncher;
import lotus.net.demo.MyGame;

public class AndroidLauncher extends VAndroidLauncher{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGame game = new MyGame(this);
        game.info.game_Address = this.getAppProcessName(this);
        game.info.is_Add_New = true;

        //广告
        game.info.app_ad_id = "ca-app-pub-2887861689802805~5485762576";
        game.info.banner_ad_id = "ca-app-pub-9276668028949645/9537230211";
        game.info.interstitial_ad_id = "ca-app-pub-2887861689802805/6437458573";
        game.info.rewardedVideo_ad_id = "ca-app-pub-2887861689802805/3587152459";
        game.info.interstitial_ad_condition_num = 4;

        init(game);
    }


    @Override
    public void outGame() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                showOutGameTips();
            }
        });
    }

    public void showOutGameTips() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.are)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                AndroidLauncher.this.finish();
                                System.exit(0);
                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                            }
                        }).show();
    }
}
