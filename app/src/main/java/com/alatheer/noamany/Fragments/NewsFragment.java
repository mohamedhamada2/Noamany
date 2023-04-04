package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Adapters.NewsAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.News.NewsModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.List;


public class NewsFragment extends Fragment {
  @BindView(R.id.back_img)
  ImageView back_img;
  @BindView(R.id.txt_news)
  TextView txt_news;
  @BindView(R.id.news_recycler)
  RecyclerView news_recycler;
  @BindView(R.id.swiperefresh)
  SwipeRefreshLayout swiperefresh;
  String language,govern_id;
  Context context;
  Resources resources;
  List<NewsModel> newsModelList;
  NewsAdapter newsAdapter;
  RecyclerView.LayoutManager layoutManager;
  UserSharedPreference userSharedPreference;
  UserModel userModel;
  SharedPreference2 sharedPreference2;
    Govern govern;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_news, container, false);
    ButterKnife.bind(this,view);
    userSharedPreference = UserSharedPreference.getInstance();
    userModel = userSharedPreference.Get_UserData(getActivity());
    sharedPreference2 = SharedPreference2.getInstance();
    govern = sharedPreference2.Get_UserData(getActivity());
    govern_id = govern.getId();
    //Log.e("govern_id",govern_id);
    language = Paper.book().read("language");
    if(language == null){
      Paper.book().write("language","ar");
    }
    updateview(language);
    getNews(govern_id);
    back_img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new Home2();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Fragment fragment = new Home3();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }

      }
    });
    swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        getNews(govern_id);
        swiperefresh.setRefreshing(false);
      }
    });
    return view;
  }

  private void getNews(String govern_id) {

    if(Utilities.isNetworkAvailable(getActivity())){
      if (govern_id.equals("1")){
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<NewsModel>> call = getDataService.get_news();
        call.enqueue(new Callback<List<NewsModel>>() {
          @Override
          public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
            if(response.isSuccessful()){
              newsModelList = response.body();
              initrecycler(newsModelList);
            }
          }

          @Override
          public void onFailure(Call<List<NewsModel>> call, Throwable t) {

          }
        });
      }else {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
        Call<List<NewsModel>> call = getDataService.get_news();
        call.enqueue(new Callback<List<NewsModel>>() {
          @Override
          public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
            if(response.isSuccessful()){
              newsModelList = response.body();
              initrecycler(newsModelList);
            }
          }

          @Override
          public void onFailure(Call<List<NewsModel>> call, Throwable t) {

          }
        });
      }
    }

  }

  private void initrecycler(List<NewsModel> newsModelList) {
    newsAdapter = new NewsAdapter(getActivity(),newsModelList,this);
    news_recycler.setAdapter(newsAdapter);
    layoutManager = new LinearLayoutManager(getActivity());
    news_recycler.setLayoutManager(layoutManager);
    news_recycler.setHasFixedSize(true);

  }

  private void updateview(String language) {
    context = LocaleHelper.setLocale(getActivity(),language);
    resources = context.getResources();
    txt_news.setText(resources.getString(R.string.news));
  }

  public void setData(NewsModel newsModel) {
    AdaDetailsFragment adaDetailsFragment = new AdaDetailsFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("flag",2);
    bundle.putSerializable("news",newsModel);
    adaDetailsFragment.setArguments(bundle);
    FragmentManager fragmentManager =getFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.fragment_container, adaDetailsFragment).commit();
  }
}
