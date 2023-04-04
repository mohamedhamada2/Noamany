package com.alatheer.noamany.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.noamany.Data.Remote.MemberVedio.MemberVedio;
import com.alatheer.noamany.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberVedioAdapter extends RecyclerView.Adapter<MemberVedioAdapter.MemberVedioHolder> {
  Context context;
  List<MemberVedio>memberVedioList;

  public MemberVedioAdapter(Context context, List<MemberVedio> memberVedioList) {
    this.context = context;
    this.memberVedioList = memberVedioList;
  }

  @NonNull
  @Override
  public MemberVedioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.vedio_item,parent,false);
    return new MemberVedioHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MemberVedioHolder holder, int position) {
    holder.setData(memberVedioList.get(position));
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + memberVedioList.get(position).getVideo()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + memberVedioList.get(position).getVideo()));
        try {
          context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
          context.startActivity(webIntent);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return memberVedioList.size();
  }

  class MemberVedioHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.vedio_img)
    ImageView vedio_img;
    @BindView(R.id.vedio_title)
    TextView vedio_title;
    @BindView(R.id.youtube_img)
    ImageView youtube_img;
    public MemberVedioHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }

    public void setData(MemberVedio memberVedio) {
      vedio_title.setText(memberVedio.getTitle());
      Picasso.get().load("https://noamanycenter.com/uploads/v_image/"+memberVedio.getVImage()).into(vedio_img);
    }
  }
}