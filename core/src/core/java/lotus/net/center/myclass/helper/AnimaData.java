package lotus.net.center.myclass.helper;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class AnimaData extends Actor{
	private TextureAtlas atlas;
	public int index;// 第几�??
	private String animaName;
	private float delay = 0.2f;// 每帧延迟的时�??
	private boolean loop;// 是否是无限循�??
	private float time;// 游戏时间
	public int length;
	private boolean istrans = false;
	private int sleepNum,num;
	public static HashMap<String, Array<TextureRegion>> drawable = new HashMap<String, Array<TextureRegion>>();
	private Array<TextureRegion> drawableArray;
	private TextureRegion region;
	private boolean isFinishAnima = false;
	public AnimaData() {
		index = 0;
	}
	public void setTextureAtlas(TextureAtlas atlas){
		this.atlas = atlas;
	}
	public void setAnimaName(String animaName){
		this.animaName = animaName;
	}
	public void setAction() {
		setSleep(0);
		setFinishAnima(false);
		index = 0;
		drawableArray = drawable.get(animaName);
		if(drawableArray==null){
			drawableArray = new Array<TextureRegion>();
			for (int i = 0; i < atlas.getRegions().size; i++) {
				if (atlas.getRegions().get(i).name.startsWith(animaName)) {
					TextureRegion region = atlas.getRegions().get(i);
					drawableArray.add(region);
				}
			}
			drawable.put(animaName, drawableArray);
		}
		length = drawableArray.size;
		setAnimaDrawble();
	}
	public void setDelay(float delay) {
		this.delay = delay;
	}
	public void setLoops(boolean myloop) {
		loop = myloop;
	}
	
	public void setTransform(boolean istrans) {
		this.istrans = istrans;
	}
	public boolean isTransform(){
		return istrans;
	}
	public int nextFrame(float delta) {
		time += delta;
		if (time >= delay) {
			if(!loop){
				if(isFinishAnima()){
					return index;
				}else{
					if(index + 1 == length){
						setFinishAnima(true);
						index= 0;
						return index;
					}
				}
			}
			index++;
			if(index==1&&num!=0){
				num--;
				if(num==0){
					num = sleepNum;
					index = index % length;
					return index;
				}else{
					index = 0;
					return index;
				}
			}else{
				index = index % length;
				time = 0;
			}
		}
		return index;
	}
	private void setAnimaDrawble(){
		if((istrans&&!drawableArray.get(index).isFlipX())||(drawableArray.get(index).isFlipX()&&!istrans)){
			drawableArray.get(index).flip(true, false);
		}
		if(this.getRegion() == drawableArray.get(index)){
			return;
		}
		this.setRegion(drawableArray.get(index));
		this.setWidth(drawableArray.get(index).getRegionWidth()*getScaleX());
		this.setHeight(drawableArray.get(index).getRegionHeight()*getScaleY());
	}
	private TextureRegion getRegion() {
		return region;
	}
	private void setRegion(TextureRegion textureRegion) {
		region = textureRegion;
	}
	public void setSleep(int num){
		this.num = num;
		sleepNum = num;
	}
	public boolean isFinishAnima() {
		return isFinishAnima;
	}
	public void setFinishAnima(boolean isFinishAnima) {
		this.isFinishAnima = isFinishAnima;
	}
	public int getIndex(){
		return index;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(getColor());
		if(this.getRegion()!=null)
			batch.draw(region, this.getX(), this.getY(), getOriginX(), getOriginY(), this.getWidth(), this.getHeight(), 1, 1, getRotation());
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		nextFrame(delta);
		setAnimaDrawble();
	}
}
