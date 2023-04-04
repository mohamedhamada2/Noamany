package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.noamany.BodyStatsDetailsViewModel;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.MemberInbody.MemberInbudy;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentBodyStatsBinding;
import com.alatheer.noamany.databinding.FragmentBodyStatsDetailsBinding;
import com.squareup.picasso.Picasso;

public class BodyStatsDetails extends Fragment {
    FragmentBodyStatsDetailsBinding fragmentBodyStatsBinding;
    BodyStatsDetailsViewModel bodyStatsViewModel;
    MemberInbudy memberInbudy;
    Context context;
    Resources resources;
    String language,govern_id;
    SharedPreference2 sharedPreference2;
    Govern govern;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBodyStatsBinding  = DataBindingUtil.inflate(inflater,R.layout.fragment_body_stats_details,container,false);
        View v = fragmentBodyStatsBinding.getRoot();
        bodyStatsViewModel = new BodyStatsDetailsViewModel(getActivity());
        fragmentBodyStatsBinding.setMemberbodystatsdetailsviewmodel(bodyStatsViewModel);
        Paper.init(getActivity());
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        SetData();
        fragmentBodyStatsBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BodyStats();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        return v;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        if (govern_id.equals("1")){
            Picasso.get().load("https://noamanycenter.com/uploads/images/"+memberInbudy.getImage()).into(fragmentBodyStatsBinding.img);
        }else {
            Picasso.get().load("https://gym.noamanycenter.com/uploads/images/"+memberInbudy.getImage()).into(fragmentBodyStatsBinding.img);
        }
        /*fragmentBodyStatsBinding.txtTitle.setText(resources.getString(R.string.body_stats));
        fragmentBodyStatsBinding.txtSerialNum.setText(resources.getString(R.string.serial_number));
        fragmentBodyStatsBinding.txtGender.setText(resources.getString(R.string.gender));
        fragmentBodyStatsBinding.txtOld.setText(resources.getString(R.string.old));
        fragmentBodyStatsBinding.txtLength.setText(resources.getString(R.string.length));
        fragmentBodyStatsBinding.txtWaterAmount.setText(resources.getString(R.string.wateramount));
        fragmentBodyStatsBinding.txtProtein.setText(resources.getString(R.string.protein));
        fragmentBodyStatsBinding.txtMinerals.setText(resources.getString(R.string.minerals));
        fragmentBodyStatsBinding.txtFats.setText(resources.getString(R.string.fats));
        fragmentBodyStatsBinding.txtWeights.setText(resources.getString(R.string.weight));*/

    }

    private void SetData() {
        /*fragmentBodyStatsBinding.serialNumber.setText(memberInbudy.getExRkmFk());
        fragmentBodyStatsBinding.gender.setText(memberInbudy.getGender());
        fragmentBodyStatsBinding.old.setText(memberInbudy.getAge());
        fragmentBodyStatsBinding.length.setText(memberInbudy.getHeight());
        fragmentBodyStatsBinding.waterAmount.setText(memberInbudy.getTotalBodyWater());
        fragmentBodyStatsBinding.protein.setText(memberInbudy.getProtein());
        fragmentBodyStatsBinding.minerals.setText(memberInbudy.getMinerals());
        fragmentBodyStatsBinding.fats.setText(memberInbudy.getBodyFatMass());
        fragmentBodyStatsBinding.weights.setText(memberInbudy.getWeight());
        fragmentBodyStatsBinding.date.setText(memberInbudy.getDateAr());*/
    }

    public void  receiveData(MemberInbudy memberInbudy){
        this.memberInbudy = memberInbudy;

    }
}