package lotus.net.center.myclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class LParticleEffectGroup extends Group{
	private Map<String , Queue<MyParticleEffect>> effectQueueMap = new HashMap<String, Queue<MyParticleEffect>>();
	public ArrayList<ParticleEffect> tems = new ArrayList<ParticleEffect>();
	public void addParticleEffect(String name,float x,float y){
		
		Queue<MyParticleEffect> effectQueue = effectQueueMap.get(name);
		if(effectQueue == null){
			effectQueue = new LinkedList<MyParticleEffect>();
			effectQueueMap.put(name, effectQueue);
		}
		MyParticleEffect effect = effectQueue.poll();
		
		if(effect == null){
			effect = new MyParticleEffect(name);
		}
		effect.reset(x, y);
		addActor(effect);
	}

	class MyParticleEffect extends Actor{
		ParticleEffect tem;
		String name;
		public MyParticleEffect(String name){
			this.name = name;
			tem = new ParticleEffect();
			tem.load(Gdx.files.internal("data/explode/" + name + "explode.p"), Gdx.files.internal("data/explode/"));
		}
		
		public void reset(float x,float y){
			tem.reset();
			tem.setPosition(x, y);
		}
		@Override
		public void draw(Batch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			if(tem.isComplete()){
				effectQueueMap.get(name).offer(this);
				remove();
			}
				
			tem.draw(batch, Gdx.graphics.getDeltaTime());
		}
	}
}
