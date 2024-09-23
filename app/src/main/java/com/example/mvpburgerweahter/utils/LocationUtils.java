package com.example.mvpburgerweahter.utils;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LocationUtils {
    private static final String KEY = "43ff8c31aadb43368907c21f96586fb0";
    private static final String TAG = "LocationUtilsTAG";
    private static final String BASE_URL = "https://restapi.amap.com/v3/geocode/regeo";
    private static AMapLocationClient mLocationClient;
    private static AMapLocationListener mLocationListener;
    private static AMapLocationClientOption mLocationClientOption;
    public LocationUtils(Context context,AMapLocationListener locationListener) {
        mLocationListener = locationListener;
        initLocationClient(context);
    }

    private void initLocationClient(Context context) {

        AMapLocationClient.updatePrivacyShow(context, true, true);
        AMapLocationClient.updatePrivacyAgree(context, true);

        try {
            mLocationClient = new AMapLocationClient(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        // 设置定位模式
        // 高精度定位模式：会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息。
        mLocationClientOption = new AMapLocationClientOption();
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationClientOption.setOnceLocation(true);
        mLocationClientOption.setLocationCacheEnable(false);
        mLocationClient.stopLocation();

        // 设置定位客户端的配置
        mLocationClient.setLocationOption(mLocationClientOption);
        mLocationClient.setLocationListener(mLocationListener);
    }

    // 启动定位
    public void startLocation() {
        if (mLocationClient != null && mLocationListener != null) {
            mLocationClient.startLocation();
        }
    }

    // 停止定位
    public void stopLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
        }
    }
}
