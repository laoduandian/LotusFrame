package lotus.net.center.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import lotus.net.center.myclass.LGame;

public class LoadingScreen implements Screen{
	private Stage stage;
	private Texture texture;
	private LGame game;
	private boolean isOverSkip;
	public LoadingScreen(LGame game) {
		this.game = game;
	}
	@Override
	public void show() {
		stage = new Stage();
		stage.setViewport(new StretchViewport(game.info.GAME_WIDTH, game.info.GAME_HEIGHT));
		if(texture!=null){
			final int w = Gdx.graphics.getWidth();
			final int h = Gdx.graphics.getHeight();
			TextureRegion textureRegion = new TextureRegion(texture, 0, h, w, -h);
			Image image = new Image(textureRegion);
			image.setName("image");
			image.setSize(game.info.GAME_WIDTH,game.info.GAME_HEIGHT);
			image.addAction(Actions.sequence(Actions.fadeOut(0.5f),Actions.run(new Runnable() {
				@Override
				public void run() {
					isOverSkip = true;
				}
			})));
			stage.addActor(image);
		}else{
			isOverSkip = true;
		}
		
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1,1,1, 1);
		stage.act(delta);
		stage.draw();
		if(isOverSkip&&game.assetManager.update()){
			game.over_Skip();
		}
	}
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	@Override
	public void hide() {
		
	}
	@Override
	public void dispose() {
		if(this.texture!=null)
			this.texture.dispose();
		this.texture = null;
	}
	public void setTexture(FrameBuffer frameBuffer) {
		isOverSkip = false;
		if(frameBuffer == null)
			return;
		if(this.texture!=null)
			this.texture.dispose();
		this.texture = frameBuffer.getColorBufferTexture();
	}

	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	@Override
	public void pause() {
		
	}
	@Override
	public void resume() {
		
	}
}
