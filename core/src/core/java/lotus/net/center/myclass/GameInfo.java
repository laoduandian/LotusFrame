package lotus.net.center.myclass;

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

    public String rewardedVideo_ad_id = "ca-app-pub-2887861689802805/1682047996";
    public String banner_ad_id = "ca-app-pub-2887861689802805/9205794970";
    public String interstitial_ad_id = "ca-app-pub-2887861689802805/1682528175";
    public String app_ad_id ="ca-app-pub-2887861689802805~7729061775";
    public String game_Address = "1175680497";


    public void setScreenSize(short w, short h){
        this.GAME_WIDTH = w;
        this.GAME_HEIGHT = h;
    }

}
