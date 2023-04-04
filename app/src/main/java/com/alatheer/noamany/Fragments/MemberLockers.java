package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.noamany.Adapters.MemberLockerAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.MemberLocker.MemberLocker;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.MemberLockerViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentMemberLockersBinding;

import java.util.List;

public class MemberLockers extends Fragment {
    FragmentMemberLockersBinding fragmentMemberLockersBinding;
    MemberLockerViewModel memberLockerViewModel;
    MemberLockerAdapter memberLockerAdapter;
    RecyclerView.LayoutManager layoutManager;
    String language;
    Context context;
    Resources resources;
    UserSharedPreference userSharedPreference;
    SharedPreference2 sharedPreference2;
    UserModel userModel;
    Govern govern;
    String mCode,id,govern_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMemberLockersBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_member_lockers,container,false);
        View view = fragmentMemberLockersBinding.getRoot();
        memberLockerViewModel = new MemberLockerViewModel(getActivity(),this);
        fragmentMemberLockersBinding.setMemberlockerviewmodel(memberLockerViewModel);
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
        mCode = userModel.getMember().getMCode();
        id = userModel.getMember().getId();
        memberLockerViewModel.get_members_locker(id,govern_id);
        fragmentMemberLockersBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        fragmentMemberLockersBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                memberLockerViewModel.get_members_locker(id,govern_id);
                //memberLockerAdapter.notifyDataSetChanged();
                fragmentMemberLockersBinding.swiperefresh.setRefreshing(false);
            }
        });
        return view;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        fragmentMemberLockersBinding.txtLockerData.setText(resources.getString(R.string.locker_data));

    }

    public void initrecycler(List<MemberLocker> memberLockerList) {
        fragmentMemberLockersBinding.memberLockerRecycler.setHasFixedSize(true);
        memberLockerAdapter = new MemberLockerAdapter(memberLockerList,getActivity());
        fragmentMemberLockersBinding.memberLockerRecycler.setAdapter(memberLockerAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        fragmentMemberLockersBinding.memberLockerRecycler.setLayoutManager(layoutManager);
    }
}
