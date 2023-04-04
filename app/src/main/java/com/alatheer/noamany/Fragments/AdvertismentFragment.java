package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
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

import com.alatheer.noamany.Adapters.AdvertisementAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.Advertisement.Ads;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.List;

public class AdvertismentFragment extends Fragment {
    @BindView(R.id.add_advertisement_img)
    ImageView add_advertisement_img;
    @BindView(R.id.txt_ads)
    TextView txt_ads;
    @BindView(R.id.ads_recycler)
    RecyclerView ads_recycler;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    String language ;
    Context context;
    Resources resources;
    List<Ads> adsList;
    AdvertisementAdapter advertisementAdapter;
    RecyclerView.LayoutManager layoutManager;
    UserSharedPreference userSharedPreference;
    SharedPreference2 sharedPreference2;
    UserModel userModel;
    String govern_id;
    Govern govern;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advertisment, container, false);
        ButterKnife.bind(this,view);
        Paper.init(getActivity());
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        userSharedPreference = UserSharedPreference.getInstance();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        userModel = userSharedPreference.Get_UserData(getActivity());
        govern_id = govern.getId();
        getAds(govern_id);
        add_advertisement_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddAdvertismentFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
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
                getAds(govern_id);
                swiperefresh.setRefreshing(false);
            }
        });
        return view;
    }

    private void getAds(String govern_id) {
        if(Utilities.isNetworkAvailable(getContext())){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<Ads>> call = getDataService.get_ads();
                call.enqueue(new Callback<List<Ads>>() {
                    @Override
                    public void onResponse(Call<List<Ads>> call, Response<List<Ads>> response) {
                        if(response.isSuccessful()){
                            adsList = response.body();
                            init_recycler_view();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ads>> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<List<Ads>> call = getDataService.get_ads();
                call.enqueue(new Callback<List<Ads>>() {
                    @Override
                    public void onResponse(Call<List<Ads>> call, Response<List<Ads>> response) {
                        if(response.isSuccessful()){
                            adsList = response.body();
                            init_recycler_view();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ads>> call, Throwable t) {

                    }
                });
            }

        }
    }

    private void init_recycler_view() {
        advertisementAdapter = new AdvertisementAdapter(getActivity(),adsList,this);
        ads_recycler.setAdapter(advertisementAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        ads_recycler.setLayoutManager(layoutManager);
        ads_recycler.setHasFixedSize(true);
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        // all_suscription_recycler.getAdapter().notifyDataSetChanged();
        txt_ads.setText(resources.getString(R.string.advertisment));
    }

    public void senddata(Ads ads) {
        AdaDetailsFragment adaDetailsFragment = new AdaDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("flag",1);
        bundle.putSerializable("ads",ads);
        adaDetailsFragment.setArguments(bundle);
        FragmentManager fragmentManager =getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, adaDetailsFragment).commit();

    }
}
