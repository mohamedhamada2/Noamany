package com.alatheer.noamany;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.alatheer.noamany.Data.Remote.ContactUs.Contact;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Fragments.AddAdvertismentFragment;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.Utilities.Utilities;

import androidx.lifecycle.ViewModel;
import io.paperdb.Paper;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAdvertismentViewModel extends ViewModel {
    Context context,mcontext;
    AddAdvertismentFragment addAdvertismentFragment;
    String language;
    Resources resources;
    public AddAdvertismentViewModel(Context context,AddAdvertismentFragment addAdvertismentFragment){
        this.context = context;
        this.addAdvertismentFragment = addAdvertismentFragment;
        Paper.init(context);
        language = Paper.book().read("language");
        if(language == null) {
            Paper.book().write("language", "ar");
        }
    }

    public void add_ads(String company_name, String company_phone, String company_address, String product_name, String product_description, String link, Uri filePath) {
        RequestBody rb_company_name = Utilities.getRequestBodyText(company_name);
        RequestBody rb_company_phone = Utilities.getRequestBodyText(company_phone);
        RequestBody rb_company_address = Utilities.getRequestBodyText(company_address);
        RequestBody rb_product_name = Utilities.getRequestBodyText(product_name);
        RequestBody rb_product_description = Utilities.getRequestBodyText(product_description);
        RequestBody rb_link = Utilities.getRequestBodyText(link);
        MultipartBody.Part photo = Utilities.getMultiPart(context,filePath, "ads_image");
        Log.e("hello","hello");
        if(Utilities.isNetworkAvailable(context)){
            GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Contact> call = getDataService.add_ads(rb_company_address,rb_company_name,rb_company_phone,photo,rb_link,rb_product_name,rb_product_description);
            call.enqueue(new Callback<Contact>() {
                @Override
                public void onResponse(Call<Contact> call, Response<Contact> response) {
                    if(response.isSuccessful()){
                        Log.e("hello","hello");
                        if(response.body().getSuccess()==1){
                            mcontext = LocaleHelper.setLocale(context,language);
                            resources = mcontext.getResources();
                            Toast.makeText(context,resources.getString(R.string.ads_added), Toast.LENGTH_SHORT).show();
                        }
                        Log.e("hello","hello");
                    }
                }

                @Override
                public void onFailure(Call<Contact> call, Throwable t) {

                }
            });
        }

    }
}
