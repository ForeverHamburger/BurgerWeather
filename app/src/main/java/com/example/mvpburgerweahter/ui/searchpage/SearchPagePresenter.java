package com.example.mvpburgerweahter.ui.searchpage;

import android.util.Log;

import com.example.mvpburgerweahter.databean.LocationInfo;

import java.util.List;

public class SearchPagePresenter implements ISearchPageContract.ISearchPagePresenter{
    private final static String TAG = "SearchPagePresenter";
    private ISearchPageContract.ISearchPageView mSearchPageView;
    private SearchPageModel mSearchPageModel;

    public SearchPagePresenter(ISearchPageContract.ISearchPageView searchPageView) {
        this.mSearchPageView = searchPageView;
        mSearchPageModel = new SearchPageModel();
    }

    @Override
    public void getCitySearched() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LocationInfo> hotCityList = mSearchPageModel.getHotCityList();
                List<LocationInfo> savedCityList = mSearchPageModel.getSavedCityList();
                mSearchPageView.getCitySuccess(hotCityList,savedCityList);
            }
        }).start();
    }

    @Override
    public void getElasticSearch(String searchString) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LocationInfo> elasticSearchCityList = mSearchPageModel.getElasticSearchCityList(searchString);
                mSearchPageView.getElasticSearchSuccess(elasticSearchCityList);
            }
        }).start();
    }
}
