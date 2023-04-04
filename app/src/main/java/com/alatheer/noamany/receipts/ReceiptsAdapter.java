package com.alatheer.noamany.receipts;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Adapters.MemberSupscriptionsAdapter;
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

public class ReceiptsAdapter extends RecyclerView.Adapter<ReceiptsAdapter.ReceiptsHolder> {
    List<MemberSubscription> memberSubscriptionList;
    Context context ;
    HomeActivity homeActivity;
    ReceiptsFragment memberSubscriptions;
    Context m_context;
    Resources resources;
    String language;

    public ReceiptsAdapter(List<MemberSubscription> memberSubscriptionList, Context context,ReceiptsFragment memberSubscriptions) {
        this.memberSubscriptionList = memberSubscriptionList;
        this.memberSubscriptions = memberSubscriptions;
        this.context = context;
        homeActivity = (HomeActivity) context;

    }
    @Override
    public ReceiptsHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.receipts_item,parent,false);
        return new ReceiptsAdapter.ReceiptsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ReceiptsAdapter.ReceiptsHolder holder, int position) {
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

    class ReceiptsHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_name)
        TextView supscription_name;
        @BindView(R.id.subscription_status)
        TextView subscription_status;
        @BindView(R.id.inbody_count)
        TextView inbody_count;
        @BindView(R.id.txt_subscription_status)
        TextView txt_subscription_status;
        @BindView(R.id.txt_inbody_count)
        TextView txt_inbody_count;
        @BindView(R.id.subscription_price)
        TextView subscription_price;
        @BindView(R.id.txt_subscription_price)
        TextView txt_subscription_price;
        public ReceiptsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            Paper.init(homeActivity);
        }
        public void setData(MemberSubscription memberSubscription, String language) {
            m_context = LocaleHelper.setLocale(homeActivity,language);
            resources = m_context.getResources();
            supscription_name.setText(memberSubscription.getTitle());
            txt_subscription_status.setText(resources.getString(R.string.receipts_num));
            txt_inbody_count.setText(resources.getString(R.string.Receipts_value));
            txt_subscription_price.setText(resources.getString(R.string.receipts_date));
            subscription_price.setText(memberSubscription.getFromDateAr());
            if (memberSubscription.getRkmEsal()!= null){
                subscription_status.setText(memberSubscription.getRkmEsal()+"");
            }else {
                subscription_status.setText("0");
            }
            inbody_count.setText(memberSubscription.getCostAfterDiscount());

        }
    }
    public void add_subscription(List<MemberSubscription> memberSubscriptionList2){
        for (MemberSubscription memberSubscription:memberSubscriptionList2){
            memberSubscriptionList.add(memberSubscription);
        }
        notifyDataSetChanged();
    }

}
