package com.hub.gui.wag;

import android.app.Application;

import com.squareup.picasso.Picasso;

import timber.log.Timber;

public class Wag extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Picasso.Builder picassoBuilder = new Picasso.Builder(getApplicationContext());

        /*if(BuildConfig.DEBUG){
            picassoBuilder.loggingEnabled(true);
            picassoBuilder.indicatorsEnabled(true);
        }
        else{
            picassoBuilder.loggingEnabled(false);
            picassoBuilder.indicatorsEnabled(false);
        }*/

        Picasso picasso = picassoBuilder.build();
        Picasso.setSingletonInstance(picasso);


    }
}
