package com.alatheer.noamany.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import io.paperdb.Paper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.GovernmentViewModel;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.ActivityGovernmentBinding;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

public class GovernmentActivity extends AppCompatActivity {
    ActivityGovernmentBinding activityGovernmentBinding;
    GovernmentViewModel governmentViewModel;
    ArrayAdapter<String> country_adapter;
    List<Govern> governList;
    String govern_name,language,govern_id="1";
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    Govern govern;
    SharedPreference2 sharedPreference2;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government);
        //Toast.makeText(this, govern_id, Toast.LENGTH_SHORT).show();
        activityGovernmentBinding = DataBindingUtil.setContentView(this,R.layout.activity_government);
        governmentViewModel = new GovernmentViewModel(this);
        activityGovernmentBinding.setGovernmentviewmodel(governmentViewModel);
        Paper.init(this);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        governmentViewModel.getGovernments();
        getSharedPreferanceData();
        activityGovernmentBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                govern_id = governList.get(i).getId();
                govern_name = governList.get(i).getId();
                TextView textView = (TextView) view;
                try {
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        activityGovernmentBinding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Govern govern = new Govern();
                govern.setId(govern_id);
                govern.setName(govern_name);
                sharedPreference2.Create_Update_UserData(GovernmentActivity.this,govern);
                if(userModel != null){
                    startActivity(new Intent(GovernmentActivity.this,HomeActivity.class));
                    Animatoo.animateSlideUp(GovernmentActivity.this);

                }else {
                    startActivity(new Intent(GovernmentActivity.this,MainActivity.class));
                    Animatoo.animateFade(GovernmentActivity.this);
                }
            }
        });
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(this,language);
        resources = context.getResources();
        activityGovernmentBinding.btnConfirm.setText(resources.getString(R.string.confirm));
        activityGovernmentBinding.txtChoose.setText(resources.getString(R.string.choose_city));

    }

    private void getSharedPreferanceData() {
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(GovernmentActivity.this);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(this);

    }

    public void init_spinner(List<Govern> governList, List<String> governtitle) {
        this.governList = governList;
        country_adapter = new ArrayAdapter<String>(GovernmentActivity.this, R.layout.spinner_item, governtitle);
        activityGovernmentBinding.spinner.setAdapter(country_adapter);
    }
}
