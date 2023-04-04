package com.alatheer.noamany;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.MemberVedio.MemberVedio;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Fragments.MembersVedios;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberVedioViewModel {
    Context context,mcontext;
    MembersVedios membersVedios;
    List<MemberVedio>memberVedioList;
    String language,govern_id;
    Resources resources;
    public MemberVedioViewModel(Context context, MembersVedios membersVedios) {
        this.context = context;
        this.membersVedios = membersVedios;
        Paper.init(context);
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
    }

    public void get_member_vedioes(String mcode,String govern_id) {
        if(Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<MemberVedio>> call = getDataService.getvedios(mcode);
                call.enqueue(new Callback<List<MemberVedio>>() {
                    @Override
                    public void onResponse(Call<List<MemberVedio>> call, Response<List<MemberVedio>> response) {
                        if(response.isSuccessful()){
                            if(!response.body().isEmpty()){
                                memberVedioList = response.body();
                                membersVedios.init_recycler(memberVedioList);
                            }else {
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context, resources.getString(R.string.videos_found), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MemberVedio>> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<List<MemberVedio>> call = getDataService.getvedios(mcode);
                call.enqueue(new Callback<List<MemberVedio>>() {
                    @Override
                    public void onResponse(Call<List<MemberVedio>> call, Response<List<MemberVedio>> response) {
                        if(response.isSuccessful()){
                            if(!response.body().isEmpty()){
                                memberVedioList = response.body();
                                membersVedios.init_recycler(memberVedioList);
                            }else {
                                mcontext = LocaleHelper.setLocale(context,language);
                                resources = mcontext.getResources();
                                Toast.makeText(context, resources.getString(R.string.videos_found), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MemberVedio>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
