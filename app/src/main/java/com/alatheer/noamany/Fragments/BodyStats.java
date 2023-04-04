package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.noamany.Adapters.BodyStatsAdapter;
import com.alatheer.noamany.BodyStatsViewModel;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.MemberInbody.MemberInbudy;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentBodyStatsBinding;

import java.util.ArrayList;
import java.util.List;

public class BodyStats extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    BodyStatsAdapter bodyStatsAdapter;
    List<MemberInbudy>bodyStatesModelslist;
    FragmentBodyStatsBinding fragmentBodyStatsBinding;
    BodyStatsViewModel bodyStatsViewModel;
    MemberInbudy memberInbudy;
    UserSharedPreference userSharedPreference;
    UserModel usermodel;
    SharedPreference2 sharedPreference2;
    Govern govern;
    private SendData sendData;
    String language,govern_id;
    Context context;
    Resources resources;
    String mcode;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            sendData = (SendData) getActivity();
        }
        catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBodyStatsBinding  = DataBindingUtil.inflate(inflater,R.layout.fragment_body_stats,container,false);
        View v = fragmentBodyStatsBinding.getRoot();
        bodyStatsViewModel = new BodyStatsViewModel(getActivity(),this);
        fragmentBodyStatsBinding.setBodystatsviewmodel(bodyStatsViewModel);
        Paper.init(getActivity());
        userSharedPreference = UserSharedPreference.getInstance();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        usermodel = userSharedPreference.Get_UserData(getActivity());
        mcode = usermodel.getMember().getMCode();
        language = Paper.book().read("language");
        if (language == null) {
            Paper.book().write("language", "ar");
        }
        updateview(language);
        bodyStatsViewModel.get_member_inbody(mcode,govern_id);
        fragmentBodyStatsBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        fragmentBodyStatsBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bodyStatsViewModel.get_member_inbody(mcode,govern_id);
                //memberLockerAdapter.notifyDataSetChanged();
                fragmentBodyStatsBinding.swiperefresh.setRefreshing(false);
            }
        });
        return v;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(), language);
        resources = context.getResources();
        fragmentBodyStatsBinding.txtBodyStats.setText(resources.getString(R.string.body_stats));

    }

    public void initrecyclerview(List<MemberInbudy> memberInbudyList) {
        fragmentBodyStatsBinding.recyclerBodyStates.setHasFixedSize(true);
        bodyStatsAdapter = new BodyStatsAdapter(getActivity(),this,memberInbudyList);
        fragmentBodyStatsBinding.recyclerBodyStates.setAdapter(bodyStatsAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        fragmentBodyStatsBinding.recyclerBodyStates.setLayoutManager(layoutManager);
    }

    public void sendDatatoFragment(MemberInbudy memberInbudy) {
        sendData.send_member_inbody_data(memberInbudy);
    }
    public interface SendData {
        void send_member_inbody_data(MemberInbudy memberInbudy);
    }
}
