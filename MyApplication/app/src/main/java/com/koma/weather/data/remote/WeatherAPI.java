package com.koma.weather.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koma.weather.model.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koma on 7/5/16.
 */
public class WeatherAPI {
    @SerializedName("HeWeather data service 3.0") @Expose
    public List<Weather> mHeWeatherDataService30s
            = new ArrayList<>();
}
