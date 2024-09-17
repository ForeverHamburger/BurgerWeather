package com.example.mvpburgerweahter.ui.searchpage.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databinding.ActivitySearchPageBinding;
import com.example.mvpburgerweahter.ui.searchpage.ISearchPageContract;
import com.example.mvpburgerweahter.ui.searchpage.SearchPagePresenter;

import java.util.List;

public class SearchPageActivity extends AppCompatActivity implements ISearchPageContract.ISearchPageView {
    private final static String TAG = "SearchPageActivity";
    private ActivitySearchPageBinding binding;
    private SearchPagePresenter searchPagePresenter;
    private List<LocationInfo> citySearchedInfos;
    private SearchedCityRecyclerAdapter searchedCityRecyclerAdapter;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager citylinearLayoutManager;
    private LinearLayoutManager searchLinearLayoutManager;
    private HotCityRecyclerAdapter hotCityRecyclerAdapter;
    private CityManagerRecyclerAdapter cityManagerRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySearchPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.search_page_motion_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchPagePresenter = new SearchPagePresenter(this);
        searchPagePresenter.getCitySearched();


        citylinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        searchLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        searchedCityRecyclerAdapter = new SearchedCityRecyclerAdapter(citySearchedInfos);

        binding.rvSearchedCity.setLayoutManager(searchLinearLayoutManager);
        binding.rvSearchedCity.setAdapter(searchedCityRecyclerAdapter);

        binding.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(SearchPageActivity.this, "获取到EditText焦点", Toast.LENGTH_SHORT).show();
                    MotionLayout searchPageMotionLayout = binding.searchPageMotionLayout;
                    searchPageMotionLayout.setTransition(R.id.transition_search_page);
                    searchPageMotionLayout.transitionToEnd();
                }
            }
        });

        // 点击其他view时，editText失焦
        binding.searchPageMotionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.clearFocus();
                binding.rvSearchedCity.setVisibility(View.GONE);
            }
        });

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    Toast.makeText(SearchPageActivity.this, "haha" + s.toString(), Toast.LENGTH_SHORT).show();
                    searchPagePresenter.getElasticSearch(s.toString());
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.rvSearchedCity.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void getCitySuccess(List<LocationInfo> hotCityList, List<LocationInfo> cityList) {

        hotCityRecyclerAdapter = new HotCityRecyclerAdapter(hotCityList,cityList);
        cityManagerRecyclerAdapter = new CityManagerRecyclerAdapter(cityList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.rvCommandCity.setLayoutManager(gridLayoutManager);
                binding.rvCommandCity.setAdapter(hotCityRecyclerAdapter);

                binding.rvCityManager.setLayoutManager(citylinearLayoutManager);
                binding.rvCityManager.setAdapter(cityManagerRecyclerAdapter);
            }
        });
    }

    @Override
    public void getElasticSearchSuccess(List<LocationInfo> searchCityList) {
        Log.d(TAG, "getElasticSearchSuccess: " + searchCityList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchedCityRecyclerAdapter.updateData(searchCityList);
                binding.rvSearchedCity.setVisibility(View.VISIBLE);
                Toast.makeText(SearchPageActivity.this, "haha", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getCityFailed() {

    }
}