package com.alatheer.noamany.otp;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.Invitation.InvitationModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Utilities.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationCodeViewModel {
    Context context;
    VerificationCodeActivity verificationCodeActivity;
    public VerificationCodeViewModel(Context context) {
        this.context = context;
        verificationCodeActivity = (VerificationCodeActivity) context;
    }



    public void invite(String m_name, String m_tel, String m_card, String m_code, String r_name, String r_national_num, String r_phone, Integer age, String govern_id) {
        if(Utilities.isNetworkAvailable(context)){
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
        }
    }
}
