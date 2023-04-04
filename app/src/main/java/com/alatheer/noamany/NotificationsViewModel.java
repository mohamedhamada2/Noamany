package com.alatheer.noamany;

import android.content.Context;

import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.Notification.NotificationModel;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Fragments.NotificationFragment;
import com.alatheer.noamany.Utilities.Utilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsViewModel {
    Context context;
    NotificationFragment notificationFragment;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String m_code,govern_id;
    List<NotificationModel> notificationModelList;

    public NotificationsViewModel(Context context, NotificationFragment notificationFragment) {
        this.context = context;
        this.notificationFragment = notificationFragment;
    }
    public void getNotifications(String govern_id) {
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<NotificationModel>> call = getDataService.get_notification(m_code);
                call.enqueue(new Callback<List<NotificationModel>>() {
                    @Override
                    public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                        if (response.isSuccessful()){
                            notificationModelList = response.body();
                            notificationFragment.initRecyclerView(notificationModelList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NotificationModel>> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<List<NotificationModel>> call = getDataService.get_notification(m_code);
                call.enqueue(new Callback<List<NotificationModel>>() {
                    @Override
                    public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                        if (response.isSuccessful()){
                            notificationModelList = response.body();
                            notificationFragment.initRecyclerView(notificationModelList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NotificationModel>> call, Throwable t) {

                    }
                });
            }

        }
    }

    public void getSharedPreferanceData() {
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(context);
        m_code = userModel.getMember().getMCode();
    }
}
