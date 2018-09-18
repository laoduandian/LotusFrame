package lotus.net.center.uieditor.project;

import com.badlogic.gdx.utils.Array;

public class ResFile {
	private String name;
	private String address;
	private boolean isDirectory;
	private Array<ResFile> files;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	public Array<ResFile> getFiles() {
		return files;
	}
	public void setFiles(Array<ResFile> files) {
		this.files = files;
	}
}
