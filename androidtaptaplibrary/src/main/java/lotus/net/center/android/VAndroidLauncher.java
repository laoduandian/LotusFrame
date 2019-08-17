package lotus.net.center.android;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lotus.net.center.android.ad.AdCentre;
import lotus.net.center.freefont.FreePaint;
import lotus.net.center.myclass.App;
import lotus.net.center.myclass.LGame;
import lotus.net.center.net.AppChannel;
import lotus.net.center.net.AppItem;


public abstract class VAndroidLauncher extends AndroidApplication implements App {
    public LGame game = null;
    public Handler handler;
    public RelativeLayout relativeLayout;
    public AdCentre adCentre;

    //googleservices
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void init(LGame game){
        this.game = game;
        this.game.setApp(this);
        this.game.info.setAppChannel(AppChannel.taptap);
        handler = new Handler();
        relativeLayout = new RelativeLayout(this);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        View gameView = initializeForView(game, cfg);
        relativeLayout.addView(gameView);
        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermission();
        }
        setContentView(relativeLayout);
    }
    private int getAnroidColor(Color color) {
        return ((int) (255 * color.a) << 24) | ((int) (255 * color.r) << 16)
                | ((int) (255 * color.g) << 8) | ((int) (255 * color.b));
    }

    private HashMap<String, Typeface> fontFaces = new HashMap<String, Typeface>();

    public Pixmap getFontPixmap(String txt, FreePaint vpaint) {
        Paint paint = new Paint();
        if (!vpaint.getTTFName().equals("")) {
            // Typeface fontFace = fontFaces.get(vpaint.getTTFName());
            Typeface fontFace = Typeface.createFromAsset(getAssets(),
                    vpaint.getTTFName()
                            + (vpaint.getTTFName().endsWith(".ttf") ? ""
                            : ".ttf"));
            fontFaces.put(vpaint.getTTFName(), fontFace);
            paint.setTypeface(fontFace);
        }
        paint.setAntiAlias(true);
        paint.setTextSize(vpaint.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        int w = (int) paint.measureText(txt);
        int h = (int) (fm.descent - fm.ascent);
        if (w == 0) {
            w = h = vpaint.getTextSize();
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // 如果是描边类型
        if (vpaint.getStrokeColor() != null) {
            // 绘制外层
            paint.setColor(getAnroidColor(vpaint.getStrokeColor()));
            paint.setStrokeWidth(vpaint.getStrokeWidth()); // 描边宽度
            paint.setStyle(Paint.Style.FILL_AND_STROKE); // 描边种类
            paint.setFakeBoldText(true); // 外层text采用粗体
            canvas.drawText(txt, 0, -fm.ascent, paint);
            paint.setFakeBoldText(false);
        } else {
            paint.setUnderlineText(vpaint.getUnderlineText());
            paint.setStrikeThruText(vpaint.getStrikeThruText());
            paint.setFakeBoldText(vpaint.getFakeBoldText());
        }
        // 绘制内层
        paint.setStrokeWidth(0);
        paint.setColor(getAnroidColor(vpaint.getColor()));
        canvas.drawText(txt, 0, -fm.ascent, paint);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, buffer);
        byte[] encodedData = buffer.toByteArray();
        Pixmap pixmap = new Pixmap(encodedData, 0, encodedData.length);
        bitmap = null;
        canvas = null;
        return pixmap;
    }

    public void getTextInput(final Input.TextInputListener listener,
                             final String title, final String text, final String hint) {
        final Context context = getContext();
        runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle(title);
                final EditText input = new EditText(context);
                input.setHint(hint);
                input.setText(text);
                input.setSingleLine();
                alert.setView(input);
                alert.setPositiveButton(context.getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                Gdx.app.postRunnable(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.input(input.getText()
                                                .toString());
                                    }
                                });
                            }
                        });
                alert.setNegativeButton(
                        context.getString(android.R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                Gdx.app.postRunnable(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.canceled();
                                    }
                                });
                            }
                        });
                alert.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface arg0) {
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                listener.canceled();
                            }
                        });
                    }
                });
                alert.show();
            }
        });
    }
    @Override
    public void newgame(AppItem appItem) {
        Gdx.net.openURI("https://"+appItem.getInlandAddress());
    }

    @Override
    public void moreGame() {
        Gdx.net.openURI("http://www.lotusstudio.top");
    }
    /**
     * 获取当前应用程序的包名
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }

    @Override
    public void showMovie(final int id) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adCentre.showMovie(id);
            }
        });
    }

    @Override
    public void addBanners(final boolean isHead) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adCentre.addBanners(isHead);
            }
        });
    }

    @Override
    public void loadInsertscreen() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adCentre.loadInsertscreen();
            }
        });
    }

    @Override
    public void showInterstitialAd() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adCentre.showInsertscreen();
            }
        });
    }
    @Override
    public void showSomething(final String a) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(VAndroidLauncher.this,  a,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void pinfen() {
        if(game.info.isInland() && game.info.getInlandAddress()!=null)
            Gdx.net.openURI("https://"+game.info.getInlandAddress());
        else
         handler.post(new Runnable() {
            @Override
            public void run() {
                 Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+  game.info.game_Address));
                    VAndroidLauncher.this.startActivity(it);
               }
        });
    }
    @Override
    public void share() {

    }
    @Override
    public void removeRanners() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adCentre.removeRanners();
            }
        });
    }
    @Override
    public void shangchuan(String name, float a) {

    }
    @Override
    public void shangchuan(String name, int a) {
    }
    @Override
    public void paihang() {
    }

    @Override
    public void initAD() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adCentre = new AdCentre(VAndroidLauncher.this);
            }
        });
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
                .setTitle(R.string.Lotus_out)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton(R.string.Lotus_yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                VAndroidLauncher.this.finish();
                                System.exit(0);
                            }
                        })
                .setNegativeButton(R.string.Lotus_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                            }
                        }).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    /**
     *
     * ----------非常重要----------
     *
     * Android6.0以上的权限适配简单示例：
     *
     * 如果targetSDKVersion >= 23，那么必须要申请到所需要的权限，再调用广点通SDK，否则广点通SDK不会工作。
     *
     * Demo代码里是一个基本的权限申请示例，请开发者根据自己的场景合理地编写这部分代码来实现权限申请。
     * 注意：下面的`checkSelfPermission`和`requestPermissions`方法都是在Android6.0的SDK中增加的API，如果您的App还没有适配到Android6.0以上，则不需要调用这些方法，直接调用广点通SDK即可。
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        // 权限都已经有了，那么直接调用SDK
        if (lackedPermission.size() == 0) {
//            fetchSplashAD(this, container, skipView, Constants.APPID, getPosId(), this, 0);
        } else {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adCentre!=null)
            adCentre.dispose();
    }
}
