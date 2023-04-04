package com.alatheer.noamany.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alatheer.noamany.Data.Remote.Notification.NotificationModel;
import com.alatheer.noamany.Fragments.NotificationFragment;
import com.alatheer.noamany.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {
    List<NotificationModel> notificationModelList;
    Context context;
    NotificationFragment notificationFragment;

    public NotificationAdapter(List<NotificationModel> notificationModelList, Context context, NotificationFragment notificationFragment) {
        this.notificationModelList = notificationModelList;
        this.context = context;
        this.notificationFragment = notificationFragment;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        holder.setData(notificationModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    class NotificationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_title)
        TextView txt_title;
        @BindView(R.id.txt_notification)
        TextView txt_notification;
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.txt_time)
        TextView txt_time;
        public NotificationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(NotificationModel notificationModel) {
            txt_title.setText(notificationModel.getTitle());
            txt_notification.setText(notificationModel.getNotification());
            txt_date.setText(notificationModel.getDate());
            txt_time.setText(notificationModel.getTime());
        }
    }
}
