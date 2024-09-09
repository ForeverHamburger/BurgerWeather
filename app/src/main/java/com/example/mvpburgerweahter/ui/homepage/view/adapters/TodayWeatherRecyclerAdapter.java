package com.example.mvpburgerweahter.ui.homepage.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databean.HourlyWeatherInfo;
import com.example.mvpburgerweahter.utils.TimeUtils;
import com.example.mvpburgerweahter.utils.WeatherIconChooseUtils;

import java.util.List;

public class TodayWeatherRecyclerAdapter extends RecyclerView.Adapter<TodayWeatherRecyclerAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private List<HourlyWeatherInfo> mhourlyWeatherInfoList;

    public TodayWeatherRecyclerAdapter(List<HourlyWeatherInfo> hourlyWeatherInfoList) {
        this.mhourlyWeatherInfoList = hourlyWeatherInfoList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBackground;
        TextView tvTime;
        ImageView ivWeather;
        TextView tvTempreture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBackground = itemView.findViewById(R.id.iv_background);
            tvTime = itemView.findViewById(R.id.rec_tv_time);
            ivWeather = itemView.findViewById(R.id.rec_iv_weather);
            tvTempreture = itemView.findViewById(R.id.rec_tv_tempreture);
        }
    }

    @NonNull
    @Override
    public TodayWeatherRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_weatherforecast, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodayWeatherRecyclerAdapter.ViewHolder holder, int position) {
        if (position == 0) {
            holder.ivBackground.setImageResource(R.drawable.bg_recyc_now);
        } else {
            holder.ivBackground.setImageResource(R.drawable.bg_recyc_other);
        }
        HourlyWeatherInfo hourlyWeatherInfo = mhourlyWeatherInfoList.get(position);

        String fxTime = hourlyWeatherInfo.getFxTime();
        holder.tvTime.setText(TimeUtils.extractTime(fxTime));
        holder.tvTempreture.setText(hourlyWeatherInfo.getTemp() + "°");
        holder.ivWeather.setImageResource(WeatherIconChooseUtils.changeImgResourceByWeatherName(hourlyWeatherInfo.getIcon()));

    }

    @Override
    public int getItemCount() {
        return mhourlyWeatherInfoList == null ? 0 : mhourlyWeatherInfoList.size();
    }
}
