package lotus.net.center.uieditor.project.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pools;

import lotus.net.center.myclass.helper.GrayscaleShader;

public class ButtonActor extends Actor{
	private TextureRegion up,down,checked;
	private boolean isChecked; 
	private float regionX,regionY;
	private ClickListener clickListener;
	private ShaderProgram shaderProgram;
    private float time = 1f;
    private float Max_Rad = 0.6f;
	public ButtonActor(TextureRegion up){
		this(up,null,up.getRegionWidth(),up.getRegionHeight());
	}
	public ButtonActor(TextureRegion up,float w,float h){
		this(up,null,w,h);
	}
	public ButtonActor(TextureRegion up,TextureRegion down,float w,float h){
		this(up, down, null,w,h);
	}
	public ButtonActor(TextureRegion up,TextureRegion down,TextureRegion checked){
		this(up, down, checked,checked.getRegionWidth(),up.getRegionHeight());
	}
	public ButtonActor(TextureRegion up,TextureRegion down,TextureRegion checked,float w,float h) {
		this.up = up;
		this.down = down;
		this.checked = checked;
		this.setSize(w,h);
		regionX = this.getWidth()/2 - up.getRegionWidth()/2;
		regionY = this.getHeight()/2- up.getRegionHeight()/2;
		this.setOrigin(Align.center);
		setChecked(false);
		initialize();
		shaderProgram = GrayscaleShader.clickShader;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(!isTouchable())
			batch.setShader(GrayscaleShader.grayscaleShader);
		if(isPressed()) {
			ShaderProgram shader = batch.getShader();
	        batch.setShader(shaderProgram);
	        shaderProgram.setUniformf("u_lightColor", .0f,.0f,.0f,1.0f);
	        shaderProgram.setUniformf("u_lightRange",time);
			batch.setColor(getColor());
			if(isPressed()&&down!=null)
				batch.draw(down, getX(), getY());
			if(isChecked){
				if(checked!=null)
					batch.draw(checked, getX()+regionX, getY()+regionY, getOriginX(), getOriginY(), checked.getRegionWidth(), checked.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
				else
					batch.draw(up, getX()+regionX, getY()+regionY, getOriginX(), getOriginY(), up.getRegionWidth(), up.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			} else{
				if(up!=null)
					batch.draw(up, getX()+regionX, getY()+regionY, getOriginX(), getOriginY(), up.getRegionWidth(), up.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			}
			batch.setColor(Color.WHITE);
			batch.setShader(shader);
		}else {
			batch.setColor(getColor());
			if(isPressed()&&down!=null)
				batch.draw(down, getX(), getY());
			if(isChecked){
				if(checked!=null)
					batch.draw(checked, getX()+regionX, getY()+regionY, getOriginX(), getOriginY(), checked.getRegionWidth(), checked.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
				else
					batch.draw(up, getX()+regionX, getY()+regionY, getOriginX(), getOriginY(), up.getRegionWidth(), up.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			} else{
				if(up!=null)
					batch.draw(up, getX()+regionX, getY()+regionY, getOriginX(), getOriginY(), up.getRegionWidth(), up.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			}
			batch.setColor(Color.WHITE);
		}
		batch.setShader(null);
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
	public TextureRegion getUp() {
		return up;
	}
	public void setUp(TextureRegion up) {
		this.up = up;
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
}
