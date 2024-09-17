package com.example.mvpburgerweahter.ui.homepage;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;
import com.example.mvpburgerweahter.utils.hefengutils.CitySearchUtils;
import com.example.mvpburgerweahter.utils.JsonUtils;

import java.text.DecimalFormat;
import java.util.List;

public class HomePagePresenter implements IHomePageContract.IHomePagePresenter{
    private static final String TAG = "HomePagePresenter";
    private IHomePageContract.IHomePageView homePageView;
    private HomePageModel homePageModel;
    private String mcityCode;
    public HomePagePresenter(IHomePageContract.IHomePageView view,Context context,String cityCode) {
        homePageModel = new HomePageModel(context);
        this.homePageView = view;
        this.mcityCode = cityCode;
    }
    @Override
    public void getWeatherDetail() {
        if (mcityCode.equals("MyLocation")) {
            homePageModel.getLocationMsg(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation.getErrorCode() == 0) {
                        // 获取经度纬度
                        DecimalFormat decimalFormat = new DecimalFormat("#.00");
                        String longitude = decimalFormat.format(aMapLocation.getLongitude());
                        String latitude = decimalFormat.format(aMapLocation.getLatitude());
                        Log.d(TAG, "onLocationChanged:" + longitude + "," + latitude);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 获取位置Json信息
                                String locationInformation = CitySearchUtils.getCityByTitude(longitude, latitude);
                                LocationInfo locationInfo = JsonUtils.parseLocationJson(locationInformation);
                                Log.d(TAG, "run: "+ locationInfo);

                                NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo(locationInfo.getId());
                                List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo(locationInfo.getId());
                                List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo(locationInfo.getId());
                                Log.d(TAG, "run: " + nowWeatherInfo);

                                homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
                            }
                        }).start();
                    }
                }
            });
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String locationByCityCode = homePageModel.getLocationByCityCode(mcityCode);
                    LocationInfo locationInfo = JsonUtils.parseLocationJson(locationByCityCode);

                    NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo(locationInfo.getId());
                    List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo(locationInfo.getId());
                    List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo(locationInfo.getId());
                    Log.d(TAG, "run: " + nowWeatherInfo);

                    homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
                }
            }).start();
        }




    }
}
