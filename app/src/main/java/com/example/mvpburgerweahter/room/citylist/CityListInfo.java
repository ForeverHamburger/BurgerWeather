package com.example.mvpburgerweahter.room.citylist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_list")
public class CityListInfo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "identification")
    private int identification;
    @ColumnInfo(name = "city_code")
    private String cityCode;
    @ColumnInfo(name = "location_info")
    private String locationInfo;

    public CityListInfo(String cityCode, String locationInfo) {
        this.cityCode = cityCode;
        this.locationInfo = locationInfo;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    @Override
    public String toString() {
        return "CityListInfo{" +
                "identification=" + identification +
                ", cityCode='" + cityCode + '\'' +
                ", locationInfo='" + locationInfo + '\'' +
                '}';
    }
}
