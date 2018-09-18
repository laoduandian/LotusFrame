package lotus.net.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lotus.net.center.VDesktopLauncher;
import lotus.net.demo.MyGame;


public class DesktopLauncher extends VDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGame(new DesktopLauncher()), config);
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
}


