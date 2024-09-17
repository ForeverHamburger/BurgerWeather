package com.example.mvpburgerweahter.ui.searchpage.view;

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

public class CityManagerRecyclerAdapter extends RecyclerView.Adapter<CityManagerRecyclerAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private List<LocationInfo> mCityList;

    public CityManagerRecyclerAdapter(List<LocationInfo> cityList) {
        this.mCityList = cityList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton ibtnMyCity;
        TextView tvMyCityName;
        TextView tvMyCityweather;
        TextView tvMyCityTempHighAndLow;
        TextView tvMyCityTempreture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMyCityName = itemView.findViewById(R.id.tv_my_city_name);
            tvMyCityweather = itemView.findViewById(R.id.tv_my_city_weather);
            tvMyCityTempHighAndLow = itemView.findViewById(R.id.tv_my_city_temp_high_and_low);
            tvMyCityTempreture = itemView.findViewById(R.id.tv_my_city_tempreture);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_city_manager_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMyCityName.setText(mCityList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCityList == null ? 0 : mCityList.size();
    }
}
