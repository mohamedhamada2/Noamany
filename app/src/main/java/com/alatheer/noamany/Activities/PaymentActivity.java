package com.alatheer.noamany.Activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.AllSubscription.AddSubscriptionModel;
import com.alatheer.noamany.Data.Remote.Auth.Auth;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;

public class PaymentActivity extends AppCompatActivity {
    String session_id,subscription_id,subscription_value
            ,govern_id,user_id,branch_id,price,start_date,end_date,gender_id;
    WebView webView;
    ProgressBar progressBar;
    ImageView back_img;
    SharedPreference2 sharedPreference2;
    Govern govern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getDataIntent();
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);
        back_img = findViewById(R.id.back_img);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(this);
        govern_id = govern.getId();
        webView.setWebViewClient(new PaymentActivity.WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getDataIntent() {
        user_id = getIntent().getStringExtra("user_id");
        branch_id = getIntent().getStringExtra("branch_id");
        subscription_id = getIntent().getStringExtra("subscription_id");
        price = getIntent().getStringExtra("price");
        start_date = getIntent().getStringExtra("start_date");
        end_date = getIntent().getStringExtra("end_date");
        gender_id = getIntent().getStringExtra("gender_id");
        //subscription_value = getIntent().getStringExtra("id");
        //subscription_id = getIntent().getStringExtra("subscription_id");
        getSession(subscription_id,price,user_id);
    }

    private void getSession(String subscription_id, String price,String user_id) {
        if (Utilities.isNetworkAvailable(PaymentActivity.this)){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<Auth> call = getDataService.get_session((int)(Math.random()*(10000-0+1)+0)+subscription_id+user_id,price);
                call.enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        if (response.isSuccessful()){
                            session_id = response.body().getSession().getId();
                            webView.loadUrl("https://noamanycenter.com/Paid_online/check_out/"+subscription_id+"/"+session_id);
                            Log.e("url","https://noamanycenter.com/Paid_online/check_out/"+subscription_id+"/"+session_id);
                        }
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {

                    }
                });
            }
        }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            String last = url.substring(url.lastIndexOf("-") + 1);
            Log.e("last", last);
            if (url.equals("https://noamanycenter.com/Paid_online/get_check_value/SUCCESS/1")){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        add_subscription();
                    }
                }, 3000);
            }
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }

    private void add_subscription() {
        if (Utilities.isNetworkAvailable(this)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<AddSubscriptionModel> call = getDataService.add_subscription(user_id,branch_id,subscription_id,price,start_date,end_date,gender_id);
                call.enqueue(new Callback<AddSubscriptionModel>() {
                    @Override
                    public void onResponse(Call<AddSubscriptionModel> call, Response<AddSubscriptionModel> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess()==1){
                                Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PaymentActivity.this,HomeActivity.class));
                                finish();
                            /*Intent intent = new Intent(context, PaymentActivity.class);
                            intent.putExtra("id",response.body().getCost());
                            intent.putExtra("subscription_id",response.body().getId());
                            context.startActivity(intent);
                            addSubscriptionFragment.finish();*/

                            }else {
                                Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddSubscriptionModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<AddSubscriptionModel> call = getDataService.add_subscription(user_id,branch_id,subscription_id,price,start_date,end_date,gender_id);
                call.enqueue(new Callback<AddSubscriptionModel>() {
                    @Override
                    public void onResponse(Call<AddSubscriptionModel> call, Response<AddSubscriptionModel> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess()==1){
                                Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PaymentActivity.this,HomeActivity.class));
                                finish();
                            /*Intent intent = new Intent(context, PaymentActivity.class);
                            intent.putExtra("id",response.body().getCost());
                            intent.putExtra("subscription_id",response.body().getId());
                            context.startActivity(intent);
                            addSubscriptionFragment.finish();*/

                            }else {
                                Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddSubscriptionModel> call, Throwable t) {

                    }
                });
            }
        }
    }
}