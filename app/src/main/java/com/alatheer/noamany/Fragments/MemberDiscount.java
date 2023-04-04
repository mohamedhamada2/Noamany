package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.noamany.Adapters.MemberDiscountAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.memberdiscount.Record;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.MemberDiscountViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentMemberDiscountBinding;

import java.util.List;


public class MemberDiscount extends Fragment {
    FragmentMemberDiscountBinding fragmentMemberDiscountBinding;
    MemberDiscountViewModel memberDiscountViewModel;
    UserSharedPreference userSharedPreference;
    String m_code;
    UserModel userModel;
    RecyclerView.LayoutManager layoutManager;
    MemberDiscountAdapter memberDiscountAdapter;
    String language;
    Context context;
    Resources resources;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String govern_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMemberDiscountBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_member_discount, container, false);
        memberDiscountViewModel = new MemberDiscountViewModel(getActivity(),this);
        fragmentMemberDiscountBinding.setMemberdiscountviewmodel(memberDiscountViewModel);
        View v = fragmentMemberDiscountBinding.getRoot();
        getSharedPreferanceData();
        Paper.init(getActivity());
        language = Paper.book().read("language");
        updateview(language);
        fragmentMemberDiscountBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Home2();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        return v;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        fragmentMemberDiscountBinding.txtMemberDiscount.setText(resources.getString(R.string.member_discount));
    }

    private void getSharedPreferanceData() {
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(getContext());
        m_code = userModel.getMember().getMCode();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        memberDiscountViewModel.getMemberDiscounts(m_code,govern_id);
    }

    public void setData(List<Record> records) {
        fragmentMemberDiscountBinding.memberLockerRecycler.setHasFixedSize(true);
        memberDiscountAdapter = new MemberDiscountAdapter(records,getContext());
        fragmentMemberDiscountBinding.memberLockerRecycler.setAdapter(memberDiscountAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        fragmentMemberDiscountBinding.memberLockerRecycler.setLayoutManager(layoutManager);
    }
}