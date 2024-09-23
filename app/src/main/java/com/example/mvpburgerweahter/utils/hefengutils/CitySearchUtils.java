package com.example.mvpburgerweahter.utils.hefengutils;

import android.util.Log;

import androidx.core.view.accessibility.AccessibilityViewCommand;

import java.io.IOException;
import java.security.Key;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CitySearchUtils extends HeFengBase{
    private static final String BASE_URL = "https://geoapi.qweather.com/v2/city/lookup";
    private static final String TAG = "CitySearchUtils";
    public static String getCityByCityCode(String cityCode) {
        String resultResponse = "";
        String citySearchUrl = BASE_URL + "?" + "key=" + KEY + "&" + "location=" + cityCode
                + "&" + "number=" + "1" +  "&" + "lang=" + "en";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(citySearchUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                resultResponse = response.body().string();
                Log.d(TAG, "getCityByCityCode: " + resultResponse);
            }
        } catch (IOException e) {
            Log.d(TAG, "getCityByCityCode: error");
        }
        return resultResponse;
    }

    public static String getCityByTitude(String longitude,String latitude) {
        String resultResponse = "";
        String citySearchUrl = BASE_URL + "?" + "key=" + KEY + "&" + "location=" + longitude + ","
                + latitude + "&" + "number=" + "1" + "&" + "lang=" + "en";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(citySearchUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                resultResponse = response.body().string();
                Log.d(TAG, "getCityByTitude: " + resultResponse);
            }
        } catch (IOException e) {
            Log.d(TAG, "getCityByTitude: error");
        }
        return resultResponse;
    }

    public static String getCityByNameSearch(String cityName) {
        String resultResponse = "";
        String citySearchUrl = BASE_URL + "?" + "key=" + KEY + "&" +"location=" + cityName
                + "&" + "number=" + "10"  + "&" + "range=" + "cn";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(citySearchUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                resultResponse = response.body().string();
                Log.d(TAG, "getCityByNameSearch: " + resultResponse);
            }
        } catch (IOException e) {
            Log.d(TAG, "getCityByNameSearch: error");
        }
        return resultResponse;
    }

    public static String getHotCity() {
        String resultResponse = "";
        String citySearchUrl = "https://geoapi.qweather.com/v2/city/top" + "?" + "key=" + KEY + "&" +"range=" + "cn"
                + "&" + "number=" + "20" + "&" + "lang=" + "en";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(citySearchUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                resultResponse = response.body().string();
                Log.d(TAG, "getHotCity: " + resultResponse);
            }
        } catch (IOException e) {
            Log.d(TAG, "getHotCity: error");
        }
        return resultResponse;
    }
}
