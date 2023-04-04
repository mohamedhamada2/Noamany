package com.alatheer.noamany.Data.Remote.AllSubscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddSubscriptionModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
