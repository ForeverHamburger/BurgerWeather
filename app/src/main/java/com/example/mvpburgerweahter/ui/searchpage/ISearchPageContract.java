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
        void getElasticSearch(String searchString);
    }

    interface ISearchPageModel {
        List<LocationInfo> getSavedCityList();
        List<LocationInfo> getHotCityList();
        List<LocationInfo> getElasticSearchCityList(String searchString);
    }

    interface ISearchPageView {
        void getCitySuccess(List<LocationInfo> hotCityList,List<LocationInfo> cityList);
        void getElasticSearchSuccess(List<LocationInfo> searchCityList);
        void getCityFailed();
    }
}
