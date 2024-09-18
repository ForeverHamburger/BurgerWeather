package com.example.mvpburgerweahter.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InfoDao {
    @Insert
    void insertInfos(WeatherAndCityInfo info);

    @Update
    int updateInfos(WeatherAndCityInfo info);

    @Delete
    void deleteInfos(WeatherAndCityInfo info);

    // 根据cityCode查询信息
    @Query("SELECT * FROM weather_and_city_info WHERE city_code = :cityCode")
    WeatherAndCityInfo findByCityCode(String cityCode);

    @Query("SELECT * FROM weather_and_city_info")
    List<WeatherAndCityInfo> getAllInfos();
}
