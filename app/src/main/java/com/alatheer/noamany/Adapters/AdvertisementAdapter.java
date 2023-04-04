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

import com.alatheer.noamany.Data.Remote.Advertisement.Ads;
import com.alatheer.noamany.Fragments.AdvertismentFragment;
import com.alatheer.noamany.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.AdvertisementHolder> {
    Context context;
    List<Ads>adsList;
    AdvertismentFragment advertismentFragment;

    public AdvertisementAdapter(Context context, List<Ads> adsList,AdvertismentFragment advertismentFragment) {
        this.context = context;
        this.adsList = adsList;
        this.advertismentFragment = advertismentFragment;
    }

    @NonNull
    @Override
    public AdvertisementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ads_item,parent,false);
        return new AdvertisementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementHolder holder, int position) {
        holder.setData(adsList.get(position));
        holder.youtube_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + adsList.get(position).getYoutubeLink()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + adsList.get(position).getYoutubeLink()));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
        holder.facebook_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adsList.get(position).getFacebook()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(adsList.get(position).getFacebook()));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
        holder.tiktok_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adsList.get(position).getTik_tok()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(adsList.get(position).getFacebook()));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
        holder.instagram_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(adsList.get(position).getInsta());
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    context.startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(adsList.get(position).getInsta())));
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advertismentFragment.senddata(adsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }

    class AdvertisementHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ads_image)
        ImageView ads_image;
        @BindView(R.id.ads_title)
        TextView ads_title;
        @BindView(R.id.ads_desc)
        TextView ads_desc;
        @BindView(R.id.youtube_img)
        ImageView youtube_img;
        @BindView(R.id.facebook_img)
        ImageView facebook_img;
        @BindView(R.id.instagram_img)
        ImageView instagram_img;
        @BindView(R.id.tiktok_img)
        ImageView tiktok_img;
        public AdvertisementHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Ads ads) {
            if(ads.getAdsImage() != null){
                Picasso.get().load("https://noamanycenter.com/uploads/files/ads/"+ads.getAdsImage()).into(ads_image);
            }else {
                ads_image.setImageResource(R.drawable.app_logo);
            }
            ads_title.setText(ads.getTitle());
            ads_desc.setText(ads.getDescription());
        }
    }
}