package com.cimohamed.mopub_mediation_fan;

import androidx.multidex.MultiDexApplication;

import com.cimohamed.mopub_mediation_fan.AdManager.AdManager;

/**
 *Developer By @c1m0h4m3dd0tj4r
 */
public class My_Application extends MultiDexApplication {
    public void onCreate() {
        super.onCreate();
        AdManager.initFb(this);
    }
}
