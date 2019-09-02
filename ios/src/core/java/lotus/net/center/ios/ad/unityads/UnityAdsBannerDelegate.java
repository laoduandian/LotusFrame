package lotus.net.center.ios.ad.unityads;

import org.robovm.apple.foundation.NSObjectProtocol;
import org.robovm.apple.uikit.UIView;
import org.robovm.objc.annotation.Method;

public interface UnityAdsBannerDelegate extends NSObjectProtocol {

    /**
     * Called when the banner is loaded and ready to be placed in the view hierarchy.
     *
     * @param placementId The ID of the placement of the banner that is loaded.
     * @param view        View that is to be placed in the view hierarchy.
     */
    @Method(selector = "unityAdsBannerDidLoad:view:")
    void unityAdsBannerDidLoad(String placementId, UIView view);


    /**
     * Called when the banner is unloaded and references to it should be discarded.
     * The view provided in unityAdsBannerDidLoad will be removed from the view hierarchy before
     * this method is called.
     */
    @Method(selector = "unityAdsBannerDidUnload:")
    void unityAdsBannerDidUnload(String placementId);

    /**
     * Called when the banner is shown.
     *
     * @param placementId The ID of the placement that has shown.
     */
    @Method(selector = "unityAdsBannerDidShow:")
    void unityAdsBannerDidShow(String placementId);

    /**
     * Called when the banner is hidden.
     *
     * @param placementId the ID of the that has hidden.
     */
    @Method(selector = "unityAdsBannerDidHide:")
    void unityAdsBannerDidHide(String placementId);

    /**
     * Called when the user clicks the banner.
     *
     * @param placementId the ID of the placement that has been clicked.
     */
    @Method(selector = "unityAdsBannerDidClick:")
    void unityAdsBannerDidClick(String placementId);

    /**
     * Called when `UnityAdsBanner` encounters an error. All errors will be logged but this method can be used as an additional debugging aid. This callback can also be used for collecting statistics from different error scenarios.
     *
     * @param message A human readable string indicating the type of error encountered.
     */
    @Method(selector = "unityAdsBannerDidError:")
    void unityAdsBannerDidError(String message);
}
