package com.koma.weather.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koma.weather.contract.WeatherContract;
import com.koma.weather.utils.log.Logger;

import butterknife.ButterKnife;

/**
 * Created by koma on 7/4/16.
 */
public class WeatherFragment extends Fragment implements WeatherContract.MainView {
    private static final String TAG = WeatherFragment.class.getName();
    private View mView;
    private WeatherContract.MainPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.i(TAG, "onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle instanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        Logger.i(TAG, "onCreateView");
        return mView;
    }

    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.i(TAG, "onViewCreated");
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.i(TAG, "onActivityCreated");
        initEventAndData();
    }

    public void initEventAndData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.i(TAG, "onDestroyView");
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i(TAG, "onDestroy");
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    @Override
    public void setPresenter(@NonNull WeatherContract.MainPresenter presenter) {
        mPresenter = presenter;
    }
}
