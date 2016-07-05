package com.koma.weather.contract;

import com.koma.weather.base.BasePresenter;
import com.koma.weather.base.BaseView;
import com.koma.weather.model.Weather;

/**
 * Created by koma on 7/4/16.
 */
public interface WeatherContract {
    interface MainView extends BaseView<MainPresenter> {
        void refreshWeather(Weather weather);
    }

    interface MainPresenter extends BasePresenter {
        void loadWeather();

        void hanldeError();

        void showCompleted();

        void notifyDataSetChanged(Weather weather);
    }
}
