package lotus.net.center.myclass;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LParticleEffect extends Actor{
	private Map<String , ParticleEffectPool> particleEffectPoolMap = new HashMap<String, ParticleEffectPool>(); //粒子池map
	public ParticleEffect tem;
	
	public void loadParticle(String name,float x,float y){
		tem = getParticleEffectPool(name).obtain();
		tem.setPosition(x,y);
	}
	public void dispose() {
		if (tem != null)
			tem.dispose();
	}

	@Override
	public void draw(Batch batch, float arg1) {
		if(tem != null){
			tem.draw(batch, Gdx.graphics.getDeltaTime());
		}
	}
	
	@Override
	public void act(float delta) {
		if(tem.isComplete()){
			this.getParent().removeActor(this);
		}
	}
	
	/**
	 *  获取粒子的缓冲区
	 * @param name 粒子名称
	 * @return ParticleEffectPool
	 */
	public ParticleEffectPool getParticleEffectPool(String name) {
		ParticleEffectPool pool = particleEffectPoolMap.get(name);
		if(pool == null){
			ParticleEffect p = new ParticleEffect();
			p.load(Gdx.files.internal("data/" + name + ".p"), Gdx.files.internal("data/"));
			pool = new ParticleEffectPool(p, 1, 1);
			particleEffectPoolMap.put(name, pool);
		}
		return pool;
	}
}
