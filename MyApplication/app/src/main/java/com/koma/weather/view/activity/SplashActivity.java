package com.koma.weather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.koma.weather.MainActivity;
import com.koma.weather.utils.log.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by koma on 7/4/16.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private static final int DELAY_TIME = 2;
    //private static final int MESSAGE_TO_MAINACTIVITY = 0x00;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i(TAG, "onResume");
        //mHandler.sendEmptyMessageDelayed(MESSAGE_TO_MAINACTIVITY, DELAY_TIME * 1000);
        Observable.timer(DELAY_TIME, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                //activity切换的淡入淡出效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                SplashActivity.this.finish();
            }
        });
    }

    /*private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_TO_MAINACTIVITY) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                overridePendingTransition(0, 0);
            }
        }
    };*/
}
