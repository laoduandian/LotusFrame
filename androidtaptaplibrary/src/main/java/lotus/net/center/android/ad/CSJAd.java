package lotus.net.center.android.ad;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;

import java.util.List;

import lotus.net.center.android.VAndroidLauncher;

public class CSJAd implements LotusAd {
    private VAndroidLauncher activity;
    private static String APPID = "5001121";
    private static String BannerID = "901121246";
    private static String InterteristalID = "901121133";
    private static String RewardVideoADID = "901121430";
    private DisplayMetrics dm;

    public CSJAd(VAndroidLauncher activity) {
        this.activity = activity;
        APPID = activity.game.info.app_ad_id;
        BannerID = activity.game.info.banner_ad_id;
        InterteristalID = activity.game.info.interstitial_ad_id;
        RewardVideoADID = activity.game.info.rewardedVideo_ad_id;
        TTAdManagerHolder.init(activity, APPID);
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //step2:创建TTAdNative对象，createAdNative(Context context) banner广告context需要传入Activity对象
        mTTAdNative = TTAdManagerHolder.get().createAdNative(activity);
        //step3:(可选，强烈建议在合适的时机调用):申请部分权限，如read_phone_state,防止获取不了imei时候，下载类广告没有填充的问题。
        TTAdManagerHolder.get().requestPermissionIfNecessary(activity);
        initInterstitialAd();
        initBannerAd(activity.relativeLayout);
        initRewardedVideo();
    }

    private AdSlot interstitialAd_AdSlot;
    private TTNativeExpressAd interstitialAd_TTAd;

    @Override
    public void initInterstitialAd() {
        float width = Math.min(getDisplayMetrics().widthPixels, getDisplayMetrics().heightPixels);
        //step4:创建广告请求参数AdSlot,具体参数含义参考文档
        interstitialAd_AdSlot = new AdSlot.Builder()
                .setCodeId(InterteristalID) //广告位id
                .setSupportDeepLink(true)
                .setAdCount(1) //请求广告数量为1到3条
                .setExpressViewAcceptedSize(activity.px2dp(width), activity.px2dp(width)) //期望模板广告view的size,单位dp
                .setImageAcceptedSize(640, 320)//这个参数设置即可，不影响模板广告的size
                .build();
    }

    boolean isLoad_interstitialAd = false;

    @Override
    public void loadInsertscreen() {
        //step5:请求广告，调用插屏广告异步请求接口
        mTTAdNative.loadInteractionExpressAd(interstitialAd_AdSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                TToast.show(activity, "code: " + code + "  message: " + message);
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads == null || ads.size() == 0) {
                    return;
                }
                interstitialAd_TTAd = ads.get(0);
                interstitialAd_TTAd.setExpressInteractionListener(new TTNativeExpressAd.AdInteractionListener() {
                    @Override
                    public void onAdDismiss() {
                        TToast.show(activity, "广告关闭");
                    }

                    @Override
                    public void onAdClicked(View view, int type) {
                        TToast.show(activity, "广告被点击");
                    }

                    @Override
                    public void onAdShow(View view, int type) {
                        TToast.show(activity, "广告展示");
                    }

                    @Override
                    public void onRenderFail(View view, String msg, int code) {
                        Log.e("ExpressView", "render fail:");
                        TToast.show(activity, msg + " code:" + code);
                    }

                    @Override
                    public void onRenderSuccess(View view, float width, float height) {
                        Log.e("ExpressView", "render suc:");
                        //返回view的宽高 单位 dp
                        TToast.show(activity, "渲染成功");
                        isLoad_interstitialAd = true;
                    }
                });
                interstitialAd_TTAd.render();
            }
        });
    }

    @Override
    public void showInsertscreen() {
        if (isLoad_interstitialAd) {
            interstitialAd_TTAd.showInteractionExpressAd(activity);
            isLoad_interstitialAd = false;
        } else {
            mTTAdNative.loadInteractionExpressAd(interstitialAd_AdSlot, new TTAdNative.NativeExpressAdListener() {
                @Override
                public void onError(int code, String message) {
                    TToast.show(activity, "code: " + code + "  message: " + message);
                }

                @Override
                public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                    if (ads == null || ads.size() == 0) {
                        return;
                    }
                    interstitialAd_TTAd = ads.get(0);
                    interstitialAd_TTAd.setExpressInteractionListener(new TTNativeExpressAd.AdInteractionListener() {
                        @Override
                        public void onAdDismiss() {
                            TToast.show(activity, "广告关闭");
                        }

                        @Override
                        public void onAdClicked(View view, int type) {
                            TToast.show(activity, "广告被点击");
                        }

                        @Override
                        public void onAdShow(View view, int type) {
                            TToast.show(activity, "广告展示");
                        }

                        @Override
                        public void onRenderFail(View view, String msg, int code) {
                            Log.e("ExpressView", "render fail:");
                            TToast.show(activity, msg + " code:" + code);
                        }

                        @Override
                        public void onRenderSuccess(View view, float width, float height) {
                            Log.e("ExpressView", "render suc:");
                            //返回view的宽高 单位 dp
                            TToast.show(activity, "渲染成功");
                            interstitialAd_TTAd.showInteractionExpressAd(activity);
                        }
                    });
                    interstitialAd_TTAd.render();
                }
            });
        }
    }

    private AdSlot rewardedVideo_AdSlot;

    @Override
    public void initRewardedVideo() {
        //step4:创建广告请求参数AdSlot,具体参数含义参考文档
        rewardedVideo_AdSlot = new AdSlot.Builder()
                .setCodeId(RewardVideoADID)
                .setSupportDeepLink(true)
                .setImageAcceptedSize(1080, 1920)
                .setRewardName("金币") //奖励的名称
                .setRewardAmount(3)  //奖励的数量
                .setUserID("user123")//用户id,必传参数
                .setMediaExtra("media_extra") //附加参数，可选
                .setOrientation(getDisplayMetrics().widthPixels > getDisplayMetrics().heightPixels ? TTAdConstant.HORIZONTAL : TTAdConstant.VERTICAL) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                .build();
        loadRewardedVideo();
    }

    @Override
    public void loadRewardedVideo() {
        //step5:请求广告
        mTTAdNative.loadRewardVideoAd(rewardedVideo_AdSlot, new TTAdNative.RewardVideoAdListener() {
            @Override
            public void onError(int code, String message) {
                TToast.show(activity, message);
            }

            //视频广告加载后，视频资源缓存到本地的回调，在此回调后，播放本地视频，流畅不阻塞。
            @Override
            public void onRewardVideoCached() {
                TToast.show(activity, "rewardVideoAd video cached");
            }

            //视频广告的素材加载完毕，比如视频url等，在此回调后，可以播放在线视频，网络不好可能出现加载缓冲，影响体验。
            @Override
            public void onRewardVideoAdLoad(TTRewardVideoAd ad) {
                TToast.show(activity, "rewardVideoAd loaded");
                mttRewardVideoAd = ad;
//                mttRewardVideoAd.setShowDownLoadBar(false);
                mttRewardVideoAd.setRewardAdInteractionListener(new TTRewardVideoAd.RewardAdInteractionListener() {

                    @Override
                    public void onAdShow() {
                        TToast.show(activity, "rewardVideoAd show");
                    }

                    @Override
                    public void onAdVideoBarClick() {
                        TToast.show(activity, "rewardVideoAd bar click");
                    }

                    @Override
                    public void onAdClose() {
                        TToast.show(activity, "rewardVideoAd close");
                        loadRewardedVideo();
                    }

                    //视频播放完成回调
                    @Override
                    public void onVideoComplete() {
                        TToast.show(activity, "rewardVideoAd complete");
                    }

                    @Override
                    public void onVideoError() {
                        TToast.show(activity, "rewardVideoAd error");
                    }

                    //视频播放完成后，奖励验证回调，rewardVerify：是否有效，rewardAmount：奖励梳理，rewardName：奖励名称
                    @Override
                    public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName) {
                        TToast.show(activity, "verify:" + rewardVerify + " amount:" + rewardAmount +
                                " name:" + rewardName);
                        activity.game.showMovie_return(movie_index);
                    }

                    @Override
                    public void onSkippedVideo() {
                        TToast.show(activity, "rewardVideoAd has onSkippedVideo");
                    }
                });
            }
        });
    }

    //获取运行屏幕


    public DisplayMetrics getDisplayMetrics() {
        return dm;
    }

    private TTAdNative mTTAdNative;
    private RelativeLayout bannerRelativeLayout;
    private boolean isLoad_banner;
    private View bannerView;


    @Override
    public void initBannerAd(RelativeLayout bannerRelativeLayout) {
        isLoad_banner = false;
        this.bannerRelativeLayout = bannerRelativeLayout;


    }

    @Override
    public void dispose() {

    }

    private TTNativeExpressAd mTTAd;

    @Override
    public void addBanners(final boolean isHead) {
        if (bannerView == null) {
            if (isLoad_banner)
                return;
            isLoad_banner = true;
            int h = Math.min(Math.max(dm.heightPixels, dm.widthPixels) / 10, Math.min(dm.heightPixels, dm.widthPixels) / 7);
            int width = h / 3 * 20;
            final RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(h / 3 * 20, h);
            adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            //step4:创建广告请求参数AdSlot,具体参数含义参考文档
            AdSlot adSlot = new AdSlot.Builder()
                    .setCodeId(BannerID) //广告位id
                    .setSupportDeepLink(true)
                    .setAdCount(1) //请求广告数量为1到3条
                    .setExpressViewAcceptedSize(activity.px2dp(width), activity.px2dp(h)) //期望模板广告view的size,单位dp
                    .setImageAcceptedSize(600, 90)//这个参数设置即可，不影响模板广告的size
                    .build();
            //step5:请求广告，对请求回调的广告作渲染处理
            mTTAdNative.loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
                @Override
                public void onError(int code, String message) {
                    TToast.show(activity, "load error : " + code + ", " + message);
                }

                @Override
                public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                    if (ads == null || ads.size() == 0) {
                        return;
                    }
                    mTTAd = ads.get(0);
                    final long startTime = System.currentTimeMillis();
//                    bindAdListener(mTTAd);
//                    startTime = System.currentTimeMillis();
                    mTTAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {
                        @Override
                        public void onAdClicked(View view, int type) {
                            TToast.show(activity, "广告被点击");
                        }

                        @Override
                        public void onAdShow(View view, int type) {
                            TToast.show(activity, "广告展示");
                        }

                        @Override
                        public void onRenderFail(View view, String msg, int code) {
                            Log.e("ExpressView", "render fail:" + (System.currentTimeMillis() - startTime));
                            TToast.show(activity, msg + " code:" + code);
                        }

                        @Override
                        public void onRenderSuccess(View view, float width, float height) {
                            Log.e("ExpressView", "render suc:" + (System.currentTimeMillis() - startTime));
                            //返回view的宽高 单位 dp
                            TToast.show(activity, "渲染成功");
//                            mExpressContainer.removeAllViews();
//                            mExpressContainer.addView(view);
                            bannerView = view;
                            if (bannerView == null) {
                                return;
                            }
                            //设置轮播的时间间隔  间隔在30s到120秒之间的值，不设置默认不轮播
                            mTTAd.setSlideIntervalTime(30 * 1000);
                            CSJAd.this.bannerRelativeLayout.addView(bannerView, adParams);
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
                    });
                    //使用默认模板中默认dislike弹出样式
                    mTTAd.setDislikeCallback(activity, new TTAdDislike.DislikeInteractionCallback() {
                        @Override
                        public void onSelected(int position, String value) {
                            TToast.show(activity, "点击 " + value);
                            //用户选择不喜欢原因后，移除广告展示
                            removeRanners();
                        }

                        @Override
                        public void onCancel() {
                            TToast.show(activity, "点击取消 ");
                        }
                    });

                    mTTAd.render();
                }
            });
        } else {
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

    private TTRewardVideoAd mttRewardVideoAd;
    private int movie_index = 0;

    @Override
    public void showMovie(int id) {
        movie_index = id;
        if (mttRewardVideoAd != null) {
            //step6:在获取到广告后展示
            mttRewardVideoAd.showRewardVideoAd(activity);
            mttRewardVideoAd = null;
        } else {
            loadRewardedVideo();
            TToast.show(activity, "请先加载广告");
        }
    }
}
