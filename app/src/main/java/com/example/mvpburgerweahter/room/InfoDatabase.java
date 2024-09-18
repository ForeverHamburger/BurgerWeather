package com.example.mvpburgerweahter.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherAndCityInfo.class},version = 2,exportSchema = false)
public abstract class InfoDatabase extends RoomDatabase {
    public abstract InfoDao getInfoDao();
}
