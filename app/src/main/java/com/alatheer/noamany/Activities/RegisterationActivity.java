package com.alatheer.noamany.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import io.paperdb.Paper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.SignupViewModel;
import com.alatheer.noamany.databinding.ActivityRegisterationBinding;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterationActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener date_picker_dialog;
    String birth_date,name,email,phone,password,language,national_num,govern_id;
    Calendar myCalendar;
    ActivityRegisterationBinding activityRegisterationBinding;
    SignupViewModel signupViewModel;
    Context context;
    Resources resources;
    int flag;
    SharedPreference2 sharedPreference2;
    Govern govern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        Paper.init(this);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        activityRegisterationBinding = DataBindingUtil.setContentView(this,R.layout.activity_registeration);
        signupViewModel = new SignupViewModel(this);
        activityRegisterationBinding.setSignupviewmodel(signupViewModel);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(this);
        govern_id = govern.getId();
        getDataIntent();
        myCalendar = Calendar.getInstance();
        updateview(language);
        date_picker_dialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();

            }

        };
    }

    private void getDataIntent() {
        flag = getIntent().getIntExtra("flag",0);
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(this,language);
        resources = context.getResources();
        activityRegisterationBinding.etClientEmail.setHint(resources.getString(R.string.email));
        activityRegisterationBinding.etClientPhone.setHint(resources.getString(R.string.phone));
        activityRegisterationBinding.etClientName.setHint(resources.getString(R.string.client_name));
        activityRegisterationBinding.etClientBirthdate.setHint(resources.getString(R.string.birthdate));
        activityRegisterationBinding.etClientPassword.setHint(resources.getString(R.string.password));
        activityRegisterationBinding.etClientNationalNum.setHint(resources.getString(R.string.national_num));
        activityRegisterationBinding.btnSignUp.setText(resources.getString(R.string.sign_up));
        activityRegisterationBinding.txtSignUp.setText(resources.getString(R.string.newsignup));
    }

    private void updateLabelStart() {
        String myFormat = "dd-MM-yyyy";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        birth_date = sdf.format(myCalendar.getTime());
        activityRegisterationBinding.etClientBirthdate.setText(birth_date);
    }

    public void Back(View view) {
        if(flag == 2){
            startActivity(new Intent(RegisterationActivity.this,HomeActivity.class));
            finish();
        }else {
            startActivity(new Intent(RegisterationActivity.this,MainActivity.class));
            finish();
        }
        Animatoo.animateSlideRight(RegisterationActivity.this);
    }

    public void OpenCalender(View view) {
        new DatePickerDialog(RegisterationActivity.this, date_picker_dialog, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void AddMember(View view) {
        Validation();
    }

    private void Validation() {
        name = activityRegisterationBinding.etClientName.getText().toString();
        email = activityRegisterationBinding.etClientEmail.getText().toString();
        phone = activityRegisterationBinding.etClientPhone.getText().toString();
        birth_date = activityRegisterationBinding.etClientBirthdate.getText().toString();
        password = activityRegisterationBinding.etClientPassword.getText().toString();
        national_num = activityRegisterationBinding.etClientNationalNum.getText().toString();
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(phone)&&
                !TextUtils.isEmpty(birth_date)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(national_num)){
            signupViewModel.SignUpUser(name,email,phone,birth_date,password,national_num,govern_id);
        }else {
            if(TextUtils.isEmpty(name)){
                activityRegisterationBinding.etClientName.setError(resources.getString(R.string.validate_name));
            }else {
                activityRegisterationBinding.etClientName.setError(null);
            }
            if(TextUtils.isEmpty(email)){
                activityRegisterationBinding.etClientEmail.setError(resources.getString(R.string.validate_email));
            }else {
                activityRegisterationBinding.etClientEmail.setError(null);
            }
            if(TextUtils.isEmpty(phone)){
                activityRegisterationBinding.etClientPhone.setError(resources.getString(R.string.validate_phone));
            }else {
                activityRegisterationBinding.etClientPhone.setError(null);
            }
            if(TextUtils.isEmpty(birth_date)){
                activityRegisterationBinding.etClientBirthdate.setError(resources.getString(R.string.validate_birthdate));
            }else {
                activityRegisterationBinding.etClientBirthdate.setError(null);
            }
            if(TextUtils.isEmpty(password)){
                activityRegisterationBinding.etClientPassword.setError(resources.getString(R.string.validate_password));
            }else {
                activityRegisterationBinding.etClientPassword.setError(null);
            }
            if(TextUtils.isEmpty(national_num)){
                activityRegisterationBinding.etClientNationalNum.setError(resources.getString(R.string.validate_national_num));
            }else {
                activityRegisterationBinding.etClientNationalNum.setError(null);
            }
        }
    }
}
