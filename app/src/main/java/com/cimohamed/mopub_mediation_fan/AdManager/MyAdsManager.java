package com.cimohamed.mopub_mediation_fan.AdManager;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import com.mopub.mobileads.MoPubView;

/**
 *Developer By @c1m0h4m3dd0tj4r
 */
public class MyAdsManager {
    public static void Load_Native(Activity activity, LinearLayout linearLayout) {
        AdManager.loadNativeAd(activity, linearLayout);
    }

    public static void Load_FB_Native_Banner(Activity activity, LinearLayout linearLayout) {
        AdManager.loadBannerAd(activity, linearLayout, MoPubView.MoPubAdSize.HEIGHT_90);
    }

    public static void Load_FB_Banner(Activity activity, LinearLayout linearLayout) {
        AdManager.loadBannerAd(activity, linearLayout);
    }

    public static void FBInterstitial(Activity activity, Intent intent, String str) {
        AdManager.loadFullScreen(activity, intent, str);
    }
}
