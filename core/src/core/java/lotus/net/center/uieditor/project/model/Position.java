package lotus.net.center.uieditor.project.model;

public class Position {
	float X;
	float Y;
	public void setPosition(float x, float y){
		setX(x);
		setY(y);
	}
	
	public float getX() {
		return X;
	}
	public void setX(float x) {
		X = x;
	}
	public float getY() {
		return Y;
	}
	public void setY(float y) {
		Y = y;
	}
}
