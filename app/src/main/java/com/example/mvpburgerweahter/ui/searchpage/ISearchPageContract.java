package com.example.mvpburgerweahter.ui.searchpage;

import com.amap.api.location.AMapLocationListener;
import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;

import java.util.List;

public interface ISearchPageContract {
    interface ISearchPagePresenter {
        void getCitySearched();
    }

    interface ISearchPageModel {
        List<String> getSavedCityCode();
    }

    interface ISearchPageView {
        void getCitySuccess();
        void getCityFailed();
    }
}
