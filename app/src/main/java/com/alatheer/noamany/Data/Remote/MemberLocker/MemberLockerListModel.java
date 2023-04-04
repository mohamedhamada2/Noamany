package com.alatheer.noamany.Data.Remote.MemberLocker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberLockerListModel {
    @SerializedName("Member_lockers")
    @Expose
    private List<MemberLocker> memberLockers = null;

    public List<MemberLocker> getMemberLockers() {
        return memberLockers;
    }

    public void setMemberLockers(List<MemberLocker> memberLockers) {
        this.memberLockers = memberLockers;
    }

}