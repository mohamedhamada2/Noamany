package com.alatheer.noamany;

import android.content.Context;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.ContactUs.Contact;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Utilities.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsViewModel {
    Context context;
    String govern_id;
    public ContactUsViewModel(Context context) {
        this.context = context;
    }

    public void add_contact(String m_code,String name, String phone, String email, String s, String content,String govern_id) {
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<Contact> contactCall = getDataService.add_contact(m_code,name,phone,email,s,content);
                contactCall.enqueue(new Callback<Contact>() {
                    @Override
                    public void onResponse(Call<Contact> call, Response<Contact> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess()==1){
                                Toast.makeText(context,"تم ارسال ملاحظتك",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Contact> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<Contact> contactCall = getDataService.add_contact(m_code,name,phone,email,s,content);
                contactCall.enqueue(new Callback<Contact>() {
                    @Override
                    public void onResponse(Call<Contact> call, Response<Contact> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess()==1){
                                Toast.makeText(context,"تم ارسال ملاحظتك",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Contact> call, Throwable t) {

                    }
                });
            }

        }
    }
}
