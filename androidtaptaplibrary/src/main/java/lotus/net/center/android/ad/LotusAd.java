package lotus.net.center.android.ad;

import android.widget.RelativeLayout;

import lotus.net.center.android.VAndroidLauncher;

public interface LotusAd {
	void initInterstitialAd();
	void loadInsertscreen();
	void showInsertscreen();
	void initRewardedVideo();
	void loadRewardedVideo();
	void initBannerAd(RelativeLayout bannerRelativeLayout);
	void dispose();
	void addBanners(boolean isHead);
	void removeRanners();
	void showMovie(int id);

}
