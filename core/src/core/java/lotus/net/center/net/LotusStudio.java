package lotus.net.center.net;

import com.badlogic.gdx.utils.Array;

public class LotusStudio {
	public int index;
	private Array<AppRestricted> appRestricteds;
	private Array<AppItem> apps;
	public Array<AppItem> getApps() {
		return apps;
	}
	public void setApps(Array<AppItem> apps) {
		this.apps = apps;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

    public Array<AppRestricted> getAppRestricteds() {
        return appRestricteds;
    }

    public void setAppRestricteds(Array<AppRestricted> appRestricteds) {
        this.appRestricteds = appRestricteds;
    }
}
