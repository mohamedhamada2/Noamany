package com.alatheer.noamany.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.MemberInbody.MemberInbudy;
import com.alatheer.noamany.Data.Remote.Notification.NotificationModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Data.Remote.updatetoken.UpdateTokenModel;
import com.alatheer.noamany.Fragments.AdvertismentFragment;
import com.alatheer.noamany.Fragments.BodyStats;
import com.alatheer.noamany.Fragments.BodyStatsDetails;
import com.alatheer.noamany.Fragments.Home2;
import com.alatheer.noamany.Fragments.Home3;
import com.alatheer.noamany.Fragments.NotificationFragment;
import com.alatheer.noamany.Fragments.SettingsFragment;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;
import com.alatheer.noamany.databinding.ActivityHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity implements BodyStats.SendData {
    ActivityHomeBinding homeBinding;
    BottomNavigationView homeNavigationView,homenavigation2;
    Fragment selectedfragment;
    Context context;
    Resources resources;
    String language;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    boolean active;
    View badge;
    TextView txt;
    String m_code,govern_id;
    List<NotificationModel> notificationModelList;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String firebase_token;
    private AppUpdateManager mAppUpdateManager;
    private static final int UPDATE_CODE = 37;
    private String TAG ="HomeActivity";
    View parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Paper.init(this);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        homeBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        homeNavigationView = homeBinding.homeBottomnavigation;
        homenavigation2 = homeBinding.homeBottomnavigation2;
        parentLayout = findViewById(android.R.id.content);
        updateview(language);
        inAppUp();
        homeNavigationView.setOnNavigationItemSelectedListener(nav_listner);
        homenavigation2.setOnNavigationItemSelectedListener(nav_listner2);
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) homeNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(1);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        badge = LayoutInflater.from(this)
                .inflate(R.layout.notification_badge, itemView, true);
        txt = badge.findViewById(R.id.notificationsbadge);
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(this);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(this);
        govern_id = govern.getId();
        if(userModel != null){
            m_code = userModel.getMember().getMCode();
            getNotification(govern_id);
            selectedfragment = new Home2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
        }else {
            homenavigation2.setVisibility(View.VISIBLE);
            homeNavigationView.setVisibility(View.GONE);
            selectedfragment = new Home3();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
        }
        Log.e("active",active+"");
        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.noamany_FCM-MESSAGE"));
        try {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    }else {
                        firebase_token = task.getResult();
                        Log.e("firebase_token", firebase_token);
                        update_token(firebase_token);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("exception_e",e.toString());
            e.printStackTrace();
        }
    }

    private void inAppUp() {
        mAppUpdateManager = AppUpdateManagerFactory.create(this);
        com.google.android.play.core.tasks.Task<AppUpdateInfo> task = mAppUpdateManager.getAppUpdateInfo();
        task.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
                    try {
                        mAppUpdateManager.startUpdateFlowForResult(appUpdateInfo,AppUpdateType.FLEXIBLE,HomeActivity.this,UPDATE_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mAppUpdateManager.registerListener(listener);
    }

    private void getNotification(String govern_id) {
        notificationModelList = new ArrayList<>();
        if (Utilities.isNetworkAvailable(this)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<NotificationModel>> call = getDataService.get_notification(m_code);
                call.enqueue(new Callback<List<NotificationModel>>() {
                    @Override
                    public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                        if (response.isSuccessful()){
                            notificationModelList = response.body();
                            txt.setText(notificationModelList.size()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NotificationModel>> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<List<NotificationModel>> call = getDataService.get_notification(m_code);
                call.enqueue(new Callback<List<NotificationModel>>() {
                    @Override
                    public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                        if (response.isSuccessful()){
                            notificationModelList = response.body();
                            txt.setText(notificationModelList.size()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NotificationModel>> call, Throwable t) {

                    }
                });
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = false;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(this,language);
        resources = context.getResources();
        homeNavigationView.getMenu().getItem(0).setTitle(resources.getString(R.string.home));
        homeNavigationView.getMenu().getItem(1).setTitle(resources.getString(R.string.notification));
        homeNavigationView.getMenu().getItem(2).setTitle(resources.getString(R.string.advertisment));
        homeNavigationView.getMenu().getItem(3).setTitle(resources.getString(R.string.myaccount));
        homenavigation2.getMenu().getItem(0).setTitle(resources.getString(R.string.home));
        homenavigation2.getMenu().getItem(1).setTitle(resources.getString(R.string.advertisment));
        //homeNavigationView.getMenu().getItem(R.id.home).setTitle(resources.getString(R.string.home));
        //homeNavigationView.getMenu().getItem(R.id.notification).setTitle(resources.getString(R.string.notification));
        //homeNavigationView.getMenu().getItem(R.id.commercials).setTitle(resources.getString(R.string.advertisment));
    }
    private void update_token(String firebase_token) {
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(context);
        if (userModel != null){
            m_code = userModel.getMember().getMCode();
            if (Utilities.isNetworkAvailable(context)){
                if (govern_id.equals("1")){
                    GetDataService getDataService= RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<UpdateTokenModel> call = getDataService.update_token(m_code,firebase_token);
                    call.enqueue(new Callback<UpdateTokenModel>() {
                        @Override
                        public void onResponse(Call<UpdateTokenModel> call, Response<UpdateTokenModel> response) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<UpdateTokenModel> call, Throwable t) {

                        }
                    });
                }else {
                    GetDataService getDataService= RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                    Call<UpdateTokenModel> call = getDataService.update_token(m_code,firebase_token);
                    call.enqueue(new Callback<UpdateTokenModel>() {
                        @Override
                        public void onResponse(Call<UpdateTokenModel> call, Response<UpdateTokenModel> response) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<UpdateTokenModel> call, Throwable t) {

                        }
                    });
                }
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav_listner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            selectedfragment = null;
            switch (item.getItemId()) {
                case R.id.settings:
                    selectedfragment = new SettingsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    item.setTitle(resources.getString(R.string.myaccount));
                    break;
                case R.id.home:
                    if(userModel != null){
                        selectedfragment = new Home2();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                        item.setTitle(resources.getString(R.string.home));
                        txt.setVisibility(View.VISIBLE);
                    }else {
                        selectedfragment = new Home3();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                        item.setTitle(resources.getString(R.string.home));
                        txt.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.notification:
                    selectedfragment = new NotificationFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    item.setTitle(resources.getString(R.string.notification));
                    txt.setVisibility(View.GONE);

                    break;
                case R.id.commercials:
                    selectedfragment = new AdvertismentFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    item.setTitle(resources.getString(R.string.advertisment));
                    txt.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
        }
    };
    private BottomNavigationView.OnNavigationItemSelectedListener nav_listner2 = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            selectedfragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    if(userModel != null){
                        selectedfragment = new Home2();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                        item.setTitle(resources.getString(R.string.home));
                    }else {
                        selectedfragment = new Home3();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                        item.setTitle(resources.getString(R.string.home));
                    }
                    break;
                case R.id.commercials:
                    selectedfragment = new AdvertismentFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
                    item.setTitle(resources.getString(R.string.advertisment));
                    break;
            }
            return true;
        }
    };
    @Override
    public void send_member_inbody_data(MemberInbudy memberInbudy) {
        BodyStatsDetails bodyStatsDetails = new BodyStatsDetails();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, bodyStatsDetails).commit();
        bodyStatsDetails.receiveData(memberInbudy);
    }
    private BroadcastReceiver mhandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("message");
            String title = intent.getStringExtra("title");
            //message.setText(msg);
            if(active == true){
                Log.e("notify","notify");
                Utilities.showNotificationInADialog(HomeActivity.this,msg,title);
            }

        }
    };

    private void popupSnackbarForCompleteUpdate() {
        Log.e("snakebar","22222");
        Snackbar snackbar =
                Snackbar.make(
                        findViewById(android.R.id.content),
                        "New app is ready!",
                        Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("Install", view -> {
            if (mAppUpdateManager != null){
                mAppUpdateManager.completeUpdate();
            }
        });


        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }
    InstallStateUpdatedListener listener = installState -> {
      if (installState.installStatus() == InstallStatus.DOWNLOADED){
          popupSnackbarForCompleteUpdate();
      }
    };


}