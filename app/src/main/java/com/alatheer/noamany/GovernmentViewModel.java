package com.alatheer.noamany;

import android.content.Context;

import com.alatheer.noamany.Activities.GovernmentActivity;
import com.alatheer.noamany.Data.Local.Govern;

import java.util.ArrayList;
import java.util.List;

public class GovernmentViewModel {
    Context context;
    List<Govern> governList;
    List<String> governtitle;
    GovernmentActivity governmentActivity;
    public GovernmentViewModel(Context context) {
        this.context = context;
        governmentActivity = (GovernmentActivity) context;
    }

    public void getGovernments() {
        governList = new ArrayList<>();
        Govern govern = new Govern();
        govern.setId("1");
        govern.setName("شبين الكوم");
        Govern govern2 = new Govern();
        govern2.setId("2");
        govern2.setName("طنطا");
        governList.add(govern);
        governList.add(govern2);
        governtitle = new ArrayList<>();
        for (Govern govern3 : governList) {
            governtitle.add(govern3.getName());

        }
        governmentActivity.init_spinner(governList,governtitle);

    }
}
