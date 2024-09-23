package com.example.mvpburgerweahter.ui.homepage.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;
import com.example.mvpburgerweahter.databinding.FragmentHomePageBinding;
import com.example.mvpburgerweahter.ui.detailpage.view.DetailPageActivity;
import com.example.mvpburgerweahter.ui.homepage.HomePagePresenter;
import com.example.mvpburgerweahter.ui.homepage.IHomePageContract;
import com.example.mvpburgerweahter.ui.homepage.view.adapters.TodayWeatherRecyclerAdapter;
import com.example.mvpburgerweahter.utils.TimeUtils;
import com.example.mvpburgerweahter.utils.WeatherIconChooseUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment implements IHomePageContract.IHomePageView {

    private static final String TAG = "HomePageFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private FragmentHomePageBinding binding;
    private LocationInfo mlocationInfo;
    private HomePagePresenter homePagePresenter;
    private String mCityCode = "";

    public HomePageFragment() {
        // Required empty public constructor
    }
    public HomePageFragment(String cityCode) {
        this.mCityCode = cityCode;
    }

    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
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
        binding = FragmentHomePageBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 设置字体颜色渐变
        setTextViewStyles(binding.tvTempreture);

        // 绑定Presenter
        homePagePresenter = new HomePagePresenter(this, getActivity(), mCityCode);
        homePagePresenter.getWeatherDetail();
        Log.d(TAG, "onViewCreated: " + mCityCode + "presenter绑定完成");

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailPageActivity.class);
                if (mlocationInfo == null) {
                    Toast.makeText(getActivity(), "抱一丝哈，没加载出来呢", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("cityId_key",mlocationInfo.getId());
                    intent.putExtra("cityName_key",mlocationInfo.getName());
                    startActivity(intent);
                }
            }
        });
    }

    public void refreshWeatherMethod(Context context) {
        Log.d(TAG, "refreshWeatherMethod: " + mCityCode);
        homePagePresenter = new HomePagePresenter(this, getActivity(), mCityCode);
        if (homePagePresenter != null) {
            homePagePresenter.refreshWeatherPage(mCityCode);
            Toast.makeText(context, "刷新成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "加载中，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getWeatherSuccess(LocationInfo locationInfo, NowWeatherInfo nowWeatherInfo,
                                  List<HourlyWeatherInfo> hourlyWeatherInfoList, List<DailyWeatherInfo> dailyWeatherInfoList) {
        this.mlocationInfo = locationInfo;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 处理RecyclerView相关（每小时天气预报）
                // 暂定为每两小时一个，一共24小时
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                TodayWeatherRecyclerAdapter todayWeatherRecyclerAdapter = new TodayWeatherRecyclerAdapter(hourlyWeatherInfoList);
                binding.rvTodayWeather.setLayoutManager(layoutManager);
                binding.rvTodayWeather.setAdapter(todayWeatherRecyclerAdapter);

                // 处理位置相关
                binding.tvLocation.setText(locationInfo.getName());
                binding.tvTime.setText(TimeUtils.getCurrentDateFormatted());

                // 实时天气相关
                binding.tvTempreture.setText(nowWeatherInfo.getTemp() + "°");
                binding.tvWeather.setText(nowWeatherInfo.getText());
                binding.ivWeather.setImageResource(WeatherIconChooseUtils.changeImgResourceByWeatherName(nowWeatherInfo.getIcon()));
                binding.tvAirquality.setText(nowWeatherInfo.getHumidity());
            }
        });
    }

    @Override
    public void getWeatherFailed() {

    }


    private void setTextViewStyles(TextView textView) {
        float x1=textView.getPaint().measureText(textView.getText().toString());//测量文本 宽度
        int c1= 0xFFFFFFFF;//初始颜色值
        int c2= 0x30FFFFFF;//结束颜色值
        LinearGradient leftToRightLG = new LinearGradient(x1/2, 0, x1, 0,c1, c2, Shader.TileMode.CLAMP);//从左到右渐变
        textView.getPaint().setShader(leftToRightLG);
        textView.invalidate();
    }
}