package lotus.net.center.myclass;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;

public class LParticleEffect extends Pool<LParticleEffect.ParticleEffectActor> {
	private ParticleEffect rootEffect;

	public LParticleEffect(LGame game,String assetsPath){
		this.rootEffect = game.assetManager.get(assetsPath);
	}

	@Override
	protected ParticleEffectActor newObject() {
		return new ParticleEffectActor(this.rootEffect);
	}

	public class  ParticleEffectActor extends Actor{
		private ParticleEffect effect;
		public ParticleEffectActor(ParticleEffect rootEffect) {
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
			if(effect.isComplete()){
				free();
			}
		}
		public void free(){
			LParticleEffect.this.free(this);
			this.remove();
			effect.reset();
		}
	}
}
