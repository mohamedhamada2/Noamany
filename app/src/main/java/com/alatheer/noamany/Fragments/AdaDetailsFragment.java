package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.noamany.Data.Remote.Advertisement.Ads;
import com.alatheer.noamany.Data.Remote.News.NewsModel;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.squareup.picasso.Picasso;


public class AdaDetailsFragment extends Fragment {
Ads ads;
NewsModel newsModel;
TextView ads_title,ads_desc,txt_ads;
ImageView ads_image,back_img;
String language;
Context context;
Resources resources;
int flag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ada_details, container, false);
        txt_ads = view.findViewById(R.id.txt_ads);
        ads_title = view.findViewById(R.id.ads_title);
        ads_desc = view.findViewById(R.id.ads_desc);
        ads_image = view.findViewById(R.id.ads_image);
        back_img = view.findViewById(R.id.back_img);
        flag = getArguments().getInt("flag",0);
        if (flag == 1){
            ads = (Ads) getArguments().getSerializable("ads");
            Paper.init(getActivity());
            language = Paper.book().read("language");
            if(language == null){
                Paper.book().write("language","ar");
            }
            updateview(language);
            if(ads.getAdsImage() != null){
                Picasso.get().load("https://noamanycenter.com/uploads/files/ads/"+ads.getAdsImage()).into(ads_image);
            }else {
                ads_image.setImageResource(R.drawable.app_logo);
            }
            ads_title.setText(ads.getTitle());
            ads_desc.setText(ads.getDescription());
            back_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = new AdvertismentFragment();

                    FragmentManager fragmentManager = getFragmentManager();

                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                }
            });
        }else {
            newsModel = (NewsModel) getArguments().getSerializable("news");

            Paper.init(getActivity());
            language = Paper.book().read("language");
            if(language == null){
                Paper.book().write("language","ar");
            }
            updateview(language);
            if(newsModel.getNewsImage() != null){
                Picasso.get().load("https://noamanycenter.com/uploads/images/news/"+newsModel.getNewsImage()).into(ads_image);
            }else {
                ads_image.setImageResource(R.drawable.app_logo);
            }
            ads_title.setText(newsModel.getNewsName());
            ads_desc.setText(newsModel.getNewsDetails());
            back_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = new NewsFragment();

                    FragmentManager fragmentManager = getFragmentManager();

                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                }
            });
        }
        return view;

    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        // all_suscription_recycler.getAdapter().notifyDataSetChanged();
        if (flag == 1){
            txt_ads.setText(resources.getString(R.string.advertisment));
        }else {
            txt_ads.setText(resources.getString(R.string.news));
        }
    }
}