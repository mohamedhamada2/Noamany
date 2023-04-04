package com.alatheer.noamany.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Data.Remote.updatetoken.UpdateTokenModel;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.btn_skip)
    ImageView btn_skip;
    //@BindView(R.id.txt_welcome)
    //TextView txt_welcome;
    String language;
    Context context;
    Resources resources;
    String firebase_token;
    UserSharedPreference userSharedPreference;
    String m_code;
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Paper.init(this);
        language =Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        FirebaseMessaging.getInstance().subscribeToTopic("ads")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d("TAG", msg);
                        //Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(this,language);
        resources = context.getResources();
        btn_login.setText(resources.getString(R.string.login_user));
        btn_register.setText(resources.getString(R.string.signup));
        //btn_skip.setText(resources.getString(R.string.skip));
        //txt_welcome.setText(resources.getString(R.string.welcome));
        //txt_noamany.setText(resources.getString(R.string.noamany));
        //txt_description.setText(resources.getString(R.string.description));
    }

    public void go_to_registeration(View view) {
        startActivity(new Intent(this,RegisterationActivity.class));
        Animatoo.animateSlideLeft(MainActivity.this);
        finish();
    }

    public void go_to_login(View view) {
        startActivity(new Intent(this,LoginActivity.class));
        Animatoo.animateSlideLeft(MainActivity.this);
        finish();
    }

    public void Skip(View view) {
        startActivity(new Intent(this,HomeActivity.class));
        Animatoo.animateSlideUp(MainActivity.this);
    }
}
