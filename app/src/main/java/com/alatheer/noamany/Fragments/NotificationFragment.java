package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.noamany.Adapters.NotificationAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Remote.Notification.NotificationModel;
import com.alatheer.noamany.NotificationsViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentNotificationBinding;

import java.util.List;

public class NotificationFragment extends Fragment {
    FragmentNotificationBinding fragmentNotificationBinding;
    NotificationsViewModel notificationViewModel;
    RecyclerView.LayoutManager layoutManager;
    NotificationAdapter notificationAdapter;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String govern_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNotificationBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_notification, container, false);
        notificationViewModel = new NotificationsViewModel(getActivity(),this);
        View view = fragmentNotificationBinding.getRoot();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        fragmentNotificationBinding.setNotificationviewmodel(notificationViewModel);
        notificationViewModel.getSharedPreferanceData();
        notificationViewModel.getNotifications(govern_id);
        return view;
    }

    public void initRecyclerView(List<NotificationModel> notificationModelList) {
        fragmentNotificationBinding.notificationsRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        fragmentNotificationBinding.notificationsRecycler.setLayoutManager(layoutManager);
        notificationAdapter = new NotificationAdapter(notificationModelList,getActivity(),this);
        fragmentNotificationBinding.notificationsRecycler.setAdapter(notificationAdapter);
    }
}
