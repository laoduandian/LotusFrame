package lotus.net.center.ios.ad.unityads;


import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;

@Library(Library.INTERNAL)
@NativeClass
public class UnityAds extends NSObject {
    static {
        ObjCRuntime.bind(UnityAds.class);
    }

    @Method(selector = "initialize:delegate:testMode:")
    public static native void initialize(String gameId, UnityAdsDelegate delegate, boolean testMode);

    //+ (void)show:(UIViewController *)viewController placementId:(NSString *)placementId;
    @Method(selector = "show:placementId:")
    public static native void show(UIViewController viewController, String placementId);

    //    + (void)load:(NSString *)placementId;
    @Method(selector = "load:")
    public static native void load(String placementId);

    //+ (BOOL)isReady:(NSString *)placementId;
    @Method(selector = "isReady:")
    public static native boolean isReady(String placementId);
}
