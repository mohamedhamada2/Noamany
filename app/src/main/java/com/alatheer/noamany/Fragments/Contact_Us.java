package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.BodyStatsDetailsViewModel;
import com.alatheer.noamany.ContactUsViewModel;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentContactUsBinding;

import java.util.ArrayList;
import java.util.List;


public class Contact_Us extends Fragment {
  String language;
  Context context;
  Resources resources;
  FragmentContactUsBinding fragmentContactUsBinding;
  ContactUsViewModel contactUsViewModel;
  int type;
  String name,phone,email,content;
  UserSharedPreference userSharedPreference;
  UserModel userModel;
  String m_code,m_name,m_phone,m_email;
  SharedPreference2 sharedPreference2;
  Govern govern;
  String govern_id;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    fragmentContactUsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact__us, container, false);
    View v = fragmentContactUsBinding.getRoot();
    contactUsViewModel = new ContactUsViewModel(getActivity());
    fragmentContactUsBinding.setContactUsviewmodel(contactUsViewModel);
    userSharedPreference = UserSharedPreference.getInstance();
    userModel = userSharedPreference.Get_UserData(getActivity());
    sharedPreference2 = SharedPreference2.getInstance();
    govern = sharedPreference2.Get_UserData(getActivity());
    govern_id = govern.getId();
    setData();
    Paper.init(getActivity());
    language = Paper.book().read("language");
    if (language == null) {
      Paper.book().write("language", "ar");
    }
    updateview(language);
    handleSpinner();
    fragmentContactUsBinding.imgBack.setOnClickListener(new View.OnClickListener() {
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
    fragmentContactUsBinding.btnSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Validation();
      }
    });
    fragmentContactUsBinding.whatsappImg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send/?phone=+201212156070"));
        startActivity(intent);
      }
    });
    fragmentContactUsBinding.txtPhoneNumber.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Uri number = Uri.parse("tel:+201212156070");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
      }
    });
    return v;
  }

  private void setData() {
    m_code = userModel.getMember().getMCode();
    m_name = userModel.getMember().getMName();
    m_email = userModel.getMember().getMEmail();
    m_phone = userModel.getMember().getMTel();
    fragmentContactUsBinding.etClientName.setText(m_name);
    fragmentContactUsBinding.etClientEmail.setText(m_email);
    fragmentContactUsBinding.etClientPhone.setText(m_phone);
  }

  private void Validation() {

    content = fragmentContactUsBinding.etMessage.getText().toString();
    if(!TextUtils.isEmpty(m_name)&&!TextUtils.isEmpty(m_email)&&!TextUtils.isEmpty(m_phone)&&
            !TextUtils.isEmpty(content)&& type != 0){
      contactUsViewModel.add_contact(m_code,m_name,m_phone,m_email,type+"",content,govern_id);
    }else {
      if(TextUtils.isEmpty(name)){
        fragmentContactUsBinding.etClientName.setError(resources.getString(R.string.validate_name));
      }else {
        fragmentContactUsBinding.etClientName.setError(null);
      }
      if(TextUtils.isEmpty(phone)){
        fragmentContactUsBinding.etClientPhone.setError(resources.getString(R.string.validate_phone));
      }else {
        fragmentContactUsBinding.etClientPhone.setError(null);
      }
      if(TextUtils.isEmpty(email)){
        fragmentContactUsBinding.etClientEmail.setError(resources.getString(R.string.validate_email));
      }else {
        fragmentContactUsBinding.etClientEmail.setError(null);
      }
      if(TextUtils.isEmpty(content)){
        fragmentContactUsBinding.etMessage.setError(resources.getString(R.string.validate_content));
      }else {
        fragmentContactUsBinding.etMessage.setError(null);
      }
      if(type == 0){
        Toast.makeText(getActivity(),resources.getString(R.string.validate_subject), Toast.LENGTH_SHORT).show();
      }
    }
  }

  private void updateview(String language) {
    context = LocaleHelper.setLocale(getActivity(), language);
    resources = context.getResources();
    fragmentContactUsBinding.etClientName.setHint(resources.getString(R.string.client_name));
    fragmentContactUsBinding.etClientEmail.setHint(resources.getString(R.string.client_email));
    fragmentContactUsBinding.etClientPhone.setHint(resources.getString(R.string.client_phone));
    fragmentContactUsBinding.etMessage.setHint(resources.getString(R.string.message_info));
    fragmentContactUsBinding.btnSend.setText(resources.getString(R.string.send_message));
    fragmentContactUsBinding.txtContactUs.setText(resources.getString(R.string.contact_us));
  }

  public void handleSpinner() {
    fragmentContactUsBinding.spinner.setPrompt(getResources().getString(R.string.subject));
    List<String> list = new ArrayList();
    list.add(0,resources.getString(R.string.subject));
    list.add(1,resources.getString(R.string.thanks));
    list.add(2,resources.getString(R.string.evaluation));
    list.add(3,resources.getString(R.string.problem));
    ArrayAdapter governmentadapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, list);
    fragmentContactUsBinding.spinner.setAdapter(governmentadapter);
    fragmentContactUsBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        textView.setTextColor(getResources().getColor(R.color.lightblack));
        type = position;
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }
}