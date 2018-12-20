package lotus.net.center.uieditor.project.widget;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Pools;

import lotus.net.center.myclass.LGame;
import lotus.net.center.myclass.LGroup;
import lotus.net.center.myclass.helper.GrayscaleShader;

public class ButtonGroup extends LGroup {
	private boolean isChecked; 
	private ClickListener clickListener;
	private ShaderProgram shaderProgram;
    private float time = 1f;
    private float Max_Rad = 0.6f;
	public ButtonGroup(LGame game) {
		super(game);
		initialize();
		shaderProgram = GrayscaleShader.clickShader;
	}
	private void initialize () {
		setTouchable(Touchable.enabled);
		addListener(clickListener = new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				setChecked(!isChecked, true);
			}
		});
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public boolean isPressed () {
		return clickListener.isVisualPressed();
	}
	void setChecked (boolean isChecked, boolean fireEvent) {
		if (this.isChecked == isChecked) return;
		this.isChecked = isChecked;
		if (fireEvent) {
			ChangeEvent changeEvent = Pools.obtain(ChangeEvent.class);
			if (fire(changeEvent)) this.isChecked = !isChecked;
			Pools.free(changeEvent);
		}
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		if(isPressed()) {
			time -= (delta*2);
			time = Math.max(Max_Rad, time);
		}else {
			time = 1;
		}
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(isPressed()) {
			ShaderProgram shader = batch.getShader();
	        batch.setShader(shaderProgram);
	        shaderProgram.setUniformf("u_lightColor", .0f,.0f,.0f,1.0f);
	        shaderProgram.setUniformf("u_lightRange",time);
	        super.draw(batch, parentAlpha);
			batch.setShader(shader);
		}else {
			super.draw(batch, parentAlpha);
		}
	}
}
