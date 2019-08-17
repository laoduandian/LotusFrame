package lotus.net.center.myclass;


import lotus.net.center.freefont.FreeListener;
import lotus.net.center.net.AppItem;

public interface App extends FreeListener {
	public void outGame();
	public void pinfen();
	public void showSomething(String a);
	public void paihang();
	public void shangchuan(String name,int a);
	public void shangchuan(String name,float a);
	public void addBanners(boolean isHead);
	public void removeRanners();
	public void moreGame();
	public void showInterstitialAd();
	public void share();
	public void loadInsertscreen();
	public void showMovie(int id);
	public void newgame(AppItem appItem);
	public void initAD();
}
 