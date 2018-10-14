package lotus.net.center.myclass.helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class BasicButton extends BasicEventListen{

	@Override
	public void touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		if(pointer!=0)
			return;
		super.touchDown(event, x, y, pointer, button);
		this.getActor().setColor(Color.GRAY);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		if(pointer!=0)
			return;
		super.touchUp(event, x, y, pointer, button);
		if(isTouch()){
			setTouch(false);
		}
		this.getActor().setColor(Color.WHITE);
	}

	@Override
	public void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
		super.pan(event, x, y, deltaX, deltaY);
		if(isTouch()){
			this.getActor().setColor(Color.GRAY);
		}else{
			this.getActor().setColor(Color.WHITE);
		}
	}
	
}
