package lotus.net.center.android.ad;

import com.badlogic.gdx.Gdx;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import lotus.net.center.android.VAndroidLauncher;


public class GoogleAd implements LotusAd{
	private static String APPID = "ca-app-pub-3940256099942544~3347511713";
	private static String BannerID = "ca-app-pub-3940256099942544/6300978111";
	private static String InterteristalID = "ca-app-pub-3940256099942544/1033173712";
	private static String RewardVideoADID = "ca-app-pub-3940256099942544/5224354917";//支持竖版出横版视频
	private InterstitialAd interstitialAd;
	private RewardedVideoAd mRewardedVideoAd;
	private VAndroidLauncher activity;
	private AdRequest adRequest;
	public GoogleAd(VAndroidLauncher activity){
		this.activity = activity;
		APPID = activity.game.info.app_ad_id;
		BannerID = activity.game.info.banner_ad_id;
		InterteristalID = activity.game.info.interstitial_ad_id;
		RewardVideoADID = activity.game.info.rewardedVideo_ad_id;
		MobileAds.initialize(activity, APPID);
		adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("8A9C319A8D182EACC3170C7BA897D0A5")
				.addTestDevice("8B55DAD18BEF299E261C4D2E8ED751B0").build();
		initInterstitialAd();
		initRewardedVideo();
		initBannerAd(activity.relativeLayout);
	}
	@Override
	public void initInterstitialAd() {
		interstitialAd = new InterstitialAd(activity);
		interstitialAd.setAdUnitId(InterteristalID);
		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// Code to be executed when an ad finishes loading.
				Gdx.app.log("插屏广告"," 广告加载完成后，系统会执行 onAdLoaded() 方法。 ");
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				// Code to be executed when an ad request fails.
				Gdx.app.log("插屏广告"+errorCode," 广告加载失败，0：内部出现问题；例如，收到广告服务器的无效响应。，1：广告请求无效，例如，广告单元 ID 不正确 ，2：由于网络连接问题，广告请求失败，3：广告请求成功，但由于缺少广告资源，未返回广告。");
			}

			@Override
			public void onAdOpened() {
				// Code to be executed when the ad is displayed.
				// 	在广告开始展示并铺满设备屏幕时，系统会调用此方法。
				Gdx.app.log("插屏广告"," 打开广告 ");
			}

			@Override
			public void onAdLeftApplication() {
				// Code to be executed when the user has left the app.
				//此方法会在用户点击打开其他应用（如 Google Play）时被调用，从而在后台运行当前应用。
				Gdx.app.log("插屏广告","  用户点击打开其他应用");
			}

			@Override
			public void onAdClosed() {
				// Code to be executed when when the interstitial ad is closed.
				//此方法会在用户点按“关闭”图标或使用“返回”按钮关闭插页式广告时被调用。如果您的应用暂停了音频输出或游戏循环，则非常适合使用此方法恢复这些活动。
				Gdx.app.log("插屏广告"," 关闭插页式广告时 ");
			}
		});
		loadInsertscreen();
	}

	@Override
	public void loadInsertscreen() {
		interstitialAd.loadAd(adRequest);
	}

	@Override
	public void showInsertscreen() {
		if (interstitialAd.isLoaded()) {
			interstitialAd.show();
		}else{
			loadInsertscreen();
		}
	}

	@Override
	public void initRewardedVideo() {
		mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
		mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener(){
			@Override
			public void onRewardedVideoAdLoaded() {
				Gdx.app.log("ad__:"+mRewardedVideoAd.isLoaded(),"onRewardedVideoAdLoaded");
			}

			@Override
			public void onRewardedVideoAdOpened() {
				Gdx.app.log("RewardedVideo：","onRewardedVideoAdOpened");
			}

			@Override
			public void onRewardedVideoStarted() {
				Gdx.app.log("RewardedVideo：","onRewardedVideoStarted");
			}

			@Override
			public void onRewardedVideoAdClosed() {
				Gdx.app.log("RewardedVideo：","onRewardedVideoAdClosed");
				loadRewardedVideo();
			}

			@Override
			public void onRewarded(RewardItem rewardItem) {
				Gdx.app.log("RewardedVideo：","onRewarded");
				activity.game.showMovie_return(movie_index);
			}

			@Override
			public void onRewardedVideoAdLeftApplication() {
				Gdx.app.log("RewardedVideo：","onRewardedVideoAdLeftApplication");
			}

			@Override
			public void onRewardedVideoAdFailedToLoad(int i) {
				Gdx.app.log("RewardedVideo：","onRewardedVideoAdFailedToLoad：：：："+i);
			}
		});
		loadRewardedVideo();
	}

	@Override
	public void loadRewardedVideo() {
		mRewardedVideoAd.loadAd(RewardVideoADID,adRequest);


	}

	AdView adView;
	private  int loadRannerTime = 6;
	@Override
	public void initBannerAd(RelativeLayout bannerRelativeLayout) {
		adView = new AdView(activity);// a152f7167e68810//钢琴a152f8d3eeb4232
		adView.setAdListener(new AdListener(){
			@Override
			public void onAdClosed() {
				super.onAdClosed();
				Gdx.app.log("BannerAd：","onAdClosed");
			}

			@Override
			public void onAdFailedToLoad(int i) {
				super.onAdFailedToLoad(i);
				Gdx.app.log("BannerAd：","onAdFailedToLoad:"+i);
				loadRannerTime--;
				if(loadRannerTime>0)
					adView.loadAd(adRequest);
			}

			@Override
			public void onAdLeftApplication() {
				super.onAdLeftApplication();
				Gdx.app.log("BannerAd：","onAdLeftApplication");
			}

			@Override
			public void onAdOpened() {
				super.onAdOpened();
				Gdx.app.log("BannerAd：","onAdOpened");
			}

			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				Gdx.app.log("BannerAd：","onAdLoaded");
			}

			@Override
			public void onAdClicked() {
				super.onAdClicked();
				Gdx.app.log("BannerAd：","onAdClicked");
			}

			@Override
			public void onAdImpression() {
				super.onAdImpression();
				Gdx.app.log("BannerAd：","onAdImpression");
			}
		});
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(BannerID);
		LayoutParams adParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		bannerRelativeLayout.addView(adView, adParams);
		adView.loadAd(adRequest);
		adView.setVisibility(View.GONE);
	}
	@Override
	public void dispose() {
	}


	private int movie_index = 0;
	public void showMovie(int id) {
		movie_index = id;
		if (mRewardedVideoAd.isLoaded()) {
			mRewardedVideoAd.show();
		}else{
			loadRewardedVideo();
		}
	}

	public void addBanners(boolean isHead) {
		RelativeLayout.LayoutParams labelParams = (LayoutParams) adView.getLayoutParams();
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

	public void removeRanners() {
		adView.setVisibility(View.GONE);
	}



}
