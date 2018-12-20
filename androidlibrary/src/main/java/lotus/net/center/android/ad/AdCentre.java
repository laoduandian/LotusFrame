package lotus.net.center.android.ad;


import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.LayoutParams;
import com.badlogic.gdx.Gdx;
import com.google.android.gms.ads.MobileAds;

import lotus.net.center.android.VAndroidLauncher;

public class AdCentre {
	private VAndroidLauncher activity;
	private RelativeLayout bannerRelativeLayout;
	private GoogleAd myAd;
	public AdCentre(VAndroidLauncher activity) {
		this.activity = activity;
		MobileAds.initialize(activity, this.activity.game.info.app_ad_id);
		init();
	}
	private void init(){
		bannerRelativeLayout = new RelativeLayout(activity);
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		activity.relativeLayout.addView(bannerRelativeLayout,adParams);
		myAd = new GoogleAd(activity);
		initInterstitialAd();
		myAd.initRewardedVideo();
        initBannerAd();
        loadInsertscreen();
	}
	/** 插屏 **/
	private void initInterstitialAd() {
		myAd.initInterstitialAd();
	}
	public void showInsertscreen() {
		myAd.showInsertscreen();
	}
	public void loadInsertscreen(){
		myAd.loadInsertscreen();
    }
    public void initBannerAd() {
    	myAd.initBannerAd(bannerRelativeLayout);
    }
	public void addBanners(){
		bannerRelativeLayout.setVisibility(View.VISIBLE);
	}
	public void removeRanners(){
		bannerRelativeLayout.setVisibility(View.GONE);
	}
	public void dispose() {
		myAd.dispose();
	}
	public void showMovie(int id) {
		myAd.showMovie(id);
	}
}
