package lotus.net.demo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;

import lotus.net.center.myclass.GameInfo;
import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.LScreen;
import lotus.net.center.screen.LLogonScreen;

public class MyGame extends LGame{
    LLogonScreen logonScreen;
    protected MyGame(){
        setGameInfo();
    }
    private static  MyGame game;
    public static MyGame getInstance() {
        if(game == null){
            game = new MyGame();
        }
        return game;
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
                        doBackjob();
                        break;
                }
                return super.keyUp(keycode);
            }
        };
        logonScreen = LLogonScreen.getInstance(this);
        logonScreen.setNextScreen(MenuScreen.getInstance());
        setScreenshots(logonScreen);
        doSkip(null);
    }
    public void setGameInfo(){
        info = new GameInfo();
        this.info.setScreenSize((short) 720,(short)1280);
//        this.info.setScreenSize((short) 480,(short)800);
//        this.info.setScreenSize((short) 1280,(short)720);
//        this.info.setScreenSize((short) 800,(short)480);
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
