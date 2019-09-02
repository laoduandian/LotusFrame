package lotus.net.center.ios.ad.unityads;

import org.robovm.rt.bro.ValuedEnum;

public enum UnityAdsBannerPosition implements ValuedEnum {
    kUnityAdsBannerPositionTopLeft(0),
    kUnityAdsBannerPositionTopCenter(1),
    kUnityAdsBannerPositionTopRight(2),
    kUnityAdsBannerPositionBottomLeft(3),
    kUnityAdsBannerPositionBottomCenter(4),
    kUnityAdsBannerPositionBottomRight(5),
    kUnityAdsBannerPositionCenter(6),
    kUnityAdsBannerPositionNone(7);
    private final long n;

    private UnityAdsBannerPosition (long n) {
        this.n = n;
    }
    @Override
    public long value() {
        return n;
    }
}
