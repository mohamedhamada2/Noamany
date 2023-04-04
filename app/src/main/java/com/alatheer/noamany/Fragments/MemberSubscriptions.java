package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alatheer.noamany.Activities.PaymentActivity;
import com.alatheer.noamany.Adapters.MemberSupscriptionsAdapter;
import com.alatheer.noamany.AddAdvertismentViewModel;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscription;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.MemberSubscriptionViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.AddAdvertismentFragmentBinding;
import com.alatheer.noamany.databinding.FragmentMySubscriptionsBinding;

import java.util.List;

public class MemberSubscriptions extends Fragment {
    MemberSubscriptionViewModel memberSubscriptionViewModel;
    FragmentMySubscriptionsBinding fragmentMySubscriptionsBinding;
    MemberSupscriptionsAdapter memberSupscriptionsAdapter;
    GridLayoutManager layoutManager;
    Resources resources;
    Context context;
    String language;
    private boolean isloading;
    private int pastvisibleitem,visibleitemcount,totalitemcount,previous_total=0;
    int view_threshold = 10;
    int page =1 ;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String mcode,id;
    List<MemberSubscription> memberSubscriptionList;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String govern_id;
    Integer flag;
    String subscription_id,session_id,subscription_value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMySubscriptionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_subscriptions,container,false);
        View view = fragmentMySubscriptionsBinding.getRoot();
        memberSubscriptionViewModel = new MemberSubscriptionViewModel(getActivity(),this);
        //here data must be an instance of the class MarsDataProvider
        fragmentMySubscriptionsBinding.setMembersuscriptionviewmodel(memberSubscriptionViewModel);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        flag = getArguments().getInt("flag");
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
        memberSubscriptionViewModel.getmemebersupscription(page,id,govern_id);
        memberSubscriptionViewModel.getAuth();
        fragmentMySubscriptionsBinding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1){
                    Fragment fragment = new SettingsFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }else if(flag == 2){
                    Fragment fragment = new Home2();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }
            }
        });
        fragmentMySubscriptionsBinding.memberSubcriptionsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        memberSubscriptionViewModel.PerformPagination(id,page,govern_id);
                        isloading = true;
                    }

                }
            }
        });
        fragmentMySubscriptionsBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                memberSubscriptionList.clear();
                memberSubscriptionViewModel.getmemebersupscription(page,id,govern_id);
                //memberLockerAdapter.notifyDataSetChanged();
                fragmentMySubscriptionsBinding.swiperefresh.setRefreshing(false);
            }
        });
        return view;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        fragmentMySubscriptionsBinding.txtMemberSubscription.setText(resources.getString(R.string.supscription_data));
    }

    public void initrecycler(MemberSupscriptionsAdapter memberSupscriptionsAdapter, List<MemberSubscription> memberSubscriptionList) {
        this.memberSubscriptionList = memberSubscriptionList;
        fragmentMySubscriptionsBinding.memberSubcriptionsRecycler.setHasFixedSize(true);
        fragmentMySubscriptionsBinding.memberSubcriptionsRecycler.setAdapter(memberSupscriptionsAdapter);
        layoutManager = new GridLayoutManager(getActivity(),1);
        fragmentMySubscriptionsBinding.memberSubcriptionsRecycler.setLayoutManager(layoutManager);

    }

    public void setprogressbar() {
        fragmentMySubscriptionsBinding.progreesbar.setVisibility(View.GONE);
    }

    public void createDialog(MemberSubscription memberSubscription) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        final View view = inflater.inflate(R.layout.dialog_item, null);
        TextView txt = view.findViewById(R.id.txt);
        Button btn_ok = view.findViewById(R.id.btn_ok);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        txt.setText(resources.getString(R.string.add_inbody));
        btn_ok.setText(resources.getString(R.string.yes));
        btn_cancel.setText(resources.getString(R.string.no));
        builder.setView(view);
        AlertDialog dialog = builder.create();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberSubscriptionViewModel.add_inbody(memberSubscription.getId());
                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void setData(MemberSubscription memberSubscription) {
        subscription_id = memberSubscription.getId();
        subscription_value = memberSubscription.getCostAfterDiscount();
        //session_id = memberSubscription.getSession_id();
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra("id",subscription_value);
        intent.putExtra("subscription_id",subscription_id);
        startActivity(intent);
    }

    public void setSession(String id) {
        session_id =id;

    }
}
