package com.alatheer.noamany.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscription;
import com.alatheer.noamany.Fragments.MemberSubscriptions;
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

public class MemberSupscriptionsAdapter extends RecyclerView.Adapter<MemberSupscriptionsAdapter.MemberHolder> {
    List<MemberSubscription>memberSubscriptionList;
    Context context ;
    HomeActivity homeActivity;
    MemberSubscriptions memberSubscriptions;
    Context m_context;
    Resources resources;
    String language;
    public MemberSupscriptionsAdapter(List<MemberSubscription> memberSubscriptionList, Context context,MemberSubscriptions memberSubscriptions) {
        this.memberSubscriptionList = memberSubscriptionList;
        this.memberSubscriptions = memberSubscriptions;
        this.context = context;
        homeActivity = (HomeActivity) context;

    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_subscription_item,parent,false);
        return new MemberHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        language =  Paper.book().read("language");
        Log.e("language",language);
        if(language == null){
            Paper.book().write("language","ar");
        }
        holder.setData(memberSubscriptionList.get(position),language);
        holder.add_inbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberSubscriptions.createDialog(memberSubscriptionList.get(position));
            }
        });
        holder.btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberSubscriptions.setData(memberSubscriptionList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberSubscriptionList.size();
    }

    class MemberHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_name)
        TextView supscription_name;
        @BindView(R.id.start_date)
        TextView start_date;
        @BindView(R.id.end_date)
        TextView end_date;
        @BindView(R.id.subscription_status)
        TextView subscription_status;
        @BindView(R.id.inbody_count)
        TextView inbody_count;
        @BindView(R.id.txt_start_date)
        TextView txt_start_date;
        @BindView(R.id.txt_end_date)
        TextView txt_end_date;
        @BindView(R.id.txt_subscription_status)
        TextView txt_subscription_status;
        @BindView(R.id.txt_inbody_count)
        TextView txt_inbody_count;
        @BindView(R.id.add_inbody)
        TextView add_inbody;
        @BindView(R.id.txt_inbody_remain)
        TextView txt_inbody_remain;
        @BindView(R.id.inbody_remain)
        TextView inbody_remain;
        @BindView(R.id.subscription_price)
        TextView subscription_price;
        @BindView(R.id.txt_subscription_price)
        TextView txt_subscription_price;
        @BindView(R.id.btn_pay)
        Button btn_pay;
        public MemberHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            Paper.init(homeActivity);
        }

        public void setData(MemberSubscription memberSubscription, String language) {
            m_context = LocaleHelper.setLocale(homeActivity,language);
            resources = m_context.getResources();
            txt_start_date.setText(resources.getString(R.string.start_date));
            txt_subscription_status.setText(resources.getString(R.string.subscription_status));
            txt_end_date.setText(resources.getString(R.string.end_date));
            txt_inbody_count.setText(resources.getString(R.string.inbody_count));
            txt_subscription_price.setText(resources.getString(R.string.subscription_price));
            txt_inbody_remain.setText(resources.getString(R.string.inbody_remain));
            supscription_name.setText(memberSubscription.getTitle());
            inbody_count.setText(memberSubscription.getInbodyCount());
            inbody_remain.setText(memberSubscription.getRemainInbody()+"");
            add_inbody.setText(resources.getString(R.string.add_inbody));
            if (memberSubscription.getCostAfterDiscount().equals("0")||memberSubscription.getCostAfterDiscount() == null ||memberSubscription.getCostAfterDiscount().equals("")|| !memberSubscription.getPaid().equals("0")){
                btn_pay.setVisibility(View.GONE);
            }else {
                btn_pay.setVisibility(View.VISIBLE);
            }
            subscription_price.setText(memberSubscription.getCostAfterDiscount());
            long dt1 = Long.parseLong(memberSubscription.getFromDate());
            final DateFormat f = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            final Date from_dt = new Date((long) (dt1 * 1000));
            start_date.setText(memberSubscription.getFromDateAr());
            long dt2 = Long.parseLong(memberSubscription.getToDate());
            final Date to_dt = new Date((long) (dt2 * 1000));
            end_date.setText(memberSubscription.getToDateAr());
            Date currentTime = Calendar.getInstance().getTime();
            if(currentTime.compareTo(from_dt)>=0 && currentTime.compareTo(to_dt)<=0) {
                subscription_status.setText(resources.getString(R.string.available));
                subscription_status.setTextColor(homeActivity.getResources().getColor(R.color.colorgreen));
            }else {
                subscription_status.setText(resources.getString(R.string.finished));
                subscription_status.setTextColor(homeActivity.getResources().getColor(R.color.silver));
            }

        }
    }
    public void add_subscription(List<MemberSubscription> memberSubscriptionList2){
        for (MemberSubscription memberSubscription:memberSubscriptionList2){
            memberSubscriptionList.add(memberSubscription);
        }
        notifyDataSetChanged();
    }
}
