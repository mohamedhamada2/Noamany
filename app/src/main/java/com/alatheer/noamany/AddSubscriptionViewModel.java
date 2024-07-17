package com.alatheer.noamany;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alatheer.noamany.Activities.HomeActivity;
import com.alatheer.noamany.Activities.PaymentActivity;
import com.alatheer.noamany.Data.Remote.AllSubscription.AddSubscriptionModel;
import com.alatheer.noamany.Data.Remote.AllSubscription.AllSubscription;
import com.alatheer.noamany.Data.Remote.AllSubscription.SubscriptionListModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Data.Remote.branches.BranchModel;
import com.alatheer.noamany.Data.Remote.branches.Record;
import com.alatheer.noamany.Fragments.AddSubscriptionFragment;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubscriptionViewModel {
    Context context;
    AddSubscriptionFragment addSubscriptionFragment;
    List<String> branchListtitle,subscriptionlisttitle,genderlisttitle;
    List<Record> branchList;
    List<AllSubscription> allSubscriptionList;


    public AddSubscriptionViewModel(Context context, AddSubscriptionFragment addSubscriptionFragment) {
        this.context = context;
        this.addSubscriptionFragment = addSubscriptionFragment;
    }

    public void get_branches(String govern_id) {
        //Toast.makeText(context, "govern_id:"+govern_id, Toast.LENGTH_SHORT).show();
        branchListtitle = new ArrayList<>();
        if (govern_id.equals("1")){
            GetDataService services = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<BranchModel> call = services.get_branches();
            call.enqueue(new Callback<BranchModel>() {
                @Override
                public void onResponse(Call<BranchModel> call, Response<BranchModel> response) {
                    if (response.isSuccessful()){
                        if (response.body().getSuccess() == 1){
                            branchList = response.body().getRecords();
                            for (Record govern:branchList){
                                branchListtitle.add(govern.getTitle());
                            }
                            addSubscriptionFragment.setSpinner(branchListtitle,branchList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<BranchModel> call, Throwable t) {

                }
            });
        }else {
            GetDataService services = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
            Call<BranchModel> call = services.get_branches();
            call.enqueue(new Callback<BranchModel>() {
                @Override
                public void onResponse(Call<BranchModel> call, Response<BranchModel> response) {
                    if (response.isSuccessful()){
                        if (response.body().getSuccess() == 1){
                            branchList = response.body().getRecords();
                            for (Record govern:branchList){
                                branchListtitle.add(govern.getTitle());
                            }
                            addSubscriptionFragment.setSpinner(branchListtitle,branchList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<BranchModel> call, Throwable t) {

                }
            });
        }
    }

    public void get_subscriptions(String govern_id) {
        subscriptionlisttitle = new ArrayList<>();
        if (govern_id.equals("1")){
            GetDataService services = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<SubscriptionListModel> call = services.getAllSubscriptionList();
            call.enqueue(new Callback<SubscriptionListModel>() {
                @Override
                public void onResponse(Call<SubscriptionListModel> call, Response<SubscriptionListModel> response) {
                    if (response.isSuccessful()){
                        allSubscriptionList = response.body().getAllSubcriptions();
                        for (AllSubscription govern:allSubscriptionList){
                            subscriptionlisttitle.add(govern.getTitle());
                        }
                        addSubscriptionFragment.setSubscriptionSpinner(subscriptionlisttitle,allSubscriptionList);
                    }
                }

                @Override
                public void onFailure(Call<SubscriptionListModel> call, Throwable t) {

                }
            });
        }else {
            GetDataService services = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
            Call<SubscriptionListModel> call = services.getAllSubscriptionList();
            call.enqueue(new Callback<SubscriptionListModel>() {
                @Override
                public void onResponse(Call<SubscriptionListModel> call, Response<SubscriptionListModel> response) {
                    if (response.isSuccessful()){
                        allSubscriptionList = response.body().getAllSubcriptions();
                        for (AllSubscription govern:allSubscriptionList){
                            subscriptionlisttitle.add(govern.getTitle());
                        }
                        addSubscriptionFragment.setSubscriptionSpinner(subscriptionlisttitle,allSubscriptionList);
                    }
                }

                @Override
                public void onFailure(Call<SubscriptionListModel> call, Throwable t) {

                }
            });
        }
    }

    public void get_gender() {
        genderlisttitle = new ArrayList<>();
        genderlisttitle.add("اختر الجنس");
        genderlisttitle.add("رجالي");
        genderlisttitle.add("حريمي");
        addSubscriptionFragment.setgenderSpinner(genderlisttitle);
    }

    public void add_subscription(String user_id, String branch_id, String subscription_id, String price, String start_date, String end_date, String gender_id,String govern_id) {
        /*Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("user_id",user_id);
        intent.putExtra("branch_id",branch_id);
        intent.putExtra("subscription_id",subscription_id);
        intent.putExtra("price",price);
        intent.putExtra("start_date",start_date);
        intent.putExtra("end_date",end_date);
        intent.putExtra("gender_id",gender_id);
        intent.putExtra("govern_id",govern_id);
        //intent.putExtra("subscription_id",response.body().getId());
        context.startActivity(intent);
        addSubscriptionFragment.finish();*/
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<AddSubscriptionModel> call = getDataService.add_subscription(user_id,branch_id,subscription_id,price,start_date,end_date,gender_id);
                call.enqueue(new Callback<AddSubscriptionModel>() {
                    @Override
                    public void onResponse(Call<AddSubscriptionModel> call, Response<AddSubscriptionModel> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess()==1){
                            /*Intent intent = new Intent(context, PaymentActivity.class);
                            intent.putExtra("id",response.body().getCost());
                            intent.putExtra("subscription_id",response.body().getId());
                            context.startActivity(intent);*/
                            addSubscriptionFragment.finish();

                            }else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
                                addSubscriptionFragment.finish();
                            /*Intent intent = new Intent(context, PaymentActivity.class);
                            intent.putExtra("id",response.body().getCost());
                            intent.putExtra("subscription_id",response.body().getId());
                            context.startActivity(intent);
                            addSubscriptionFragment.finish();*/

                            }else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
