package com.koma.weather.utils.log;

import android.util.Log;

/**
 * Created by koma on 6/30/16.
 */
public class Logger {
    private static final boolean IS_DEBUG = true;

    public static void i(String TAG, String message) {
        if (IS_DEBUG) {
            Log.i(TAG, message);
        }
    }

    public static void e(String TAG, String message) {
        if (IS_DEBUG) {
            Log.e(TAG, message);
        }
    }
}
