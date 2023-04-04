package com.alatheer.noamany;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Activities.LoginActivity;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Utilities.Utilities;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {
    Context context;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    LoginActivity loginActivity;
    String govern_id;
    public LoginViewModel(Context context) {
        this.context= context;
        loginActivity = (LoginActivity) context;
    }

    public void SiguInUser(String email, String password,String firebase_token,String govern_id) {
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<UserModel> call = service.SignIn(email,password,firebase_token);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess() == 1) {
                                if (response.body().getMember().getBlocked().equals("0")) {
                                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                    userSharedPreference = UserSharedPreference.getInstance();
                                    userModel = response.body();
                                    userSharedPreference.Create_Update_UserData(context, userModel);
                                    context.startActivity(new Intent(context, HomeActivity.class));
                                    Animatoo.animateSlideUp(context);
                                    loginActivity.finish();
                                }else {
                                    Toast.makeText(context, response.body().getMember().getReasonBlocked(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService service = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<UserModel> call = service.SignIn(email,password,firebase_token);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess() == 1) {
                                if (response.body().getMember().getBlocked().equals("0")) {
                                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                    userSharedPreference = UserSharedPreference.getInstance();
                                    userModel = response.body();
                                    userSharedPreference.Create_Update_UserData(context, userModel);
                                    context.startActivity(new Intent(context, HomeActivity.class));
                                    Animatoo.animateSlideUp(context);
                                    loginActivity.finish();
                                }else {
                                    Toast.makeText(context, response.body().getMember().getReasonBlocked(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });
            }
        }

    }
}
