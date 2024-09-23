package com.example.mvpburgerweahter.ui.searchpage.view;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databean.LocationInfo;
import com.example.mvpburgerweahter.databean.NowWeatherInfo;
import com.example.mvpburgerweahter.ui.detailpage.view.DetailPageActivity;

import java.util.List;

public class CityManagerRecyclerAdapter extends RecyclerView.Adapter<CityManagerRecyclerAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private List<LocationInfo> mCityList;
    private Context mContext;
    private List<NowWeatherInfo> mWeatherList;
    public CityManagerRecyclerAdapter(Context context, List<LocationInfo> cityList,List<NowWeatherInfo> savedWeatherList) {
        this.mContext = context;
        this.mCityList = cityList;
        this.mWeatherList = savedWeatherList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton ibtnMyCity;
        TextView tvMyCityName;
        TextView tvMyCityweather;
        TextView tvMyCityTempreture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ibtnMyCity = itemView.findViewById(R.id.ibtn_my_city);
            tvMyCityName = itemView.findViewById(R.id.tv_my_city_name);
            tvMyCityweather = itemView.findViewById(R.id.tv_my_city_weather);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvMyCityName.setText(mCityList.get(position).getName());
        holder.tvMyCityweather.setText(mWeatherList.get(position).getText());
        holder.tvMyCityTempreture.setText(mWeatherList.get(position).getTemp() + "°");
        holder.ibtnMyCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailPageActivity.class);
                intent.putExtra("cityId_key",mCityList.get(position).getId());
                intent.putExtra("cityName_key",mCityList.get(position).getName());
                mContext.startActivity(intent);
            }
        });

        holder.ibtnMyCity.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((SearchPageActivity)mContext).deleteCity(mCityList.get(position));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCityList == null ? 0 : mCityList.size();
    }

    public void updateData(List<LocationInfo> cityList,List<NowWeatherInfo> weatherList) {
        this.mCityList = cityList;
        this.mWeatherList = weatherList;
        notifyDataSetChanged();
    }
}
