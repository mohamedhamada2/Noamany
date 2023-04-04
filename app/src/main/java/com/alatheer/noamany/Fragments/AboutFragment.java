package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Data.About.About;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;
import com.squareup.picasso.Picasso;


public class AboutFragment extends Fragment {
  String language;
  Context context;
  Resources resources;
  @BindView(R.id.txt_about)
  TextView txt_about;
  @BindView(R.id.about_title)
  TextView txt_title;
  @BindView(R.id.txt_details)
  TextView txt_details;
  @BindView(R.id.about_img)
  ImageView about_img;
  @BindView(R.id.back_img)
  ImageView back_img;
  UserSharedPreference userSharedPreference;
  String govern_id;
  UserModel userModel;
  SharedPreference2 sharedPreference2;
  Govern govern;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_about, container, false);
    ButterKnife.bind(this,view);
    userSharedPreference = UserSharedPreference.getInstance();
    userModel = userSharedPreference.Get_UserData(getActivity());
    Paper.init(getActivity());
    language = Paper.book().read("language");
    sharedPreference2 = SharedPreference2.getInstance();
    govern = sharedPreference2.Get_UserData(getActivity());
    govern_id = govern.getId();
    back_img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(userModel != null){
          Fragment fragment = new Home2();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }else {
          Fragment fragment = new Home3();

          FragmentManager fragmentManager = getFragmentManager();

          fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
      }
    });
    updateview(language);
    get_about(govern_id);
    return  view;
  }

  private void updateview(String language) {
    context = LocaleHelper.setLocale(getActivity(),language);
    resources = context.getResources();
    txt_about.setText(resources.getString(R.string.about_app));
  }
  private void get_about(String govern_id) {
   // Toast.makeText(getActivity(), govern_id, Toast.LENGTH_SHORT).show();
    if(Utilities.isNetworkAvailable(getActivity())){
      if (govern_id.equals("1")){
        Toast.makeText(getActivity(), govern_id, Toast.LENGTH_SHORT).show();
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<About> call = getDataService.get_about();
        call.enqueue(new Callback<About>() {
          @Override
          public void onResponse(Call<About> call, Response<About> response) {
            if(response.isSuccessful()){
              if(response.body().getSuccess()==1){
                about_img.setImageResource(R.drawable.logo1);
                txt_title.setText(response.body().getAboutApp().getSiteName());
                txt_details.setText(Html.fromHtml(response.body().getAboutApp().getNotes()));
                txt_details.setMovementMethod(LinkMovementMethod.getInstance());
              }
            }
          }

          @Override
          public void onFailure(Call<About> call, Throwable t) {

          }
        });
      }else if (govern_id.equals("2")){
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
        Call<About> call = getDataService.get_about();
        call.enqueue(new Callback<About>() {
          @Override
          public void onResponse(Call<About> call, Response<About> response) {
            if(response.isSuccessful()){
              if(response.body().getSuccess()==1){
                about_img.setImageResource(R.drawable.logo1);
                txt_title.setText(response.body().getAboutApp().getSiteName());
                txt_details.setText(Html.fromHtml(response.body().getAboutApp().getNotes()));
                txt_details.setMovementMethod(LinkMovementMethod.getInstance());
              }
            }
          }

          @Override
          public void onFailure(Call<About> call, Throwable t) {

          }
        });
      }
    }
  }
}
