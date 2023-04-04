package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Adapters.AllSubscriptionAdapters;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AllSubscription.AllSubscription;
import com.alatheer.noamany.Data.Remote.AllSubscription.SubscriptionListModel;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class HomeFragment extends Fragment {
  @BindView(R.id.all_subcriptions_recycler)
  RecyclerView all_suscription_recycler;
  @BindView(R.id.progreesbar)
  ProgressBar progressBar;
  @BindView(R.id.txt_home)
  TextView txt_home;
  @BindView(R.id.img_back)
  ImageView img_back;
  String language,govern_id;
  List<AllSubscription> subscriptionList;
  RecyclerView.LayoutManager layoutManager;
  AllSubscriptionAdapters allSubscriptionAdapters;
  UserSharedPreference userSharedPreference;
  UserModel userModel;
  SharedPreference2 sharedPreference2;
  Govern govern;
  Context context;
  Resources resources;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v= inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this,v);
    Paper.init(getActivity());
    language = Paper.book().read("language");
    if(language == null){
      Paper.book().write("language","ar");
    }
    updateview(language);
    userSharedPreference = UserSharedPreference.getInstance();
    userModel = userSharedPreference.Get_UserData(getActivity());
    sharedPreference2 = SharedPreference2.getInstance();
    govern = sharedPreference2.Get_UserData(getActivity());
    govern_id = govern.getId();
    getSubscription(govern_id);
    img_back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new Home2();
          FragmentManager fragmentManager = getFragmentManager();
          fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }else {
          Fragment fragment = new Home3();
          FragmentManager fragmentManager = getFragmentManager();
          fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }

      }
    });
    return v;
  }

  private void updateview(String language) {
    context = LocaleHelper.setLocale(getActivity(),language);
    resources = context.getResources();
    // all_suscription_recycler.getAdapter().notifyDataSetChanged();
    txt_home.setText(resources.getString(R.string.supscription));
  }

  private void getSubscription(String govern_id) {
    if(Utilities.isNetworkAvailable(getActivity())){
      if (govern_id.equals("1")){
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<SubscriptionListModel> call = getDataService.getAllSubscriptionList();
        call.enqueue(new Callback<SubscriptionListModel>() {
          @Override
          public void onResponse(Call<SubscriptionListModel> call, Response<SubscriptionListModel> response) {
            if(response.isSuccessful()){
              subscriptionList = response.body().getAllSubcriptions();
              progressBar.setVisibility(View.GONE);
              initrecycler();
            }
          }

          @Override
          public void onFailure(Call<SubscriptionListModel> call, Throwable t) {

          }
        });
      }else {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
        Call<SubscriptionListModel> call = getDataService.getAllSubscriptionList();
        call.enqueue(new Callback<SubscriptionListModel>() {
          @Override
          public void onResponse(Call<SubscriptionListModel> call, Response<SubscriptionListModel> response) {
            if(response.isSuccessful()){
              subscriptionList = response.body().getAllSubcriptions();
              progressBar.setVisibility(View.GONE);
              initrecycler();
            }
          }

          @Override
          public void onFailure(Call<SubscriptionListModel> call, Throwable t) {

          }
        });
      }
    }
  }

  private void initrecycler() {
    layoutManager = new LinearLayoutManager(getActivity());
    all_suscription_recycler.setLayoutManager(layoutManager);
    allSubscriptionAdapters = new AllSubscriptionAdapters(getActivity(),subscriptionList);
    all_suscription_recycler.setAdapter(allSubscriptionAdapters);
    all_suscription_recycler.setHasFixedSize(true);
  }
}
