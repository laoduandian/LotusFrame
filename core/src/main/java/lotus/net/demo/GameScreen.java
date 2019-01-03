package lotus.net.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.LScreen;
import lotus.net.center.uieditor.project.widget.ButtonActor;
import lotus.net.center.uieditor.project.widget.TextButtonActor;

public class GameScreen extends LScreen{
    private static GameScreen gameScreen;
    public static GameScreen getInstance(){
        if(gameScreen == null){
            gameScreen = new GameScreen();
        }
        return gameScreen;
    }
    @Override
    public void show() {
        super.show();
        Image image = new Image(game.assetManager.get("data/bg/1.jpg",Texture.class));
        this.getStage().addActor(image);
        TextButtonActor overscreen = new TextButtonActor(new TextureRegionDrawable(new TextureRegion(game.texture)));
        overscreen.setColor(Color.PINK);
        overscreen.setPosition(200,1100);
        overscreen.setSize(200,50);
        overscreen.add(game.font.getLabel("结束界面"));
        this.getStage().addActor(overscreen);
        overscreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreenshots(OverScreen.getInstance());
            }
        });
    }

    @Override
    public void resume() {
        this.game.assetManager.load("data/bg/1.jpg", Texture.class);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void dobackJob() {
        game.setScreenshots(MenuScreen.getInstance());
    }
}
