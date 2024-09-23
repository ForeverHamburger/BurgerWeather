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
        Log.d(TAG, "getWeatherDetail: " + mcityCode);
        if (mcityCode.equals("MyLocation")) {
            Log.d(TAG, "getWeatherDetail: "+ "正在获取本地位置");
            // 若是暂时未能获取到，则先展示此前的界面
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    // 获取位置Json信息
//                    LocationInfo locationInfo = homePageModel.getCityByTitude("longitude", "latitude");
//                    Log.d(TAG, "run1111: "+ locationInfo);
//
//                    if (locationInfo == null) {
//                        getLocation();
//                    } else {
//                        homePageModel.saveToDataBase(locationInfo.getId());
//
//                        NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo("now location");
//                        List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo("now location");
//                        List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo("now location");
//                        Log.d(TAG, "run: " + nowWeatherInfo);
//
//                        homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
//                    }
//                }
//            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    LocationInfo locationInfo = homePageModel.getLocationByCityCode("101110102");

                    homePageModel.saveToDataBase("101110102");

                    NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo(locationInfo.getId());
                    List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo(locationInfo.getId());
                    List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo(locationInfo.getId());
                    Log.d(TAG, "run: " + nowWeatherInfo);

                    homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
                }
            }).start();

        } else {
            // 非本地则调用此方法
            Log.d(TAG, "getWeatherDetail: " + "获取非本地位置");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LocationInfo locationInfo = homePageModel.getLocationByCityCode(mcityCode);

                    homePageModel.saveToDataBase(mcityCode);

                    NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo(locationInfo.getId());
                    List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo(locationInfo.getId());
                    List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo(locationInfo.getId());
                    Log.d(TAG, "run: " + nowWeatherInfo);

                    homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
                }
            }).start();
        }

    }

    @Override
    public void refreshWeatherPage(String cityCode) {
        if (cityCode.equals("MyLocation")) {
            Log.d(TAG, "refreshWeatherPage: "+ "正在获取本地位置");
            // 获取位置信息
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
                                LocationInfo locationInfo = homePageModel.getCityByTitudeUpdate(longitude, latitude);
                                Log.d(TAG, "run: "+ locationInfo);

                                homePageModel.updateToDataBase(locationInfo.getId(),"now location");

                                Log.d("hhhhahaaha", "run: " + locationInfo.getName());

                                NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo("now location");
                                List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo("now location");
                                List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo("now location");
                                Log.d(TAG, "run: " + nowWeatherInfo);

                                homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
                            }
                        }).start();
                    }
                }
            });
        } else {
            // 非本地则调用此方法
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LocationInfo locationInfo = homePageModel.getLocationByCityCode(mcityCode);
                    homePageModel.updateToDataBase(mcityCode);
                    NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo(locationInfo.getId());
                    List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo(locationInfo.getId());
                    List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo(locationInfo.getId());
                    Log.d(TAG, "run: " + nowWeatherInfo);

                    homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
                }
            }).start();
        }
    }

    private void getLocation() {
        Log.d(TAG, "getLocation: "+"12312");
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
                            LocationInfo locationInfo = homePageModel.getCityByTitudeUpdate(longitude, latitude);
                            Log.d(TAG, "getLocation: "+ locationInfo);

                            homePageModel.saveToDataBase("now location");

                            Log.d("hhhhahaaha", "getLocation: " + locationInfo.getName());

                            NowWeatherInfo nowWeatherInfo = homePageModel.getNowWeatherInfo("now location");
                            List<HourlyWeatherInfo> hourlyWeatherInfos = homePageModel.getHourlyWeatherInfo("now location");
                            List<DailyWeatherInfo> dailyWeatherInfos = homePageModel.getDailyWeatherInfo("now location");
                            Log.d(TAG, "run: " + nowWeatherInfo);

                            homePageView.getWeatherSuccess(locationInfo,nowWeatherInfo,hourlyWeatherInfos,dailyWeatherInfos);
                        }
                    }).start();
                }
            }
        });
    }
}
