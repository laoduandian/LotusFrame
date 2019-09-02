package lotus.net.center.ios.ad.unityads;

import org.robovm.apple.foundation.NSObjectProtocol;
import org.robovm.objc.annotation.Method;

public interface UnityAdsDelegate extends NSObjectProtocol {

//    @protocol UnityAdsDelegate <NSObject>
//- (void)unityAdsReady:(NSString *)placementId;
    @Method(selector = "unityAdsReady:")
    void unityAdsReady(String placementId);
//- (void)unityAdsDidStart:(NSString *)placementId;
    @Method(selector = "unityAdsDidStart:")
    void unityAdsDidStart(String placementId);
//- (void)unityAdsDidFinish:(NSString *)placementId
//    withFinishState:(UnityAdsFinishState)state;
    @Method(selector = "unityAdsDidFinish:withFinishState:")
    void unityAdsDidFinish(String placementId,UnityAdsFinishState finishState);
//- (void)unityAdsDidError:(UnityAdsError)error withMessage: (NSString *)message;
    @Method(selector = "unityAdsDidError:withMessage:")
    void unityAdsDidError(UnityAdsError error,String s);

}
