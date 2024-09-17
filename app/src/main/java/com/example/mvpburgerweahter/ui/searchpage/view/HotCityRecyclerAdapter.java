package com.example.mvpburgerweahter.ui.searchpage.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databean.LocationInfo;

import java.util.List;

public class HotCityRecyclerAdapter extends RecyclerView.Adapter<HotCityRecyclerAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private List<LocationInfo> mHotCityList;
    private List<LocationInfo> mCityList;
    private final static String TAG = "HotCityRecyclerAdapter";

    public HotCityRecyclerAdapter(List<LocationInfo> hotCityList,List<LocationInfo> cityList) {
        this.mCityList = cityList;
        this.mHotCityList = hotCityList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton ibtnHotCity;
        TextView tvHotCity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ibtnHotCity = itemView.findViewById(R.id.ibtn_hot_city);
            tvHotCity = itemView.findViewById(R.id.tv_hotcity);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hot_city_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvHotCity.setText(mHotCityList.get(position).getName());
        holder.ibtnHotCity.setBackgroundResource(R.drawable.btn_click_search_hui);
        for (LocationInfo locationInfo : mCityList) {
            Log.d(TAG, "onBindViewHolder: " + locationInfo.getName() + mHotCityList.get(position).getName());
            if (mHotCityList.get(position).getName().equals(locationInfo.getName())) {
                Log.d(TAG, "haha: " + locationInfo.getName() + mHotCityList.get(position).getName());
                holder.ibtnHotCity.setBackgroundResource(R.drawable.btn_click_search);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mHotCityList == null ? 0 : mHotCityList.size();
    }
}
