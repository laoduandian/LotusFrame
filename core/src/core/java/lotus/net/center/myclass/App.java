package lotus.net.center.myclass;


import lotus.net.center.freefont.FreeListener;

public interface App extends FreeListener {
	public void outGame();
	public void pinfen();
	public void showSomething(String a);
	public void paihang();
	public void shangchuan(String name,int a);
	public void shangchuan(String name,float a);
	void addBanners();
	void removeRanners();
	public void moreGame();
	public void showInterstitialAd();
	public void share();
	public void loadInsertscreen();
	public void showMovie(int id);
	public void newgame(String address);
}
 