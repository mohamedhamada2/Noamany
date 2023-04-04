package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.paperdb.Paper;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.InviteFriendViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;
import com.alatheer.noamany.databinding.FragmentInviteFriendBinding;

public class InviteFriend extends Fragment {
  FragmentInviteFriendBinding fragmentInviteFriendBinding;
  InviteFriendViewModel inviteFriendViewModel;
  UserSharedPreference userSharedPreference;
  SharedPreference2 sharedPreference2;
  Govern govern;
  UserModel userModel;
  String m_name,m_tel,m_card,m_code,r_name,r_national_num,r_phone,language,r_age,govern_id;
  Context context;
  Resources resources;
  int age;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    fragmentInviteFriendBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_invite_friend, container, false);
    View view = fragmentInviteFriendBinding.getRoot();
    inviteFriendViewModel = new InviteFriendViewModel(getActivity());
    fragmentInviteFriendBinding.setInvitefriendviewmodel(inviteFriendViewModel);
    userSharedPreference = UserSharedPreference.getInstance();
    userModel = userSharedPreference.Get_UserData(getActivity());
    sharedPreference2 = SharedPreference2.getInstance();
    govern = sharedPreference2.Get_UserData(getActivity());
    govern_id = govern.getId();
    fragmentInviteFriendBinding.btnInvite.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        validation();
      }
    });
    fragmentInviteFriendBinding.imgBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new Home2();

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
      }
    });
    Paper.init(getActivity());
    language = Paper.book().read("language");
    if(language == null){
      Paper.book().write("language","ar");
    }
    updateview(language);
    return view;
  }

  private void updateview(String language) {
    context = LocaleHelper.setLocale(getActivity(),language);
    resources = context.getResources();
    fragmentInviteFriendBinding.etName.setHint(resources.getString(R.string.name));
    fragmentInviteFriendBinding.etNationalNum.setHint(resources.getString(R.string.national_num));
    fragmentInviteFriendBinding.etTelephone.setHint(resources.getString(R.string.phone));
    fragmentInviteFriendBinding.etAge.setHint(resources.getString(R.string.age));
    fragmentInviteFriendBinding.btnInvite.setText(resources.getString(R.string.invite_friend));
    fragmentInviteFriendBinding.txtInvite.setText(resources.getString(R.string.invite_friend));
  }

  private void validation() {
    r_name = fragmentInviteFriendBinding.etName.getText().toString();
    r_national_num = fragmentInviteFriendBinding.etNationalNum.getText().toString();
    r_phone = fragmentInviteFriendBinding.etTelephone.getText().toString();
    try{
      r_age = fragmentInviteFriendBinding.etAge.getText().toString();
      age = Integer.parseInt(r_age);
    }catch (Exception e){
      age = 0;
    }
    m_name = userModel.getMember().getMName();
    m_card = userModel.getMember().getMCard();
    m_tel = userModel.getMember().getMTel();
    m_code = userModel.getMember().getMCode();
    if(!TextUtils.isEmpty(r_name)&&!TextUtils.isEmpty(r_national_num)&&!TextUtils.isEmpty(r_phone)&& age>= 16){
      inviteFriendViewModel.invite(m_name,m_tel,m_card,m_code,r_name,r_national_num,r_phone,age,govern_id);
    }else {
      if(TextUtils.isEmpty(r_name)){
        fragmentInviteFriendBinding.etName.setError(resources.getString(R.string.validate_name));
      }else {
        fragmentInviteFriendBinding.etName.setError(null);
      }
      if(TextUtils.isEmpty(r_national_num)){
        fragmentInviteFriendBinding.etNationalNum.setError(resources.getString(R.string.validate_national_num));
      }else {
        fragmentInviteFriendBinding.etNationalNum.setError(null);
      }
      if(TextUtils.isEmpty(r_phone)){
        fragmentInviteFriendBinding.etTelephone.setError(resources.getString(R.string.validate_phone));
      }else {
        fragmentInviteFriendBinding.etTelephone.setError(null);
      }
      if(age < 16){
        fragmentInviteFriendBinding.etAge.setError(resources.getString(R.string.validate_age));
      }else {
        fragmentInviteFriendBinding.etAge.setError(null);
      }
    }
  }

}
