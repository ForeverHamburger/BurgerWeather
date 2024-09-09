package com.example.mvpburgerweahter.ui.homepage;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocationListener;
import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;
import com.example.mvpburgerweahter.utils.CitySearchUtils;
import com.example.mvpburgerweahter.utils.JsonUtils;
import com.example.mvpburgerweahter.utils.LocationUtils;
import com.example.mvpburgerweahter.utils.WeatherUtils;

import java.util.List;

public class HomePageModel implements IHomePageContract.IHomePageModel{
    private static final String TAG = "HomePageModel";
    private Context context;
    private LocationInfo locationInfo;
    public  HomePageModel(Context context) {
        this.context = context;
    }
    @Override
    public LocationInfo getLocationMsg(AMapLocationListener aMapLocationListener) {
        LocationUtils locationUtils = new LocationUtils(context.getApplicationContext(), aMapLocationListener);
        locationUtils.startLocation();
        return locationInfo;
    }

    @Override
    public String getLocationByCityCode(String cityCode) {
        String cityByCityCode = CitySearchUtils.getCityByCityCode(cityCode);
        return cityByCityCode;
    }


    @Override
    public NowWeatherInfo getNowWeatherInfo(String cityCode) {
        String nowWeatherInfo = WeatherUtils.getNowWeatherInfo(cityCode);
        NowWeatherInfo info = JsonUtils.parseNowWeatherJson(nowWeatherInfo);
        return info;
    }

    @Override
    public List<HourlyWeatherInfo> getHourlyWeatherInfo(String cityCode) {
        String hourlyWeatherInfo = WeatherUtils.getHourlyWeatherInfo(cityCode);
        List<HourlyWeatherInfo> infos = JsonUtils.parseHourlyWeatherInfoJson(hourlyWeatherInfo);
        Log.d(TAG, "getHourlyWeatherInfo: "+ infos);
        return infos;
    }

    @Override
    public List<DailyWeatherInfo> getDailyWeatherInfo(String cityCode) {
        return null;
    }
}
