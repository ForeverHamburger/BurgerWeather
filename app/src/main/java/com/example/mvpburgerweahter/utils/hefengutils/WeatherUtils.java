package com.example.mvpburgerweahter.utils.hefengutils;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherUtils extends HeFengBase{
    private static final String BASE_URL = "https://devapi.qweather.com/v7/weather/";
    private static final String TAG = "WeatherUtils";

    public static String getNowWeatherInfo(String locationID) {
        String resultResponse = "";
        String locationUrl = BASE_URL + "now" + "?" + "key=" + KEY + "&" +"location=" + locationID
                + "&" + "lang=" + "en";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(locationUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                resultResponse = response.body().string();
                Log.d(TAG, "getNowWeatherInfo: " + resultResponse);
            }
        } catch (IOException e) {
            Log.d(TAG, "getNowWeatherInfo: yichang");
        }
        return resultResponse;
    }

    public static String getHourlyWeatherInfo(String locationID) {
        String resultResponse = "";
        String locationUrl = BASE_URL + "24h" + "?" + "key=" + KEY + "&" +"location=" + locationID;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(locationUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                resultResponse = response.body().string();
                Log.d(TAG, "getHourlyWeatherInfo: " + resultResponse);
            }
        } catch (IOException e) {
            Log.d(TAG, "getHourlyWeatherInfo: yichang");
        }
        return resultResponse;
    }

    public static String getDailyWeatherInfo(String locationID) {
        String resultResponse = "";
        String locationUrl = BASE_URL + "7d" + "?" + "key=" + KEY + "&" +"location=" + locationID
                + "&" + "lang=" + "en";;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(locationUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                resultResponse = response.body().string();
                Log.d(TAG, "getDailyWeatherInfo: " + resultResponse);
            }
        } catch (IOException e) {
            Log.d(TAG, "getDailyWeatherInfo: yichang");
        }
        return resultResponse;
    }

}
