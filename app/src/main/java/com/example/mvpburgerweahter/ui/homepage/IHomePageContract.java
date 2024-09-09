package com.example.mvpburgerweahter.ui.homepage;

import com.amap.api.location.AMapLocationListener;
import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;

import java.util.List;

public interface IHomePageContract {

    interface IHomePagePresenter {
        void getWeatherDetail();
    }

    interface IHomePageModel {
        LocationInfo getLocationMsg(AMapLocationListener aMapLocationListener);
        String getLocationByCityCode(String cityCode);
        NowWeatherInfo getNowWeatherInfo(String cityCode);
        List<HourlyWeatherInfo> getHourlyWeatherInfo(String cityCode);
        List<DailyWeatherInfo> getDailyWeatherInfo(String cityCode);
    }

    interface IHomePageView {
        void getWeatherSuccess(LocationInfo locationInfo,NowWeatherInfo nowWeatherInfo,List<HourlyWeatherInfo> hourlyWeatherInfoList,List<DailyWeatherInfo> dailyWeatherInfoList);

        void getWeatherFailed();
    }
}