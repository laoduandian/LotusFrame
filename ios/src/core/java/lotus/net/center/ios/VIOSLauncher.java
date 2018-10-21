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
import org.robovm.apple.foundation.NSObject;
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
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.pods.google.GGLContext;
import org.robovm.pods.google.mobileads.GADAdReward;
import org.robovm.pods.google.mobileads.GADAdSize;
import org.robovm.pods.google.mobileads.GADBannerView;
import org.robovm.pods.google.mobileads.GADBannerViewDelegateAdapter;
import org.robovm.pods.google.mobileads.GADInterstitial;
import org.robovm.pods.google.mobileads.GADInterstitialDelegate;
import org.robovm.pods.google.mobileads.GADMobileAds;
import org.robovm.pods.google.mobileads.GADRequest;
import org.robovm.pods.google.mobileads.GADRequestError;
import org.robovm.pods.google.mobileads.GADRewardBasedVideoAd;
import org.robovm.pods.google.mobileads.GADRewardBasedVideoAdDelegate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import lotus.net.center.freefont.FreePaint;
import lotus.net.center.freefont.TTFParser;
import lotus.net.center.ios.gamecenter.GameCenterListener;
import lotus.net.center.ios.gamecenter.GameCenterManager;
import lotus.net.center.myclass.App;
import lotus.net.center.myclass.LGame;

public abstract class VIOSLauncher extends IOSApplication.Delegate implements
        App ,GADRewardBasedVideoAdDelegate,GADInterstitialDelegate {
    private GameCenterManager gcManager;
    private LGame game;
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
    public void newgame(String address) {
        Gdx.net.openURI(String.format("%s%s","https://itunes.apple.com/cn/app/id",address));
    }

    @Override
    public void moreGame() {
        Gdx.net.openURI("http://www.lotusstudio.top");
    }
    /**
     * Initialise gamecenter manager.
     */
    public GameCenterManager getGcManager() {
        if (null == gcManager) {

            gcManager = new GameCenterManager(UIApplication.getSharedApplication().getKeyWindow(),
                    new GameCenterListener(){

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
        }
        return gcManager;
    }
    @Override
    public void didReceiveAd(GADInterstitial ad) {
        log.debug("GADInterstitial: didReceiveAd");
    }

    @Override
    public void didFailToReceiveAd(GADInterstitial ad, GADRequestError error) {
        log.debug("GADInterstitial: didFailToReceiveAd");
    }

    @Override
    public void willPresentScreen(GADInterstitial ad) {
        log.debug("GADInterstitial: willPresentScreen");
    }

    @Override
    public void didFailToPresentScreen(GADInterstitial ad) {
        log.debug("GADInterstitial: didFailToPresentScreen");
    }

    @Override
    public void willDismissScreen(GADInterstitial ad) {
        log.debug("GADInterstitial: willDismissScreen");
    }


    @Override
    public void willLeaveApplication(GADInterstitial ad) {
        log.debug("GADInterstitial: willLeaveApplication");
    }

    @Override
    public boolean shouldChangeAudioSessionToCategory(NSObject ad, String audioSessionCategory) {
        return false;
    }

    @Override
    public void didReceiveAd(GADRewardBasedVideoAd rewardBasedVideoAd) {
        log.debug("GADRewardBasedVideoAd: didReceiveAd");
    }

    @Override
    public void didOpen(GADRewardBasedVideoAd rewardBasedVideoAd) {
        log.debug("GADRewardBasedVideoAd: didOpen");
    }

    @Override
    public void didStartPlaying(GADRewardBasedVideoAd rewardBasedVideoAd) {
        log.debug("GADRewardBasedVideoAd: didStartPlaying");
    }
    @Override
    public void showSomething(String a) {

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

    public void setGame(LGame game) {
        this.game = game;
    }

    public LGame getGame() {
        return game;
    }
    private GADBannerView bannerView;

    GADRewardBasedVideoAd gadRewardBasedVideoAd;
    private UIWindow window;
    private UIViewController rootViewController;
    GADRequest request = new GADRequest();


    private GADInterstitial interstitial;

    public void initializeBanner() {
        log.debug("Initalizing ads...");
        bannerView = new GADBannerView(GADAdSize.Banner());
        bannerView.setAdUnitID(this.getGame().info.banner_ad_id); //put your secret key here
        bannerView.setRootViewController( ((IOSApplication)Gdx.app).getUIViewController());
        ((IOSApplication)Gdx.app).getUIViewController().getView().addSubview(bannerView);
        bannerView.setDelegate(new GADBannerViewDelegateAdapter() {
            @Override
            public void didReceiveAd(GADBannerView view) {
                super.didReceiveAd(view);
                log.debug("didReceiveAd");
            }

            @Override
            public void didFailToReceiveAd(GADBannerView view,
                                           GADRequestError error) {
                super.didFailToReceiveAd(view, error);
                log.debug("didFailToReceiveAd:" + error);
            }
        });
        bannerView.loadRequest(request);
        log.debug("Initalizing ads complete.");
    }
    public void initializeRewardVideoAd() {
        gadRewardBasedVideoAd = GADRewardBasedVideoAd.getSharedInstance();
        gadRewardBasedVideoAd.loadRequest(request, this.getGame().info.rewardedVideo_ad_id);
        gadRewardBasedVideoAd.setDelegate(this);
    }
    public void intializeInterstitial() {
        interstitial = new GADInterstitial(this.getGame().info.interstitial_ad_id);
        interstitial.setDelegate(this);
        loadInsertscreen();
    }

    @Override
    public void pinfen() {
        String url =  String.format("https://itunes.apple.com/cn/app/duel-of-clans/id%s?mt=8", this.getGame().info.game_Address);
        Gdx.net.openURI(url);
    }


    @Override
    public void addBanners() {
        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        double screenWidth = screenSize.getWidth();
        double  screenHeight= screenSize.getHeight();
        final CGSize adSize = bannerView.getBounds().getSize();
        double adWidth = adSize.getWidth();
        double adHeight = adSize.getHeight();
        log.debug(String.format("Showing ad. size[%s, %s]", adWidth, adHeight));
        double adX = (screenWidth / 2) - (adWidth / 2);
        double adY = screenHeight - adHeight;
        bannerView.setFrame(new CGRect(adX, adY, adWidth, adHeight));
    }

    @Override
    public void removeRanners() {
        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        double screenWidth = screenSize.getWidth();
        double  screenHeight= screenSize.getHeight();
        final CGSize adSize = bannerView.getBounds().getSize();
        double adWidth = adSize.getWidth();
        double adHeight = adSize.getHeight();
        log.debug(String.format("Hidding ad. size[%s, %s]", adWidth, adHeight));
        float bannerWidth = (float) screenWidth;
        float bannerHeight = (float) (bannerWidth / adWidth * adHeight);
        bannerView.setFrame(new CGRect(0, -bannerWidth, adWidth, adHeight));
    }


    @Override
    public void showInterstitialAd() {
        if(interstitial.isReady()) {
            window.makeKeyAndVisible();
            interstitial.present(rootViewController);
        }
    }
    @Override
    public void loadInsertscreen() {
        interstitial.loadRequest(request);
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
    public void initializeAD() {
        rootViewController = new UIViewController();
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.setRootViewController(rootViewController);
        GADMobileAds.configure(getGame().info.app_ad_id);
        initializeBanner();
        initializeRewardVideoAd();
        intializeInterstitial();
    }
    private int movie_index = 0;
    @Override
    public void showMovie(int id) {
        movie_index = id;
        if(gadRewardBasedVideoAd.isReady()) {
            window.makeKeyAndVisible();
            gadRewardBasedVideoAd.present(rootViewController);
            getGame().pause();
        }
    }
    @Override
    public void didDismissScreen(GADInterstitial ad) {
        log.debug("GADInterstitial: didDismissScreen");
        intializeInterstitial();
        window.setHidden(true);
    }
    @Override
    public void didFailToLoad(GADRewardBasedVideoAd rewardBasedVideoAd, NSError error) {
        log.debug("GADRewardBasedVideoAd: didFailToLoad:"+error);
        gadRewardBasedVideoAd.loadRequest(request, this.getGame().info.rewardedVideo_ad_id);
    }
    @Override
    public void didClose(GADRewardBasedVideoAd rewardBasedVideoAd) {
        log.debug("GADRewardBasedVideoAd: didClose");
        gadRewardBasedVideoAd.loadRequest(request, this.getGame().info.rewardedVideo_ad_id);
        window.setHidden(true);
        getGame().resume();
    }

    @Override
    public void willLeaveApplication(GADRewardBasedVideoAd rewardBasedVideoAd) {
        log.debug("GADRewardBasedVideoAd: willLeaveApplication");
    }

    @Override
    public void didRewardUser(GADRewardBasedVideoAd rewardBasedVideoAd, GADAdReward reward) {
        log.debug("GADRewardBasedVideoAd: didRewardUser");
        getGame().resume();
        getGame().showMovie_return(movie_index);
    }
}

