package com.alatheer.noamany.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alatheer.noamany.Fragments.PointsFragment;
import com.alatheer.noamany.Points.OrderStatus;
import com.alatheer.noamany.R;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.StoreDetailsHolder> {
    List<OrderStatus> storeDetailsList;
    Context context,m_context;
    Integer selectedItem = 0;
    PointsFragment pointsFragment;
    Resources resources;

    public OrderStatusAdapter(List<OrderStatus> storeDetailsList, Context context,PointsFragment pointsFragment) {
        this.storeDetailsList = storeDetailsList;
        this.context = context;
        this.pointsFragment = pointsFragment;
    }

    @NonNull
    @Override
    public StoreDetailsHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_status_item,parent,false);
        return new StoreDetailsHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull StoreDetailsHolder holder, int position) {
        holder.setData(storeDetailsList.get(position));
        holder.txt_title.setBackgroundResource(R.drawable.txt_non_active_bg);
        holder.txt_title.setTextColor(context.getColor(R.color.red));
        if (selectedItem == position) {
            holder.txt_title.setBackgroundResource(R.drawable.txt_active_bg);
            holder.txt_title.setTextColor(context.getColor(R.color.white));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousItem = selectedItem;
                selectedItem = position;
                notifyItemChanged(previousItem);
                notifyItemChanged(position);
                if (position == 0){
                    pointsFragment.get_available_points();
                }else {
                    pointsFragment.get_dis_available_points();
                }
                //searchActivity.sendData(categoryList.get(position).getId());


            }
        });
    }

    @Override
    public int getItemCount() {
        return storeDetailsList.size();
    }

    class StoreDetailsHolder extends RecyclerView.ViewHolder{
        TextView txt_title;
        ConstraintLayout constraintLayout;
        public StoreDetailsHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_order_status);
        }

        public void setData(OrderStatus orderStatus) {
            txt_title.setText(orderStatus.getAr_name());
        }
    }

}
