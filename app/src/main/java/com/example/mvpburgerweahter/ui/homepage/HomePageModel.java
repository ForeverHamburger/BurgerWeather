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
    public LocationInfo getLocationByCityCode(String cityCode) {
        LocationInfo locationInfo = null;
        if (infoDao.findByCityCode(cityCode) != null) {
            WeatherAndCityInfo infoString = infoDao.findByCityCode(cityCode);
            String locationJson = infoString.getLocationJson();
            locationInfo = JsonUtils.parseLocationJson(locationJson);
        } else {
            String cityByCityCode = CitySearchUtils.getCityByCityCode(cityCode);
            locationInfo = JsonUtils.parseLocationJson(cityByCityCode);
        }
        return locationInfo;
    }
    @Override
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
            infoDao.insertInfos(new WeatherAndCityInfo("now location",locationInformation));
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
        List<DailyWeatherInfo> infos = null;
        if (infoDao.findByCityCode(cityCode) != null) {
            WeatherAndCityInfo infoString = infoDao.findByCityCode(cityCode);
            String forecastJsonPerDay = infoString.getForecastJsonPerDay();
            infos = JsonUtils.parseDailyWeatherInfoJson(forecastJsonPerDay);
        } else {
            String dailyWeatherInfo = WeatherUtils.getDailyWeatherInfo(cityCode);
            infos = JsonUtils.parseDailyWeatherInfoJson(dailyWeatherInfo);
        }
        Log.d(TAG, "getHourlyWeatherInfo: "+ infos);
        return infos;
    }

    public void saveToDataBase(String cityCode) {
        if (infoDao.findByCityCode(cityCode) != null) {
            Log.d(TAG, "saveToDataBase: " + "数据库里有了兄弟们");
            return;
        } else {
            Log.d(TAG, "saveToDataBase: " + "存了兄弟们");
            String cityByCityCode = CitySearchUtils.getCityByCityCode(cityCode);
            String nowWeatherInfo = WeatherUtils.getNowWeatherInfo(cityCode);
            String hourlyWeatherInfo = WeatherUtils.getHourlyWeatherInfo(cityCode);
            String dailyWeatherInfo = WeatherUtils.getDailyWeatherInfo(cityCode);
            infoDao.insertInfos(new WeatherAndCityInfo(cityCode,cityByCityCode,
                    nowWeatherInfo,hourlyWeatherInfo,dailyWeatherInfo));
            Log.d(TAG, "saveToDataBase: " + "存完了兄弟们");
        }
    }

    public void updateToDataBase(String cityCode) {
        String cityByCityCode = CitySearchUtils.getCityByCityCode(cityCode);
        String nowWeatherInfo = WeatherUtils.getNowWeatherInfo(cityCode);
        String hourlyWeatherInfo = WeatherUtils.getHourlyWeatherInfo(cityCode);
        String dailyWeatherInfo = WeatherUtils.getDailyWeatherInfo(cityCode);
        infoDao.updateInfos(new WeatherAndCityInfo(cityCode,cityByCityCode,
                nowWeatherInfo,hourlyWeatherInfo,dailyWeatherInfo));
    }
}
