package lotus.net.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;

import lotus.net.center.myclass.App;
import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.MyAssetManager;

public class MyGame extends LGame{

    public MyGame(App handler) {
        super(handler);
    }

    @Override
    public void create() {
        super.create();
        this.multiplexer = new InputMultiplexer() {
            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.BACK:
                        break;
                    case Input.Keys.HOME:
                        break;
                    case Input.Keys.SPACE:
                        break;
                    case Input.Keys.DPAD_LEFT:
                        break;
                }
                return super.keyUp(keycode);
            }
        };
        LogonScreen logonScreen = new LogonScreen(this);
        assetManager = new MyAssetManager(this);
        assetManager.load("data/logobg.jpg", Texture.class);
        setScreenshots(logonScreen);
        doSkip(null);
        Gdx.app.log("","sss");
    }
}
