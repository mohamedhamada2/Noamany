package com.alatheer.noamany.Data.Remote;

import com.alatheer.noamany.Data.BasicAuthInterceptor;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasicAuthClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance3(){
        OkHttpClient client =  new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("merchant.TESTCIB701481", "267777b2451e12d1f8aca69d1fe14aed"))
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://cibpaynow.gateway.mastercard.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
