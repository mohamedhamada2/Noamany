package com.alatheer.noamany;

import android.content.Context;

import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Fragments.MemberDiscount;
import com.alatheer.noamany.Utilities.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberDiscountViewModel {
    Context context;
    MemberDiscount memberDiscount;

    public MemberDiscountViewModel(Context context, MemberDiscount memberDiscount) {
        this.context = context;
        this.memberDiscount = memberDiscount;
    }

    public void getMemberDiscounts(String m_code,String govern_id) {
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> call = getDataService.get_member_discounts(m_code);
                call.enqueue(new Callback<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount>() {
                    @Override
                    public void onResponse(Call<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> call, Response<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> response) {
                        if (response.isSuccessful()){
                            memberDiscount.setData(response.body().getRecords());
                        }
                    }

                    @Override
                    public void onFailure(Call<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> call = getDataService.get_member_discounts(m_code);
                call.enqueue(new Callback<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount>() {
                    @Override
                    public void onResponse(Call<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> call, Response<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> response) {
                        if (response.isSuccessful()){
                            memberDiscount.setData(response.body().getRecords());
                        }
                    }

                    @Override
                    public void onFailure(Call<com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount> call, Throwable t) {

                    }
                });
            }
        }
    }
}
