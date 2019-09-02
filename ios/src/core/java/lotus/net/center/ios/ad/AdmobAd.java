package lotus.net.center.ios.ad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.pods.google.mobileads.GADAdReward;
import org.robovm.pods.google.mobileads.GADAdSize;
import org.robovm.pods.google.mobileads.GADBannerView;
import org.robovm.pods.google.mobileads.GADBannerViewDelegateAdapter;
import org.robovm.pods.google.mobileads.GADInterstitial;
import org.robovm.pods.google.mobileads.GADInterstitialDelegateAdapter;
import org.robovm.pods.google.mobileads.GADMobileAds;
import org.robovm.pods.google.mobileads.GADRequest;
import org.robovm.pods.google.mobileads.GADRequestError;
import org.robovm.pods.google.mobileads.GADRewardBasedVideoAd;
import org.robovm.pods.google.mobileads.GADRewardBasedVideoAdDelegateAdapter;

import java.util.ArrayList;

import lotus.net.center.ios.VIOSLauncher;

public class AdmobAd implements LotusAd {
    private VIOSLauncher launcher;
    private static String APPID = "ca-app-pub-3940256099942544~1458002511";
    private static String BannerID = "ca-app-pub-3940256099942544/2934735716";
    private static String InterteristalID = "ca-app-pub-3940256099942544/4411468910";
    private static String RewardVideoADID = "ca-app-pub-3940256099942544/1712485313";//支持竖版出横版视频
    private GADRequest request;

    public AdmobAd(VIOSLauncher launcher) {
        this.launcher = launcher;
        APPID = launcher.getGame().info.app_ad_id;
        BannerID = launcher.getGame().info.banner_ad_id;
        InterteristalID = launcher.getGame().info.interstitial_ad_id;
        RewardVideoADID = launcher.getGame().info.rewardedVideo_ad_id;
        ArrayList<String> devices = new ArrayList<>();
        devices.add("b800648efecd2b69d12d080ab3f60676");
        request = new GADRequest();
        request.setTestDevices(devices);
        rootViewController = new UIViewController();
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.setRootViewController(rootViewController);
        GADMobileAds.configure(APPID);
        initInterstitialAd();
        initBannerAd();
        initRewardedVideo();
        launcher.log.debug("BannerID: " + BannerID);
    }

    private GADInterstitial interstitial;
    private UIWindow window;
    private UIViewController rootViewController;

    @Override
    public void initInterstitialAd() {
        interstitial = new GADInterstitial(InterteristalID);
        interstitial.setDelegate(new GADInterstitialDelegateAdapter() {
            @Override
            public void didDismissScreen(GADInterstitial ad) {
                launcher.log.debug("GADInterstitial: didDismissScreen");
                initInterstitialAd();
                window.setHidden(true);
            }

            @Override
            public void didFailToReceiveAd(GADInterstitial ad, GADRequestError error) {
                launcher.log.debug("GADInterstitial: didFailToReceiveAd");
            }

            @Override
            public void didReceiveAd(GADInterstitial ad) {
                launcher.log.debug("GADInterstitial: didReceiveAd");
            }

            @Override
            public void willPresentScreen(GADInterstitial ad) {
                launcher.log.debug("GADInterstitial: willPresentScreen");
            }

            @Override
            public void didFailToPresentScreen(GADInterstitial ad) {
                launcher.log.debug("GADInterstitial: didFailToPresentScreen");
            }

            @Override
            public void willDismissScreen(GADInterstitial ad) {
                launcher.log.debug("GADInterstitial: willDismissScreen");
            }

            @Override
            public void willLeaveApplication(GADInterstitial ad) {
                launcher.log.debug("GADInterstitial: willLeaveApplication");
            }
        });
        loadInsertscreen();
    }

    @Override
    public void loadInsertscreen() {

        interstitial.loadRequest(request);
    }

    @Override
    public void showInsertscreen() {
        if (interstitial.isReady()) {
            window.makeKeyAndVisible();
            interstitial.present(rootViewController);
        } else {
            loadInsertscreen();
        }
    }

    private GADBannerView bannerView;

    @Override
    public void initBannerAd() {
        launcher.log.debug("Initalizing ads...");
        bannerView = new GADBannerView(GADAdSize.Banner());
        bannerView.setAdUnitID(BannerID); //put your secret key here
        bannerView.setRootViewController(((IOSApplication) Gdx.app).getUIViewController());
        ((IOSApplication) Gdx.app).getUIViewController().getView().addSubview(bannerView);
        bannerView.setDelegate(new GADBannerViewDelegateAdapter() {
            @Override
            public void didReceiveAd(GADBannerView view) {
                super.didReceiveAd(view);
                launcher.log.debug("didReceiveAd");
            }

            @Override
            public void didFailToReceiveAd(GADBannerView view,
                                           GADRequestError error) {
                super.didFailToReceiveAd(view, error);
                launcher.log.debug("didFailToReceiveAd:" + error);
            }
        });
        bannerView.loadRequest(request);
        launcher.log.debug("Initalizing ads complete.");
    }

    @Override
    public void addBanners(boolean isHead) {
        if (bannerView == null)
            return;
        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        final CGSize adSize = bannerView.getBounds().getSize();
        double adWidth = adSize.getWidth();
        double adHeight = adSize.getHeight();
        launcher.log.debug(String.format("Showing ad. size[%s, %s]", adWidth, adHeight));
        double adX = (screenWidth / 2) - (adWidth / 2);
        double adY = screenHeight - adHeight;
        if (isHead)
            bannerView.setFrame(new CGRect(adX, 0, adWidth, adHeight));
        else
            bannerView.setFrame(new CGRect(adX, adY, adWidth, adHeight));
    }

    @Override
    public void removeRanners() {
        if (bannerView == null)
            return;
        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        double screenWidth = screenSize.getWidth();
        final CGSize adSize = bannerView.getBounds().getSize();
        double adWidth = adSize.getWidth();
        double adHeight = adSize.getHeight();
        launcher.log.debug(String.format("Hidding ad. size[%s, %s]", adWidth, adHeight));
        float bannerWidth = (float) screenWidth;
        bannerView.setFrame(new CGRect(0, -bannerWidth, adWidth, adHeight));
    }

    /**
     * The Gad reward based video ad.
     */
    GADRewardBasedVideoAd gadRewardBasedVideoAd;

    @Override
    public void initRewardedVideo() {
        gadRewardBasedVideoAd = GADRewardBasedVideoAd.getSharedInstance();
        gadRewardBasedVideoAd.loadRequest(request, RewardVideoADID);
        gadRewardBasedVideoAd.setDelegate(new GADRewardBasedVideoAdDelegateAdapter() {
            @Override
            public void didReceiveAd(GADRewardBasedVideoAd rewardBasedVideoAd) {
                launcher.log.debug("GADRewardBasedVideoAd: didReceiveAd");
            }

            @Override
            public void didOpen(GADRewardBasedVideoAd rewardBasedVideoAd) {
                launcher.log.debug("GADRewardBasedVideoAd: didOpen");
            }

            @Override
            public void didStartPlaying(GADRewardBasedVideoAd rewardBasedVideoAd) {
                launcher.log.debug("GADRewardBasedVideoAd: didStartPlaying");
            }

            @Override
            public void rewardBasedVideoAdDidCompletePlaying(GADRewardBasedVideoAd rewardBasedVideoAd) {
                launcher.log.debug("GADRewardBasedVideoAd: rewardBasedVideoAdDidCompletePlaying");

            }

            @Override
            public void didFailToLoad(GADRewardBasedVideoAd rewardBasedVideoAd, NSError error) {
                launcher.log.debug("GADRewardBasedVideoAd: didFailToLoad:" + error);
//                gadRewardBasedVideoAd.loadRequest(new GADRequest(), RewardVideoADID);
            }

            @Override
            public void didClose(GADRewardBasedVideoAd rewardBasedVideoAd) {
                launcher.log.debug("GADRewardBasedVideoAd: didClose");
                loadRewardedVideo();
            }

            @Override
            public void willLeaveApplication(GADRewardBasedVideoAd rewardBasedVideoAd) {
                launcher.log.debug("GADRewardBasedVideoAd: willLeaveApplication");
            }

            @Override
            public void didRewardUser(GADRewardBasedVideoAd rewardBasedVideoAd, GADAdReward reward) {
                launcher.log.debug("GADRewardBasedVideoAd:---------didRewardUser");
                launcher.getGame().showMovie_return(movie_index);
            }
        });
    }

    @Override
    public void loadRewardedVideo() {
        gadRewardBasedVideoAd.loadRequest(request, RewardVideoADID);
        window.setHidden(true);
    }

    private int movie_index = 0;

    @Override
    public void showMovie(int id) {
        movie_index = id;
        if (gadRewardBasedVideoAd.isReady()) {
            window.makeKeyAndVisible();
            gadRewardBasedVideoAd.present(rootViewController);
        } else {
            loadRewardedVideo();
        }
    }

    @Override
    public void adDispose() {

    }
}
