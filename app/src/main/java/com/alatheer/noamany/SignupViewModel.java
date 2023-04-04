package com.alatheer.noamany;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alatheer.noamany.Activities.LoginActivity;
import com.alatheer.noamany.Activities.RegisterationActivity;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AddUser.AddUser;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Utilities.Utilities;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupViewModel {
    Context context;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    RegisterationActivity registerationActivity;
    String govern_id;
    public SignupViewModel(Context context) {
        this.context= context;
        registerationActivity = (RegisterationActivity) context;
    }

    public void SignUpUser(String name, String email, String phone, String birth_date, String password,String national_num,String govern_id) {
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<AddUser> call = service.SignUp(name,email,phone,birth_date,password,national_num);
                call.enqueue(new Callback<AddUser>() {
                    @Override
                    public void onResponse(Call<AddUser> call, Response<AddUser> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSuccess()== 1){
                                Toast.makeText(context, "تم انشاء الحساب بنجاح سيتم التفعيل خلال 24 ساعة", Toast.LENGTH_LONG).show();
                                //userSharedPreference.Create_Update_UserData(context,userModel);
                                context.startActivity(new Intent(context, LoginActivity.class));
                                Animatoo.animateFade(context);
                                registerationActivity.finish();

                            }else{
                                Toast.makeText(context,response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddUser> call, Throwable t) {

                    }
                });
            }else {
                GetDataService service = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<AddUser> call = service.SignUp(name,email,phone,birth_date,password,national_num);
                call.enqueue(new Callback<AddUser>() {
                    @Override
                    public void onResponse(Call<AddUser> call, Response<AddUser> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSuccess()== 1){
                                Toast.makeText(context, "تم انشاء الحساب بنجاح سيتم التفعيل خلال 24 ساعة", Toast.LENGTH_LONG).show();
                                //userSharedPreference.Create_Update_UserData(context,userModel);
                                context.startActivity(new Intent(context, LoginActivity.class));
                                Animatoo.animateFade(context);
                                registerationActivity.finish();

                            }else{
                                Toast.makeText(context,response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddUser> call, Throwable t) {

                    }
                });
            }

        }
    }
}
