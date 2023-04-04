package com.alatheer.noamany.Data.Remote.LogoutModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogout {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
