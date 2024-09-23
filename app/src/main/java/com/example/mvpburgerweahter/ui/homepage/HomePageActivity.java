package com.example.mvpburgerweahter.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databinding.ActivityHomePageBinding;
import com.example.mvpburgerweahter.manager.CityListManager;
import com.example.mvpburgerweahter.room.citylist.CityListInfo;
import com.example.mvpburgerweahter.ui.homepage.view.HomePageFragment;
import com.example.mvpburgerweahter.ui.homepage.view.adapters.ViewPagerAdapter;
import com.example.mvpburgerweahter.ui.searchpage.view.SearchPageActivity;
import com.example.mvpburgerweahter.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class HomePageActivity extends AppCompatActivity {
    private final static String TAG = "HomePageActivity";
    private HomePagePresenter homePagePresenter;
    private ActivityHomePageBinding binding;
    private List<HomePageFragment> homePageFragments;
    private ViewPagerAdapter viewPagerAdapter;
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

    @Override
    protected void onRestart() {
        super.onRestart();
        // 刷新fragmentList
        Log.d(TAG, "onRestart: " + "开始刷新咯");
        homePageFragments = initFragmentList();
        viewPagerAdapter.updateFragmentList(homePageFragments);
        binding.circleCenter.setViewPager(binding.vpShowWeather);
        Log.d(TAG, "onRestart: " + "刷新结束咯");
    }

    private void initViews() {
        homePageFragments = initFragmentList();

        // ViewPager初始化
        viewPagerAdapter = new ViewPagerAdapter(this, homePageFragments);
        binding.vpShowWeather.setAdapter(viewPagerAdapter);
        // 设置预加载页面数量
        binding.vpShowWeather.setOffscreenPageLimit(viewPagerAdapter.getItemCount()-1 == 0 ? 1:viewPagerAdapter.getItemCount()-1);
        // 绑定顶部圆点指示器
        int itemCount = viewPagerAdapter.getItemCount();
        Log.d(TAG, "initViews: " + itemCount);
        binding.circleCenter.setViewPager(binding.vpShowWeather);

        // 绑定下拉刷新
        PtrFrameLayout ptrHomePage = binding.ptrHomePage;

        final MaterialHeader header = new MaterialHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        ptrHomePage.setHeaderView(header);
        ptrHomePage.addPtrUIHandler(header);
        ptrHomePage.disableWhenHorizontalMove(true);

        ptrHomePage.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // 刷新内层数据
                int itemCount = binding.vpShowWeather.getCurrentItem();
                homePageFragments.get(itemCount).refreshWeatherMethod(getApplicationContext());
                Log.d(TAG, "onRefreshBegin: " + homePageFragments);
                ptrHomePage.refreshComplete();
            }
        });
    }

    private List<HomePageFragment> initFragmentList() {
        // fragment的数据初始化创建
        List<HomePageFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomePageFragment("MyLocation"));

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                CityListManager cityListManager = new CityListManager(getApplicationContext());
                List<CityListInfo> cityListInfos = cityListManager.showAllCityList();
                Log.d(TAG, "run: " + cityListInfos);

                for (CityListInfo cityListInfo : cityListInfos) {
                    String locationInfo = cityListInfo.getLocationInfo();
                    LocationInfo info = JsonUtils.parseLocationJson(locationInfo);
                    Log.d(TAG, "run: " + info.getId());
                    HomePageFragment homePageFragment = new HomePageFragment(info.getId());
                    fragmentList.add(homePageFragment);
                }

                latch.countDown();
            }
        }).start();

        try {
            // 等待新线程完成
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fragmentList;
    }
}