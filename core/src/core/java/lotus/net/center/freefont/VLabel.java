package lotus.net.center.freefont;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class VLabel extends Label {
    private boolean isStroke = false;// 是否描边
    private Color strokeColor=new Color();
    private float strokeWidth;
    private float shadowOffsetX = 0f;//设置阴影位移x
    private float shadowOffsetY = 0f;//设置阴影位移x
    private ShadowOption shadowOption = ShadowOption.Disable;//设置阴影选项
    private Color shadowColor = new Color(Color.GRAY);//阴影颜色
    private BitmapFontCache fontCache;
    private boolean isHasEmoji=false;//是否含有emoji

  //  private Color labColor=new Color(1,1,1,1);//当使用带 emoji 的 FreeFontBitmap 时，调用 setColor 将会设置给这个参数

    public enum ShadowOption {
        Disable, Projection, Smear
    }

    public VLabel(CharSequence text, LabelStyle style) {
        super(append(text, style), style);
        setSize(getPrefWidth(), getPrefHeight());
        setColor(style.fontColor);
        fontCache = getBitmapFontCache();
    }


    private static CharSequence append(CharSequence text, LabelStyle style) {
        return ((FreeBitmapFont) style.font).appendTextPro(text.toString());
    }

    public void setText(CharSequence newText) {
        super.setText(append(newText, getStyle()));
    }


    public void setColor(Color color) {
        setColor(color.r, color.g, color.b, color.a);
    }

    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);
        if (isStroke) strokeColor.set(r, g, b, a);
    }

    /**
     * 设置加粗(参数0-1为宜)
     */
    public void setBold(float width) {
        setStroke(getColor().cpy(), width);
    }

    /**
     * 设置描边
     */
    public void setStroke(Color strokeColor) {
        setStroke(strokeColor, 1);
    }

    /**
     * 设置描边
     */
    public void setStroke(Color strokeColor, float strokeWidth) {
        this.strokeColor.set(strokeColor);
        this.strokeWidth = strokeWidth;
        isStroke = true;
        // refushCache();
    }

    /**
     * 设置字体缩放
     */

    public void setFontScale(float fontScale) {
        this.setFontScale(fontScale, fontScale);
    }

    public void setFontScale(float fontScaleX, float fontScaleY) {
        super.setFontScale(fontScaleX, fontScaleY);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public float getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(float shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    public float getShadowOffsetY() {
        return shadowOffsetY;
    }

    public void setShadowOffsetY(float shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
    }

    public ShadowOption getShadowOption() {
        return shadowOption;
    }

    public void setShadowOption(ShadowOption shadowOption) {
        this.shadowOption = shadowOption;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }


    private float dxs[] = {1, 1, -1, -1, 1, -1, 0, 0};
    private float dys[] = {1, -1, 1, -1, 0, 0, 1, -1};
    private float oldShadowAlpha, oldStrokeAlpha;

    public void draw(Batch batch, float parentAlpha) {
        switch (shadowOption) {
            case Disable: //关闭阴影特效
                drawLabel(batch, parentAlpha);
                break;
            case Projection: //投影效果
                oldShadowAlpha = shadowColor.a;
                shadowColor.a = getColor().a;
                fontCache.tint(shadowColor);
                fontCache.setPosition(getX() + shadowOffsetX, getY() + shadowOffsetY);
                fontCache.draw(batch);
                shadowColor.a = oldShadowAlpha;
                drawLabel(batch, parentAlpha);
                break;
            case Smear: //拖影效果
                float k = shadowOffsetY / shadowOffsetX;
                float offsetX = shadowOffsetX, offsetY = shadowOffsetY;
                float off = 0;
                if (offsetX > 0) off = -Math.abs(k);
                else off = Math.abs(k);

                oldShadowAlpha = shadowColor.a;
                shadowColor.a = getColor().a;
                while (Math.abs(offsetX) > 0 && Math.abs(offsetY) > 0) {
                    offsetX += off;
                    offsetY = k * offsetX;
                    if (isStroke) {
                        validate();
                        for (int i = 0; i < dxs.length; i++) {
                            fontCache.tint(shadowColor);
                            fontCache.setPosition(getX() + offsetX + dxs[i] * strokeWidth,
                                    getY() + offsetY + dys[i] * strokeWidth + strokeWidth);
                            fontCache.draw(batch);
                        }
                    } else {
                        fontCache.tint(shadowColor);
                        fontCache.setPosition(getX() + offsetX, getY() + offsetY);
                        fontCache.draw(batch);
                    }
                }
                shadowColor.a = oldShadowAlpha;
                drawLabel(batch, parentAlpha);
                break;

        }
    }

    public void act(float delta){
        super.act(delta);
        if(getActions().size>0){
            fontCache.tint(getColor());
        }
    }

    public void setBackground(Drawable drawable){
        getStyle().background=drawable;
    }


    public void drawLabel(Batch batch, float parentAlpha) {
        validate();
        if (getStyle().background != null) {
            batch.setColor(1,1,1,getColor().a);
            float padding=getHeight()*0.15f;
            float paddingX=getStyle().font.getSpaceWidth()*0.5f;
            getStyle().background.draw(batch, getX()-paddingX, getY(), getWidth()+paddingX*2, getHeight()+padding);
        }
        if (isStroke) {
            strokeColor.a = getColor().a;
            fontCache.tint(strokeColor);
            for (int i = 0; i < dxs.length; i++) {
                fontCache.setPosition(getX() + dxs[i] * strokeWidth, getY() + dys[i] * strokeWidth + strokeWidth);
                fontCache.draw(batch);
            }
            fontCache.setPosition(getX(), getY() + strokeWidth);
            fontCache.tint(getColor());
            fontCache.draw(batch);
        } else {
            fontCache.setPosition(getX(), getY() + strokeWidth);
            fontCache.tint(getColor());
            fontCache.draw(batch);
        }
    }
}
