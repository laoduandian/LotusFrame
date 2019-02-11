package lotus.net.center.myclass;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;


public class LParticleEffectPool extends Pool<LParticleEffectPool.ParticleActor> {
	private ParticleEffect rootEffect;


	public LParticleEffectPool(LGame game, String effectPath, String imagesDir){
		rootEffect = game.getParticleEffect(effectPath,imagesDir);
	}
	public LParticleEffectPool(LGame game, String effectPath){
		rootEffect = game.getParticleEffect(effectPath);
	}

	public ParticleActor obtain(float x ,float y) {
		ParticleActor actor = super.obtain();
		actor.setPosition(x, y);
		return actor;
	}

	@Override
	protected ParticleActor newObject() {
		return new ParticleActor(this.rootEffect);
	}

	public class  ParticleActor extends Actor{
		private ParticleEffect effect;
		public ParticleActor(ParticleEffect rootEffect) {
			effect = new ParticleEffect(rootEffect);
			effect.reset();
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			batch.setColor(getColor());
			effect.setPosition(getX(),getY());
			effect.draw(batch);

		}
		@Override
		public void act(float delta) {
			super.act(delta);
			effect.update(delta);
			if(effect.isComplete())
				free();
		}
		public void free(){
			LParticleEffectPool.this.free(this);
			this.remove();
			effect.reset();
		}
	}
}
