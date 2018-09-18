package lotus.net.center.uieditor.project.model;

public class Anchor {
	private float x,y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public void setAnchor(float x,float y){
		setX(x);
		setY(y);
	}
}
