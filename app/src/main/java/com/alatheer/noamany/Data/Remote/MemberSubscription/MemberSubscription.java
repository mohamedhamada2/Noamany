package com.alatheer.noamany.Data.Remote.MemberSubscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberSubscription {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("member_code")
    @Expose
    private String memberCode;
    @SerializedName("captain_id")
    @Expose
    private Object captainId;
    @SerializedName("branch_id_fk")
    @Expose
    private String branchIdFk;
    @SerializedName("main_branch_id_fk")
    @Expose
    private String mainBranchIdFk;
    @SerializedName("subscription_members")
    @Expose
    private String subscriptionMembers;
    @SerializedName("subscription_type")
    @Expose
    private String subscriptionType;
    @SerializedName("cost_before_discount")
    @Expose
    private String costBeforeDiscount;
    @SerializedName("discount_value")
    @Expose
    private String discountValue;
    @SerializedName("cost_after_discount")
    @Expose
    private String costAfterDiscount;
    @SerializedName("paid")
    @Expose
    private String paid;
    @SerializedName("remain")
    @Expose
    private String remain;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("to_date_old")
    @Expose
    private Object toDateOld;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_s")
    @Expose
    private String dateS;
    @SerializedName("date_ar")
    @Expose
    private String dateAr;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("deport")
    @Expose
    private String deport;
    @SerializedName("had_back_value")
    @Expose
    private Object hadBackValue;
    @SerializedName("had_back_date")
    @Expose
    private Object hadBackDate;
    @SerializedName("had_back_date_s")
    @Expose
    private Object hadBackDateS;
    @SerializedName("had_back_date_ar")
    @Expose
    private Object hadBackDateAr;
    @SerializedName("had_back_publisher")
    @Expose
    private Object hadBackPublisher;
    @SerializedName("stopped_subscription")
    @Expose
    private String stoppedSubscription;
    @SerializedName("stopped_date_from")
    @Expose
    private Object stoppedDateFrom;
    @SerializedName("stopped_date_to")
    @Expose
    private Object stoppedDateTo;
    @SerializedName("stopped_reason")
    @Expose
    private Object stoppedReason;
    @SerializedName("followed")
    @Expose
    private String followed;
    @SerializedName("followed_date")
    @Expose
    private Object followedDate;
    @SerializedName("followed_date_ar")
    @Expose
    private Object followedDateAr;
    @SerializedName("followed_publisher")
    @Expose
    private Object followedPublisher;
    @SerializedName("followed_publisher_name")
    @Expose
    private Object followedPublisherName;
    @SerializedName("added_time")
    @Expose
    private String addedTime;
    @SerializedName("pay_method_id_fk")
    @Expose
    private Object payMethodIdFk;
    @SerializedName("male_or_female")
    @Expose
    private Object maleOrFemale;
    @SerializedName("subscription_group_rkm")
    @Expose
    private Object subscriptionGroupRkm;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("deport_sand_qabd")
    @Expose
    private String deportSandQabd;
    @SerializedName("qued_num")
    @Expose
    private String quedNum;
    @SerializedName("deport_date_ar")
    @Expose
    private Object deportDateAr;
    @SerializedName("have_class")
    @Expose
    private String haveClass;
    @SerializedName("num_classes")
    @Expose
    private String numClasses;
    @SerializedName("hadback")
    @Expose
    private String hadback;
    @SerializedName("rkm_esal")
    @Expose
    private Object rkmEsal;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("points_value")
    @Expose
    private String pointsValue;
    @SerializedName("discount_code_id_fk")
    @Expose
    private String discountCodeIdFk;
    @SerializedName("discount_code_nesba")
    @Expose
    private String discountCodeNesba;
    @SerializedName("have_spa")
    @Expose
    private String haveSpa;
    @SerializedName("num_spa")
    @Expose
    private String numSpa;
    @SerializedName("stopped_time")
    @Expose
    private Object stoppedTime;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("from_date_ar")
    @Expose
    private String fromDateAr;
    @SerializedName("to_date_ar")
    @Expose
    private String toDateAr;
    @SerializedName("stopped_from_date_ar")
    @Expose
    private String stoppedFromDateAr;
    @SerializedName("stopped_to_date_ar")
    @Expose
    private String stoppedToDateAr;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("inbody_count")
    @Expose
    private String inbodyCount;
    @SerializedName("taked_inbody")
    @Expose
    private Integer takedInbody;
    @SerializedName("remain_inbody")
    @Expose
    private Integer remainInbody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Object getCaptainId() {
        return captainId;
    }

    public void setCaptainId(Object captainId) {
        this.captainId = captainId;
    }

    public String getBranchIdFk() {
        return branchIdFk;
    }

    public void setBranchIdFk(String branchIdFk) {
        this.branchIdFk = branchIdFk;
    }

    public String getMainBranchIdFk() {
        return mainBranchIdFk;
    }

    public void setMainBranchIdFk(String mainBranchIdFk) {
        this.mainBranchIdFk = mainBranchIdFk;
    }

    public String getSubscriptionMembers() {
        return subscriptionMembers;
    }

    public void setSubscriptionMembers(String subscriptionMembers) {
        this.subscriptionMembers = subscriptionMembers;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getCostBeforeDiscount() {
        return costBeforeDiscount;
    }

    public void setCostBeforeDiscount(String costBeforeDiscount) {
        this.costBeforeDiscount = costBeforeDiscount;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getCostAfterDiscount() {
        return costAfterDiscount;
    }

    public void setCostAfterDiscount(String costAfterDiscount) {
        this.costAfterDiscount = costAfterDiscount;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Object getToDateOld() {
        return toDateOld;
    }

    public void setToDateOld(Object toDateOld) {
        this.toDateOld = toDateOld;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public String getDateAr() {
        return dateAr;
    }

    public void setDateAr(String dateAr) {
        this.dateAr = dateAr;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeport() {
        return deport;
    }

    public void setDeport(String deport) {
        this.deport = deport;
    }

    public Object getHadBackValue() {
        return hadBackValue;
    }

    public void setHadBackValue(Object hadBackValue) {
        this.hadBackValue = hadBackValue;
    }

    public Object getHadBackDate() {
        return hadBackDate;
    }

    public void setHadBackDate(Object hadBackDate) {
        this.hadBackDate = hadBackDate;
    }

    public Object getHadBackDateS() {
        return hadBackDateS;
    }

    public void setHadBackDateS(Object hadBackDateS) {
        this.hadBackDateS = hadBackDateS;
    }

    public Object getHadBackDateAr() {
        return hadBackDateAr;
    }

    public void setHadBackDateAr(Object hadBackDateAr) {
        this.hadBackDateAr = hadBackDateAr;
    }

    public Object getHadBackPublisher() {
        return hadBackPublisher;
    }

    public void setHadBackPublisher(Object hadBackPublisher) {
        this.hadBackPublisher = hadBackPublisher;
    }

    public String getStoppedSubscription() {
        return stoppedSubscription;
    }

    public void setStoppedSubscription(String stoppedSubscription) {
        this.stoppedSubscription = stoppedSubscription;
    }

    public Object getStoppedDateFrom() {
        return stoppedDateFrom;
    }

    public void setStoppedDateFrom(Object stoppedDateFrom) {
        this.stoppedDateFrom = stoppedDateFrom;
    }

    public Object getStoppedDateTo() {
        return stoppedDateTo;
    }

    public void setStoppedDateTo(Object stoppedDateTo) {
        this.stoppedDateTo = stoppedDateTo;
    }

    public Object getStoppedReason() {
        return stoppedReason;
    }

    public void setStoppedReason(Object stoppedReason) {
        this.stoppedReason = stoppedReason;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    public Object getFollowedDate() {
        return followedDate;
    }

    public void setFollowedDate(Object followedDate) {
        this.followedDate = followedDate;
    }

    public Object getFollowedDateAr() {
        return followedDateAr;
    }

    public void setFollowedDateAr(Object followedDateAr) {
        this.followedDateAr = followedDateAr;
    }

    public Object getFollowedPublisher() {
        return followedPublisher;
    }

    public void setFollowedPublisher(Object followedPublisher) {
        this.followedPublisher = followedPublisher;
    }

    public Object getFollowedPublisherName() {
        return followedPublisherName;
    }

    public void setFollowedPublisherName(Object followedPublisherName) {
        this.followedPublisherName = followedPublisherName;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public Object getPayMethodIdFk() {
        return payMethodIdFk;
    }

    public void setPayMethodIdFk(Object payMethodIdFk) {
        this.payMethodIdFk = payMethodIdFk;
    }

    public Object getMaleOrFemale() {
        return maleOrFemale;
    }

    public void setMaleOrFemale(Object maleOrFemale) {
        this.maleOrFemale = maleOrFemale;
    }

    public Object getSubscriptionGroupRkm() {
        return subscriptionGroupRkm;
    }

    public void setSubscriptionGroupRkm(Object subscriptionGroupRkm) {
        this.subscriptionGroupRkm = subscriptionGroupRkm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeportSandQabd() {
        return deportSandQabd;
    }

    public void setDeportSandQabd(String deportSandQabd) {
        this.deportSandQabd = deportSandQabd;
    }

    public String getQuedNum() {
        return quedNum;
    }

    public void setQuedNum(String quedNum) {
        this.quedNum = quedNum;
    }

    public Object getDeportDateAr() {
        return deportDateAr;
    }

    public void setDeportDateAr(Object deportDateAr) {
        this.deportDateAr = deportDateAr;
    }

    public String getHaveClass() {
        return haveClass;
    }

    public void setHaveClass(String haveClass) {
        this.haveClass = haveClass;
    }

    public String getNumClasses() {
        return numClasses;
    }

    public void setNumClasses(String numClasses) {
        this.numClasses = numClasses;
    }

    public String getHadback() {
        return hadback;
    }

    public void setHadback(String hadback) {
        this.hadback = hadback;
    }

    public Object getRkmEsal() {
        return rkmEsal;
    }

    public void setRkmEsal(Object rkmEsal) {
        this.rkmEsal = rkmEsal;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getPointsValue() {
        return pointsValue;
    }

    public void setPointsValue(String pointsValue) {
        this.pointsValue = pointsValue;
    }

    public String getDiscountCodeIdFk() {
        return discountCodeIdFk;
    }

    public void setDiscountCodeIdFk(String discountCodeIdFk) {
        this.discountCodeIdFk = discountCodeIdFk;
    }

    public String getDiscountCodeNesba() {
        return discountCodeNesba;
    }

    public void setDiscountCodeNesba(String discountCodeNesba) {
        this.discountCodeNesba = discountCodeNesba;
    }

    public String getHaveSpa() {
        return haveSpa;
    }

    public void setHaveSpa(String haveSpa) {
        this.haveSpa = haveSpa;
    }

    public String getNumSpa() {
        return numSpa;
    }

    public void setNumSpa(String numSpa) {
        this.numSpa = numSpa;
    }

    public Object getStoppedTime() {
        return stoppedTime;
    }

    public void setStoppedTime(Object stoppedTime) {
        this.stoppedTime = stoppedTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getFromDateAr() {
        return fromDateAr;
    }

    public void setFromDateAr(String fromDateAr) {
        this.fromDateAr = fromDateAr;
    }

    public String getToDateAr() {
        return toDateAr;
    }

    public void setToDateAr(String toDateAr) {
        this.toDateAr = toDateAr;
    }

    public String getStoppedFromDateAr() {
        return stoppedFromDateAr;
    }

    public void setStoppedFromDateAr(String stoppedFromDateAr) {
        this.stoppedFromDateAr = stoppedFromDateAr;
    }

    public String getStoppedToDateAr() {
        return stoppedToDateAr;
    }

    public void setStoppedToDateAr(String stoppedToDateAr) {
        this.stoppedToDateAr = stoppedToDateAr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInbodyCount() {
        return inbodyCount;
    }

    public void setInbodyCount(String inbodyCount) {
        this.inbodyCount = inbodyCount;
    }

    public Integer getTakedInbody() {
        return takedInbody;
    }

    public void setTakedInbody(Integer takedInbody) {
        this.takedInbody = takedInbody;
    }

    public Integer getRemainInbody() {
        return remainInbody;
    }

    public void setRemainInbody(Integer remainInbody) {
        this.remainInbody = remainInbody;
    }

}
