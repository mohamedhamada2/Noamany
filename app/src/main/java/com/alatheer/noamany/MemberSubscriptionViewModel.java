package com.alatheer.noamany;

import android.content.Context;
import android.content.res.Resources;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.alatheer.noamany.Adapters.MemberSupscriptionsAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.Auth.Auth;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.BasicAuthClient;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.Invitation.InvitationModel;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscription;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscriptionListModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Fragments.AddAdvertismentFragment;
import com.alatheer.noamany.Fragments.MemberSubscriptions;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberSubscriptionViewModel {
    List<MemberSubscription> memberSubscriptionList;
    MemberSubscriptions memberSubscriptionsfragment;
    MemberSupscriptionsAdapter memberSupscriptionsAdapter;
    Context context,mcontext;
    int limit;
    String language,govern_id;
    Resources resources;
    Date currentTime;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String m_name,m_phone,m_card,m_code;
    public MemberSubscriptionViewModel(Context context,MemberSubscriptions memberSubscriptionsfragment){
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
                                    if (memberSubscription.getStoppedSubscription().equals("0")) {
                                        memberSubscriptionList.add(memberSubscription);
                                    } else if (memberSubscription.getStoppedSubscription().equals("1")) {
                                        long dt2 = Long.parseLong((String) memberSubscription.getStoppedDateTo());
                                        final Date to_dt = new Date((long) (dt2 * 1000.02));
                                        if (currentTime.after(to_dt)) {
                                            memberSubscriptionList.add(memberSubscription);
                                        }
                                    }
                                }

                                memberSupscriptionsAdapter = new MemberSupscriptionsAdapter(memberSubscriptionList, context,memberSubscriptionsfragment);
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
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<MemberSubscriptionListModel> call = getDataService.get_member_subscription(mcode,page);
                call.enqueue(new Callback<MemberSubscriptionListModel>() {
                    @Override
                    public void onResponse(Call<MemberSubscriptionListModel> call, Response<MemberSubscriptionListModel> response) {
                        if(response.isSuccessful()){
                            if(!response.body().getMemberSubscriptions().isEmpty()){
                                for (MemberSubscription memberSubscription : response.body().getMemberSubscriptions()) {
                                    if (memberSubscription.getStoppedSubscription().equals("0")) {
                                        memberSubscriptionList.add(memberSubscription);
                                    } else if (memberSubscription.getStoppedSubscription().equals("1")) {
                                        long dt2 = Long.parseLong((String) memberSubscription.getStoppedDateTo());
                                        final Date to_dt = new Date((long) (dt2 * 1000.02));
                                        if (currentTime.after(to_dt)) {
                                            memberSubscriptionList.add(memberSubscription);
                                        }
                                    }
                                }

                                memberSupscriptionsAdapter = new MemberSupscriptionsAdapter(memberSubscriptionList, context,memberSubscriptionsfragment);
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

    public void add_inbody(String id) {
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(context);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(context);
        govern_id = govern.getId();
        m_card = userModel.getMember().getMCard();
        m_code = userModel.getMember().getMCode();
        m_name = userModel.getMember().getMName();
        m_phone = userModel.getMember().getMTel();
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<InvitationModel> call = getDataService.request_inbody(m_name,m_phone,m_card,m_code,id);
                call.enqueue(new Callback<InvitationModel>() {
                    @Override
                    public void onResponse(Call<InvitationModel> call, Response<InvitationModel> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InvitationModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<InvitationModel> call = getDataService.request_inbody(m_name,m_phone,m_card,m_code,id);
                call.enqueue(new Callback<InvitationModel>() {
                    @Override
                    public void onResponse(Call<InvitationModel> call, Response<InvitationModel> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InvitationModel> call, Throwable t) {

                    }
                });
            }
        }
    }

    public void getAuth() {
        //Toast.makeText(context, "auth", Toast.LENGTH_SHORT).show();
        if (Utilities.isNetworkAvailable(context)){
            GetDataService getDataService = BasicAuthClient.getRetrofitInstance3().create(GetDataService.class);
            Call<Auth> call = getDataService.auth();
            call.enqueue(new Callback<Auth>() {
                @Override
                public void onResponse(Call<Auth> call, Response<Auth> response) {
                    if (response.isSuccessful()){
                        Log.e("session",response.body().getSession().getId());
                        memberSubscriptionsfragment.setSession(response.body().getSession().getId());
                        //Log.e("auth_error",response.body().getSession().getId());
                        //Toast.makeText(context, response.body().getSession().getId(), Toast.LENGTH_SHORT).show();
                    }else {

                    }
                }

                @Override
                public void onFailure(Call<Auth> call, Throwable t) {
                    Log.e("auth_error",t.getMessage());
                }
            });
        }
    }
}
