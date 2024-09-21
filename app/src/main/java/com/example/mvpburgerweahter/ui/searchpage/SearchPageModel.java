package com.example.mvpburgerweahter.ui.searchpage;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.manager.CityListManager;
import com.example.mvpburgerweahter.room.InfoDao;
import com.example.mvpburgerweahter.room.InfoDatabase;
import com.example.mvpburgerweahter.room.WeatherAndCityInfo;
import com.example.mvpburgerweahter.room.citylist.CityListInfo;
import com.example.mvpburgerweahter.utils.hefengutils.CitySearchUtils;
import com.example.mvpburgerweahter.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchPageModel implements ISearchPageContract.ISearchPageModel{
    private final static String TAG = "SearchPageModel";
    private Context context;
    private InfoDao infoDao;
    private InfoDatabase infoDatabase;
    private CityListManager cityListManager;
    public  SearchPageModel(Context context) {
        this.context = context;
        infoDatabase = Room.databaseBuilder(context, InfoDatabase.class, "info.db")
                .build();
        infoDao = infoDatabase.getInfoDao();
        cityListManager = new CityListManager(context);
    }
    @Override
    public List<LocationInfo> getSavedCityList() {
        List<LocationInfo> infos = new ArrayList<>();

        WeatherAndCityInfo info = infoDao.findByCityCode("now location");
        LocationInfo nowLocation = JsonUtils.parseLocationJson(info.getLocationJson());
        infos.add(nowLocation);

        List<CityListInfo> cityListInfos = cityListManager.showAllCityList();

        for (CityListInfo cityListInfo : cityListInfos) {
            LocationInfo locationInfo = JsonUtils.parseLocationJson(cityListInfo.getLocationInfo());
            infos.add(locationInfo);
        }

        return infos;
    }

    @Override
    public List<LocationInfo> getHotCityList() {
        String hotCity = CitySearchUtils.getHotCity();
        List<LocationInfo> infos = JsonUtils.parseHotCityJson(hotCity);
        Log.d(TAG, "getHotCityList: "+infos);
        return infos;
    }

    @Override
    public List<LocationInfo> getElasticSearchCityList(String searchString) {
        String cityByNameSearch = CitySearchUtils.getCityByNameSearch(searchString);
        if (cityByNameSearch.equals("{\"code\":\"404\"}")) {
            return null;
        } else {
            List<LocationInfo> infos = JsonUtils.parseSearchCityJson(cityByNameSearch);
            return infos;
        }
    }

    @Override
    public void saveCityToDatabase(LocationInfo info) {
        String locationJson = "";
        if (infoDao.findByCityCode(info.getId()) != null) {
            WeatherAndCityInfo infoStr = infoDao.findByCityCode(info.getId());
            locationJson = infoStr.getLocationJson();
        } else {
            locationJson = CitySearchUtils.getCityByCityCode(info.getId());
        }
        cityListManager.insertCityInfo(new CityListInfo(info.getId(),locationJson));
    }

    @Override
    public void deleteCityFromDatabase(String cityCode) {
        cityListManager.deleteCityInfo(cityCode);
    }
}
