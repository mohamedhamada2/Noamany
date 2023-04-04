package com.alatheer.noamany.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Data.Remote.memberdiscount.Record;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;

import org.w3c.dom.Text;

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

public class MemberDiscountAdapter extends RecyclerView.Adapter<MemberDiscountAdapter.MemberDiscountHolder> {
    List<Record> recordList;
    Context context,m_context;
    Resources resources;
    String language;
    HomeActivity homeActivity;
    public MemberDiscountAdapter(List<Record> recordList, Context context) {
        this.recordList = recordList;
        this.context = context;
        homeActivity = (HomeActivity) context;
    }

    @Override
    public MemberDiscountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_discount_item,parent,false);
        return new MemberDiscountHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MemberDiscountAdapter.MemberDiscountHolder holder, int position) {
        language =  Paper.book().read("language");
        Log.e("language",language);
        if(language == null){
            Paper.book().write("language","ar");
        }
        holder.setData(recordList.get(position),language);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    class MemberDiscountHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_title)
        TextView txt_title;
        @BindView(R.id.txt_discount_date)
        TextView txt_discount_date;
        @BindView(R.id.txt_captain)
        TextView txt_captain;
        @BindView(R.id.date)
        TextView discount_date;
        @BindView(R.id.txt_dead_line_date)
        TextView txt_dead_line_date;
        @BindView(R.id.captain)
        TextView captain;
        @BindView(R.id.dead_line_date)
        TextView dead_line_date;
        public MemberDiscountHolder(@NonNull  View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Record record,String language) {
            txt_title.setText(record.getTitle());
            txt_discount_date.setText(record.getDate());
            txt_captain.setText(record.getEmployee());
            txt_dead_line_date.setText(record.getEndedDateAr());
            m_context = LocaleHelper.setLocale(homeActivity,language);
            resources = m_context.getResources();
            discount_date.setText(resources.getString(R.string.discount_date));
            dead_line_date.setText(resources.getString(R.string.deadline_date));
            captain.setText(resources.getString(R.string.captain));
        }
    }
}
