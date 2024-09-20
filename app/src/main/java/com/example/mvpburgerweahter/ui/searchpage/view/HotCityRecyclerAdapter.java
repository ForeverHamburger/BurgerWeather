package com.example.mvpburgerweahter.ui.searchpage.view;

import static com.example.mvpburgerweahter.R.drawable.btn_click_search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpburgerweahter.R;
import com.example.mvpburgerweahter.databean.LocationInfo;

import java.util.ConcurrentModificationException;
import java.util.List;

public class HotCityRecyclerAdapter extends RecyclerView.Adapter<HotCityRecyclerAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private List<LocationInfo> mHotCityList;
    private List<LocationInfo> mCityList;
    private Context mContext;
    private final static String TAG = "HotCityRecyclerAdapter";

    public HotCityRecyclerAdapter(Context context, List<LocationInfo> hotCityList,List<LocationInfo> cityList) {
        this.mContext = context;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvHotCity.setText(mHotCityList.get(position).getName());
        holder.ibtnHotCity.setBackgroundResource(R.drawable.btn_click_search_hui);
        for (LocationInfo locationInfo : mCityList) {
            Log.d(TAG, "onBindViewHolder: " + locationInfo.getName() + mHotCityList.get(position).getName());
            if (mHotCityList.get(position).getName().equals(locationInfo.getName())) {
                Log.d(TAG, "haha: " + locationInfo.getName() + mHotCityList.get(position).getName());
                holder.ibtnHotCity.setBackgroundResource(btn_click_search);
            }
        }

        if (!holder.ibtnHotCity.getBackground().equals(btn_click_search)) {
            holder.ibtnHotCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SearchPageActivity) mContext).addCityToCityList(mHotCityList.get(position));
                }
            });
        } else {
            Toast.makeText(mContext, "已添加到CityList中~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return mHotCityList == null ? 0 : mHotCityList.size();
    }
}
