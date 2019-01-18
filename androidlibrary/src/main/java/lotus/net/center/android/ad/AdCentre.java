package lotus.net.center.android.ad;


import com.google.android.gms.ads.MobileAds;
import com.unity3d.ads.metadata.MetaData;

import lotus.net.center.android.VAndroidLauncher;

public class AdCentre {
	private VAndroidLauncher activity;
	private GoogleAd myAd;
	public AdCentre(VAndroidLauncher activity) {
		this.activity = activity;
		MetaData gdprMetaData = new MetaData(activity);
		gdprMetaData.set("gdpr.consent", true);
		gdprMetaData.commit();
		MobileAds.initialize(activity, this.activity.game.info.app_ad_id);
		init();
	}
	private void init(){
		myAd = new GoogleAd(activity);
		initInterstitialAd();
		myAd.initRewardedVideo();
        initBannerAd();
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
    	myAd.initBannerAd(activity.relativeLayout);
    }
	public void addBanners(boolean isHead){
		myAd.addBanners(isHead);
	}

	public void removeRanners(){
		myAd.removeRanners();

	}
	public void dispose() {
		myAd.dispose();
	}
	public void showMovie(int id) {
		myAd.showMovie(id);
	}
}
