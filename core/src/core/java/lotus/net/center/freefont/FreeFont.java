package lotus.net.center.freefont;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import lotus.net.center.myclass.M;
import lotus.net.center.myclass.LGame;

public class FreeFont {
	private LGame game;
	private final HashMap<String, FreeBitmapFont> fonts = new HashMap<String, FreeBitmapFont>();// 字体列表
	public int fontSize = 30;

	public FreeFont(LGame game) {
		this.game = game;
		FreeBitmapFont font = new FreeBitmapFont(this.game, new FreePaint(getDefaultFontSize()));
		fonts.put("font", font);
	}

	/**
	 * 设置默认的font字大小
	 *
	 * @param fontSize
	 */
	public void setDefaultFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getDefaultFontSize() {
		return fontSize;
	}

	/**
	 * 获取当前程序开销
	 */
	public String getHeap() {
		return "F:" + Gdx.graphics.getFramesPerSecond() + "-J:" + ((Gdx.app.getNativeHeap() * 10) >> 20) / 10f + "m"
				+ "/" + ((Gdx.app.getJavaHeap() * 10) >> 20) / 10f + "m";
	}

	/**
	 * font
	 */
	public FreeBitmapFont getFont() {
		return fonts.get("font");
	}

	/**
	 * font
	 */
	public FreeBitmapFont getFont(String key) {
		FreeBitmapFont font = fonts.get(key);
		if (font == null) {
			font = new FreeBitmapFont(this.game, new FreePaint(getDefaultFontSize()));
//			font.appendText("01234567890LoadingC" + getHeap());
			fonts.put(key, font);
		}
		return font;
	}
	
	/**
	 * font
	 */
	public FreeBitmapFont getFont(int fontSize) {
		FreeBitmapFont font = fonts.get(String.format(M.F_FONT_, fontSize));
		if (font == null) {
			font = new FreeBitmapFont(this.game, new FreePaint(fontSize));
//			font.appendText("01234567890LoadingC" + getHeap());
			fonts.put(String.format(M.F_FONT_, fontSize), font);
			
		}
		return font;
	}
	

	/**
	 * 返回font
	 */
	public void setFont(String key, FreeBitmapFont font) {
		fonts.put(key, font);
	}

	/**
	 * 创建Label
	 */
	public VLabel getLabel() {
		return getLabel("");
	}

	/**
	 * 创建Label
	 */
	public VLabel getLabel(String text) {
		return getLabel(text, "font");
	}

	/**
	 * 创建Label
	 */
	public VLabel getLabel(String text, String fontName) {
		return getLabel(text, getLabelStyle(getFont(fontName)));
	}
	
	public VLabel getLabel(String text, int fontSize) {
		return getLabel(text, getLabelStyle(getFont(fontSize)));
	}

	/**
	 * 创建Label
	 */
	public VLabel getLabel(String text, FreeBitmapFont font) {
		return getLabel(text, getLabelStyle(font));
	}

	/**
	 * 创建Label
	 */
	public VLabel getLabel(String text, LabelStyle style) {
		VLabel label = new VLabel(text, style);
		return label;
	}

	public VLabel getLabel(String fontString, Color color, String text) {
		return getLabel(text, getLabelStyle(getFont(fontString), color));
	}

	public VLabel getLabel(Color color, String text) {
		return getLabel(text, getLabelStyle(getFont(), color));
	}

	/**
	 * 创建LabelStyle
	 */
	public LabelStyle getLabelStyle(FreeBitmapFont font) {
		return new LabelStyle(font, Color.WHITE);
	}

	/**
	 * 创建LabelStyle
	 */
	public LabelStyle getLabelStyle(FreeBitmapFont font, Color color) {
		return new LabelStyle(font, color);
	}

	/**
	 * 创建LabelStyle
	 */
	public LabelStyle getLabelStyle(String key) {
		return new LabelStyle(getFont(key), Color.WHITE);
	}

}
