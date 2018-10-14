package lotus.net.center.myclass;

import com.badlogic.gdx.scenes.scene2d.Group;

public class LGroup extends Group{
	public LGame game;
	public LGroup(LGame game) {
		this.game = game;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
	}
}
