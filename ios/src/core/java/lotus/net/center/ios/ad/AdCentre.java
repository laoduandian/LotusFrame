package lotus.net.center.ios.ad;

import lotus.net.center.ios.VIOSLauncher;

public class AdCentre {
    private VIOSLauncher viosLauncher;
    private LotusAd myAd;
    private boolean adsOnOff = true;
    public AdCentre(VIOSLauncher viosLauncher){
        this.viosLauncher = viosLauncher;
        init();
    }
    private void init(){
        switch (viosLauncher.getGame().info.getAdsType()){
            case admob:
                myAd = new AdmobAd(viosLauncher);
                break;
            case uad:
                myAd = new UnityAd(viosLauncher);
                break;
                default:
                    myAd = new AdmobAd(viosLauncher);
                    break;
        }
//        myAd = new AdmobAd(viosLauncher);
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
            myAd.adDispose();
    }
}
