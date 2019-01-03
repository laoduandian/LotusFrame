package lotus.net.demo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.LScreen;

public class OverScreen extends LScreen{
    private static OverScreen overScreen;
    public static OverScreen getInstance(){
        if(overScreen == null){
            overScreen = new OverScreen();
        }
        return overScreen;
    }
    @Override
    public void show() {
        super.show();
        Image image = new Image(game.assetManager.get("data/bg/2.jpg",Texture.class));
        this.getStage().addActor(image);

    }
    @Override
    public void resume() {
        this.game.assetManager.load("data/bg/2.jpg", Texture.class);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void dobackJob() {
        game.setScreenshots(GameScreen.getInstance());
    }
}
