package lotus.net.center.ios.ad.unityads;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;

@NativeClass
public class UnityAdsBanner extends NSObject {

    /**
     * Loads the banner with the default banner placement.
     * +(void)loadBanner;
     */
    @Method(selector = "loadBanner")
    public static native void loadBanner();

    /**
     * +(void)loadBanner:(NSString *)placementId;
     * Loads the banner with the given placement.
     * @param placementId The placement ID, as defined in the Unity Ads admin tools.
     */
    @Method(selector = "loadBanner:")
    public static native void loadBanner(String placementId);

    /**
     * +(void)setBannerPosition:(UnityAdsBannerPosition)bannerPosition;
     * @param bannerPosition
     */
    @Method(selector = "setBannerPosition:")
    public static native void setBannerPosition(UnityAdsBannerPosition bannerPosition);

    /**
     * Destroys the banner.
     * +(void)destroy;
     */
    @Method(selector = "destroy")
    public static native void destroy();

    /**
     * +(nullable id <UnityAdsBannerDelegate>)getDelegate;
     * Provides the currently assigned `UnityAdsBannerDelegate`.
     * @return The current `UnityAdsBannerDelegate`.
     */
    @Method(selector = "getDelegate")
    public static native UnityAdsBannerDelegate getDelegate();

    /**
     * Asigns the banner delegate.
     *+(void)setDelegate:(id <UnityAdsBannerDelegate>)delegate;
     * @param delegate The new `UnityAdsBannerDelegate' for UnityAds to send banner callbacks to.
     */
    @Method(selector = "setDelegate:")
    public static native void setDelegate(UnityAdsBannerDelegate delegate);




}
