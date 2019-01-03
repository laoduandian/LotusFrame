package lotus.net.center.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.AttributedString;
import java.util.HashMap;
import javax.imageio.ImageIO;
import lotus.net.center.freefont.FreePaint;

import lotus.net.center.myclass.App;
import lotus.net.center.myclass.LGame;

public abstract class VDesktopLauncher implements App {
    protected static LGame game;

    public Pixmap getFontPixmap(String txt, FreePaint vpaint) {
        Font font = getFont(vpaint);
        FontMetrics fm = metrics.get(vpaint.getName());
        int strWidth = fm.stringWidth(txt);
        int strHeight = fm.getAscent() + fm.getDescent();
        if (strWidth == 0) {
            strWidth = strHeight = vpaint.getTextSize();
        }
        BufferedImage bi = new BufferedImage(strWidth, strHeight,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font);
        if (vpaint.getStrokeColor() != null) {
            // 描边
            GlyphVector v = font.createGlyphVector(fm.getFontRenderContext(),
                    txt);
            Shape shape = v.getOutline();
            g.setColor(getColor(vpaint.getColor()));
            g.translate(0, fm.getAscent());
            g.fill(shape);
            g.setStroke(new BasicStroke(vpaint.getStrokeWidth()));
            g.setColor(getColor(vpaint.getStrokeColor()));
            g.draw(shape);
        } else if (vpaint.getUnderlineText() == true) {
            // 下划线
            AttributedString as = new AttributedString(txt);
            as.addAttribute(TextAttribute.FONT, font);
            as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            g.setColor(getColor(vpaint.getColor()));
            g.drawString(as.getIterator(), 0, fm.getAscent());
        } else if (vpaint.getStrikeThruText() == true) {
            // 删除线
            AttributedString as = new AttributedString(txt);
            as.addAttribute(TextAttribute.FONT, font);
            as.addAttribute(TextAttribute.STRIKETHROUGH,
                    TextAttribute.STRIKETHROUGH_ON);
            g.setColor(getColor(vpaint.getColor()));
            g.drawString(as.getIterator(), 0, fm.getAscent());
        } else {
            // 普通
            g.setColor(getColor(vpaint.getColor()));
            g.drawString(txt, 0, fm.getAscent());
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pixmap pixmap = new Pixmap(buffer.toByteArray(), 0,
                buffer.toByteArray().length);
        return pixmap;
    }

    private HashMap<String, Font> fonts = new HashMap<String, Font>();
    private HashMap<String, FontMetrics> metrics = new HashMap<String, FontMetrics>();

    private Font getFont(FreePaint vpaint) {
        boolean isBolo = vpaint.getFakeBoldText()
                || vpaint.getStrokeColor() != null;
        Font font = fonts.get(vpaint.getName());
        if (font == null) {
            if (vpaint.getTTFName().equals("")) {
                font = new Font(null, isBolo ? Font.BOLD : Font.PLAIN,
                        vpaint.getTextSize());
            } else {
                try {
                    ByteArrayInputStream in = new ByteArrayInputStream(
                            Gdx.files.internal(
                                    vpaint.getTTFName()
                                            + (vpaint.getTTFName().endsWith(
                                            ".ttf") ? "" : ".ttf"))
                                    .readBytes());
                    BufferedInputStream fb = new BufferedInputStream(in);
                    font = Font.createFont(Font.TRUETYPE_FONT, fb).deriveFont(
                            Font.BOLD, vpaint.getTextSize());
                    fb.close();
                    in.close();
                } catch (FontFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            fonts.put(vpaint.getName(), font);
            BufferedImage bi = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = bi.createGraphics();
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            metrics.put(vpaint.getName(), fm);
        }
        return font;
    }

    public void setGame(LGame game) {
        this.game = game;
        game.setApp(this);
    }

    private java.awt.Color getColor(Color libColor) {
        return new java.awt.Color(libColor.r, libColor.g, libColor.b, libColor.a);
    }

    @Override
    public void newgame(String address) {
        Gdx.net.openURI(String.format("%s%s","https://itunes.apple.com/cn/app/id",address));
    }

    @Override
    public void moreGame() {
        Gdx.net.openURI("http://www.lotusstudio.top");
    }

    @Override
    public void pinfen() {
        Gdx.net.openURI(String.format("%s%s","https://itunes.apple.com/cn/app/id",game.info.game_Address));
    }
    @Override
    public void paihang() {
        Gdx.app.log(getClass().getName(),"排行榜");
    }

    @Override
    public void addBanners(boolean isHead) {
        Gdx.app.log(getClass().getName(),String.format("%s_%s_%s","显示广告条",this.game.info.app_ad_id,this.game.info.banner_ad_id));
    }

    @Override
    public void removeRanners() {
        Gdx.app.log(getClass().getName(),String.format("%s_%s_%s","移除广告条",this.game.info.app_ad_id,this.game.info.banner_ad_id));
    }

    @Override
    public void outGame() {
        Gdx.app.log(getClass().getName(),"outGame");
    }


    @Override
    public void showSomething(String a) {
        Gdx.app.log(getClass().getName(),String.format("%s_%s","显示信息",a));
    }



    @Override
    public void shangchuan(String name, int a) {
        Gdx.app.log(getClass().getName(),String.format("%s_%d","shangchuan_int",a));
    }

    @Override
    public void shangchuan(String name, float a) {
        Gdx.app.log(getClass().getName(),String.format("%s_%f","shangchuan_int",a));
    }


    @Override
    public void showInterstitialAd() {
        Gdx.app.log(getClass().getName(), String.format("%s_%s_%s_显示间隔_%d","显示插屏",this.game.info.app_ad_id,this.game.info.interstitial_ad_id,this.game.info.interstitial_ad_condition_num));
    }

    @Override
    public void share() {
        Gdx.app.log(getClass().getName(),"share");
    }

    @Override
    public void loadInsertscreen() {
        Gdx.app.log(getClass().getName(),String.format("%s_%s_%s_显示间隔_%d","加载插屏",this.game.info.app_ad_id,this.game.info.interstitial_ad_id,this.game.info.interstitial_ad_condition_num));
    }


    @Override
    public void showMovie(int id) {
        Gdx.app.log(getClass().getName(),String.format("%s_%s_%s_标记_%d","显示视频",this.game.info.app_ad_id,this.game.info.rewardedVideo_ad_id,id));
    }

    @Override
    public void initAD() {
        Gdx.app.log(getClass().getName(),"initAd");
    }
}
