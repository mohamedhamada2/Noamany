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

public class AllSubscriptionAdapters extends RecyclerView.Adapter<AllSubscriptionAdapters.AllSubscriptionHolder> {
    Context context,m_context;
    List<AllSubscription> allSubscriptionList;
    Resources resources;
    String language;
    HomeActivity homeActivity;
    public AllSubscriptionAdapters(Context context, List<AllSubscription> allSubscriptionList) {
        this.context = context;
        this.allSubscriptionList = allSubscriptionList;
        homeActivity = (HomeActivity) context;
    }

    @NonNull
    @Override
    public AllSubscriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subscription_row_item,parent,false);
        return new AllSubscriptionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllSubscriptionHolder holder, int position) {
        language =  Paper.book().read("language");
        Log.e("language",language);
        if(language == null){
            Paper.book().write("language","ar");
        }
        holder.setData(allSubscriptionList.get(position),language);

    }

    @Override
    public int getItemCount() {
        return allSubscriptionList.size();
    }

    class AllSubscriptionHolder extends RecyclerView.ViewHolder{
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
        @BindView(R.id.subscription_price)
        TextView subscription_price;
        @BindView(R.id.txt_subscription_price)
        TextView txt_subscription_price;
        public AllSubscriptionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            Paper.init(homeActivity);
        }

        public void setData(AllSubscription allSubscription,String language) {
            m_context = LocaleHelper.setLocale(homeActivity,language);
            resources = m_context.getResources();
            txt_start_date.setText(resources.getString(R.string.start_date));
            txt_end_date.setText(resources.getString(R.string.end_date));
            txt_subscription_price.setText(resources.getString(R.string.subscription_price));
            txt_name.setText(allSubscription.getTitle());
            subscription_price.setText(allSubscription.getValueGal2());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());
            start_date.setText(currentDateandTime);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(currentDateandTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE,Integer.parseInt(allSubscription.getNumDays())-1);
            String enddate = sdf.format(c.getTime());
            end_date.setText(enddate);
        }
    }

}
