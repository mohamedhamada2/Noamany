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

import com.alatheer.noamany.Data.Remote.News.NewsModel;
import com.alatheer.noamany.Fragments.NewsFragment;
import com.alatheer.noamany.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
  Context context;
  List<NewsModel>newsModelList;
  NewsFragment newsFragment;

  public NewsAdapter(Context context, List<NewsModel> newsModelList,NewsFragment newsFragment) {
    this.context = context;
    this.newsModelList = newsModelList;
    this.newsFragment = newsFragment;
  }

  @NonNull
  @Override
  public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(context).inflate(R.layout.ads_item,parent,false);
    return new NewsHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
    holder.setData(newsModelList.get(position));
    if(newsModelList.get(position).getNewsVideo().equals("")){
      holder.youtube_img.setVisibility(View.INVISIBLE);
    }
    if (newsModelList.get(position).getNewsImage().equals("noImage.png")){
      holder.ads_image.setImageResource(R.drawable.app_logo);
    }
    holder.youtube_img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + newsModelList.get(position).getNewsVideo()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + newsModelList.get(position).getNewsVideo()));
        try {
          context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
          context.startActivity(webIntent);
        }
      }
    });
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newsFragment.setData(newsModelList.get(position));
      }
    });
  }

  @Override
  public int getItemCount() {
    return newsModelList.size();
  }

  class NewsHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.ads_title)
    TextView ads_title;
    @BindView(R.id.ads_desc)
    TextView ads_desc;
    @BindView(R.id.ads_image)
    ImageView ads_image;
    @BindView(R.id.youtube_img)
    ImageView youtube_img;
    public NewsHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }

    public void setData(NewsModel newsModel) {
      ads_title.setText(newsModel.getNewsName());
      ads_desc.setText(newsModel.getNewsDetails());
      Picasso.get().load("https://noamanycenter.com/uploads/images/news/"+newsModel.getNewsImage()).into(ads_image);
    }
  }
}
