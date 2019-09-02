package lotus.net.center.ios.ad.unityads;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.NotImplemented;

public class UnityAdsDelegateAdapter extends NSObject implements UnityAdsDelegate  {
    public UnityAdsDelegateAdapter(){

    }
    @NotImplemented("unityAdsReady:")
    public void unityAdsReady(String placementId) {

    }

    @NotImplemented("unityAdsDidStart:")
    public void unityAdsDidStart(String placementId) {

    }

    @NotImplemented("unityAdsDidFinish:withFinishState:")
    public void unityAdsDidFinish(String placementId, UnityAdsFinishState finishState) {

    }

    @NotImplemented("unityAdsDidError:withMessage:")
    public void unityAdsDidError(UnityAdsError error, String s) {

    }

}
