package com.example.mvpburgerweahter.room.citylist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvpburgerweahter.room.WeatherAndCityInfo;

import java.util.List;
@Dao
public interface CityListDao {

    @Insert
    void insertInfos(CityListInfo info);
    @Query("DELETE FROM city_list WHERE city_code = :cityCode")
    void deleteCityByCode(String cityCode);
    @Query("SELECT * FROM city_list")
    List<CityListInfo> getAllInfos();
}