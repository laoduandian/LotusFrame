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
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import lotus.net.center.android.VAndroidLauncher;


public class GoogleAd implements LotusAd,RewardedVideoAdListener{
	private InterstitialAd interstitialAd;
	private RewardedVideoAd mRewardedVideoAd;
	private VAndroidLauncher activity;
	public GoogleAd(VAndroidLauncher activity){
		this.activity = activity;
	}
	@Override
	public void initInterstitialAd() {
		interstitialAd = new InterstitialAd(activity);
		interstitialAd.setAdUnitId(this.activity.game.info.interstitial_ad_id);
		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
			}
			@Override
			public void onAdFailedToLoad(int errorCode) {
			}
		});
	}

	@Override
	public void loadInsertscreen() {
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("8B55DAD18BEF299E261C4D2E8ED751B0").build();
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
		mRewardedVideoAd.setRewardedVideoAdListener(this);
		loadRewardedVideo();
	}

	@Override
	public void loadRewardedVideo() {
		mRewardedVideoAd.loadAd(this.activity.game.info.rewardedVideo_ad_id,
				new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("8B55DAD18BEF299E261C4D2E8ED751B0").build());
	}


	@Override
	public void initBannerAd(RelativeLayout bannerRelativeLayout) {
		AdView adView = new AdView(activity);// a152f7167e68810//钢琴a152f8d3eeb4232
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(this.activity.game.info.banner_ad_id);
		LayoutParams adParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bannerRelativeLayout.addView(adView, adParams);
		adView.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("8B55DAD18BEF299E261C4D2E8ED751B0").build());
	}
	@Override
	public void dispose() {
	}

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
		loadRewardedVideo();
		Gdx.app.log("RewardedVideo：","onRewardedVideoAdClosed");
		
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
		Gdx.app.log("RewardedVideo：","onRewardedVideoAdFailedToLoad");
		loadRewardedVideo();
	}

	public void onRewardedVideoCompleted() {
		Gdx.app.log("RewardedVideo：","onRewardedVideoCompleted");
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
}
