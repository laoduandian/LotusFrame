package lotus.net.center.uieditor.project.widget;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class LImageNumber extends Actor{
	private Map<Character, TextureRegion> regionMap = new HashMap<Character, TextureRegion>();
	private String text = "321";
	private float regionW ,regionH;
	private String imageNumberContent;
	private TextureRegion region;
	private float anchorX ,anchorY;
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		drawNumber(batch);
	}
	public void setRegions(){
		regionMap.clear();
		if(getImageNumberContent()==null)
			return;
		if(getImageNumberContent().length()<0)
			return;
		String content = getImageNumberContent();
		int length = content.length();
		regionH = getRegion().getRegionHeight();
		regionW = getRegion().getRegionWidth()/length;
		setLImageNumberSize();
		TextureRegion regions[] = getRegion().split((int)regionW , (int)regionH)[0];
		for (int i = 0; i < length; i++) {
			regionMap.put(content.charAt(i), regions[i]);
		}
		this.setTouchable(Touchable.disabled);
	}
	private void drawNumber(Batch batch){
		char[] cs= text.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if(regionMap.get(c) != null)
			batch.draw(regionMap.get(c), getX()+ i*regionW - getAnchorX()*getWidth(), getY()- getAnchorY()*getHeight(), regionW, regionH);
		}
	}
	private void setLImageNumberSize(){
		this.setSize(regionW*text.length(), regionH);
	}

	public String getImageNumberContent() {
		return imageNumberContent;
	}

	public void setImageNumberContent(String imageNumberContent) {
		this.imageNumberContent = imageNumberContent;
	}

	public TextureRegion getRegion() {
		return region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}

	public void setText(String text) {
		this.text = text;
		this.setSize(regionW*text.length(), regionH);
	}
	public float getAnchorX() {
		return anchorX;
	}
	public void setAnchorX(float anchorX) {
		this.anchorX = anchorX;
	}
	public float getAnchorY() {
		return anchorY;
	}
	public void setAnchorY(float anchorY) {
		this.anchorY = anchorY;
	}
}
