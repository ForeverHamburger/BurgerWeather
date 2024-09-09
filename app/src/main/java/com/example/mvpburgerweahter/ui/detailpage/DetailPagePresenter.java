package com.example.mvpburgerweahter.ui.detailpage;

import com.example.mvpburgerweahter.databean.DailyWeatherInfo;

import java.util.List;

public class DetailPagePresenter implements IDetailPageContract.IDetailPagePresenter{
    private String mcityCode;
    private IDetailPageContract.IDetailPageView mdetailPageView;
    private IDetailPageContract.IDetailPageModel mdetailPageModel;

    public DetailPagePresenter(IDetailPageContract.IDetailPageView detailPageView) {
        this.mdetailPageView = detailPageView;
        mdetailPageModel = new DetailPageModel();
    }

    @Override
    public void getWeatherDetail(String cityCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DailyWeatherInfo> dailyWeatherInfo = mdetailPageModel.getDailyWeatherInfo(cityCode);
                mdetailPageView.getWeatherSuccess(dailyWeatherInfo);
            }
        }).start();
    }
}
