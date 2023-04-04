package com.alatheer.noamany.Data.Remote.AllSubscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubscriptionListModel {
    @SerializedName("AllSubcriptions")
    @Expose
    private List<AllSubscription> allSubcriptions = null;

    public List<AllSubscription> getAllSubcriptions() {
        return allSubcriptions;
    }

    public void setAllSubcriptions(List<AllSubscription> allSubcriptions) {
        this.allSubcriptions = allSubcriptions;
    }
}