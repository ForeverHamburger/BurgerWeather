package com.example.mvpburgerweahter.ui.detailpage;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.room.InfoDao;
import com.example.mvpburgerweahter.room.InfoDatabase;
import com.example.mvpburgerweahter.room.WeatherAndCityInfo;
import com.example.mvpburgerweahter.utils.JsonUtils;
import com.example.mvpburgerweahter.utils.hefengutils.CitySearchUtils;
import com.example.mvpburgerweahter.utils.hefengutils.WeatherUtils;

import java.util.List;

public class DetailPageModel implements IDetailPageContract.IDetailPageModel{
    private static final String TAG = "DetailPageModel";
    private Context context;
    private InfoDao infoDao;
    public  DetailPageModel(Context context) {
        this.context = context;
        InfoDatabase infoDatabase = Room.databaseBuilder(context, InfoDatabase.class, "info.db")
                .build();
        infoDao = infoDatabase.getInfoDao();
    }
    @Override
    public List<DailyWeatherInfo> getDailyWeatherInfo(String cityCode) {
        List<DailyWeatherInfo> infos = null;
        if (infoDao.findByCityCode(cityCode) != null) {
            WeatherAndCityInfo infoString = infoDao.findByCityCode(cityCode);
            String forecastJsonPerDay = infoString.getForecastJsonPerDay();
            infos = JsonUtils.parseDailyWeatherInfoJson(forecastJsonPerDay);
        } else {
            String dailyWeatherInfo = WeatherUtils.getDailyWeatherInfo(cityCode);
            infos = JsonUtils.parseDailyWeatherInfoJson(dailyWeatherInfo);
        }
        Log.d(TAG, "getHourlyWeatherInfo: "+ infos);
        return infos;
    }



    @Override
    public List<DailyWeatherInfo> refreshWeatherInfo(String cityCode) {
        String cityByCityCode = CitySearchUtils.getCityByCityCode(cityCode);
        String nowWeatherInfo = WeatherUtils.getNowWeatherInfo(cityCode);
        String hourlyWeatherInfo = WeatherUtils.getHourlyWeatherInfo(cityCode);
        String dailyWeatherInfo = WeatherUtils.getDailyWeatherInfo(cityCode);
        if (infoDao.findByCityCode(cityCode) == null) {
            infoDao.insertInfos(new WeatherAndCityInfo(cityCode,cityByCityCode,
                    nowWeatherInfo,hourlyWeatherInfo,dailyWeatherInfo));
        } else {
            infoDao.updateInfos(new WeatherAndCityInfo(cityCode,cityByCityCode,
                    nowWeatherInfo,hourlyWeatherInfo,dailyWeatherInfo));
        }
        return JsonUtils.parseDailyWeatherInfoJson(dailyWeatherInfo);
    }
}
