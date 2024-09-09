package com.example.mvpburgerweahter.ui.detailpage;

import com.amap.api.location.AMapLocationListener;
import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;

import java.util.List;

public interface IDetailPageContract {

    interface IDetailPagePresenter {
        void getWeatherDetail(String cityCode);
    }

    interface IDetailPageModel {
        List<DailyWeatherInfo> getDailyWeatherInfo(String cityCode);
    }

    interface IDetailPageView {
        void getWeatherSuccess(List<DailyWeatherInfo> dailyWeatherInfoList);
        void getWeatherFailed();
    }
}
