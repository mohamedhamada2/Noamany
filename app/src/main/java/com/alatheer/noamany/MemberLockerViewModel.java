package com.alatheer.noamany;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.MemberLocker.MemberLocker;
import com.alatheer.noamany.Data.Remote.MemberLocker.MemberLockerListModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Fragments.MemberLockers;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberLockerViewModel {
    Context context;
    MemberLockers memberLockers;
    List<MemberLocker> memberLockerList;
    String language,govern_id;
    Context mcontext;
    Resources resources;
    public MemberLockerViewModel(Context context, MemberLockers memberLockers) {
        this.context = context;
        this.memberLockers = memberLockers;
        Paper.init(context);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
    }

    public void get_members_locker(String mCode,String govern_id) {
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<MemberLockerListModel> call = getDataService.get_member_locker(mCode);
                call.enqueue(new Callback<MemberLockerListModel>() {
                    @Override
                    public void onResponse(Call<MemberLockerListModel> call, Response<MemberLockerListModel> response) {
                        if(response.isSuccessful()){
                            if(!response.body().getMemberLockers().isEmpty()){
                                memberLockerList = response.body().getMemberLockers();
                                memberLockers.initrecycler(memberLockerList);
                            }else {
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context,resources.getString(R.string.locker_data_found), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<MemberLockerListModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<MemberLockerListModel> call = getDataService.get_member_locker(mCode);
                call.enqueue(new Callback<MemberLockerListModel>() {
                    @Override
                    public void onResponse(Call<MemberLockerListModel> call, Response<MemberLockerListModel> response) {
                        if(response.isSuccessful()){
                            if(!response.body().getMemberLockers().isEmpty()){
                                memberLockerList = response.body().getMemberLockers();
                                memberLockers.initrecycler(memberLockerList);
                            }else {
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context,resources.getString(R.string.locker_data_found), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<MemberLockerListModel> call, Throwable t) {

                    }
                });
            }
        }
    }
}
