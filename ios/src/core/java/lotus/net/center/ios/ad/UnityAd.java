package lotus.net.center.ios.ad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import lotus.net.center.ios.VIOSLauncher;
import lotus.net.center.ios.ad.unityads.UnityAds;
import lotus.net.center.ios.ad.unityads.UnityAdsBanner;
import lotus.net.center.ios.ad.unityads.UnityAdsBannerDelegateAdapter;
import lotus.net.center.ios.ad.unityads.UnityAdsBannerPosition;
import lotus.net.center.ios.ad.unityads.UnityAdsDelegateAdapter;
import lotus.net.center.ios.ad.unityads.UnityAdsError;
import lotus.net.center.ios.ad.unityads.UnityAdsFinishState;

public class UnityAd extends UnityAdsDelegateAdapter implements LotusAd{
    private VIOSLauncher launcher;
    private static String APPID = "2999604";
    private static String BannerID = "banner";
    private static String InterteristalID = "video";
    private static String RewardVideoADID = "rewardedVideo";//支持竖版出横版视频
    public UnityAd(VIOSLauncher viosLauncher){
        this.launcher = viosLauncher;
        APPID = launcher.getGame().info.app_ad_id;
        BannerID = launcher.getGame().info.banner_ad_id;
        InterteristalID = launcher.getGame().info.interstitial_ad_id;
        RewardVideoADID = launcher.getGame().info.rewardedVideo_ad_id;
        UnityAds.initialize(APPID,this,false);
        initInterstitialAd();
        initBannerAd();
        initRewardedVideo();
    }
    @Override
    public void initInterstitialAd() {
        loadInsertscreen();
    }

    @Override
    public void loadInsertscreen() {
        UnityAds.load(InterteristalID);
    }
    private UIViewController getUIViewController() {
        return ((IOSApplication) Gdx.app).getUIViewController();
    }
    @Override
    public void showInsertscreen() {
        if(UnityAds.isReady(InterteristalID)){
            UnityAds.show(getUIViewController(),InterteristalID);
        }else {
            loadInsertscreen();
        }
    }
    UIView bannerView;
    boolean isHead = true;
    @Override
    public void initBannerAd() {

    }
    boolean isLoad_banner = false;
    @Override
    public void addBanners(final boolean isHead) {
        if (bannerView == null) {
            if (isLoad_banner)
                return;
            isLoad_banner = true;
            UnityAdsBanner.loadBanner(BannerID);
            UnityAdsBanner.setBannerPosition(UnityAdsBannerPosition.kUnityAdsBannerPositionNone);
            UnityAdsBanner.setDelegate(new UnityAdsBannerDelegateAdapter(){
                @Override
                public void unityAdsBannerDidLoad(String placementId, UIView view) {
                    super.unityAdsBannerDidLoad(placementId, view);
                    bannerView = view;
                    ((IOSApplication) Gdx.app).getUIViewController().getView().addSubview(bannerView);
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
                public void unityAdsBannerDidUnload(String placementId) {
                    super.unityAdsBannerDidUnload(placementId);
                }

                @Override
                public void unityAdsBannerDidShow(String placementId) {
                    super.unityAdsBannerDidShow(placementId);
                }

                @Override
                public void unityAdsBannerDidHide(String placementId) {
                    super.unityAdsBannerDidHide(placementId);
                }

                @Override
                public void unityAdsBannerDidClick(String placementId) {
                    super.unityAdsBannerDidClick(placementId);
                }

                @Override
                public void unityAdsBannerDidError(String message) {
                    super.unityAdsBannerDidError(message);
                }
            });
        }else{
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
    }

    @Override
    public void removeRanners() {
        if(bannerView == null)
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

    @Override
    public void initRewardedVideo() {
        loadRewardedVideo();
    }

    @Override
    public void loadRewardedVideo() {
        UnityAds.load(RewardVideoADID);
    }
    private int movie_index;
    @Override
    public void showMovie(int id) {
        movie_index = id;
        if (UnityAds.isReady (RewardVideoADID)) {
            UnityAds.show(getUIViewController(),RewardVideoADID);
        }else{
            loadRewardedVideo();
        }
    }

    @Override
    public void adDispose() {

    }

    @Override
    public void unityAdsReady(String placementId) {
    }

    @Override
    public void unityAdsDidStart(String placementId) {
    }

    @Override
    public void unityAdsDidFinish(String placementId, UnityAdsFinishState finishState) {
        launcher.log.debug(placementId+":"+finishState);
        if(placementId.equals(RewardVideoADID)){
            if (finishState == UnityAdsFinishState.kUnityAdsFinishStateCompleted) {
                launcher.getGame().showMovie_return(movie_index);
                // Reward the user for watching the ad to completion.
            } else if (finishState == UnityAdsFinishState.kUnityAdsFinishStateSkipped) {
                // Do not reward the user for skipping the ad.
            } else if (finishState == UnityAdsFinishState.kUnityAdsFinishStateError) {
                // Log an error.
            }
            loadRewardedVideo();
        }
    }
    @Override
    public void unityAdsDidError(UnityAdsError error, String s) {
    }
}
