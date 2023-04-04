package com.alatheer.noamany.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {
    int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView logo1,logo2;
    LinearLayout linear_splash;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    CardView cardView;
    Govern govern;
    SharedPreference2 sharedPreference2;
    VideoView imageView;
    String video_path,package_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //logo2 = findViewById(R.id.logo2_img);
        imageView = findViewById(R.id.SplashScreenImage);
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(this);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(this);
        try {
            package_name = String.valueOf(getPackageManager()
                    .getPackageInfo(getPackageName(), 0).packageName);
            video_path = "android.resource://"+getPackageName()+"/"+R.raw.noamany1;
            Uri uri = Uri.parse(video_path);
            imageView.setVideoURI(uri);
            imageView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if(userModel != null && govern != null){
                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        Animatoo.animateFade(SplashActivity.this);
                        finish();
                    }else if (userModel == null && govern != null){
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        Animatoo.animateFade(SplashActivity.this);
                        finish();
                    }else if (userModel != null && govern == null){
                        startActivity(new Intent(SplashActivity.this,GovernmentActivity.class));
                        Animatoo.animateFade(SplashActivity.this);
                        finish();
                    }else if (userModel == null && govern == null) {
                        startActivity(new Intent(SplashActivity.this, GovernmentActivity.class));
                        Animatoo.animateFade(SplashActivity.this);
                        finish();
                    }
                }
            });
            imageView.start();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        // HERE WE ARE TAKING THE REFERENCE OF OUR IMAGE
        // SO THAT WE CAN PERFORM ANIMATION USING THAT IMAGE

        /*Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        imageView.startAnimation(slideAnimation);*/

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userModel != null && govern != null){
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    Animatoo.animateSlideUp(SplashActivity.this);
                    finish();
                }else if (userModel == null && govern != null){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    Animatoo.animateFade(SplashActivity.this);
                    finish();
                }else if (userModel != null && govern == null){
                    startActivity(new Intent(SplashActivity.this,GovernmentActivity.class));
                    Animatoo.animateSlideUp(SplashActivity.this);
                    finish();
                }else if (userModel == null && govern == null) {
                    startActivity(new Intent(SplashActivity.this, GovernmentActivity.class));
                    Animatoo.animateSlideUp(SplashActivity.this);
                    finish();
                }
            }
        },SPLASH_DISPLAY_LENGTH);*/
    }

}
