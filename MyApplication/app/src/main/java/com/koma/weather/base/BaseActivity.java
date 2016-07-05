package com.koma.weather.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.koma.weather.utils.log.Logger;

import butterknife.ButterKnife;

/**
 * Created by koma on 6/30/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getName();

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        Logger.i(TAG, "onCreate");
        setContentView(getLayout());
        ButterKnife.bind(this);
    }

    protected void setCommonBackToolBack(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i(TAG, "onDestroy");
        ButterKnife.unbind(this);
    }

    protected abstract int getLayout();
}
