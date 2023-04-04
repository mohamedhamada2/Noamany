package com.alatheer.noamany;

import android.content.Context;

import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Data.Remote.wallet.Wallet;
import com.alatheer.noamany.Fragments.PointsFragment;
import com.alatheer.noamany.Points.OrderStatus;
import com.alatheer.noamany.Points.Points;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointsViewModel {
    Context context;
    PointsFragment pointsFragment;
    List<OrderStatus> orderStatusList;

    public PointsViewModel(Context context, PointsFragment pointsFragment) {
        this.context = context;
        this.pointsFragment = pointsFragment;
    }

    public void getAvailablePoints(String m_code,String govern_id) {
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<Points>> call = getDataService.get_points(m_code);
                call.enqueue(new Callback<List<Points>>() {
                    @Override
                    public void onResponse(Call<List<Points>> call, Response<List<Points>> response) {
                        if (response.isSuccessful()){
                            pointsFragment.init_recycler(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Points>> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<List<Points>> call = getDataService.get_points(m_code);
                call.enqueue(new Callback<List<Points>>() {
                    @Override
                    public void onResponse(Call<List<Points>> call, Response<List<Points>> response) {
                        if (response.isSuccessful()){
                            pointsFragment.init_recycler(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Points>> call, Throwable t) {

                    }
                });
            }

        }
    }

    public void getDisAvailablePoints(String m_code, String govern_id) {
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<Points>> call = getDataService.get_ended_points(m_code);
                call.enqueue(new Callback<List<Points>>() {
                    @Override
                    public void onResponse(Call<List<Points>> call, Response<List<Points>> response) {
                        if (response.isSuccessful()){
                            pointsFragment.init_recycler(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Points>> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<List<Points>> call = getDataService.get_ended_points(m_code);
                call.enqueue(new Callback<List<Points>>() {
                    @Override
                    public void onResponse(Call<List<Points>> call, Response<List<Points>> response) {
                        if (response.isSuccessful()){
                            pointsFragment.init_recycler(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Points>> call, Throwable t) {

                    }
                });
            }

        }
    }
    public void get_points_in_wallet(String m_code,String govern_id) {
        if (Utilities.isNetworkAvailable(context)) {
            if (govern_id.equals("1")) {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<Wallet> call = getDataService.get_points_in_wallet(m_code);
                call.enqueue(new Callback<Wallet>() {
                    @Override
                    public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                        if (response.isSuccessful()) {
                            pointsFragment.setData(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Wallet> call, Throwable t) {

                    }
                });
            } else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<Wallet> call = getDataService.get_points_in_wallet(m_code);
                call.enqueue(new Callback<Wallet>() {
                    @Override
                    public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                        if (response.isSuccessful()) {
                            pointsFragment.setData(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Wallet> call, Throwable t) {

                    }
                });
            }
        }
    }

    public void get_orders_status() {
        orderStatusList = new ArrayList<>();
        orderStatusList.add(new OrderStatus("Available points","النقاط المتاحة"));
        orderStatusList.add(new OrderStatus("unavailable points","النقاط الغير متاحة"));
        pointsFragment.init_order_status(orderStatusList);
    }
}
