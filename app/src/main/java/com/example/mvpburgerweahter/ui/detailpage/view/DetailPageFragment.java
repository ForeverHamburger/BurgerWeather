package com.example.mvpburgerweahter.ui.detailpage.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databinding.FragmentDetailPageBinding;
import com.example.mvpburgerweahter.diyview.SunriseView;
import com.example.mvpburgerweahter.utils.TimeUtils;
import com.example.mvpburgerweahter.utils.WeatherIconChooseUtils;

import java.util.Calendar;

public class DetailPageFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private DailyWeatherInfo mdailyWeatherInfo;
    private FragmentDetailPageBinding binding;

    public DetailPageFragment() {
    }

    public DetailPageFragment(DailyWeatherInfo dailyWeatherInfo) {
        this.mdailyWeatherInfo = dailyWeatherInfo;
    }

    public static DetailPageFragment newInstance(String param1, String param2) {
        DetailPageFragment fragment = new DetailPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailPageBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvQuality.setText("12");
        binding.tvPressure.setText(mdailyWeatherInfo.getPressure() + "hpa");
        binding.tvUv.setText(mdailyWeatherInfo.getUvIndex());
        binding.tvPrecipitation.setText(mdailyWeatherInfo.getPrecip() + "mm");
        binding.tvWind.setText(mdailyWeatherInfo.getWindScaleDay() + "km/h");
        binding.tvVisibility.setText(mdailyWeatherInfo.getVis() + "km");

        binding.ivWeatherDetail.setImageResource(WeatherIconChooseUtils.changeImgResourceByWeatherName(mdailyWeatherInfo.getIconDay()));
        binding.tvWeatherDetail.setText(mdailyWeatherInfo.getTextDay());
        binding.tvTempretureDetail.setText(mdailyWeatherInfo.getTempMax() +"°");

        // 日升日落模块
        binding.tvSunrise.setText(mdailyWeatherInfo.getSunrise());
        binding.tvSunset.setText(mdailyWeatherInfo.getSunset());
        startSunAnim(TimeUtils.convertTimeToFloat(mdailyWeatherInfo.getSunrise()),TimeUtils.convertTimeToFloat(mdailyWeatherInfo.getSunset()));
    }

    public void startSunAnim(float sunrise, float sunset) {
        Calendar calendarNow = Calendar.getInstance();
        SunriseView mSunriseView = binding.sunRise;
        float hour = calendarNow.get(Calendar.HOUR_OF_DAY);
        if(hour < sunrise){
            mSunriseView.sunAnim(0);
        }else if(hour > sunset){
            mSunriseView.sunAnim(1);
        }else {
            mSunriseView.sunAnim(((float) hour - (float) sunrise) / ((float) sunset - (float) sunrise));
        }
    }
}