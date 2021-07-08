package com.cimohamed.mopub_mediation_fan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cimohamed.mopub_mediation_fan.AdManager.AdManager;
import com.facebook.ads.AudienceNetworkAds;

/**
 *Developer By @c1m0h4m3dd0tj4r
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (!AudienceNetworkAds.isInitialized(this)) {
            AudienceNetworkAds.initialize(this);
        }
        AdManager.initMopubSdk(this);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 3500);
    }
}