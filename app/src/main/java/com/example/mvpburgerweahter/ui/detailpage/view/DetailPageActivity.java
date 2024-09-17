package com.example.mvpburgerweahter.ui.detailpage.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databean.DailyWeatherInfo;
import com.example.mvpburgerweahter.databinding.ActivityDetailPageBinding;
import com.example.mvpburgerweahter.ui.detailpage.DetailPagePresenter;
import com.example.mvpburgerweahter.ui.detailpage.IDetailPageContract;
import com.example.mvpburgerweahter.ui.detailpage.view.DetailPageFragment;
import com.example.mvpburgerweahter.ui.detailpage.view.DetailViewPagerAdapter;
import com.example.mvpburgerweahter.utils.TimeUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class DetailPageActivity extends AppCompatActivity implements IDetailPageContract.IDetailPageView {
    private static final String TAG = "DetailPageActivity";
    private ActivityDetailPageBinding binding;
    private IDetailPageContract.IDetailPagePresenter presenter;
    private DetailViewPagerAdapter detailViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String cityIdKey = intent.getStringExtra("cityId_key");
        String cityName = intent.getStringExtra("cityName_key");

        initView(cityName);

        presenter = new DetailPagePresenter(this);
        presenter.getWeatherDetail(cityIdKey);

        binding.ibtnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(String cityName) {
        binding.tvLocation.setText(cityName);
    }

    @Override
    public void getWeatherSuccess(List<DailyWeatherInfo> dailyWeatherInfoList) {
        Log.d(TAG, "getWeatherSuccess: " + dailyWeatherInfoList);
        initViewPagerAndTabLayout(dailyWeatherInfoList);
    }

    private void initViewPagerAndTabLayout(List<DailyWeatherInfo> dailyWeatherInfoList) {
        List<DetailPageFragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            fragmentList.add(new DetailPageFragment(dailyWeatherInfoList.get(i)));
        }

        detailViewPagerAdapter = new DetailViewPagerAdapter(this,fragmentList);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.vpDetail.setAdapter(detailViewPagerAdapter);

                new TabLayoutMediator(binding.tabDetail, binding.vpDetail, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                        tab.setText("undefined");
                    }
                }).attach();

                TabLayout tabDetail = binding.tabDetail;
                String[] upcomingWeekdays = TimeUtils.getUpcomingWeekdays();
                List<String> nextSevenDays = TimeUtils.getNextSevenDays();
                for (int i = 0; i < 7; i++) {
                    TabLayout.Tab tab = tabDetail.getTabAt(i).setCustomView(getTabView());
                    View customView = tab.getCustomView();
                    if (customView != null) {
                        TextView textView = customView.findViewById(R.id.tv_tab_title);
                        TextView dateView = customView.findViewById(R.id.tv_tab_date);
                        ImageView imageView = customView.findViewById(R.id.iv_tab_imageView);
                        if(i == 0){
                            textView.setText("Today");
                            dateView.setText(nextSevenDays.get(i));
                            imageView.setImageResource(R.drawable.bg_tab_selected);
                            dateView.setTextColor(getResources().getColor(R.color.white));
                        }else {
                            textView.setText(upcomingWeekdays[i]);
                            dateView.setText(nextSevenDays.get(i));
                        }
                    } else {
                        Log.d(TAG, "run: " + i + "go wrong");
                    }
                }

                binding.tabDetail.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (tab.getCustomView() != null) {
                            ImageView imageView = tab.getCustomView().findViewById(R.id.iv_tab_imageView);
                            TextView textView = tab.getCustomView().findViewById(R.id.tv_tab_date);
                            imageView.setImageResource(R.drawable.bg_tab_selected);
                            textView.setTextColor(getResources().getColor(R.color.white));
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        if (tab.getCustomView() != null) {
                            ImageView imageView = tab.getCustomView().findViewById(R.id.iv_tab_imageView);
                            TextView textView = tab.getCustomView().findViewById(R.id.tv_tab_date);
                            imageView.setImageResource(R.color.touming);
                            textView.setTextColor(getResources().getColor(R.color._828282));
                        }
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        });
    }

    @Override
    public void getWeatherFailed() {

    }

    public View getTabView() {
        View tabView = LayoutInflater.from(this).inflate(R.layout.tab_view, null);
        //设置相关显示 tabView
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        tabView.setLayoutParams(params);
        return tabView;
    }
}