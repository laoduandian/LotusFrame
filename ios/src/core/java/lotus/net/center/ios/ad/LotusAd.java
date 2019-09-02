package lotus.net.center.ios.ad;

public interface LotusAd {
    void initInterstitialAd();
    void loadInsertscreen();
    void showInsertscreen();
    void initBannerAd();
    void addBanners(boolean isHead);
    void removeRanners();
    void initRewardedVideo();
    void loadRewardedVideo();
    void showMovie(int id);
    void adDispose();
}
