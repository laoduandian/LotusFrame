package lotus.net.center.ios;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Logger;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSBundle;
import org.robovm.apple.foundation.NSData;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSErrorException;
import org.robovm.apple.foundation.NSMutableAttributedString;
import org.robovm.apple.foundation.NSNumber;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.gamekit.GKAchievement;
import org.robovm.apple.gamekit.GKLeaderboard;
import org.robovm.apple.uikit.NSAttributedStringAttribute;
import org.robovm.apple.uikit.NSUnderlineStyle;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UIGraphics;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UILabel;
import org.robovm.pods.google.GGLContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import lotus.net.center.freefont.FreePaint;
import lotus.net.center.freefont.TTFParser;
import lotus.net.center.ios.ad.AdCentre;
import lotus.net.center.ios.ad.unityads.UnityAdsDelegate;
import lotus.net.center.ios.ad.unityads.UnityAdsError;
import lotus.net.center.ios.ad.unityads.UnityAdsFinishState;
import lotus.net.center.ios.gamecenter.GameCenterListener;
import lotus.net.center.ios.gamecenter.GameCenterManager;
import lotus.net.center.myclass.App;
import lotus.net.center.myclass.LGame;
import lotus.net.center.net.AppItem;

/**
 * The type Vios launcher.
 */
public abstract class VIOSLauncher extends IOSApplication.Delegate implements App {
    private GameCenterManager gcManager;
    private LGame game;
    private AdCentre adCentre;
    /**
     * The Log.
     */
    public final Logger log = new Logger(VIOSLauncher.class.getName(), Application.LOG_DEBUG);


    private UIColor getColor(Color color) {
        return UIColor.fromRGBA(color.r, color.g, color.b, color.a);
    }

    private HashMap<String, UIFont> fonts;

    public Pixmap getFontPixmap(String strings, FreePaint vpaint) {
        if (fonts == null)
            fonts = new HashMap<String, UIFont>();
        UIFont font = fonts.get(vpaint.getName());
        if (font == null) {
            if (vpaint.getTTFName().equals("")) {
                if (vpaint.getFakeBoldText() || vpaint.getStrokeColor() != null) {
                    font = UIFont.getBoldSystemFont(vpaint.getTextSize());
                } else {
                    font = UIFont.getSystemFont(vpaint.getTextSize());
                }
            } else {
                TTFParser parser = new TTFParser();
                try {
                    parser.parse(NSBundle.getMainBundle().getResourcePath()
                            + "/"
                            + vpaint.getTTFName()
                            + (vpaint.getTTFName().endsWith(".ttf") ? ""
                            : ".ttf"));
                    font = UIFont.getFont(parser.getFontPSName(),
                            vpaint.getTextSize());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fonts.put(vpaint.getName(), font);
        }
        try {
            NSString string = new NSString(strings);
            CGSize dim = string.getSize(font);
            UILabel label = new UILabel(new CGRect(0, 0, dim.getWidth(),
                    dim.getHeight()));
            UILabel label2 = null;// 描边层
            label.setText(strings);
            label.setBackgroundColor(UIColor.fromRGBA(1, 1, 1, 0));
            label.setTextColor(getColor(vpaint.getColor()));
            label.setFont(font);
            label.setOpaque(false);
            label.setAlpha(1);
            NSRange range = new NSRange(0, strings.length());
            NSMutableAttributedString mutableString = new NSMutableAttributedString(
                    strings);
            mutableString.addAttribute(
                    NSAttributedStringAttribute.ForegroundColor,
                    getColor(vpaint.getColor()), range);
            if (vpaint.getStrokeColor() != null) {
                label2 = new UILabel(new CGRect(0, 0, dim.getWidth(),
                        dim.getHeight()));
                label2.setText(strings);
                label2.setBackgroundColor(UIColor.fromRGBA(1, 1, 1, 0));
                label2.setTextColor(getColor(vpaint.getColor()));
                label2.setFont(font);
                label2.setOpaque(false);
                label2.setAlpha(1);
                NSMutableAttributedString mutableString2 = new NSMutableAttributedString(
                        strings);
                mutableString2.addAttribute(
                        NSAttributedStringAttribute.StrokeColor,
                        getColor(vpaint.getStrokeColor()), range);
                mutableString2.addAttribute(
                        NSAttributedStringAttribute.StrokeWidth,
                        NSNumber.valueOf(vpaint.getStrokeWidth()), range);
                label2.setAttributedText(mutableString2);
            } else if (vpaint.getUnderlineText() == true) {
                mutableString.addAttribute(
                        NSAttributedStringAttribute.UnderlineStyle,
                        NSNumber.valueOf(NSUnderlineStyle.StyleSingle.value()),
                        range);
            } else if (vpaint.getStrikeThruText() == true) {
                mutableString
                        .addAttribute(
                                NSAttributedStringAttribute.StrikethroughStyle,
                                NSNumber.valueOf(NSUnderlineStyle.StyleSingle
                                        .value()
                                        | NSUnderlineStyle.PatternSolid.value()),
                                range);
            }
            label.setAttributedText(mutableString);
            UIGraphics.beginImageContext(dim, false, 1);
            label.getLayer().render(UIGraphics.getCurrentContext());
            if (vpaint.getStrokeColor() != null) {
                label2.getLayer().render(UIGraphics.getCurrentContext());
            }
            UIImage image = UIGraphics.getImageFromCurrentImageContext();
            UIGraphics.endImageContext();
            NSData nsData = image.toPNGData();
            Pixmap pixmap = new Pixmap(nsData.getBytes(), 0,
                    nsData.getBytes().length);
            return pixmap;
        } catch (Exception ex) {
            return getFontPixmap(" ", vpaint);
        }
    }

    @Override
    public void newgame(AppItem appItem) {
        Gdx.net.openURI(String.format("%s%s", "https://itunes.apple.com/cn/app/id", appItem.getAppAddress()));
    }

    @Override
    public void moreGame() {
        Gdx.net.openURI("http://www.lotusstudio.top");
    }

    @Override
    public void didBecomeActive(UIApplication application) {
        super.didBecomeActive(application);
        getGcManager();
    }

    /**
     * Initialise gamecenter manager.
     *
     * @return the gc manager
     */
    public GameCenterManager getGcManager() {
        if (null == gcManager) {
            gcManager = new GameCenterManager(UIApplication.getSharedApplication().getKeyWindow(),
                    new GameCenterListener() {

                        @Override
                        public void achievementReportCompleted() {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void achievementReportFailed(
                                NSError arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void achievementViewDismissed() {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void achievementsLoadCompleted(
                                ArrayList<GKAchievement> arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void achievementsLoadFailed(
                                NSError arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void achievementsResetCompleted() {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void achievementsResetFailed(
                                NSError arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void leaderboardViewDismissed() {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void leaderboardsLoadCompleted(
                                ArrayList<GKLeaderboard> arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void leaderboardsLoadFailed(
                                NSError arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void playerLoginCompleted() {
                            // TODO Auto-generated method stub
                            gcManager.loadLeaderboards();

                        }

                        @Override
                        public void playerLoginFailed(
                                NSError arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void scoreReportCompleted() {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void scoreReportFailed(
                                NSError arg0) {

                        }


                    });
            gcManager.login();
        }
        return gcManager;
    }


    @Override
    public void showSomething(String a) {
        log.debug(a);
    }

    @Override
    public void paihang() {
        getGcManager().showLeaderboardsView();
    }

    @Override
    public void shangchuan(String name, int a) {
        getGcManager().reportScore(name, a);
    }

    @Override
    public void shangchuan(String name, float a) {

    }

    @Override
    public void outGame() {
        Gdx.net.openURI("http://www.lotusstudio.top");
    }


    @Override
    public void share() {

    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(LGame game) {
        this.game = game;
        this.game.setApp(this);
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public LGame getGame() {
        return game;
    }

    @Override
    public void showInterstitialAd() {
        if(adCentre!=null)
            adCentre.showInsertscreen();
    }


    @Override
    public void loadInsertscreen() {
        if(adCentre!=null)
            adCentre.loadInsertscreen();
    }

    @Override
    public void pinfen() {
        String url = String.format("https://itunes.apple.com/cn/app/duel-of-clans/id%s?mt=8", this.getGame().info.game_Address);
        Gdx.net.openURI(url);
    }

    @Override
    public void addBanners(boolean isHead) {
        if(adCentre!=null)
            adCentre.addBanners(isHead);
    }

    @Override
    public void removeRanners() {
        if(adCentre!=null)
            adCentre.removeRanners();
    }

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        try {
            GGLContext.getSharedInstance().configure();
        } catch (NSErrorException e) {
            System.err.println("Error configuring the Google context: " + e.getError());
        }
        return super.didFinishLaunching(application, launchOptions);
    }

    @Override
    public void initAD() {
        adCentre = new AdCentre(this);
//        UnityAds.initialize("2999604",this,true);
    }


    @Override
    public void showMovie(int id) {
        if(adCentre!=null)
            adCentre.showMovie(id);
    }
}

