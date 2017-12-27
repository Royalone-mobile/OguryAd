package com.example.ogury.sample_project_ogury;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import io.presage.Presage;
import io.presage.ads.PresageInterstitial;

public class MainActivity extends AppCompatActivity {

    private PresageInterstitial Interstitial_ogury;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start of Ogury SDK
        Presage.getInstance().setContext(this.getBaseContext());
        Presage.getInstance().start();

        //Set up ad unit
        Interstitial_ogury = new PresageInterstitial(this, "6c673db0-bd5d-0135-643e-0242ac120003");
        Interstitial_ogury.setPresageInterstitialCallback(presageInterstitialCallback);

        //load an Ad
        Interstitial_ogury.load();


        button1 = (Button) findViewById(R.id.but1);
        View.OnClickListener butclik1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Display an Ad
                if (Interstitial_ogury.canShow()) {
                    Interstitial_ogury.show();
                }
            }
        };
        button1.setOnClickListener(butclik1);

    }

    @Override
    public void onResume(){
        super.onResume();
        //Load an Ad
        Interstitial_ogury.load();
    }

    //Declacration callback Interstitial
    private PresageInterstitial.PresageInterstitialCallback presageInterstitialCallback = new PresageInterstitial.PresageInterstitialCallback() {
        @Override
        public void onAdAvailable() {
            Log.i("PRESAGE", "ad available");
        }

        @Override
        public void onAdNotAvailable() {
            Log.i("PRESAGE", "no ad available");
        }

        @Override
        public void onAdLoaded() {
            Log.i("PRESAGE", "an ad in loaded, ready to be shown");
        }

        @Override
        public void onAdDisplayed() {
            Log.i("PRESAGE", "ad displayed");
        }

        @Override
        public void onAdClosed() {
            Log.i("PRESAGE", "ad closed");
            Interstitial_ogury.load();
        }

        @Override
        public void onAdError(int code) {
            Log.i("PRESAGE", String.format("error with code %d", code));
        }
    };
}
