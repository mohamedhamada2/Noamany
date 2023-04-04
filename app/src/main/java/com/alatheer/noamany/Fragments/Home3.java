package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Activities.RegisterationActivity;
import com.alatheer.noamany.Activities.SplashActivity;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Home3 extends Fragment {
    @BindView(R.id.about_card)
    LinearLayout about_card;
    @BindView(R.id.news_card)
    LinearLayout news_card;
    @BindView(R.id.ads_card)
    LinearLayout ads_card;
    @BindView(R.id.subsription_card)
    LinearLayout subsription_card;
    @BindView(R.id.share_card)
    LinearLayout share_card;
    @BindView(R.id.add_account_card)
    LinearLayout add_account_card;
    @BindView(R.id.logout_card)
    LinearLayout logout_card;
    @BindView(R.id.languages_card)
    LinearLayout languages_card;
    @BindView(R.id.txt_about)
    TextView txt_about;
    @BindView(R.id.txt_news)
    TextView txt_news;
    @BindView(R.id.txt_ads)
    TextView txt_ads;
    @BindView(R.id.txt_subscription)
    TextView txt_subscription;
    @BindView(R.id.txt_share)
    TextView txt_share;
    @BindView(R.id.txt_add_account)
    TextView txt_add_account;
    @BindView(R.id.txt_logout)
    TextView txt_logout;
    @BindView(R.id.txt_languages)
    TextView txt_languges;

    String language,govern_id;
    Resources resources;
    Context context;
    UserSharedPreference userSharedPreference;
    SharedPreference2 sharedPreference2;
    Govern govern;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home3, container, false);
        Paper.init(getActivity());
        ButterKnife.bind(this,view);
        userSharedPreference = UserSharedPreference.getInstance();
        sharedPreference2 = SharedPreference2.getInstance();
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        about_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AboutFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        news_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new NewsFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        ads_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AdvertismentFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        subsription_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        share_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SHOP_PEAK");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.alatheer.noamany";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                getActivity().startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        });
        add_account_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RegisterationActivity.class);
                i.putExtra("flag",2);
                startActivity(i);
            }
        });
        languages_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAlertDialog();
            }
        });
        logout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        return  view;
    }
    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        txt_about.setText(resources.getString(R.string.about_app));
        txt_news.setText(resources.getString(R.string.news));
        txt_ads.setText(resources.getString(R.string.advertisment));
        txt_subscription.setText(resources.getString(R.string.supscription));
        txt_share.setText(resources.getString(R.string.share));
        //txt_contact_us.setText(resources.getString(R.string.contact_us));
        txt_add_account.setText(resources.getString(R.string.sign_up));
        txt_logout.setText(resources.getString(R.string.logout));
        txt_languges.setText(resources.getString(R.string.language));
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

    private void restartActivity() {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }
    private void Logout() {
        if(Utilities.isNetworkAvailable(getActivity())){
            userSharedPreference.ClearData(getActivity());
            sharedPreference2.ClearData(getActivity());
            startActivity(new Intent(getActivity(), SplashActivity.class));
            Animatoo.animateFade(getActivity());
            getActivity().finish();
        }
    }


}
