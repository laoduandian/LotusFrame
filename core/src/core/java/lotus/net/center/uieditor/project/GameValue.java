package lotus.net.center.uieditor.project;


public class GameValue {
	private int screenWidth;
	private int screenHeight;
	private GameResources resources;
	private GameScene actors;
	public int getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	public GameResources getResources() {
		if(resources == null)
			resources = new GameResources();
		return resources;
	}
	public void setResources(GameResources resources) {
		this.resources = resources;
	}
	public GameScene getActors() {
		if(actors == null)
			actors = new GameScene();
		return actors;
	}
	public void setActors(GameScene actors) {
		this.actors = actors;
	}
}
