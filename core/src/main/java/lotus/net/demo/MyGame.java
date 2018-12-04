package lotus.net.demo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;

import lotus.net.center.myclass.App;
import lotus.net.center.myclass.GameInfo;
import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.LAssetManager;
import lotus.net.center.myclass.LScreen;
import lotus.net.center.screen.LLogonScreen;

public class MyGame extends LGame{
    LLogonScreen logonScreen;
    public MyGame(App lhandler) {
        super(lhandler);
        setGameInfo();
    }

    @Override
    public void create() {
        super.create();
        this.multiplexer = new InputMultiplexer() {
            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.BACK:
                        doBackjob();
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
        logonScreen = new LLogonScreen(this);
        logonScreen.setNextScreen(getScreen(MenuScreen.class));
        assetManager = new LAssetManager(this);
        setScreenshots(logonScreen);
        doSkip(null);
    }
    public void setGameInfo(){
        info = new GameInfo();
        this.info.setScreenSize((short) 720,(short)1280);
//        this.info.setScreenSize((short) 1280,(short)720);
        this.info.game_name = "demo";
    }

    @Override
    public void showMovie_return(int id) {
        switch (id){
            case 0:
                break;
            case 1:
                break;
        }
    }
    private void doBackjob(){
        if (getScreen() instanceof LScreen) {
            LScreen screen = (LScreen) getScreen();
            screen.dobackJob();
        }
    }
}
