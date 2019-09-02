package lotus.net.center.ios.ad.unityads;

import org.robovm.rt.bro.ValuedEnum;

public enum  UnityAdsFinishState implements ValuedEnum {
    /**
     *  A state that indicates that the ad did not successfully display.
     */
    kUnityAdsFinishStateError(0),
    /**
     *  A state that indicates that the user skipped the ad.
     */
    kUnityAdsFinishStateSkipped(1),
    /**
     *  A state that indicates that the ad was played entirely.
     */
    kUnityAdsFinishStateCompleted(2);
    private final long n;

    private UnityAdsFinishState (long n) {
        this.n = n;
    }
    @Override
    public long value() {
        return n;
    }
}
