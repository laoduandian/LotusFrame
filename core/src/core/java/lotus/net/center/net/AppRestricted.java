package lotus.net.center.net;

/**
 * 限制
 * 0、应用id
 * 1、自推广开启 //默认是开启的
 * 2、插屏显示条件
 */
public class AppRestricted {
    private String address;
    private boolean isAddNew;
    private int showTime;
    private String app_ad_id;
    private String banner_ad_id;
    private String interstitial_ad_id;
    private String rewardedVideo_ad_id;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAddNew() {
        return isAddNew;
    }

    public void setAddNew(boolean addNew) {
        isAddNew = addNew;
    }

    public int getShowTime() {
        return showTime;
    }

    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    public String getApp_ad_id() {
        return app_ad_id;
    }

    public void setApp_ad_id(String app_ad_id) {
        this.app_ad_id = app_ad_id;
    }

    public String getBanner_ad_id() {
        return banner_ad_id;
    }

    public void setBanner_ad_id(String banner_ad_id) {
        this.banner_ad_id = banner_ad_id;
    }

    public String getInterstitial_ad_id() {
        return interstitial_ad_id;
    }

    public void setInterstitial_ad_id(String interstitial_ad_id) {
        this.interstitial_ad_id = interstitial_ad_id;
    }

    public String getRewardedVideo_ad_id() {
        return rewardedVideo_ad_id;
    }

    public void setRewardedVideo_ad_id(String rewardedVideo_ad_id) {
        this.rewardedVideo_ad_id = rewardedVideo_ad_id;
    }
}
