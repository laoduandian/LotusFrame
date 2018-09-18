package lotus.net.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import lotus.net.center.ios.VIOSLauncher;


public class IOSLauncher extends VIOSLauncher {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new MyGame(this), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
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