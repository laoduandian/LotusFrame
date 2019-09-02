package lotus.net.center.ios.ad.unityads;

import org.robovm.rt.bro.ValuedEnum;

public enum  UnityAdsError implements ValuedEnum {
    /**
     *  An error that indicates failure due to `UnityAds` currently being uninitialized.
     */
    kUnityAdsErrorNotInitialized (0),
    /**
     *  An error that indicates failure due to a failure in the initialization process.
     */
    kUnityAdsErrorInitializedFailed(1),
    /**
     *  An error that indicates failure due to attempting to initialize `UnityAds` with invalid parameters.
     */
    kUnityAdsErrorInvalidArgument(2),
    /**
     *  An error that indicates failure of the video player.
     */
    kUnityAdsErrorVideoPlayerError(3),
    /**
     *  An error that indicates failure due to having attempted to initialize the `UnityAds` class in an invalid environment.
     */
    kUnityAdsErrorInitSanityCheckFail(4),
    /**
     *  An error that indicates failure due to the presence of an ad blocker.
     */
    kUnityAdsErrorAdBlockerDetected(5),
    /**
     *  An error that indicates failure due to inability to read or write a file.
     */
    kUnityAdsErrorFileIoError(6),
    /**
     *  An error that indicates failure due to a bad device identifier.
     */
    kUnityAdsErrorDeviceIdError(7),
    /**
     *  An error that indicates a failure when attempting to show an ad.
     */
    kUnityAdsErrorShowError(8),
    /**
     *  An error that indicates an internal failure in `UnityAds`.
     */
    kUnityAdsErrorInternalError(9);
    private final long n;

    private UnityAdsError (long n) {
        this.n = n;
    }
    @Override
    public long value() {
        return n;
    }
}
