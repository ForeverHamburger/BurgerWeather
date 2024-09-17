package com.example.mvpburgerweahter.ui.searchpage;

import android.util.Log;

import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.utils.hefengutils.CitySearchUtils;
import com.example.mvpburgerweahter.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchPageModel implements ISearchPageContract.ISearchPageModel{
    private final static String TAG = "SearchPageModel";
    @Override
    public List<LocationInfo> getSavedCityList() {
        List<LocationInfo> infos = new ArrayList<>();
        String[] strings = {"长安","北京","西湖"};
        for (String s : strings) {
            String cityByNameSearch = CitySearchUtils.getCityByNameSearch(s);
            LocationInfo locationInfo = JsonUtils.parseLocationJson(cityByNameSearch);
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
        List<LocationInfo> infos = JsonUtils.parseSearchCityJson(cityByNameSearch);
        return infos;
    }
}
