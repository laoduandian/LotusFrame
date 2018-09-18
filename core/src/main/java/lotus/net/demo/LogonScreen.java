package lotus.net.demo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import lotus.net.center.myclass.M;
import lotus.net.center.myclass.MyScreen;
import lotus.net.center.myclass.Tools;


public class LogonScreen extends MyScreen {
	public LogonScreen(MyGame game) {
		super(game);
	}
	@Override
	public void show() {
		super.show();
		game.app.initializeAD();
        init();
	}
	private void init() {
		Texture texture = game.assetManager.get("data/logobg.jpg");
		Image bgImage = new Image(Tools.setTextureFilter(texture));
		
		bgImage.setSize(M.GAME_WIDTH, bgImage.getHeight()* M.GAME_WIDTH/bgImage.getWidth());
		bgImage.setPosition(M.GAME_WIDTH/2 - bgImage.getWidth()/2, M.GAME_HEIGHT/2 -bgImage.getHeight()/2);
		
		getStage().addActor(bgImage);
		bgImage.addListener(new ActorGestureListener(){

			@Override
			public void fling(InputEvent event, float velocityX,
					float velocityY, int button) {
				super.fling(event, velocityX, velocityY, button);
			}
		});

	}
	@Override
	public void resume() {
	}
	boolean isTiaozhuan = false;
	@Override
	public void render(float delta) {
		super.render(delta);
	}
	@Override
	public void dispose() {
	}
	@Override
	public void dobackJob() {
		
	}
}
