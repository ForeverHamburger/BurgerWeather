package com.example.mvpburgerweahter.manager;

import android.content.Context;

import androidx.room.Room;

import com.example.mvpburgerweahter.room.citylist.CityListDao;
import com.example.mvpburgerweahter.room.citylist.CityListDatabase;
import com.example.mvpburgerweahter.room.citylist.CityListInfo;

import java.util.List;

public class CityListManager {
    private Context context;
    private CityListDatabase cityListDatabase;
    private CityListDao cityListDao;
    public CityListManager(Context context) {
        this.context = context;
        cityListDatabase = Room.databaseBuilder(context, CityListDatabase.class, "city_list.db")
                .build();
        cityListDao = cityListDatabase.getInfoDao();
    }

    public void insertCityInfo(CityListInfo info){
        cityListDao.insertInfos(info);
    }

    public void deleteCityInfo(String cityCode) {
        cityListDao.deleteCityByCode(cityCode);
    }

    public List<CityListInfo> showAllCityList() {
        List<CityListInfo> allInfos = cityListDao.getAllInfos();
        return allInfos;
    }
}
