package lotus.net.center.ios.ad.unityads;

import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.uikit.UIView;
import org.robovm.objc.annotation.NotImplemented;

public class UnityAdsBannerDelegateAdapter extends NSObject implements UnityAdsBannerDelegate {
    public UnityAdsBannerDelegateAdapter(){

    }

    @NotImplemented("unityAdsBannerDidLoad:view:")
    public void unityAdsBannerDidLoad(String placementId, UIView view) {

    }

    @NotImplemented("unityAdsBannerDidUnload:")
    public void unityAdsBannerDidUnload(String placementId) {

    }

    @NotImplemented("unityAdsBannerDidShow:")
    public void unityAdsBannerDidShow(String placementId) {

    }

    @NotImplemented("unityAdsBannerDidHide:")
    public void unityAdsBannerDidHide(String placementId) {

    }

    @NotImplemented("unityAdsBannerDidClick:")
    public void unityAdsBannerDidClick(String placementId) {

    }

    @NotImplemented("unityAdsBannerDidError:")
    public void unityAdsBannerDidError(String message) {

    }
}
