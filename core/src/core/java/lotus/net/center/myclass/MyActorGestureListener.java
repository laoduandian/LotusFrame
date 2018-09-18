package lotus.net.center.myclass;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MyActorGestureListener extends ActorGestureListener {
	public Actor actor;
	public int code;
	private boolean isTouchDown;
	private float SUMX,SUMY,range;

	public MyActorGestureListener(LGame game) {
		super();
	}
	
	@Override
	public void touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		if (pointer != 0)
			return;
		SUMX = SUMY = 0;
		isTouchDown = true;
		actor = event.getListenerActor();
		code = Integer.parseInt(actor.getName());
		if (actor.getColor().equals(Color.GRAY)) {
			isTouchDown = false;
			return;
		}
		actor.setColor(Color.GRAY);
	}


	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		if (pointer != 0 || !isTouchDown)
			return;
		actor.setColor(Color.WHITE);
			
	}

	@Override
	public void tap(InputEvent event, float x, float y, int count, int button) {
		if (!isTouchDown)
			return;
		doClick(x, y);
	}

	@Override
	public void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
		SUMX += deltaX;
		SUMY += deltaY;
		if (!isTouchDown || Math.hypot(SUMX, SUMY) < range)
			return;
		actor.setColor(Color.WHITE);
		isTouchDown = false;
	}

	public void doClick(float x, float y) {
		switch (code) {
		case 1:
			break;
		}
	}
}
