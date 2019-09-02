package lotus.net.center.android.ad;



import lotus.net.center.android.VAndroidLauncher;

public class AdCentre {
	private VAndroidLauncher activity;
	private LotusAd myAd;
	public AdCentre(VAndroidLauncher activity) {
		this.activity = activity;
		init();
	}
	private void init(){
		switch (activity.game.info.getAdsType()){
			case admob:
				myAd = new GoogleAd(activity);
				break;
			case uad:
				myAd = new UnityAd(activity);
				break;
				default:
					myAd = new UnityAd(activity);
					break;
		}
//		myAd = new UnityAd(activity);
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
