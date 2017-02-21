package com.demo.retrofitwithrecyclerview.utils;

import android.app.Application;

import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by Android Dev on 2/6/2017.
 */

public class MessageHubApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
