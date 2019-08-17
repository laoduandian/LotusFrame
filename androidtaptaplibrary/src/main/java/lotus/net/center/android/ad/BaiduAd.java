package lotus.net.center.android.ad;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.mobad.video.XAdManager;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.baidu.mobads.InterstitialAd;
import com.baidu.mobads.InterstitialAdListener;
import com.baidu.mobads.rewardvideo.RewardVideoAd;

import org.json.JSONObject;

import lotus.net.center.android.VAndroidLauncher;

public class BaiduAd implements LotusAd{
    private VAndroidLauncher activity;
    private static String APPID = "e866cfb0";
    private static String BannerID = "2015351";
    private static String InterteristalID = "2403633";
    private static String RewardVideoADID = "5925490";//支持竖版出横版视频
    private DisplayMetrics dm;
    public BaiduAd(VAndroidLauncher activity){
        this.activity = activity;
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        APPID = activity.game.info.app_ad_id;
        BannerID = activity.game.info.banner_ad_id;
        InterteristalID = activity.game.info.interstitial_ad_id;
        RewardVideoADID = activity.game.info.rewardedVideo_ad_id;
        XAdManager.getInstance(activity).setAppSid(APPID);
        AdView.setAppSid(activity,APPID);
        initInterstitialAd();
        initBannerAd(activity.relativeLayout);
        initRewardedVideo();

    }
    InterstitialAd interAd;
    @Override
    public void initInterstitialAd() {
        interAd = new InterstitialAd(activity, InterteristalID);
        interAd.setListener(new InterstitialAdListener() {

            @Override
            public void onAdClick(InterstitialAd arg0) {
                Log.i("InterstitialAd", "onAdClick");
            }

            @Override
            public void onAdDismissed() {
                Log.i("InterstitialAd", "onAdDismissed");
                loadInsertscreen();
            }

            @Override
            public void onAdFailed(String arg0) {
                Log.i("InterstitialAd", "onAdFailed");
            }

            @Override
            public void onAdPresent() {
                Log.i("InterstitialAd", "onAdPresent");
            }

            @Override
            public void onAdReady() {
                Log.i("InterstitialAd", "onAdReady");
            }

        });
        loadInsertscreen();
    }

    @Override
    public void loadInsertscreen() {
        interAd.loadAd();
    }

    @Override
    public void showInsertscreen() {
        if (interAd.isAdReady()) {
            interAd.showAd(activity);
        } else {
            loadInsertscreen();
        }
    }
    public static final String TAG = "RewardVideoActivity";
    RewardVideoAd mRewardVideoAd;
    @Override
    public void initRewardedVideo() {
        mRewardVideoAd = new RewardVideoAd(activity, RewardVideoADID, new RewardVideoAd.RewardVideoAdListener() {
            @Override
            public void onVideoDownloadSuccess() {
                // 视频缓存成功
                // 说明：如果想一定走本地播放，那么收到该回调之后，可以调用show
                Log.i(TAG, "onVideoDownloadSuccess,isReady=" + mRewardVideoAd.isReady());
            }

            @Override
            public void onVideoDownloadFailed() {
                // 视频缓存失败，如果想走本地播放，可以在这儿重新load下一条广告，最好限制load次数（4-5次即可）。
                Log.i(TAG, "onVideoDownloadFailed");
                loadRewardedVideo();
            }

            @Override
            public void playCompletion() {
                // 播放完成回调，媒体可以在这儿给用户奖励
                Log.i(TAG, "playCompletion");
                activity.game.showMovie_return(movie_index);
            }

            @Override
            public void onAdShow() {
                // 视频开始播放时候的回调
                Log.i(TAG, "onAdShow");
            }

            @Override
            public void onAdClick() {
                // 广告被点击的回调
                Log.i(TAG, "onAdClick");
            }

            @Override
            public void onAdClose(float playScale) {
                // 用户关闭了广告
                // 说明：关闭按钮在mssp上可以动态配置，媒体通过mssp配置，可以选择广告一开始就展示关闭按钮，还是播放结束展示关闭按钮
                // 建议：收到该回调之后，可以重新load下一条广告,最好限制load次数（4-5次即可）
                // playScale[0.0-1.0],1.0表示播放完成，媒体可以按照自己的设计给予奖励
                Log.i(TAG, "onAdClose" + playScale);
                loadRewardedVideo();
            }

            @Override
            public void onAdFailed(String arg0) {
                // 广告失败回调 原因：广告内容填充为空；网络原因请求广告超时
                // 建议：收到该回调之后，可以重新load下一条广告，最好限制load次数（4-5次即可）
                Log.i(TAG, "onAdFailed");
                loadRewardedVideo();

            }
        }, false);
        loadRewardedVideo();
    }

    @Override
    public void loadRewardedVideo() {
        mRewardVideoAd.load();
    }
    AdView adView;
    private boolean close_banner;
    private RelativeLayout bannerContainer;
    @Override
    public void initBannerAd(RelativeLayout bannerRelativeLayout) {
        close_banner = false;
        this.bannerContainer = bannerRelativeLayout;
        adView = new AdView(activity, BannerID);
        // 设置监听器
        adView.setListener(new AdViewListener() {
            public void onAdSwitch() {
                Log.w("", "onAdSwitch");
            }

            public void onAdShow(JSONObject info) {
                // 广告已经渲染出来
                Log.w("baiduBannerAd", "onAdShow " + info.toString());
            }

            public void onAdReady(AdView adView) {
                // 资源已经缓存完毕，还没有渲染出来
                Log.w("baiduBannerAd", "onAdReady " + adView);
            }

            public void onAdFailed(String reason) {
                Log.w("", "onAdFailed " + reason);
            }

            public void onAdClick(JSONObject info) {
                 Log.w("baiduBannerAd", "onAdClick " + info.toString());

            }

            @Override
            public void onAdClose(JSONObject arg0) {
                close_banner = true;
                Log.w("baiduBannerAd", "onAdClose");
            }
        });
//        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        int h = Math.min(Math.max(dm.heightPixels,dm.widthPixels)/10,Math.min(dm.heightPixels,dm.widthPixels)/7);
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(h/3*20, h);
        Log.w("baiduBannerAd", h/3*20+":"+h);
        bannerRelativeLayout.addView(adView, adParams);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void addBanners(boolean isHead) {
        if(close_banner){
            adView.destroy();
            initBannerAd(bannerContainer);
        }
        RelativeLayout.LayoutParams labelParams = (RelativeLayout.LayoutParams) adView.getLayoutParams();
        if(isHead){
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,0);
        }else {
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }
        adView.setLayoutParams(labelParams);
        adView.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeRanners() {
        adView.setVisibility(View.GONE);
    }
    private int movie_index = 0;
    @Override
    public void showMovie(int id) {
        movie_index = id;
        if(mRewardVideoAd.isReady()){
            mRewardVideoAd.show();
        }else {
            loadRewardedVideo();
        }
    }
}
