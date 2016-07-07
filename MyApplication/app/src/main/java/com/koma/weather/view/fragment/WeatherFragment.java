package com.koma.weather.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.koma.weather.R;
import com.koma.weather.base.HidingScrollListener;
import com.koma.weather.contract.WeatherContract;
import com.koma.weather.model.Weather;
import com.koma.weather.utils.log.Logger;
import com.koma.weather.view.adapter.WeatherAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by koma on 7/4/16.
 */
public class WeatherFragment extends Fragment implements WeatherContract.MainView {
    private static final String TAG = WeatherFragment.class.getName();
    private Context mContext;
    private View mView;
    private WeatherContract.MainPresenter mPresenter;
    private WeatherAdapter mAdapter;
    private Weather mWeather = new Weather();
    FloatingActionButton fab;
    @Bind(R.id.swiprefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.iv_erro)
    ImageView mImageView;

    public WeatherFragment() {

    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.i(TAG, "onAttach");
        mContext = context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle instanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        Logger.i(TAG, "onCreateView");
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        return mView;
    }

    public int getLayoutId() {
        return R.layout.content_main;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.i(TAG, "onViewCreated");
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                fab.animate()
                        .translationY(fab.getHeight() /*+ fabBottomMargin*/)
                        .setInterpolator(new AccelerateInterpolator(2))
                        .start();
            }

            @Override
            public void onShow() {
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.i(TAG, "onActivityCreated");
        initEventAndData();
    }

    public void initEventAndData() {
        Logger.i(TAG, "initEventAndData");

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
        Logger.i(TAG, "setPresenter");
        mPresenter = presenter;
    }

    @Override
    public void refreshWeather(Weather weather) {
        Logger.i(TAG, "refreshWeather");
        mWeather = weather;
        mAdapter = new WeatherAdapter(mContext, weather);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCompleted() {
        Logger.i(TAG, "showCompleted");
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
