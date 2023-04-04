package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
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

import com.alatheer.noamany.Adapters.AdvertisementAdapter;
import com.alatheer.noamany.Adapters.StoppedSubscriptionAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.Advertisement.Ads;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscription;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscriptionListModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.ArrayList;
import java.util.List;


public class StoppedMember extends Fragment {
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.txt_stopped_data)
    TextView txt_stopped_data;
    @BindView(R.id.stopped_recycler)
    RecyclerView stopped_recycler;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    String language,govern_id ;
    Context context;
    Resources resources;
    List<MemberSubscription> memberSubscriptionList;
    StoppedSubscriptionAdapter stoppedSubscriptionAdapter;
    GridLayoutManager layoutManager;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String mcode,id;
    private int pastvisibleitem,visibleitemcount,totalitemcount,previous_total=0;
    int view_threshold = 10;
    int page =1 ;
    private boolean isloading;
    SharedPreference2 sharedPreference2;
    Govern govern;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stopped_member, container, false);
        memberSubscriptionList = new ArrayList<>();
        ButterKnife.bind(this,view);
        Paper.init(getActivity());
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(getActivity());
        mcode = userModel.getMember().getMCode();
        id = userModel.getMember().getId();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        getStoppedData(id,govern_id);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                memberSubscriptionList.clear();
                getStoppedData(id,govern_id);
                //stoppedSubscriptionAdapter.notifyDataSetChanged();
                swiperefresh.setRefreshing(false);
            }
        });
        stopped_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleitemcount = layoutManager.getChildCount();
                totalitemcount = layoutManager.getItemCount();
                pastvisibleitem = layoutManager.findFirstVisibleItemPosition();
                if(dy>0){
                    if(isloading){
                        if(totalitemcount>previous_total){
                            isloading = false;
                            previous_total = totalitemcount;
                        }
                    }
                    if(!isloading &&(totalitemcount-visibleitemcount)<= pastvisibleitem+view_threshold){
                        page++;
                        PerformPagination(page);
                        isloading = true;
                    }

                }
            }
        });
        return view;
    }

    private void getStoppedData(String mcode,String govern_id) {
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(mcode,1);
                call.enqueue(new Callback<MemberSubscriptionListModel>() {
                    @Override
                    public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                        if (response.isSuccessful()) {
                            if(!response.body().getMemberSubscriptions().isEmpty()){
                                for (MemberSubscription memberSubscription : response.body().getMemberSubscriptions()) {
                                    if (memberSubscription.getStoppedSubscription().equals("1")) {
                                        memberSubscriptionList.add(memberSubscription);
                                    }
                                    init_recycler_view(memberSubscriptionList);
                                }
                            }else {
                                Toast.makeText(getActivity(), resources.getString(R.string.stopped_data_found), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                    @Override
                    public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(mcode,1);
                call.enqueue(new Callback<MemberSubscriptionListModel>() {
                    @Override
                    public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                        if (response.isSuccessful()) {
                            if(!response.body().getMemberSubscriptions().isEmpty()){
                                for (MemberSubscription memberSubscription : response.body().getMemberSubscriptions()) {
                                    if (memberSubscription.getStoppedSubscription().equals("1")) {
                                        memberSubscriptionList.add(memberSubscription);
                                    }
                                    init_recycler_view(memberSubscriptionList);
                                }
                            }else {
                                Toast.makeText(getActivity(), resources.getString(R.string.stopped_data_found), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                    @Override
                    public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {

                    }
                });
            }

        }
    }
    private void PerformPagination(int page) {
        if(Utilities.isNetworkAvailable(context)){
            if (Utilities.isNetworkAvailable(context)) {
                if (govern_id.equals("1")){
                    GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(id,page);
                    call.enqueue(new Callback<MemberSubscriptionListModel>() {
                        @Override
                        public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                            if(response.isSuccessful()){
                                if(page <= response.body().getPagesCount()){
                                    memberSubscriptionList = response.body().getMemberSubscriptions();
                                    stoppedSubscriptionAdapter.add_subscription(memberSubscriptionList);
                                    //Toast.makeText(context, "page:"+page+"is loaded", Toast.LENGTH_SHORT).show();
                                }else {
                                    //Toast.makeText(context, "no more available", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {

                        }
                    });
                }else {
                    GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                    Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(id,page);
                    call.enqueue(new Callback<MemberSubscriptionListModel>() {
                        @Override
                        public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                            if(response.isSuccessful()){
                                if(page <= response.body().getPagesCount()){
                                    memberSubscriptionList = response.body().getMemberSubscriptions();
                                    stoppedSubscriptionAdapter.add_subscription(memberSubscriptionList);
                                    //Toast.makeText(context, "page:"+page+"is loaded", Toast.LENGTH_SHORT).show();
                                }else {
                                    //Toast.makeText(context, "no more available", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {

                        }
                    });
                }

            }

        }
    }

    private void init_recycler_view(List<MemberSubscription> memberSubscriptionList) {
        stoppedSubscriptionAdapter = new StoppedSubscriptionAdapter(getActivity(),memberSubscriptionList);
        stopped_recycler.setAdapter(stoppedSubscriptionAdapter);
        layoutManager = new GridLayoutManager(getActivity(),1);
        stopped_recycler.setLayoutManager(layoutManager);
        stopped_recycler.setHasFixedSize(true);
    }



    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        // all_suscription_recycler.getAdapter().notifyDataSetChanged();
        txt_stopped_data.setText(resources.getString(R.string.stopped_data));
    }

}
