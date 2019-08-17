package lotus.net.center.net;

/**
 * 限制
 * 0、应用id
 * 1、自推广开启 //默认是开启的
 * 2、插屏显示条件
 */
public class AppRestricted {
    private int index;
    private String inlandAddress;//国内地址
    private boolean isAddNew;
    private int showTime;
    private String adsFile;//广告ID文件名
    private String adsFileIos;//ios广告ID文件名
    private int adsChannelios;//广告渠道_ios
    private int adsChannelgol;//广告渠道_google
    private int adsChanneltap;//广告渠道_tap


    public String getInlandAddress() {
        return inlandAddress;
    }

    public boolean isAddNew() {
        return isAddNew;
    }

    public int getShowTime() {
        return showTime;
    }

    public String getAdsFile() {
        return adsFile;
    }

    public String getAdsFileIos() {
        return adsFileIos;
    }

    public int getIndex() {
        return index;
    }

    public int getAdsChannelios() {
        return adsChannelios;
    }

    public int getAdsChannelgol() {
        return adsChannelgol;
    }

    public int getAdsChanneltap() {
        return adsChanneltap;
    }
}
