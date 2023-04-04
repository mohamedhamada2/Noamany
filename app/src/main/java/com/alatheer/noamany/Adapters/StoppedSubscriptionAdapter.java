package com.alatheer.noamany.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscription;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class StoppedSubscriptionAdapter extends RecyclerView.Adapter<StoppedSubscriptionAdapter.StoppedSubscriptionHolder> {
    Context context,m_context;
    List<MemberSubscription> memberSubscriptionList;
    HomeActivity homeActivity;
    String language;
    Resources resources;
    public StoppedSubscriptionAdapter(Context context, List<MemberSubscription> memberSubscriptionList) {
        this.context = context;
        this.memberSubscriptionList = memberSubscriptionList;
        homeActivity = (HomeActivity) context;
    }

    @NonNull
    @Override
    public StoppedSubscriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stopped_item,parent,false);
        return new StoppedSubscriptionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoppedSubscriptionHolder holder, int position) {
        language =  Paper.book().read("language");
        Log.e("language",language);
        if(language == null){
            Paper.book().write("language","ar");
        }
        holder.setData(memberSubscriptionList.get(position),language);
    }

    @Override
    public int getItemCount() {
        return memberSubscriptionList.size();
    }

    class StoppedSubscriptionHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_trainer)
        TextView txt_trainer;
        @BindView(R.id.txt_reason)
        TextView txt_reason;
        @BindView(R.id.txt_stopped_date)
        TextView txt_stopped_date;
        @BindView(R.id.trainer)
        TextView trainer;
        @BindView(R.id.stopped_date)
        TextView stopped_date;
        @BindView(R.id.reason)
        TextView reason;
        @BindView(R.id.txt_subscription)
        TextView txt_subscription;
        @BindView(R.id.txt_end_date)
        TextView txt_end_date;
        @BindView(R.id.end_date)
        TextView end_date;
        public StoppedSubscriptionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            Paper.init(homeActivity);
        }

        public void setData(MemberSubscription memberSubscription, String language) {
            if(memberSubscription.getStoppedReason()== null){
                reason.setText("hkgjhfthgdgcdr");
            }else {
                reason.setText(memberSubscription.getStoppedReason()+"");
            }
            if(memberSubscription.getStoppedDateFrom()== null){
                stopped_date.setText("hkgjhfthgdgcdr");
            }else {
                long from_dt = Long.parseLong(memberSubscription.getStoppedDateFrom()+"");
                final Date d1 = new Date((long) (from_dt * 1000.04));
                final DateFormat f = new SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault());
                stopped_date.setText(memberSubscription.getStoppedFromDateAr());
                long to_dt = Long.parseLong(memberSubscription.getStoppedDateTo()+"");
                final Date d2 = new Date((long) (to_dt * 1000.04));
                end_date.setText(memberSubscription.getStoppedToDateAr());
            }
            trainer.setText(memberSubscription.getPublisher());
            txt_subscription.setText(memberSubscription.getTitle());
            //stopped_date.setText(memberSubscription.getStoppedDateFrom());
            m_context = LocaleHelper.setLocale(homeActivity,language);
            resources = m_context.getResources();
            txt_trainer.setText(resources.getString(R.string.trainer));
            txt_reason.setText(resources.getString(R.string.reason));
            txt_stopped_date.setText(resources.getString(R.string.start_date));
            txt_end_date.setText(resources.getString(R.string.end_date));
        }
    }
    public void add_subscription(List<MemberSubscription> memberSubscriptionList2){
        for (MemberSubscription memberSubscription:memberSubscriptionList2){
            memberSubscriptionList.add(memberSubscription);
        }
        notifyDataSetChanged();
    }
}
