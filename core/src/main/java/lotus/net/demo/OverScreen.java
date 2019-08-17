package lotus.net.demo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

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
        Image image = new Image(game.getTexture(L.data.bg.image.addName_1));
        image.setOrigin(Align.bottom);
        this.getStage().addActor(image);

    }
    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }

    @Override
    public void dobackJob() {
        game.setScreenshots(GameScreen.getInstance());
    }
}
