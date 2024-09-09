package com.example.mvpburgerweahter.ui.detailpage;

import android.util.Log;

import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.utils.JsonUtils;
import com.example.mvpburgerweahter.utils.WeatherUtils;

import java.util.List;

public class DetailPageModel implements IDetailPageContract.IDetailPageModel{
    private static final String TAG = "DetailPageModel";
    @Override
    public List<DailyWeatherInfo> getDailyWeatherInfo(String cityCode) {
        String dailyWeatherInfo = WeatherUtils.getDailyWeatherInfo(cityCode);
        Log.d(TAG, "getHourlyWeatherInfo: "+ dailyWeatherInfo);
        List<DailyWeatherInfo> infos = JsonUtils.parseDailyWeatherInfoJson(dailyWeatherInfo);
        Log.d(TAG, "getHourlyWeatherInfo: "+ infos);
        return infos;
    }
}
