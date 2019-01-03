package lotus.net.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lotus.net.center.desktop.VDesktopLauncher;
import lotus.net.demo.MyGame;


public class DesktopLauncher extends VDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		DesktopLauncher launcher = new DesktopLauncher();
		launcher.setGame(MyGame.getInstance());
		config.width = game.info.GAME_WIDTH;
		config.height = game.info.GAME_HEIGHT;
//		game.info.is_Add_New = true;
		game.info.game_Address = "1375561039";

		new LwjglApplication(game, config);
	}

}


