package lotus.net.center.myclass;

import com.badlogic.gdx.scenes.scene2d.Group;

public class MyGroup extends Group{
	public LGame game;
	public MyGroup(LGame game) {
		this.game = game;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
	}
}
