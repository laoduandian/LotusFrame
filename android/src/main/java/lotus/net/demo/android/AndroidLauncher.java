package lotus.net.demo.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;

import lotus.net.center.android.VAndroidLauncher;
import lotus.net.center.freefont.FreePaint;
import lotus.net.demo.MyGame;

public class AndroidLauncher extends VAndroidLauncher {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGame(this), config);
	}

	@Override
	public void outGame() {

	}

	@Override
	public void pinfen() {

	}

	@Override
	public void showSomething(String a) {

	}

	@Override
	public void paihang() {

	}

	@Override
	public void shangchuan(String name, int a) {

	}

	@Override
	public void shangchuan(String name, float a) {

	}

	@Override
	public void addBanners() {

	}

	@Override
	public void removeRanners() {

	}

	@Override
	public void moreGame() {

	}

	@Override
	public void showInterstitialAd() {

	}

	@Override
	public void share() {

	}

	@Override
	public void loadInsertscreen() {

	}

	@Override
	public void showMovie() {

	}

	@Override
	public void showMovie(int id) {

	}

	@Override
	public void newgame() {

	}

	@Override
	public void initializeAD() {

	}

	@Override
	public Pixmap getFontPixmap(String txt, FreePaint vpaint) {
		return null;
	}
}
