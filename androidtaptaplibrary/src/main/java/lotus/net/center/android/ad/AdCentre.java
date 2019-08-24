package lotus.net.center.android.ad;


import lotus.net.center.android.VAndroidLauncher;

public class AdCentre {
    private VAndroidLauncher activity;
    private LotusAd myAd;
    private boolean adsOnOff = true;

    public AdCentre(VAndroidLauncher activity) {
        this.activity = activity;
        adsOnOff = activity.game.info.isAdsOnOff();
        init();
    }

    private void init() {
        switch (activity.game.info.getAdsType()) {
            case gdt:
                myAd = new GdtAd(activity);
                break;
            case csj:
                myAd = new CSJAd(activity);
                break;
            case baidu:
                myAd = new BaiduAd(activity);
                break;
            default:
                myAd = new GdtAd(activity);
                break;
        }
//        myAd = new GdtAd(activity);
    }

    public void showInsertscreen() {
        if (adsOnOff)
            myAd.showInsertscreen();
    }

    public void loadInsertscreen() {
        if (adsOnOff)
            myAd.loadInsertscreen();
    }

    public void addBanners(boolean isHead) {
        if (adsOnOff)
            myAd.addBanners(isHead);
    }

    public void removeRanners() {
        if (adsOnOff)
            myAd.removeRanners();
    }

    public void showMovie(int id) {
        if (adsOnOff)
            myAd.showMovie(id);
    }

    public void dispose() {
        if (adsOnOff)
            myAd.dispose();
    }
}
