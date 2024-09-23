package com.example.mvpburgerweahter.ui.searchpage.view;

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
import com.example.mvpburgerweahter.ui.detailpage.view.DetailPageActivity;

import java.util.List;

public class SearchedCityRecyclerAdapter extends RecyclerView.Adapter<SearchedCityRecyclerAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private List<LocationInfo> locationInfos;
    private Context mContext;

    public SearchedCityRecyclerAdapter(Context context, List<LocationInfo> hourlyWeatherInfoList) {
        this.mContext = context;
        this.locationInfos = hourlyWeatherInfoList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton ibtnBackground;
        ImageButton ibtnSeachedAdd;
        TextView tvCityName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ibtnBackground = itemView.findViewById(R.id.ibtn_searched_city);
            ibtnSeachedAdd = itemView.findViewById(R.id.ibtn_search_add);
            tvCityName = itemView.findViewById(R.id.tv_searched_city_name);
        }
    }

    @NonNull
    @Override
    public SearchedCityRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_city_search_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedCityRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LocationInfo locationInfo = locationInfos.get(position);
        holder.tvCityName.setText(locationInfo.getName() + " - " + locationInfo.getAdm1() +
                " , " + locationInfo.getCountry());
        holder.ibtnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailPageActivity.class);
                intent.putExtra("cityId_key", locationInfos.get(position).getId());
                intent.putExtra("cityName_key",locationInfos.get(position).getName());
                mContext.startActivity(intent);
            }
        });

        holder.ibtnSeachedAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchPageActivity) mContext).addCityToCityList(locationInfos.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationInfos == null ? 0 : locationInfos.size();
    }

    public void updateData(List<LocationInfo> newLocationInfos) {
        // 检查新数据是否为空，避免更新空数据
        if (newLocationInfos != null && newLocationInfos.size() > 0) {
            this.locationInfos = newLocationInfos;
            notifyDataSetChanged();
        }
    }
}
