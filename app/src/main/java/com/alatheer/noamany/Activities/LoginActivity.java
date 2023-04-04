package com.alatheer.noamany.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import io.paperdb.Paper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.LoginViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.ActivityLoginBinding;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding loginBinding;
    LoginViewModel loginViewModel;
    String email,password,language;
    Context context;
    Resources resources;
    String firebase_token;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String govern_id;
    private String TAG ="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Paper.init(this);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        sharedPreference2= SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(this);
        govern_id = govern.getId();
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        loginBinding.setLoginviewmodel(loginViewModel);
        updateview(language);
        try {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    }else {
                        firebase_token = task.getResult();
                        Log.e("firebase_token", firebase_token);

                    }
                }
            });
        } catch (Exception e) {
            Log.e("exception",e.toString());
            e.printStackTrace();
        }
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(this,language);
        resources = context.getResources();
        loginBinding.etEmail.setHint(resources.getString(R.string.email));
        loginBinding.etPassword.setHint(resources.getString(R.string.password));
        loginBinding.txtLogin.setText(resources.getString(R.string.login_user));
        loginBinding.txtLogin2.setText(resources.getString(R.string.login));

    }

    public void Back(View view) {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        Animatoo.animateSlideRight(LoginActivity.this);
        finish();
    }

    public void LoginMember(View view) {
        Validation();
    }

    private void Validation() {
        email = loginBinding.etEmail.getText().toString();
        password = loginBinding.etPassword.getText().toString();
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)){
            loginViewModel.SiguInUser(email,password,firebase_token,govern_id);
        }else {
            if(TextUtils.isEmpty(email)){
                loginBinding.etEmail.setError(resources.getString(R.string.validate_email));
            }else {
                loginBinding.etEmail.setError(null);
            }
            if(TextUtils.isEmpty(password)){
                loginBinding.etPassword.setError(resources.getString(R.string.validate_password));
            }else {
                loginBinding.etPassword.setError(null);
            }
        }
    }
}