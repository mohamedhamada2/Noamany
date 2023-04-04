package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Activities.SplashActivity;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Data.Remote.wallet.Wallet;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;
import com.alatheer.noamany.receipts.ReceiptsFragment;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

public class SettingsFragment extends Fragment {
  @BindView(R.id.relative_profile)
  RelativeLayout relative_profile;
  @BindView(R.id.relative_eshterakat)
  RelativeLayout relative_eshterakat;
  @BindView(R.id.relative_add_subscription)
  RelativeLayout relative_add_subscription;
  @BindView(R.id.relative_loker)
  RelativeLayout relative_loker;
  @BindView(R.id.relative_waqf)
  RelativeLayout relative_waqf;
  @BindView(R.id.relative_body)
  RelativeLayout relative_body;
  @BindView(R.id.relative_share)
  RelativeLayout relative_share;
  @BindView(R.id.relative_change_language)
  RelativeLayout relative_change_language;
  @BindView(R.id.relative_contact_us)
  RelativeLayout relative_contact_us;
  @BindView(R.id.relative_receipts)
  RelativeLayout relative_receipts;
  @BindView(R.id.relative_logout)
  RelativeLayout relative_logout;
  @BindView(R.id.txt_profile_data)
  TextView txt_profile_data;
  @BindView(R.id.txt_subscription_data)
  TextView txt_subscription_data;
  @BindView(R.id.txt_add_subscription)
  TextView txt_add_subscription;
  @BindView(R.id.txt_locker_data)
  TextView txt_locker_data;
  @BindView(R.id.txt_stopped_data)
  TextView txt_stopped_data;
  @BindView(R.id.txt_body_stats)
  TextView txt_body_stats;
  @BindView(R.id.txt_share)
  TextView txt_share;
  @BindView(R.id.txt_language)
  TextView txt_language;
  @BindView(R.id.txt_contact_us)
  TextView txt_contact_us;
  @BindView(R.id.txt_receipts)
  TextView txt_receipts;
  @BindView(R.id.txt_logout)
  TextView txt_logout;
  @BindView(R.id.user_img)
  ImageView user_img;
  @BindView(R.id.txt_name)
  TextView txt_name;
  @BindView(R.id.values)
  TextView values;
  @BindView(R.id.txt_value)
  TextView txt_value;
  @BindView(R.id.points)
  TextView points;
  @BindView(R.id.txt_points)
  TextView txt_points;
  UserSharedPreference userSharedPreference;
  SharedPreference2 sharedPreference2;
  UserModel userModel;
  String token,language,govern_id;
  Context context;
  Resources resources;
  String m_code,m_name,m_image;
  Govern govern ;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    userSharedPreference = UserSharedPreference.getInstance();
    userModel = userSharedPreference.Get_UserData(getActivity());
    sharedPreference2 = SharedPreference2.getInstance();
    govern = sharedPreference2.Get_UserData(getActivity());
    govern_id = govern.getId();
    ButterKnife.bind(this,view);
    Paper.init(getContext());
    m_code = userModel.getMember().getMCode();
    m_name = userModel.getMember().getMName();
    m_image = userModel.getMember().getMImage();
    get_points_in_wallet();
    txt_name.setText(m_name);
    Log.e("m_image",m_image);
    if(m_image != null){
      Picasso.get().load("https://noamanycenter.com/uploads/images/"+m_image).into(user_img);
    }else {
      user_img.setImageResource(R.drawable.ic_account_profile);
    }
    language = Paper.book().read("language");
    if(language == null){
      Paper.book().write("language","ar");
    }
    updateview(language);
    relative_profile.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new ProfileFragment();

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
      }
    });
    relative_body.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new BodyStats();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
          Animatoo.animateFade(getActivity());
        }else {
          Utilities.CreatealertDialog(getActivity());
        }
      }
    });
    relative_eshterakat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new MemberSubscriptions();

          FragmentManager fragmentManager = getFragmentManager();
          Bundle bundle = new Bundle();
          bundle.putInt("flag",1);
          fragment.setArguments(bundle);
          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }
      }
    });
    relative_eshterakat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new MemberSubscriptions();

          FragmentManager fragmentManager = getFragmentManager();
          Bundle bundle = new Bundle();
          bundle.putInt("flag",1);
          fragment.setArguments(bundle);
          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }
      }
    });
    relative_add_subscription.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new AddSubscriptionFragment();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }

      }
    });
    relative_loker.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new MemberLockers();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }

      }
    });
    relative_waqf.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new StoppedMember();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }

      }
    });
    relative_share.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SHOP_PEAK");
        String shareMessage= "\nLet me recommend you this application\n\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.alatheer.noamany";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        getActivity().startActivity(Intent.createChooser(shareIntent, "choose one"));
      }
    });
    relative_change_language.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CreateAlertDialog();
      }
    });
    relative_contact_us.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new Contact_Us();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }

      }
    });
    relative_receipts.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new ReceiptsFragment();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }

      }
    });
    relative_logout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (userModel != null){
          Logout();
        }else {
          Utilities.CreatealertDialog(getActivity());
        }

      }
    });
    return  view;
  }

  private void restartActivity() {
    Intent intent = getActivity().getIntent();
    getActivity().finish();
    startActivity(intent);
    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
  }
  private void CreateAlertDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    final View view = inflater.inflate(R.layout.languages_item, null);
    RadioGroup radioGroup = view.findViewById(R.id.languages_radip_groups);
    int radioId = radioGroup.getCheckedRadioButtonId();
    RadioButton r_arabic = view.findViewById(R.id.radio_btn_arabic);
    RadioButton r_english = view.findViewById(R.id.radio_btn_english);
    builder.setView(view);
    AlertDialog dialog = builder.create();
    r_arabic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        restartActivity();
        Paper.book().write("language","ar");
        updateview(Paper.book().read("language"));
        Toast.makeText(getActivity(), "ar-eEG", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
      }
    });
    r_english.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        restartActivity();
        Paper.book().write("language","en");
        updateview(Paper.book().read("language"));
        Toast.makeText(getActivity(), "ar-eEG", Toast.LENGTH_SHORT).show();
        dialog.dismiss();

      }
    });
    dialog.show();
    dialog.getWindow().setLayout(750, ViewGroup.LayoutParams.WRAP_CONTENT);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
  }

  private void updateview(String language) {
    context = LocaleHelper.setLocale(getActivity(),language);
    resources = context.getResources();
    txt_profile_data.setText(resources.getString(R.string.profile));
    txt_subscription_data.setText(resources.getString(R.string.supscription_data));
    txt_add_subscription.setText(resources.getString(R.string.add_subscription));
    txt_locker_data.setText(resources.getString(R.string.locker_data));
    txt_stopped_data.setText(resources.getString(R.string.stopped_data));
    txt_body_stats.setText(resources.getString(R.string.body_stats));
    txt_share.setText(resources.getString(R.string.share));
    txt_language.setText(resources.getString(R.string.language));
    txt_contact_us.setText(resources.getString(R.string.contact_us));
    txt_logout.setText(resources.getString(R.string.logout));
    values.setText(resources.getString(R.string.value));
    points.setText(resources.getString(R.string.points));
    txt_receipts.setText(resources.getString(R.string.receipts));
    //txt_settings.setText(resources.getString(R.string.myaccount));
  }

  private void Logout() {
    if(Utilities.isNetworkAvailable(getActivity())){
      userSharedPreference.ClearData(getActivity());
      startActivity(new Intent(getActivity(), SplashActivity.class));
      Animatoo.animateFade(getActivity());
      getActivity().finish();
    }
  }
  private void get_points_in_wallet() {
    if (Utilities.isNetworkAvailable(getContext())){
      if (govern_id.equals("1")){
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Wallet> call = getDataService.get_points_in_wallet(m_code);
        call.enqueue(new Callback<Wallet>() {
          @Override
          public void onResponse(Call<Wallet> call, Response<Wallet> response) {
            if (response.isSuccessful()){
              txt_points.setText(response.body().getPoints());
              txt_value.setText(response.body().getValues()+" "+"جنيه");
            }
          }

          @Override
          public void onFailure(Call<Wallet> call, Throwable t) {

          }
        });
      }else {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
        Call<Wallet> call = getDataService.get_points_in_wallet(m_code);
        call.enqueue(new Callback<Wallet>() {
          @Override
          public void onResponse(Call<Wallet> call, Response<Wallet> response) {
            if (response.isSuccessful()){
              txt_points.setText(response.body().getPoints()+"");
              txt_value.setText(response.body().getValues());
            }
          }

          @Override
          public void onFailure(Call<Wallet> call, Throwable t) {

          }
        });
      }
    }
  }
}
