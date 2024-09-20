package com.example.mvpburgerweahter.utils;

import android.content.Context;
import android.util.Log;

import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtilsTAG";

    public static LocationInfo parseLocationJson(String jsonStr) {
        LocationInfo locationInfo = null;
        try {
            JSONObject locationJson = new JSONObject(jsonStr);
            JSONArray location = locationJson.optJSONArray("location");
            JSONObject jsonObject = location.optJSONObject(0);
            Log.d(TAG, "parseJsonData: " + jsonObject);
            Gson gson = new Gson();
            locationInfo = gson.fromJson(String.valueOf(jsonObject), LocationInfo.class);
        } catch (JSONException e) {
            Log.d(TAG, "parseJsonData: 解析Json数据失败");
        }
        return locationInfo;
    }

    public static NowWeatherInfo parseNowWeatherJson(String jsonStr) {
        NowWeatherInfo info = null;
        try {
            JSONObject locationJson = new JSONObject(jsonStr);
            String nowJson = locationJson.optString("now");
            Gson gson = new Gson();
            info = gson.fromJson(String.valueOf(nowJson), NowWeatherInfo.class);
            Log.d(TAG, "parseJsonData: " + nowJson);
        } catch (JSONException e) {
            Log.d(TAG, "parseJsonData: 解析Json数据失败");
        }
        return info;
    }

    public static List<HourlyWeatherInfo> parseHourlyWeatherInfoJson(String jsonStr) {
        List<HourlyWeatherInfo> infos = new ArrayList<>();
        try {
            JSONObject locationJson = new JSONObject(jsonStr);
            JSONArray hourly = locationJson.optJSONArray("hourly");
            Log.d(TAG, "parseJsonData: " + hourly);

            Gson gson = new Gson();
            for (int i = 0; i < 24; i++) {
                JSONObject jsonObject = hourly.optJSONObject(i);
                HourlyWeatherInfo info = gson.fromJson(String.valueOf(jsonObject), HourlyWeatherInfo.class);
                infos.add(info);
            }
        } catch (JSONException e) {
            Log.d(TAG, "parseJsonData: 解析Json数据失败");
        }
        return infos;
    }

    public static List<DailyWeatherInfo> parseDailyWeatherInfoJson(String jsonStr) {
        List<DailyWeatherInfo> infos = new ArrayList<>();
        try {
            JSONObject locationJson = new JSONObject(jsonStr);
            JSONArray daily = locationJson.optJSONArray("daily");

            Gson gson = new Gson();
            for (int i = 0; i < 7; i++) {
                JSONObject jsonObject = daily.optJSONObject(i);
                DailyWeatherInfo info = gson.fromJson(String.valueOf(jsonObject), DailyWeatherInfo.class);
                infos.add(info);
            }
        } catch (JSONException e) {
            Log.d(TAG, "parseJsonData: 解析Json数据失败");
        }
        return infos;
    }

    public static List<LocationInfo> parseHotCityJson(String jsonStr) {
        List<LocationInfo> infos = new ArrayList<>();
        try {
            JSONObject locationJson = new JSONObject(jsonStr);
            JSONArray topCityList = locationJson.optJSONArray("topCityList");

            for (int i = 0; i < 20; i++) {
                Gson gson = new Gson();
                LocationInfo locationInfo = gson.fromJson(topCityList.get(i).toString(), LocationInfo.class);
                infos.add(locationInfo);
            }
        } catch (JSONException e) {
            Log.d(TAG, "parseJsonData: 解析Json数据失败");
        }
        return infos;
    }

    public static List<LocationInfo> parseSearchCityJson(String jsonStr) {
        Log.d(TAG, "test: " + jsonStr);
        List<LocationInfo> infos = new ArrayList<>();
        try {
            JSONObject locationJson = new JSONObject(jsonStr);
            JSONArray topCityList = locationJson.optJSONArray("location");
            for (int i = 0; i < 10; i++) {
                Gson gson = new Gson();
                LocationInfo locationInfo = gson.fromJson(topCityList.get(i).toString(), LocationInfo.class);
                infos.add(locationInfo);
            }
        } catch (JSONException e) {
            Log.d(TAG, "parseJsonData: 解析Json数据失败");
        }
        return infos;
    }
}
