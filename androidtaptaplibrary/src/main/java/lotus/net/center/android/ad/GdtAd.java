package lotus.net.center.android.ad;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.util.AdError;

import java.util.Date;
import java.util.Locale;

import lotus.net.center.android.VAndroidLauncher;

public class GdtAd implements LotusAd {
    private VAndroidLauncher activity;
    private static String APPID = "1101152570";
    private static String BannerID = "4080052898050840";
    private static String InterteristalID = "3040652898151811";
    private static String RewardVideoADID = "2090845242931421";//支持竖版出横版视频
    UnifiedInterstitialAD iad;
    private DisplayMetrics dm;
    public GdtAd(VAndroidLauncher activity) {
        this.activity = activity;
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        APPID = activity.game.info.app_ad_id;
        BannerID = activity.game.info.banner_ad_id;
        InterteristalID = activity.game.info.interstitial_ad_id;
        RewardVideoADID = activity.game.info.rewardedVideo_ad_id;
        initInterstitialAd();
        initBannerAd(activity.relativeLayout);
        initRewardedVideo();
    }


    @Override
    public void initInterstitialAd() {
        getIAD().loadAD();
    }

    private UnifiedInterstitialAD getIAD() {
        if (this.iad != null) {
            iad.close();
            iad.destroy();
            iad = null;
        }
        if (iad == null) {
            iad = new UnifiedInterstitialAD(activity, APPID, InterteristalID, new UnifiedInterstitialADListener() {


                @Override
                public void onADReceive() {
                    Log.i("GDTInterstitialAd", "onADReceive:插屏2.0广告加载完毕，此回调后才可以调用 show 方法");
                }

                @Override
                public void onNoAD(AdError adError) {
                    Log.i("GDTInterstitialAd", "onNoAD:广告加载失败，error 对象包含了错误码和错误信息  ");
                }

                @Override
                public void onADOpened() {
                    Log.i("GDTInterstitialAd", "onADOpened:插屏2.0广告展开时回调  ");
                }

                @Override
                public void onADExposure() {
                    Log.i("GDTInterstitialAd", "onADExposure:插屏2.0广告曝光时回调  ");
                }

                @Override
                public void onADClicked() {
                    Log.i("GDTInterstitialAd", "onADClicked:插屏2.0广告点击时回调  ");
                }

                @Override
                public void onADLeftApplication() {
                    Log.i("GDTInterstitialAd", "onADLeftApplication:插屏2.0广告点击离开应用时回调  ");
                }

                @Override
                public void onADClosed() {
                    Log.i("GDTInterstitialAd", "onADClosed:插屏2.0广告关闭时回调  ");
                }
            });
        }
        return iad;
    }

    @Override
    public void loadInsertscreen() {
        iad.loadAD();
    }

    @Override
    public void showInsertscreen() {
        iad.show();
    }

    private RewardVideoAD rewardVideoAD;
    private boolean adLoaded;//广告加载成功标志
    private boolean videoCached;//视频素材文件下载完成标志

    @Override
    public void initRewardedVideo() {
        rewardVideoAD = new RewardVideoAD(this.activity, APPID, RewardVideoADID, new RewardVideoADListener() {

            /**
             * 广告加载成功，可在此回调后进行广告展示
             **/
            @Override
            public void onADLoad() {
                adLoaded = true;
                String msg = "load ad success ! expireTime = " + new Date(System.currentTimeMillis() +
                        rewardVideoAD.getExpireTimestamp() - SystemClock.elapsedRealtime());
//                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }

            /**
             * 视频素材缓存成功，可在此回调后进行广告展示
             */
            @Override
            public void onVideoCached() {
                videoCached = true;
                Log.i("RewardedVideo", "onVideoCached");
            }

            /**
             * 激励视频广告页面展示
             */
            @Override
            public void onADShow() {
                Log.i("RewardedVideo", "onADShow");
            }

            /**
             * 激励视频广告曝光
             */
            @Override
            public void onADExpose() {
                Log.i("RewardedVideo", "onADExpose");
            }

            /**
             * 激励视频触发激励（观看视频大于一定时长或者视频播放完毕）
             */
            @Override
            public void onReward() {
                Log.i("RewardedVideo", "onReward");
            }

            /**
             * 激励视频广告被点击
             */
            @Override
            public void onADClick() {
                Log.i("RewardedVideo", "onADClick");
            }

            /**
             * 激励视频播放完毕
             */
            @Override
            public void onVideoComplete() {
                Log.i("RewardedVideo", "onVideoComplete");
                activity.game.showMovie_return(movie_index);
            }

            /**
             * 激励视频广告被关闭
             */
            @Override
            public void onADClose() {
                Log.i("RewardedVideo", "onADClose");
                loadRewardedVideo();
            }

            /**
             * 广告流程出错
             */
            @Override
            public void onError(AdError adError) {
                String msg = String.format(Locale.getDefault(), "onError, error code: %d, error msg: %s",
                        adError.getErrorCode(), adError.getErrorMsg());
//                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }
        });
        loadRewardedVideo();
    }

    @Override
    public void loadRewardedVideo() {
        rewardVideoAD.loadAD();
    }

    UnifiedBannerView bv;
    private RelativeLayout bannerContainer;
    private boolean close_banner;

    @Override
    public void initBannerAd(RelativeLayout bannerRelativeLayout) {
        this.bannerContainer = bannerRelativeLayout;
        getBanner().loadAD();

    }

    private UnifiedBannerView getBanner() {
        if (this.bv != null) {
            bannerContainer.removeView(bv);
            bv.destroy();
        }
        close_banner = false;
        this.bv = new UnifiedBannerView(activity, APPID, BannerID, new UnifiedBannerADListener() {
            @Override
            public void onNoAD(AdError adError) {
                Log.i("GDTBanner", "onNoAD:广告加载失败，error 对象包含了错误码和错误信息" + adError.getErrorCode() + adError.getErrorMsg());
            }

            @Override
            public void onADReceive() {
                Log.i("GDTBanner", "onADReceive:广告加载成功回调，表示广告相关的资源已经加载完毕，Ready To Show");
            }

            @Override
            public void onADExposure() {
                Log.i("GDTBanner", "onADExposure:当广告曝光时发起的回调");
            }

            @Override
            public void onADClosed() {
                Log.i("GDTBanner", "onADClosed:当广告关闭时调用");
                close_banner = true;
            }

            @Override
            public void onADClicked() {
                Log.i("GDTBanner", "onADClicked:当广告点击时发起的回调，由于点击去重等原因可能和平台最终的统计数据有差异");
            }

            @Override
            public void onADLeftApplication() {
                Log.i("GDTBanner", "onADLeftApplication:由于广告点击离开 APP 时调用");
            }

            @Override
            public void onADOpenOverlay() {
                Log.i("GDTBanner", "onADOpenOverlay:当广告打开浮层时调用，如打开内置浏览器、内容展示浮层，一般发生在点击之后");
            }

            @Override
            public void onADCloseOverlay() {
                Log.i("GDTBanner", "onADCloseOverlay:浮层关闭时调用  ");
            }
        });
        // 注意：如果开发者的banner不是始终展示在屏幕中的话，请关闭自动刷新，否则将导致曝光率过低。
        // 并且应该自行处理：当banner广告区域出现在屏幕后，再手动loadAD。
        bv.setRefresh(30);
        int h = Math.min(Math.max(dm.heightPixels,dm.widthPixels)/10,Math.min(dm.heightPixels,dm.widthPixels)/7);
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(h/3*20, h);
        bannerContainer.addView(bv, adParams);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bv.setVisibility(View.GONE);
        return this.bv;
    }

    /**
     * dp转为px
     *
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    //DP转PX
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //PX转DP
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void addBanners(boolean isHead) {
        if (close_banner)
            getBanner().loadAD();
        RelativeLayout.LayoutParams labelParams = (RelativeLayout.LayoutParams) bv.getLayoutParams();
        if (isHead) {
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
        } else {
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            labelParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }

        bv.setLayoutParams(labelParams);
        bv.setVisibility(View.VISIBLE);
    }

    public void removeRanners() {
        if (bv != null)
            bv.setVisibility(View.GONE);
    }

    @Override
    public void dispose() {
        if (bv != null) {
            bv.destroy();
            bv = null;
        }
        if (this.iad != null) {
            iad.close();
            iad.destroy();
            iad = null;
        }
    }

    private int movie_index = 0;

    public void showMovie(int id) {
        movie_index = id;
        // 3. 展示激励视频广告
        if (adLoaded && rewardVideoAD != null) {//广告展示检查1：广告成功加载，此处也可以使用videoCached来实现视频预加载完成后再展示激励视频广告的逻辑
            if (!rewardVideoAD.hasShown()) {//广告展示检查2：当前广告数据还没有展示过
                long delta = 1000;//建议给广告过期时间加个buffer，单位ms，这里demo采用1000ms的buffer
                //广告展示检查3：展示广告前判断广告数据未过期
                if (SystemClock.elapsedRealtime() < (rewardVideoAD.getExpireTimestamp() - delta)) {
                    rewardVideoAD.showAD();
                } else {
//                    Toast.makeText(this.activity, "激励视频广告已过期，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show();
                    loadRewardedVideo();
                }
            } else {
//                Toast.makeText(this.activity, "此条广告已经展示过，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show();
                loadRewardedVideo();
            }
        } else {
//            Toast.makeText(this.activity, "成功加载广告后再进行广告展示！", Toast.LENGTH_LONG).show();
            loadRewardedVideo();
        }
    }
}
