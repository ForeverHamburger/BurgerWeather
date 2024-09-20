package com.example.mvpburgerweahter.room.citylist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvpburgerweahter.room.InfoDao;
import com.example.mvpburgerweahter.room.WeatherAndCityInfo;

@Database(entities = {CityListInfo.class},version = 1,exportSchema = false)
public abstract class CityListDatabase extends RoomDatabase {
    public abstract CityListDao getInfoDao();
}
