package lotus.net.center.myclass;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import lotus.net.center.net.AdsId;
import lotus.net.center.net.AdsType;
import lotus.net.center.net.AdsValue;
import lotus.net.center.net.AppChannel;
import lotus.net.center.net.AppRestricted;
import lotus.net.center.net.LotusStudio;

/**
 * 游戏的基础信息，
 *  #屏幕尺寸；
 *  #游戏ID；
 *  #
 */
public class GameInfo {
    public short GAME_WIDTH = 720;
    public short GAME_HEIGHT = 1280;

    // 游戏ID
    public int GAME_ID = 0;

    //游戏的存档名称
    public String game_name = "gameName";

    /**0、插屏出现的频率**/
    public int interstitial_ad_condition_num = 4;
    /**3、显示自推广**/
    public boolean is_Add_New = true;

    public String BIG_FONT_STRING = "Bigfont";
    public String F_FONT_ = "font_%d";


    public boolean MUSIC_ON_OFF = true;
    public boolean SOUND_ON_OFF = true;
    public final String MUSIC_NAME = "music";
    public final String SOUND_NAME = "sound";

    public String rewardedVideo_ad_id = "ca-app-pub-2887861689802805/1682047996";
    public String banner_ad_id = "ca-app-pub-2887861689802805/9205794970";
    public String interstitial_ad_id = "ca-app-pub-2887861689802805/1682528175";
    public String app_ad_id ="ca-app-pub-2887861689802805~7729061775";
    public String game_Address = "1175680497";
    /**
     * 是否是国内渠道
     */
    private boolean isInland = false;
    private String inlandAddress;
    private AdsType adsType;


    private boolean adsOnOff = true;
    private int adsChannel;
    private String adsFile;
    private AppChannel appChannel;

    private Array<AdsType> ownTypes = new Array<>();

    /**
     * android admob 测试广告ID
     */
    public void setAndroid_Test_Ads(){
        app_ad_id ="ca-app-pub-3940256099942544~3347511713";
        banner_ad_id = "ca-app-pub-3940256099942544/6300978111";
        interstitial_ad_id = "ca-app-pub-3940256099942544/1033173712";
        rewardedVideo_ad_id = "ca-app-pub-3940256099942544/5224354917";
    }

    /**
     * iOS admob 测试广告ID
     */
    public void seIOS_Test_Ads(){
        app_ad_id ="ca-app-pub-3940256099942544~1458002511";
        banner_ad_id = "ca-app-pub-3940256099942544/2934735716";
        interstitial_ad_id = "ca-app-pub-3940256099942544/4411468910";
        rewardedVideo_ad_id = "ca-app-pub-3940256099942544/1712485313";
    }


    public void setScreenSize(short w, short h){
        this.GAME_WIDTH = w;
        this.GAME_HEIGHT = h;
    }

    public boolean isInland() {
        return isInland;
    }

    public void setInland(boolean inland) {
        isInland = inland;
    }

    public String getInlandAddress() {
        return inlandAddress;
    }

    public void setInlandAddress(String inlandAddress) {
        this.inlandAddress = inlandAddress;
    }


    public AdsType getAdsType() {
        return adsType;
    }

    public void setAdsType(AdsType adsType) {
        this.adsType = adsType;
    }

    public boolean isAdsOnOff() {
        return adsOnOff;
    }

    public AppChannel getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(AppChannel appChannel) {
        this.appChannel = appChannel;
    }

    public Array<AdsType> getOwnTypes() {
        return ownTypes;
    }

    public void setLotusStudioAppMessage(LotusStudio app) {
        if (app == null)
            return;
        if(app.getAdsId() == null)
            return;
        AppRestricted appRestricted = app.getAppRestricted();
        if(appRestricted == null)
            return;
        this.interstitial_ad_condition_num = appRestricted.getShowTime();
        this.is_Add_New = appRestricted.isAddNew();
        this.inlandAddress = appRestricted.getInlandAddress();
        switch (this.getAppChannel()){
            case ios:
                setAdsType(appRestricted.getAdsChannelios(),app.getAdsId());
                break;
            case google:
                setAdsType(appRestricted.getAdsChannelgol(),app.getAdsId());
                break;
            case taptap:
                setAdsType(appRestricted.getAdsChanneltap(),app.getAdsId());
                break;
        }
    }
    private void setAdsType(int adsValue, AdsId adsId) {
        this.adsOnOff = true;
        switch (adsValue){
            case AdsValue.admob:
                this.setAdsType(AdsType.admob);
                this.app_ad_id = adsId.getApp_ad_id();
                this.banner_ad_id = adsId.getBanner_ad_id();
                this.interstitial_ad_id = adsId.getInterstitial_ad_id();
                this.rewardedVideo_ad_id = adsId.getRewardedVideo_ad_id();
                break;
            case AdsValue.uad:
                this.setAdsType(AdsType.uad);
                this.app_ad_id = adsId.getApp_uad_id();
                this.banner_ad_id = adsId.getBanner_uad_id();
                this.interstitial_ad_id = adsId.getInterstitial_uad_id();
                this.rewardedVideo_ad_id = adsId.getRewardedVideo_uad_id();
                break;
            case AdsValue.gdt:
                this.setAdsType(AdsType.gdt);
                this.app_ad_id = adsId.getApp_gdtad_id();
                this.banner_ad_id = adsId.getBanner_gdtad_id();
                this.interstitial_ad_id = adsId.getInterstitial_gdtad_id();
                this.rewardedVideo_ad_id = adsId.getRewardedVideo_gdtad_id();
                break;
            case AdsValue.baidu:
                this.setAdsType(AdsType.baidu);
                this.app_ad_id = adsId.getApp_bdad_id();
                this.banner_ad_id = adsId.getBanner_bdad_id();
                this.interstitial_ad_id = adsId.getInterstitial_bdad_id();
                this.rewardedVideo_ad_id = adsId.getRewardedVideo_bdad_id();
                break;
            case AdsValue.csj:
                this.setAdsType(AdsType.csj);
                this.app_ad_id = adsId.getApp_csjad_id();
                this.banner_ad_id = adsId.getBanner_csjad_id();
                this.interstitial_ad_id = adsId.getInterstitial_csjad_id();
                this.rewardedVideo_ad_id = adsId.getRewardedVideo_csjad_id();
                break;
            case AdsValue.admobUad:
                randomType(adsId,AdsType.admob,AdsType.uad);
                 break;
            case AdsValue.gdtBaidu:
                randomType(adsId,AdsType.gdt,AdsType.baidu);
                break;
            case AdsValue.gdtCsj:
                randomType(adsId,AdsType.gdt,AdsType.csj);
                break;
            case AdsValue.csjBaidu:
                randomType(adsId,AdsType.csj,AdsType.baidu);
                break;
            case AdsValue.gdtCsjBaidu:
                randomType(adsId,AdsType.gdt,AdsType.csj,AdsType.baidu);
                break;
            case 999:
                this.adsOnOff = false;
                break;
                default:
                    break;
        }
    }
    private void randomType(AdsId adsId,AdsType...types ){
        Array<AdsType> orderTypes = new Array<>();
        orderTypes.addAll(types);
        Array<AdsType> exists = new Array<>();
        exists.addAll(orderTypes);
        Array<AdsType> notexists = new Array<>();
        notexists.addAll(orderTypes);
        exists.removeAll(getOwnTypes(),false);//不同的
        notexists.removeAll(exists,false);//相同的
        if(notexists.size == 0){
            adsOnOff = false;
        }else{
            int random = MathUtils.random(notexists.size-1);
            setAdsType(getAdsValue(notexists.get(random)),adsId);
        }
    }
    private int getAdsValue(AdsType type){
        switch (type){
            case admob:
                return AdsValue.admob;
            case uad:
                return AdsValue.uad;
            case baidu:
                return AdsValue.baidu;
            case gdt:
                return AdsValue.gdt;
            case csj:
                return AdsValue.csj;
            default:
                return 999;
        }
    }
}
