package lotus.net.center.uieditor.project;

import com.badlogic.gdx.utils.Array;

import lotus.net.center.uieditor.project.model.ObjectData;

public class GameScene {
	private Array<ObjectData> actorArray;
	public Array<ObjectData> getObjectArray() {
		if (actorArray == null) 		
			actorArray = new Array<ObjectData>();
		return actorArray;
	}
	public void setFileArray(Array<ObjectData> actorArray) {
		this.actorArray = actorArray;
	}
}
