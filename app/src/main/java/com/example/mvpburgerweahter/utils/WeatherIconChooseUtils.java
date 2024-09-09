package com.example.mvpburgerweahter.utils;

import com.example.mvpburgerweahter.R;

import java.util.HashMap;

public class WeatherIconChooseUtils {
    private static final HashMap<String,Integer> weatherToResourceMap = new HashMap<>();

    static {
        weatherToResourceMap.put("100", R.drawable.pic_sunny);
        weatherToResourceMap.put("101", R.drawable.pic_rainbow_cloud);
        weatherToResourceMap.put("102", R.drawable.pic_sun_cloud);
        weatherToResourceMap.put("103", R.drawable.pic_rainbow_cloud);
        weatherToResourceMap.put("104", R.drawable.pic_cloud);
        weatherToResourceMap.put("150", R.drawable.pic_moon);
        weatherToResourceMap.put("151", R.drawable.pic_moon_cloud);
        weatherToResourceMap.put("152", R.drawable.pic_moon_cloud);
        weatherToResourceMap.put("153", R.drawable.pic_moon_cloud);

        weatherToResourceMap.put("300", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("301", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("302", R.drawable.pic_rain_lightning);
        weatherToResourceMap.put("303", R.drawable.pic_rain_lightning);
        weatherToResourceMap.put("304", R.drawable.pic_rain_lightning);

        weatherToResourceMap.put("305", R.drawable.pic_rain);
        weatherToResourceMap.put("306", R.drawable.pic_rain);
        weatherToResourceMap.put("307", R.drawable.pic_rain);
        weatherToResourceMap.put("308", R.drawable.pic_rain);
        weatherToResourceMap.put("309", R.drawable.pic_rain);
        weatherToResourceMap.put("310", R.drawable.pic_rain);
        weatherToResourceMap.put("311", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("312", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("313", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("314", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("315", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("316", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("317", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("318", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("350", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("351", R.drawable.pic_rain_cloud);
        weatherToResourceMap.put("399", R.drawable.pic_rain_cloud);

        weatherToResourceMap.put("400", R.drawable.pic_snow_cloud);
        weatherToResourceMap.put("401", R.drawable.pic_snow_cloud);
        weatherToResourceMap.put("402", R.drawable.pic_snow_cloud);
        weatherToResourceMap.put("403", R.drawable.pic_snow_cloud);
        weatherToResourceMap.put("404", R.drawable.pic_rain_snow);
        weatherToResourceMap.put("405", R.drawable.pic_rain_snow);
        weatherToResourceMap.put("406", R.drawable.pic_rain_snow);
        weatherToResourceMap.put("407", R.drawable.pic_snowflake);
        weatherToResourceMap.put("408", R.drawable.pic_snowflake);
        weatherToResourceMap.put("409", R.drawable.pic_snowflake);
        weatherToResourceMap.put("410", R.drawable.pic_snowflake);
        weatherToResourceMap.put("456", R.drawable.pic_rain_snow);
        weatherToResourceMap.put("457", R.drawable.pic_snowstorm);
        weatherToResourceMap.put("499", R.drawable.pic_snowstorm);

        weatherToResourceMap.put("500", R.drawable.pic_wind);
        weatherToResourceMap.put("501", R.drawable.pic_wind);
        weatherToResourceMap.put("502", R.drawable.pic_wind);
        weatherToResourceMap.put("503", R.drawable.pic_wind);
        weatherToResourceMap.put("504", R.drawable.pic_wind);
        weatherToResourceMap.put("507", R.drawable.pic_wind);
        weatherToResourceMap.put("508", R.drawable.pic_wind);
        weatherToResourceMap.put("509", R.drawable.pic_wind);
        weatherToResourceMap.put("510", R.drawable.pic_wind);
        weatherToResourceMap.put("511", R.drawable.pic_wind);
        weatherToResourceMap.put("512", R.drawable.pic_wind);
        weatherToResourceMap.put("513", R.drawable.pic_wind);
        weatherToResourceMap.put("514", R.drawable.pic_wind);
        weatherToResourceMap.put("515", R.drawable.pic_wind);
        weatherToResourceMap.put("900", R.drawable.pic_wind);
        weatherToResourceMap.put("901", R.drawable.pic_wind);
        weatherToResourceMap.put("999", R.drawable.pic_wind);
    }

    public static int changeImgResourceByWeatherName(String weatherName) {
        if (weatherToResourceMap.containsKey(weatherName)) {
            return weatherToResourceMap.get(weatherName);
        } else {
            return R.drawable.pic_rainbow_cloud;
        }
    }
}
