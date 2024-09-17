package com.example.mvpburgerweahter.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databinding.ActivityHomePageBinding;
import com.example.mvpburgerweahter.ui.homepage.view.HomePageFragment;
import com.example.mvpburgerweahter.ui.homepage.view.adapters.ViewPagerAdapter;
import com.example.mvpburgerweahter.ui.searchpage.view.SearchPageActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private HomePagePresenter homePagePresenter;
    private ActivityHomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        binding.ibtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {

        // fragment的数据初始化创建
        List<HomePageFragment> fragmentList = new ArrayList<>();

        fragmentList.add(new HomePageFragment());

        // ViewPager初始化
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, fragmentList);
        binding.vpShowWeather.setAdapter(viewPagerAdapter);
        // 设置预加载页面数量
        binding.vpShowWeather.setOffscreenPageLimit(viewPagerAdapter.getItemCount()-1 == 0 ? 1:viewPagerAdapter.getItemCount()-1);
        // 绑定顶部圆点指示器
        binding.circleCenter.setViewPager(binding.vpShowWeather);
    }
}