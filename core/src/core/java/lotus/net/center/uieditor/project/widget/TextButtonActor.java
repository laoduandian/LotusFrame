package lotus.net.center.uieditor.project.widget;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import lotus.net.center.myclass.helper.GrayscaleShader;

public class TextButtonActor extends Button {
    private ShaderProgram shaderProgram;
    private float time = 1f;
    private float Max_Rad = 0.6f;

    public TextButtonActor(Drawable up) {
        super(up);
        shaderProgram = GrayscaleShader.clickShader;
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if (isPressed()) {
            time -= (delta * 2);
            time = Math.max(Max_Rad, time);
        } else {
            time = 1;
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isPressed()) {
            ShaderProgram shader = batch.getShader();
            batch.setShader(shaderProgram);
            shaderProgram.setUniformf("u_lightColor", .0f, .0f, .0f, 1.0f);
            shaderProgram.setUniformf("u_lightRange", time);
            super.draw(batch, parentAlpha);
            batch.setShader(shader);
        } else {
            super.draw(batch, parentAlpha);
        }
    }
}
