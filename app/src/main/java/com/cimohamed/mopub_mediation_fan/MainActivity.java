package com.cimohamed.mopub_mediation_fan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cimohamed.mopub_mediation_fan.AdManager.AdManager;
import com.cimohamed.mopub_mediation_fan.AdManager.MyAdsManager;
import com.facebook.ads.AudienceNetworkAds;

/**
 *Developer By @c1m0h4m3dd0tj4r
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView show_inters=findViewById(R.id.show_inters);


        MyAdsManager.Load_Native(this, (LinearLayout) findViewById(R.id.native_ad_container));
        MyAdsManager.Load_FB_Native_Banner(this, (LinearLayout) findViewById(R.id.native_banner_ad_container));
        MyAdsManager.Load_FB_Banner(this, (LinearLayout) findViewById(R.id.banner_container));

        show_inters.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
            MyAdsManager.FBInterstitial(MainActivity.this, intent, AdManager.MOPUB_FULLSCREEN);
        });
    }
}