package com.alatheer.noamany.Adapters;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.noamany.Data.Remote.MemberInbody.MemberInbudy;
import com.alatheer.noamany.Fragments.BodyStats;
import com.alatheer.noamany.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BodyStatsAdapter extends RecyclerView.Adapter<BodyStatsAdapter.BodyStatsHolder> {
    Context context;
    List<MemberInbudy>bodyStatslist;
    BodyStats bodyStats;
    public BodyStatsAdapter(Context context,BodyStats bodyStats ,List<MemberInbudy> bodyStatslist) {
        this.context = context;
        this.bodyStatslist = bodyStatslist;
        this.bodyStats = bodyStats;
    }

    @NonNull
    @Override
    public BodyStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.body_state_item,parent,false);
        return new BodyStatsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyStatsHolder holder, int position) {
        holder.setData(bodyStatslist.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyStats.sendDatatoFragment(bodyStatslist.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bodyStatslist.size();
    }

    class BodyStatsHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.img)
        ImageView imageView;
        public BodyStatsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(MemberInbudy memberInbudy) {
            txt_date.setText(memberInbudy.getInbodyDate());
            imageView.setImageResource(R.drawable.ic_remove_red_eye);

        }
    }
}