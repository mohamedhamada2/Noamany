package com.alatheer.noamany;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.alatheer.noamany.Adapters.MemberSupscriptionsAdapter;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscription;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscriptionListModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.Utilities.Utilities;
import com.alatheer.noamany.receipts.ReceiptsAdapter;
import com.alatheer.noamany.receipts.ReceiptsFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiptsViewModel {
    List<MemberSubscription> memberSubscriptionList;
    ReceiptsFragment memberSubscriptionsfragment;
    ReceiptsAdapter memberSupscriptionsAdapter;
    Context context,mcontext;
    int limit;
    String language,govern_id;
    Resources resources;
    Date currentTime;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String m_name,m_phone,m_card,m_code;
    public ReceiptsViewModel(Context context, ReceiptsFragment memberSubscriptionsfragment){
        this.memberSubscriptionsfragment = memberSubscriptionsfragment;
        this.context = context;
        memberSubscriptionList = new ArrayList<>();
        Paper.init(context);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
    }

    public void getmemebersupscription(int page, String mcode,String govern_id) {
        currentTime = Calendar.getInstance().getTime();
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(mcode,page);
                call.enqueue(new Callback<MemberSubscriptionListModel>() {
                    @Override
                    public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                        if(response.isSuccessful()){
                            if(!response.body().getMemberSubscriptions().isEmpty()){
                                for (MemberSubscription memberSubscription : response.body().getMemberSubscriptions()) {
                                    memberSubscriptionList.add(memberSubscription);

                                }
                                memberSupscriptionsAdapter = new ReceiptsAdapter(memberSubscriptionList, context,memberSubscriptionsfragment);
                                memberSubscriptionsfragment.initrecycler(memberSupscriptionsAdapter,memberSubscriptionList);
                                //Toast.makeText(context, "first page is loaded", Toast.LENGTH_SHORT).show();
                                memberSubscriptionsfragment.setprogressbar();
                            }else {
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context,resources.getString(R.string.supscription_data_found), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {
                        Log.e("error9",t.getMessage());
                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(mcode,page);
                call.enqueue(new Callback<MemberSubscriptionListModel>() {
                    @Override
                    public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                        if(response.isSuccessful()){
                            if(!response.body().getMemberSubscriptions().isEmpty()){
                                for (MemberSubscription memberSubscription : response.body().getMemberSubscriptions()) {
                                    memberSubscriptionList.add(memberSubscription);

                                }
                                memberSupscriptionsAdapter = new ReceiptsAdapter(memberSubscriptionList, context,memberSubscriptionsfragment);
                                memberSubscriptionsfragment.initrecycler(memberSupscriptionsAdapter,memberSubscriptionList);
                                //Toast.makeText(context, "first page is loaded", Toast.LENGTH_SHORT).show();
                                memberSubscriptionsfragment.setprogressbar();
                            }else {
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context,resources.getString(R.string.supscription_data_found), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {

                    }
                });
            }
        }
    }

    public void PerformPagination(String mcode, int page,String govern_id){
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(mcode,page);
                call.enqueue(new Callback<MemberSubscriptionListModel>() {
                    @Override
                    public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                        if(response.isSuccessful()){
                            if(page <= response.body().getPagesCount()){
                                memberSubscriptionList = response.body().getMemberSubscriptions();
                                memberSupscriptionsAdapter.add_subscription(memberSubscriptionList);
                                //Toast.makeText(context, "page:"+page+"is loaded", Toast.LENGTH_SHORT).show();
                            }else {
                                //Toast.makeText(context, "no more available", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(mcode,page);
                call.enqueue(new Callback<MemberSubscriptionListModel>() {
                    @Override
                    public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                        if(response.isSuccessful()){
                            if(page <= response.body().getPagesCount()){
                                memberSubscriptionList = response.body().getMemberSubscriptions();
                                memberSupscriptionsAdapter.add_subscription(memberSubscriptionList);
                                //Toast.makeText(context, "page:"+page+"is loaded", Toast.LENGTH_SHORT).show();
                            }else {
                                //Toast.makeText(context, "no more available", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<MemberSubscriptionListModel> call, Throwable t) {

                    }
                });
            }
        }
    }
}
