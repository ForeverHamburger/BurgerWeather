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
        void saveCityToCityList(LocationInfo info);
        void deleteCityFromCityList(String cityCode);
    }

    interface ISearchPageModel {
        List<LocationInfo> getSavedCityList();
        List<LocationInfo> getHotCityList();
        List<LocationInfo> getElasticSearchCityList(String searchString);
        void saveCityToDatabase(LocationInfo info);
        void deleteCityFromDatabase(String cityCode);
    }

    interface ISearchPageView {
        void getCitySuccess(List<LocationInfo> hotCityList,List<LocationInfo> cityList,List<NowWeatherInfo> savedWeatherList);
        void getElasticSearchSuccess(List<LocationInfo> searchCityList);
        void updateCityManager(List<LocationInfo> savedCityList,List<NowWeatherInfo> savedWeatherList);
        void getCityFailed();
    }
}
