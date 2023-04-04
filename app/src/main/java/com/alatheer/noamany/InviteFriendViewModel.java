package com.alatheer.noamany;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.Invitation.InvitationModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Utilities.Utilities;
import com.alatheer.noamany.otp.VerificationCodeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteFriendViewModel {
  Context context;
  String govern_id;
  public InviteFriendViewModel(Context context) {
    this.context = context;
  }

  public void invite(String m_name, String m_tel, String m_card, String m_code, String r_name, String r_national_num, String r_phone,int age,String govern_id) {
    Intent intent = new Intent(context, VerificationCodeActivity.class);
    intent.putExtra("m_name",m_name);
    intent.putExtra("m_tel",m_tel);
    intent.putExtra("m_card",m_card);
    intent.putExtra("m_code",m_code);
    intent.putExtra("r_name",r_name);
    intent.putExtra("r_national_num",r_national_num);
    intent.putExtra("r_phone",r_phone);
    intent.putExtra("age",age);
    intent.putExtra("govern_id",govern_id);
    intent.putExtra("flag",1);
    context.startActivity(intent);
    /*if(Utilities.isNetworkAvailable(context)){
      if (govern_id.equals("1")){
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("تحميل ...");
        pd.show();
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<InvitationModel> call = getDataService.invite_friend(m_name,m_tel,m_card,m_code,r_name,r_national_num,r_phone,age);
        call.enqueue(new Callback<InvitationModel>() {
          @Override
          public void onResponse(Call<InvitationModel> call, Response<InvitationModel> response) {
            if(response.isSuccessful()){
              if(response.body().getSuccess()==1){
                pd.dismiss();
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }else {
                pd.dismiss();
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }
            }
          }

          @Override
          public void onFailure(Call<InvitationModel> call, Throwable t) {

          }
        });
      }else {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
        Call<InvitationModel> call = getDataService.invite_friend(m_name,m_tel,m_card,m_code,r_name,r_national_num,r_phone,age);
        call.enqueue(new Callback<InvitationModel>() {
          @Override
          public void onResponse(Call<InvitationModel> call, Response<InvitationModel> response) {
            if(response.isSuccessful()){
              if(response.body().getSuccess()==1){
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }else {
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }
            }
          }

          @Override
          public void onFailure(Call<InvitationModel> call, Throwable t) {

          }
        });
      }
    }*/
  }
}
