package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

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

import com.alatheer.noamany.Adapters.MemberVedioAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.MemberVedio.MemberVedio;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.MemberSubscriptionViewModel;
import com.alatheer.noamany.MemberVedioViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentMembersVediosBinding;

import java.util.List;

public class MembersVedios extends Fragment {
    MemberVedioViewModel memberVedioViewModel;
    FragmentMembersVediosBinding fragmentMembersVediosBinding;
    MemberVedioAdapter memberVedioAdapter;
    RecyclerView.LayoutManager layoutManager;
    String language;
    Context context;
    Resources resources;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String mcode;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String govern_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMembersVediosBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_members_vedios, container, false);
        View view = fragmentMembersVediosBinding.getRoot();
        memberVedioViewModel = new MemberVedioViewModel(getActivity(),this);
        //here data must be an instance of the class MarsDataProvider
        fragmentMembersVediosBinding.setMembervediosviewmodel(memberVedioViewModel);
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
        mcode = userModel.getMember().getMCode();
        memberVedioViewModel.get_member_vedioes(mcode,govern_id);
        fragmentMembersVediosBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Home2();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        fragmentMembersVediosBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                memberVedioViewModel.get_member_vedioes(mcode,govern_id);
                //memberLockerAdapter.notifyDataSetChanged();
                fragmentMembersVediosBinding.swiperefresh.setRefreshing(false);
            }
        });

        return  view;

    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        fragmentMembersVediosBinding.txtMemberExercise.setText(resources.getString(R.string.exercise));
    }


    public void init_recycler(List<MemberVedio> memberVedioList) {
        fragmentMembersVediosBinding.memberVediosRecycler.setHasFixedSize(true);
        memberVedioAdapter = new MemberVedioAdapter(getActivity(),memberVedioList);
        fragmentMembersVediosBinding.memberVediosRecycler.setAdapter(memberVedioAdapter);
        layoutManager = new GridLayoutManager(getActivity(),1);
        fragmentMembersVediosBinding.memberVediosRecycler.setLayoutManager(layoutManager);
    }
}
