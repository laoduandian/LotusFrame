package lotus.net.center.myclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class MyScreen implements Screen{
	public LGame game;
	private Stage stage;
	private FrameBuffer frameBuffer;
	private TextureAtlas modeAtlas;
	private TextureAtlas publicAtlas;
	public MyScreen(LGame game) {
		this.game = game;
	}
	@Override
	public void show() {
		stage = new Stage();
		stage.setViewport(new StretchViewport(M.GAME_WIDTH, M.GAME_HEIGHT));
        game.multiplexer.addProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(game.multiplexer);
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),false);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act(delta);
		if(game.isScreenshots()){
			frameBuffer.begin();
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stage.draw();
			frameBuffer.end();
			game.doSkip(frameBuffer);
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void pause() {
		
	}
	@Override
	public abstract void resume();

	@Override
	public void hide() {
	}

	@Override
	public abstract void dispose();
	public Stage getStage() {
		return stage;
	}
	public TextureRegion getModeTextureRegion(String regionName){
		return modeAtlas.findRegion(regionName);
	}
	public void setModeAtlas(TextureAtlas modeAtlas) {
		this.modeAtlas = modeAtlas;
	}
	public void setModeAtlas(String string) {
		this.modeAtlas = game.assetManager.get(string);
		for (Texture texture : modeAtlas.getTextures()) {
			Tools.setTextureFilter(texture);
		}
	}
	public TextureAtlas getModeAtlas() {
		return modeAtlas;
	}
	
	public TextureRegion getPublicTextureRegion(String regionName){
		return publicAtlas.findRegion(regionName);
	}
	public void setPublicAtlas(String string) {
		this.publicAtlas = game.assetManager.get(string);
		for (Texture texture : publicAtlas.getTextures()) {
			Tools.setTextureFilter(texture);
		}
	}
	public TextureAtlas getPublicAtlas() {
		return publicAtlas;
	}
	public abstract void dobackJob();
}
