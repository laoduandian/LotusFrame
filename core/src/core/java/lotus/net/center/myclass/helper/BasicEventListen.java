package lotus.net.center.myclass.helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class BasicEventListen extends ActorGestureListener{
	Actor actor;
	private boolean isTouch = false;
	private boolean isCanTouch = false;
	@Override
	public void touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		super.touchDown(event, x, y, pointer, button);
		if(pointer!=0)
			return;
		actor = event.getListenerActor();
		isCanTouch = !actor.getColor().equals(Color.GRAY);
		if(!isCanTouch)
			return;
		setTouch(true);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		if(pointer!=0)
			return;
		super.touchUp(event, x, y, pointer, button);
	}

	@Override
	public void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
		super.pan(event, x, y, deltaX, deltaY);
		if(!isCanTouch)
			return;
		if(x>0&&y>0&&x<actor.getWidth()&&y<actor.getHeight()){
			this.setTouch(true);
		}else{
			this.setTouch(false);
		}
	}

	public boolean isTouch() {
		return isTouch;
	}

	public void setTouch(boolean isTouch) {
		this.isTouch = isTouch;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public boolean isCanTouch() {
		return isCanTouch;
	}

	public void setCanTouch(boolean isCanTouch) {
		this.isCanTouch = isCanTouch;
	}
	
}
