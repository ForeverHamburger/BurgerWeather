package com.example.mvpburgerweahter.ui.homepage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.room.Room;

import com.amap.api.location.AMapLocationListener;
import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;
import com.example.mvpburgerweahter.room.InfoDao;
import com.example.mvpburgerweahter.room.InfoDatabase;
import com.example.mvpburgerweahter.room.WeatherAndCityInfo;
import com.example.mvpburgerweahter.utils.hefengutils.CitySearchUtils;
import com.example.mvpburgerweahter.utils.JsonUtils;
import com.example.mvpburgerweahter.utils.LocationUtils;
import com.example.mvpburgerweahter.utils.hefengutils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePageModel implements IHomePageContract.IHomePageModel{
    private static final String TAG = "HomePageModel";
    private Context context;
    private InfoDao infoDao;
    public  HomePageModel(Context context) {
        this.context = context;
        InfoDatabase infoDatabase = Room.databaseBuilder(context, InfoDatabase.class, "info.db")
                .build();
        infoDao = infoDatabase.getInfoDao();
    }
    @Override
    public LocationInfo getLocationMsg(AMapLocationListener aMapLocationListener) {
        LocationInfo locationInfo = null;
        LocationUtils locationUtils = new LocationUtils(context.getApplicationContext(), aMapLocationListener);
        locationUtils.startLocation();
        return locationInfo;
    }

    @Override
    public String getLocationByCityCode(String cityCode) {
        String cityByCityCode = CitySearchUtils.getCityByCityCode(cityCode);
        return cityByCityCode;
    }

    public LocationInfo getCityByTitude(String longitude,String latitude) {
        LocationInfo locationInfo = null;
        Log.d(TAG, "getCityByTitude: " + "getlocation start");
        if (infoDao.findByCityCode("now location") != null) {
            Log.d(TAG, "getCityByTitude: " + "getlocation by database");
            WeatherAndCityInfo nowLocation = infoDao.findByCityCode("now location");
            locationInfo = JsonUtils.parseLocationJson(nowLocation.getLocationJson());
        } else {
            Log.d(TAG, "getCityByTitude: " + "getlocation by network");
            String locationInformation = CitySearchUtils.getCityByTitude(longitude, latitude);
            locationInfo = JsonUtils.parseLocationJson(locationInformation);
        }
        return locationInfo;
    }


    @Override
    public NowWeatherInfo getNowWeatherInfo(String cityCode) {
        NowWeatherInfo info = null;
        if (infoDao.findByCityCode(cityCode) != null) {
            WeatherAndCityInfo infoString = infoDao.findByCityCode(cityCode);
            info = JsonUtils.parseNowWeatherJson(infoString.getWeatherJson());
        } else {
            String nowWeatherInfo = WeatherUtils.getNowWeatherInfo(cityCode);
            info = JsonUtils.parseNowWeatherJson(nowWeatherInfo);
        }
        Log.d(TAG, "getNowWeatherInfo: "+ info);
        return info;
    }

    @Override
    public List<HourlyWeatherInfo> getHourlyWeatherInfo(String cityCode) {
        List<HourlyWeatherInfo> infos = null;
        if (infoDao.findByCityCode(cityCode) != null) {
            WeatherAndCityInfo infoString = infoDao.findByCityCode(cityCode);
            String forcastJsonPerHour = infoString.getForcastJsonPerHour();
            infos = JsonUtils.parseHourlyWeatherInfoJson(forcastJsonPerHour);
        } else {
            String hourlyWeatherInfo = WeatherUtils.getHourlyWeatherInfo(cityCode);
            infos = JsonUtils.parseHourlyWeatherInfoJson(hourlyWeatherInfo);
        }
        Log.d(TAG, "getHourlyWeatherInfo: "+ infos);
        return infos;
    }

    @Override
    public List<DailyWeatherInfo> getDailyWeatherInfo(String cityCode) {
        return null;
    }


}
