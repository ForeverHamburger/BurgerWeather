package com.example.mvpburgerweahter.ui.detailpage;

import android.content.Context;

import com.example.mvpburgerweahter.databean.DailyWeatherInfo;

import java.util.List;

public class DetailPagePresenter implements IDetailPageContract.IDetailPagePresenter{
    private String mcityCode;
    private IDetailPageContract.IDetailPageView mdetailPageView;
    private IDetailPageContract.IDetailPageModel mdetailPageModel;

    public DetailPagePresenter(IDetailPageContract.IDetailPageView detailPageView) {
        this.mdetailPageView = detailPageView;
        mdetailPageModel = new DetailPageModel((Context) detailPageView);
    }

    @Override
    public void getWeatherDetail(String cityCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DailyWeatherInfo> infos = mdetailPageModel.getDailyWeatherInfo(cityCode);
                mdetailPageView.getWeatherSuccess(infos);
            }
        }).start();
    }

    @Override
    public void refreshWeatherDetail(String cityCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DailyWeatherInfo> infos = mdetailPageModel.refreshWeatherInfo(cityCode);
                mdetailPageView.getRefreshWeatherSuccess(infos);
            }
        }).start();
    }
}
