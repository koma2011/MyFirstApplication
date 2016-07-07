package com.koma.weather.presenter;

import android.support.annotation.NonNull;

import com.koma.weather.contract.WeatherContract;
import com.koma.weather.data.remote.RetrofitSingleton;
import com.koma.weather.model.Weather;
import com.koma.weather.utils.log.Logger;
import com.koma.weather.view.fragment.WeatherFragment;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by koma on 7/4/16.
 */
public class WeatherPresenter implements WeatherContract.MainPresenter {
    private static final String TAG = "WeatherPresenter";
    private CompositeSubscription mCompositeSubscription;
    private Weather mWeather = new Weather();
    private Subscriber<Weather> mSubscriber;
    private WeatherContract.MainView mView;

    public WeatherPresenter(@NonNull WeatherFragment weatherFragment) {
        mView = weatherFragment;
        mCompositeSubscription = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        Logger.i(TAG, "subscribe");
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        if (mSubscriber == null) {
            mSubscriber = new Subscriber<Weather>() {
                @Override
                public void onCompleted() {
                    showCompleted();
                }

                @Override
                public void onError(Throwable e) {
                    Logger.e(TAG, e.toString());
                }

                @Override
                public void onNext(Weather weather) {
                    Logger.i(TAG, "subscribe  onNext");
                    mWeather.status = weather.status;
                    mWeather.aqi = weather.aqi;
                    mWeather.basic = weather.basic;
                    mWeather.suggestion = weather.suggestion;
                    mWeather.now = weather.now;
                    mWeather.dailyForecast = weather.dailyForecast;
                    mWeather.hourlyForecast = weather.hourlyForecast;
                    notifyDataSetChanged(mWeather);
                }
            };
        }
        loadWeather();
    }

    @Override
    public void unSubscribe() {
        Logger.i(TAG, "unSubscribe");
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void loadWeather() {
        Logger.i(TAG, "loadWeather");
        addSubscription(RetrofitSingleton.getInstance().fetchWeather("深圳").subscribe(mSubscriber));
    }

    private void addSubscription(Subscription subscription) {
        Logger.i(TAG, "addSubscription");
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);

    }

    @Override
    public void notifyDataSetChanged(Weather weather) {
        Logger.i(TAG, "notifyDataSetChanged");
        mView.refreshWeather(weather);
    }

    @Override
    public void hanldeError() {
        Logger.i(TAG, "handleError");

    }

    @Override
    public void showCompleted() {
        Logger.i(TAG, "showCompleted");
        mView.showCompleted();
    }
}
