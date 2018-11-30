package lotus.net.center.android.ad;

import android.widget.RelativeLayout;

public interface LotusAd {
	public void initInterstitialAd();
	public void loadInsertscreen();
	public void showInsertscreen();
	public void initRewardedVideo();
	public void loadRewardedVideo();
	public void initBannerAd(RelativeLayout bannerRelativeLayout);
	public void dispose();
}
