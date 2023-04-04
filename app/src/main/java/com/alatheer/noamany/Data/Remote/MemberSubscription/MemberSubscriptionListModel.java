package com.alatheer.noamany.Data.Remote.MemberSubscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberSubscriptionListModel {
    @SerializedName("pages_count")
    @Expose
    private Integer pagesCount;
    @SerializedName("MemberSubscriptions")
    @Expose
    private List<MemberSubscription> memberSubscriptions = null;

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<MemberSubscription> getMemberSubscriptions() {
        return memberSubscriptions;
    }

    public void setMemberSubscriptions(List<MemberSubscription> memberSubscriptions) {
        this.memberSubscriptions = memberSubscriptions;
    }

}
      