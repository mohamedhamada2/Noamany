package com.alatheer.noamany.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.noamany.Adapters.OrderStatusAdapter;
import com.alatheer.noamany.Adapters.PointsAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.wallet.Wallet;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.NotificationsViewModel;
import com.alatheer.noamany.Points.OrderStatus;
import com.alatheer.noamany.Points.Points;
import com.alatheer.noamany.PointsViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentPointsBinding;

import java.util.List;


public class PointsFragment extends Fragment {
    FragmentPointsBinding fragmentPointsBinding;
    PointsViewModel pointsViewModel;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String m_code;
    Integer flag = 1;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String govern_id;
    RecyclerView.LayoutManager layoutManager;
    PointsAdapter pointsAdapter;
    String language;
    Context context;
    Resources resources;
    OrderStatusAdapter orderStatusAdapter;
    LinearLayoutManager orders_status_manager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPointsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_points, container, false);
        pointsViewModel = new PointsViewModel(getActivity(),this);
        View view = fragmentPointsBinding.getRoot();
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(getActivity());
        m_code = userModel.getMember().getMCode();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        fragmentPointsBinding.setPointsviewmodel(pointsViewModel);
        pointsViewModel.get_orders_status();
        pointsViewModel.get_points_in_wallet(m_code,govern_id);
        Paper.init(getActivity());
        language = Paper.book().read("language");
        fragmentPointsBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Home2();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        updateview(language);
        if (flag == 1){
            pointsViewModel.getAvailablePoints(m_code,govern_id);
        }
        return view;
    }

    public void init_recycler(List<Points> body) {
        layoutManager = new LinearLayoutManager(getContext());
        pointsAdapter = new PointsAdapter(getContext(),body);
        fragmentPointsBinding.notificationsRecycler.setHasFixedSize(true);
        fragmentPointsBinding.notificationsRecycler.setAdapter(pointsAdapter);
        fragmentPointsBinding.notificationsRecycler.setLayoutManager(layoutManager);
    }
    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        fragmentPointsBinding.txtPoints.setText(resources.getString(R.string.points));
        fragmentPointsBinding.points.setText(resources.getString(R.string.points));
        fragmentPointsBinding.values.setText(resources.getString(R.string.value));
    }

    public void setData(Wallet body) {
        fragmentPointsBinding.txtPoints2.setText(body.getPoints());
        fragmentPointsBinding.txtValue.setText(body.getValues()+" "+"جنيه");
    }


    public void get_available_points() {
        flag = 1;
        pointsViewModel.getAvailablePoints(m_code,govern_id);
    }

    public void get_dis_available_points() {
        flag = 2;
        pointsViewModel.getDisAvailablePoints(m_code,govern_id);
    }
    public void init_order_status(List<OrderStatus> orderStatusList) {
        orders_status_manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
        orders_status_manager.setReverseLayout(true);
        orderStatusAdapter = new OrderStatusAdapter(orderStatusList,getActivity(),this);
        fragmentPointsBinding.orderStatusRecycler.setAdapter(orderStatusAdapter);
        fragmentPointsBinding.orderStatusRecycler.setLayoutManager(orders_status_manager);
        fragmentPointsBinding.orderStatusRecycler.setHasFixedSize(true);
    }
}