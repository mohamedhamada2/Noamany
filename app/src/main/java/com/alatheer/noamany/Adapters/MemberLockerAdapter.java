package com.alatheer.noamany.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Data.Remote.MemberLocker.MemberLocker;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;

import java.text.DateFormat;
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

public class MemberLockerAdapter extends RecyclerView.Adapter<MemberLockerAdapter.MemberLockerHolder> {
    List<MemberLocker> memberLockerList;
    Context context,m_context;
    HomeActivity homeActivity;
    Resources resources;
    String language;

    public MemberLockerAdapter(List<MemberLocker> memberLockerList, Context context) {
        this.memberLockerList = memberLockerList;
        this.context = context;
        homeActivity = (HomeActivity) context;
    }

    @NonNull
    @Override
    public MemberLockerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_subscription_item,parent,false);

        return new MemberLockerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberLockerHolder holder, int position) {
        language =  Paper.book().read("language");
        Log.e("language",language);
        if(language == null){
            Paper.book().write("language","ar");
        }
        holder.setData(memberLockerList.get(position),language);
    }

    @Override
    public int getItemCount() {
        return memberLockerList.size();
    }

    class MemberLockerHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_name)
        TextView locker_name;
        @BindView(R.id.start_date)
        TextView start_date;
        @BindView(R.id.end_date)
        TextView end_date;
        @BindView(R.id.subscription_status)
        TextView subscription_status;
        @BindView(R.id.txt_start_date)
        TextView txt_start_date;
        @BindView(R.id.txt_end_date)
        TextView txt_end_date;
        @BindView(R.id.txt_subscription_status)
        TextView txt_subscription_status;
        public MemberLockerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            Paper.init(homeActivity);
        }

        public void setData(MemberLocker memberLocker, String language) {
            locker_name.setText(memberLocker.getLockerTitle());
            long dt1 = Long.parseLong(memberLocker.getFromDate());
            final Date from_dt = new Date((long) (dt1 * 1000.07));
            final DateFormat f = new SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault());
            start_date.setText(f.format(from_dt));
            long dt2 = Long.parseLong(memberLocker.getToDate());
            final Date to_dt = new Date((long) (dt2 * 1000.07));
            end_date.setText(f.format(to_dt));
            m_context = LocaleHelper.setLocale(homeActivity,language);
            resources = m_context.getResources();
            txt_start_date.setText(resources.getString(R.string.start_date));
            txt_end_date.setText(resources.getString(R.string.end_date));
            txt_subscription_status.setText(resources.getString(R.string.subscription_status));
            Date currentTime = Calendar.getInstance().getTime();
            if(currentTime.after(from_dt) && currentTime.before(to_dt)) {
                subscription_status.setText(resources.getString(R.string.available));
                subscription_status.setTextColor(homeActivity.getResources().getColor(R.color.colorgreen));
            }else {
                subscription_status.setText(resources.getString(R.string.finished));
                subscription_status.setTextColor(homeActivity.getResources().getColor(R.color.silver));
            }

        }
    }
}
