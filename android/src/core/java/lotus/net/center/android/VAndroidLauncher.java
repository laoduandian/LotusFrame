package lotus.net.center.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidGraphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import lotus.net.center.freefont.FreePaint;
import lotus.net.center.myclass.App;
import lotus.net.center.myclass.LGame;

public abstract class VAndroidLauncher extends AndroidApplication implements
        App {
    @SuppressWarnings("unused")
    private LGame game = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    protected void onResume() {
        super.onResume();
        AndroidGraphics graphics = (AndroidGraphics) getGraphics();
        graphics.getView().requestFocus();
    }

    public void setGame(LGame game) {
        this.game = game;
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
                alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
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


}
