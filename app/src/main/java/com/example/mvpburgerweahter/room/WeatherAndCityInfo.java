package com.example.mvpburgerweahter.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_and_city_info")
public class WeatherAndCityInfo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city_code")
    private String cityCode;
    @ColumnInfo(name = "location_json")
    private String locationJson;
    @ColumnInfo(name = "weather_json")
    private String weatherJson;
    @ColumnInfo(name = "forecast_json_per_hour")
    private String forcastJsonPerHour;
    @ColumnInfo(name = "forecast_json_per_day")
    private String forecastJsonPerDay;

    @Ignore
    public WeatherAndCityInfo(String cityCode, String locationJson) {
        this.cityCode = cityCode;
        this.locationJson = locationJson;
    }

    public WeatherAndCityInfo(@NonNull String cityCode, String locationJson, String weatherJson, String forcastJsonPerHour, String forecastJsonPerDay) {
        this.cityCode = cityCode;
        this.locationJson = locationJson;
        this.weatherJson = weatherJson;
        this.forcastJsonPerHour = forcastJsonPerHour;
        this.forecastJsonPerDay = forecastJsonPerDay;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getLocationJson() {
        return locationJson;
    }

    public String getWeatherJson() {
        return weatherJson;
    }

    public String getForcastJsonPerHour() {
        return forcastJsonPerHour;
    }

    public String getForecastJsonPerDay() {
        return forecastJsonPerDay;
    }

    public void setCityCode(@NonNull String cityCode) {
        this.cityCode = cityCode;
    }

    public void setLocationJson(String locationJson) {
        this.locationJson = locationJson;
    }

    public void setWeatherJson(String weatherJson) {
        this.weatherJson = weatherJson;
    }

    public void setForcastJsonPerHour(String forcastJsonPerHour) {
        this.forcastJsonPerHour = forcastJsonPerHour;
    }

    public void setForecastJsonPerDay(String forecastJsonPerDay) {
        this.forecastJsonPerDay = forecastJsonPerDay;
    }
}
