package com.cimohamed.mopub_mediation_fan.AdManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.cimohamed.mopub_mediation_fan.R;
import com.facebook.ads.AudienceNetworkAds;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import com.mopub.nativeads.AdapterHelper;
import com.mopub.nativeads.FacebookAdRenderer;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.ViewBinder;
/**
*Developer By @c1m0h4m3dd0tj4r
 */
public class AdManager {
    private static final String LOG = "admanager";
    private static final String MOPUB_BANNNER = "b195f8dd8ded45fe847ad89ed1d016da";
    public static final String MOPUB_FULLSCREEN = "24534e1901884e398f1253216226017e";
    private static final String MOPUB_NATIVE = "11a17b188668469fb0412708c3d16813";
    public static ProgressDialog dialog;

    public static void loadFullScreen(final Activity activity, final Intent mintent, String str) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        dialog = progressDialog;
        progressDialog.setMessage("Ads Loading Please Wait....");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (!MoPub.isSdkInitialized()) {
            initMopubSdk(activity);
        }
        if (isNetworkConnected(activity)) {
            dialog.show();
        }
        MoPubInterstitial moPubInterstitial = new MoPubInterstitial(activity, str);
        moPubInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
            public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
                Log.d(AdManager.LOG, "onInterstitialLoaded");
                moPubInterstitial.show();
                if (AdManager.dialog.isShowing()) {
                    AdManager.dialog.dismiss();
                }
            }

            public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
                Log.d(AdManager.LOG, "onInterstitialFailed error=" + moPubErrorCode.name());
                if (AdManager.dialog.isShowing()) {
                    AdManager.dialog.dismiss();
                }
                if (mintent != null) {
                    activity.startActivity(mintent);
                }
            }

            public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
                Log.d(AdManager.LOG, "onInterstitialShown");
            }

            public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
                Log.d(AdManager.LOG, "onInterstitialClicked");
            }

            public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
                if (AdManager.dialog.isShowing()) {
                    AdManager.dialog.dismiss();
                }
                if (mintent != null) {
                    activity.startActivity(mintent);
                }
                Log.d(AdManager.LOG, "onInterstitialDismissed");
            }
        });
        moPubInterstitial.load();
    }

    public static void loadBannerAd(Activity activity, ViewGroup viewGroup) {
        if (!MoPub.isSdkInitialized()) {
            initMopubSdk(activity);
        }
        MoPubView moPubView = new MoPubView(activity);
        viewGroup.addView(moPubView);
        moPubView.setAdSize(MoPubView.MoPubAdSize.HEIGHT_50);
        moPubView.setAdUnitId(MOPUB_BANNNER);
        moPubView.loadAd();
    }

    public static void loadBannerAd(Activity activity, ViewGroup viewGroup, MoPubView.MoPubAdSize moPubAdSize) {
        if (!MoPub.isSdkInitialized()) {
            initMopubSdk(activity);
        }
        MoPubView moPubView = new MoPubView(activity);
        viewGroup.addView(moPubView);
        moPubView.setAdSize(moPubAdSize);
        moPubView.setAdUnitId(MOPUB_BANNNER);
        moPubView.loadAd();
    }

    public static void loadNativeAd(final Activity activity, final ViewGroup viewGroup) {
        if (!MoPub.isSdkInitialized()) {
            initMopubSdk(activity);
        }
        MoPubNative moPubNative = new MoPubNative(activity, MOPUB_NATIVE, new MoPubNative.MoPubNativeNetworkListener() {
            public void onNativeLoad(NativeAd nativeAd) {
                viewGroup.addView(new AdapterHelper(activity, 0, 2).getAdView((View) null, (ViewGroup) null, nativeAd, new ViewBinder.Builder(0).build()));
                Log.d(AdManager.LOG, "native ad loaded");
            }

            public void onNativeFail(NativeErrorCode nativeErrorCode) {
                Log.d(AdManager.LOG, "Native Ad Failed To Load error=" + nativeErrorCode.name());
            }
        });
        FacebookAdRenderer facebookAdRenderer = new FacebookAdRenderer(new FacebookAdRenderer.FacebookViewBinder.Builder(R.layout.native_ad_fan_list_item).titleId(R.id.native_title).textId(R.id.native_text).mediaViewId(R.id.native_media_view).adIconViewId(R.id.native_icon).callToActionId(R.id.native_cta).adChoicesRelativeLayoutId(R.id.native_privacy_information_icon_layout).build());
        MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(new ViewBinder.Builder(R.layout.native_ad_list_item).titleId(R.id.native_title).textId(R.id.native_text).mainImageId(R.id.native_main_image).iconImageId(R.id.native_icon_image).callToActionId(R.id.native_cta).privacyInformationIconImageId(R.id.native_privacy_information_icon_image).sponsoredTextId(R.id.native_sponsored_text_view).build());
        moPubNative.registerAdRenderer(facebookAdRenderer);
        moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);
        moPubNative.makeRequest();
    }

    private static boolean isNetworkConnected(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static void initMopubSdk(Context context) {
        MoPub.initializeSdk(context, new SdkConfiguration.Builder(MOPUB_FULLSCREEN).build(), initSdkListener());
    }

    public static void initFb(Context context) {
        AudienceNetworkAds.initialize(context);
        Log.d(LOG, "AudienceNetworkAds initialize=" + AudienceNetworkAds.isInitialized(context));
    }

    private static SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            public void onInitializationFinished() {
                Log.d(AdManager.LOG, "onInitializationFinished");
            }
        };
    }
}
