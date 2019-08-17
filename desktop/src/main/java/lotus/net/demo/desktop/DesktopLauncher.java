package lotus.net.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import lotus.net.center.desktop.VDesktopLauncher;
import lotus.net.center.net.AdsType;
import lotus.net.center.net.AppChannel;
import lotus.net.demo.MyGame;


public class DesktopLauncher extends VDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		DesktopLauncher launcher = new DesktopLauncher();
		launcher.setGame(MyGame.getInstance());
		config.width = game.info.GAME_WIDTH;
		config.height = game.info.GAME_HEIGHT;
		game.info.is_Add_New = true;
		game.info.game_Address = "1375561039";
		game.info.setInland(true);
		game.info.setInlandAddress("www.baidu.com");
		game.info.setAppChannel(AppChannel.google);
		game.info.getOwnTypes().add(AdsType.csj,AdsType.baidu);
		game.info.setAdsType(AdsType.admob);
//		try {
//			game.info.setAdType(AdsValue.class.getField("gdt").getInt(AdsValue.class));
//		} catch (NoSuchFieldException e) {
//			System.out.println("+");
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			System.out.println("-");
//			e.printStackTrace();
//		}
		new LwjglApplication(game, config);


	}

}


