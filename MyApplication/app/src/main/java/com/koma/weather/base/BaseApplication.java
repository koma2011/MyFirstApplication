package com.koma.weather.base;

import android.app.Application;
import android.content.Context;

import com.koma.weather.data.remote.RetrofitSingleton;

/**
 * Created by koma on 6/30/16.
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        RetrofitSingleton.init();
    }

    public static Context getAppContext() {

        return mContext;
    }
}
