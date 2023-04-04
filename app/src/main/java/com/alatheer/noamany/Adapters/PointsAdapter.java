package com.alatheer.noamany.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Data.Remote.AllSubscription.AllSubscription;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.Points.Points;
import com.alatheer.noamany.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointsHolder> {
    Context context,m_context;
    List<Points> pointsList;
    Resources resources;
    String language;
    HomeActivity homeActivity;

    public PointsAdapter(Context context, List<Points> pointsList) {
        this.context = context;
        this.pointsList = pointsList;
        homeActivity = (HomeActivity) context;
    }

    @NonNull
    @Override
    public PointsHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.points_item,parent,false);
        return new PointsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  PointsAdapter.PointsHolder holder, int position) {
        language =  Paper.book().read("language");
        Log.e("language",language);
        if(language == null){
            Paper.book().write("language","ar");
        }
        holder.setData(pointsList.get(position),language);
    }

    @Override
    public int getItemCount() {
        return pointsList.size();
    }

    class PointsHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.start_date)
        TextView start_date;
        @BindView(R.id.txt_start_date)
        TextView txt_start_date;
        @BindView(R.id.end_date)
        TextView end_date;
        @BindView(R.id.txt_end_date)
        TextView txt_end_date;
        @BindView(R.id.points)
        TextView point;
        @BindView(R.id.txt_points)
        TextView txt_points;
        public PointsHolder(@NonNull  View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            Paper.init(homeActivity);
        }

        public void setData(Points points, String language) {
            m_context = LocaleHelper.setLocale(homeActivity,language);
            resources = m_context.getResources();
            txt_start_date.setText(resources.getString(R.string.start_date));
            txt_end_date.setText(resources.getString(R.string.end_date));
            txt_points.setText(resources.getString(R.string.points));
            txt_name.setText(points.getActionName());
            point.setText(points.getPoints());
            start_date.setText(points.getDateAdded());
            end_date.setText(points.getDeadlineDate());
        }
    }
}
