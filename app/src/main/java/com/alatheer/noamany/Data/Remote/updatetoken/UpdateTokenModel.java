package com.alatheer.noamany.Data.Remote.updatetoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTokenModel {
    @SerializedName("sucess")
    @Expose
    private Integer sucess;
    @SerializedName("member")
    @Expose
    private Member member;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSucess() {
        return sucess;
    }

    public void setSucess(Integer sucess) {
        this.sucess = sucess;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
