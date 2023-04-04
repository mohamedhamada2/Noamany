package com.alatheer.noamany;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.MemberInbody.MemberInbodyListModel;
import com.alatheer.noamany.Data.Remote.MemberInbody.MemberInbudy;
import com.alatheer.noamany.Data.Remote.MemberLocker.MemberLocker;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Fragments.BodyStats;
import com.alatheer.noamany.Fragments.MemberLockers;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BodyStatsViewModel {
    Context context;
    BodyStats bodyStats;
    List<MemberInbudy> memberInbudyList;
    Resources resources;
    Context mcontext;
    String language;
    String govern_id;
    public BodyStatsViewModel(Context context, BodyStats bodyStats) {
        this.context = context;
        this.bodyStats = bodyStats;
        Paper.init(context);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
    }

    public void get_member_inbody(String mcode,String govern_id) {
        //govern_id = SharedPreference2.loadData(context);
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<MemberInbodyListModel> call = getDataService.get_member_inbudyDetails(mcode);
                call.enqueue(new Callback<MemberInbodyListModel>() {
                    @Override
                    public void onResponse(Call<MemberInbodyListModel> call, Response<MemberInbodyListModel> response) {
                        if(response.isSuccessful()){
                            if (!response.body().getInbody().isEmpty()){
                                memberInbudyList = response.body().getInbody();
                                bodyStats.initrecyclerview(memberInbudyList);
                            }else{
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context, resources.getString(R.string.body_stats_found),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MemberInbodyListModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<MemberInbodyListModel> call = getDataService.get_member_inbudyDetails(mcode);
                call.enqueue(new Callback<MemberInbodyListModel>() {
                    @Override
                    public void onResponse(Call<MemberInbodyListModel> call, Response<MemberInbodyListModel> response) {
                        if(response.isSuccessful()){
                            if (!response.body().getInbody().isEmpty()){
                                memberInbudyList = response.body().getInbody();
                                bodyStats.initrecyclerview(memberInbudyList);
                            }else{
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context, resources.getString(R.string.body_stats_found),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MemberInbodyListModel> call, Throwable t) {

                    }
                });
            }
        }
    }
}
