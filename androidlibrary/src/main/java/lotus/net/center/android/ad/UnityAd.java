package lotus.net.center.android.ad;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import lotus.net.center.android.VAndroidLauncher;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;
import com.unity3d.services.banners.view.BannerPosition;

public class UnityAd implements LotusAd , IUnityAdsListener {
    private VAndroidLauncher activity;
    private static String APPID = "2999605";
    private static String BannerID = "banner";
    private static String InterteristalID = "video";
    private static String RewardVideoADID = "rewardedVideo";//支持竖版出横版视频
    private DisplayMetrics dm;
    public UnityAd(VAndroidLauncher activity){
        this.activity = activity;
        APPID = activity.game.info.app_ad_id;
        BannerID = activity.game.info.banner_ad_id;
        InterteristalID = activity.game.info.interstitial_ad_id;
        RewardVideoADID = activity.game.info.rewardedVideo_ad_id;
        dm = new DisplayMetrics();
        initBannerAd(activity.relativeLayout);
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        UnityAds.initialize(activity, APPID, this,false);

    }
    @Override
    public void initInterstitialAd() {
        loadInsertscreen();
    }

    @Override
    public void loadInsertscreen() {
        UnityAds.load(InterteristalID);
    }

    @Override
    public void showInsertscreen() {
        if (UnityAds.isReady (InterteristalID)) {
            UnityAds.show(activity,InterteristalID);
        }else{
            loadInsertscreen();
        }
    }

    @Override
    public void initRewardedVideo() {
        loadRewardedVideo();
    }

    @Override
    public void loadRewardedVideo() {
        UnityAds.load(RewardVideoADID);
    }
    private View bannerView;
    private RelativeLayout bannerRelativeLayout;
    private boolean isLoad_banner;
    @Override
    public void initBannerAd(RelativeLayout bannerRelativeLayout) {
        isLoad_banner = false;
        this.bannerRelativeLayout = bannerRelativeLayout;
    }

    @Override
    public void dispose() {

    }
    @Override
    public void addBanners(final boolean isHead) {
        if (bannerView == null) {
            if (isLoad_banner)
                return;
            isLoad_banner = true;
            int h = Math.min(Math.max(dm.heightPixels,dm.widthPixels)/10,Math.min(dm.heightPixels,dm.widthPixels)/7);
            final RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(h/3*20, h);
            adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            UnityBanners.setBannerListener(new IUnityBannerListener() {
                @Override
                public void onUnityBannerLoaded(String s, View view) {
                    bannerView = view;
                    if (bannerView == null) {
                        return;
                    }

                    UnityAd.this.bannerRelativeLayout.addView(bannerView, adParams);
                    if (isHead) {
                        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
                    } else {
                        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    }
                    bannerView.setLayoutParams(adParams);
                    bannerView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onUnityBannerUnloaded(String s) {
                }

                @Override
                public void onUnityBannerShow(String s) {

                }

                @Override
                public void onUnityBannerClick(String s) {

                }

                @Override
                public void onUnityBannerHide(String s) {
                    removeRanners();
                }

                @Override
                public void onUnityBannerError(String s) {
                    isLoad_banner = false;
                }
            });
            UnityBanners.loadBanner (this.activity, BannerID);
            UnityBanners.setBannerPosition (BannerPosition.NONE);
        }else {
            RelativeLayout.LayoutParams labelParams = (RelativeLayout.LayoutParams) bannerView.getLayoutParams();
            if (isHead) {
                labelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                labelParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
            } else {
                labelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                labelParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            }
            bannerView.setLayoutParams(labelParams);
            bannerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void removeRanners() {
        if (bannerView != null)
            bannerView.setVisibility(View.GONE);
    }
    private int movie_index = 0;
    @Override
    public void showMovie(int id) {
        movie_index = id;
        if (UnityAds.isReady (RewardVideoADID)) {
            UnityAds.show(activity,RewardVideoADID);
        }else{
            loadRewardedVideo();
        }
    }

    @Override
    public void onUnityAdsReady(String placementId) {
    }

    @Override
    public void onUnityAdsStart(String s) {
    }

    @Override
    public void onUnityAdsFinish(String placementId, UnityAds.FinishState finishState) {
        if(placementId.equals(RewardVideoADID)){
            if (finishState == UnityAds.FinishState.COMPLETED) {
                activity.game.showMovie_return(movie_index);
                // Reward the user for watching the ad to completion.
            } else if (finishState == UnityAds.FinishState.SKIPPED) {
                // Do not reward the user for skipping the ad.
            } else if (finishState == UnityAds.FinishState.ERROR) {
                // Log an error.
            }
            loadRewardedVideo();
        }
    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
//        activity.game.app.showSomething("onUnityAdsError:"+s);
    }
}
