package com.alatheer.noamany.Data.Remote.AuthenticationModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("member")
    @Expose
    private Member member;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("success")
    @Expose
    private Integer success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
