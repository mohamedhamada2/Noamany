package com.alatheer.noamany.Data.Remote.MemberLocker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberLocker {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("member_code")
    @Expose
    private String memberCode;
    @SerializedName("main_branch_id_fk")
    @Expose
    private String mainBranchIdFk;
    @SerializedName("branch_id_fk")
    @Expose
    private String branchIdFk;
    @SerializedName("number_locker_id_fk")
    @Expose
    private String numberLockerIdFk;
    @SerializedName("subscription_type")
    @Expose
    private Object subscriptionType;
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
    @SerializedName("locker_title")
    @Expose
    private String lockerTitle;

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

    public String getMainBranchIdFk() {
        return mainBranchIdFk;
    }

    public void setMainBranchIdFk(String mainBranchIdFk) {
        this.mainBranchIdFk = mainBranchIdFk;
    }

    public String getBranchIdFk() {
        return branchIdFk;
    }

    public void setBranchIdFk(String branchIdFk) {
        this.branchIdFk = branchIdFk;
    }

    public String getNumberLockerIdFk() {
        return numberLockerIdFk;
    }

    public void setNumberLockerIdFk(String numberLockerIdFk) {
        this.numberLockerIdFk = numberLockerIdFk;
    }

    public Object getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(Object subscriptionType) {
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

    public String getLockerTitle() {
        return lockerTitle;
    }

    public void setLockerTitle(String lockerTitle) {
        this.lockerTitle = lockerTitle;
    }
}
