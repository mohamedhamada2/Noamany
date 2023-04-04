package com.alatheer.noamany.Data.Remote.MemberInbody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberInbodyListModel {

    @SerializedName("inbody")
    @Expose
    private List<MemberInbudy> inbody = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<MemberInbudy> getInbody() {
        return inbody;
    }

    public void setInbody(List<MemberInbudy> inbody) {
        this.inbody = inbody;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}