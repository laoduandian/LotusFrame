package lotus.net.center.uieditor.project;

import com.badlogic.gdx.utils.Array;

public class GameResources {
	private Array<ResFile> fileArray;
	public Array<ResFile> getFileArray() {
		if (fileArray == null) 		
			fileArray = new Array<ResFile>();
		return fileArray;
	}
	public void setFileArray(Array<ResFile> fileArray) {
		this.fileArray = fileArray;
	}
}
