package com.lotus.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lotus.game.MyGame;
import var3d.net.demo.Game;
import var3d.net.center.desktop.VDesktopLauncher;

public class DesktopLauncher extends VDesktopLauncher{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGame(), config);
		new LwjglApplication(new Game(new DesktopLauncher()), getConfig(Size.iphoneX_w));
	}
}


