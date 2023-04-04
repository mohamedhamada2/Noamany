package com.alatheer.noamany.Data.Remote.AllSubscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllSubscription {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("num_days")
    @Expose
    private String numDays;
    @SerializedName("stopped")
    @Expose
    private String stopped;
    @SerializedName("stopped_days_num")
    @Expose
    private String stoppedDaysNum;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("approved")
    @Expose
    private String approved;
    @SerializedName("student")
    @Expose
    private String student;
    @SerializedName("special_offer")
    @Expose
    private String specialOffer;
    @SerializedName("value_gal2")
    @Expose
    private String valueGal2;
    @SerializedName("value_brshrqe")
    @Expose
    private String valueBrshrqe;
    @SerializedName("value_tagm3")
    @Expose
    private String valueTagm3;
    @SerializedName("invitations_count")
    @Expose
    private String invitationsCount;
    @SerializedName("inbody_count")
    @Expose
    private String inbodyCount;
    @SerializedName("have_class")
    @Expose
    private String haveClass;
    @SerializedName("num_classes")
    @Expose
    private String numClasses;
    @SerializedName("app_display")
    @Expose
    private String appDisplay;
    @SerializedName("app_notify")
    @Expose
    private String appNotify;
    @SerializedName("points")
    @Expose
    private String points;
    @SerializedName("have_spa")
    @Expose
    private String haveSpa;
    @SerializedName("num_spa")
    @Expose
    private String numSpa;
    @SerializedName("expire_num_days")
    @Expose
    private Integer expireNumDays;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumDays() {
        return numDays;
    }

    public void setNumDays(String numDays) {
        this.numDays = numDays;
    }

    public String getStopped() {
        return stopped;
    }

    public void setStopped(String stopped) {
        this.stopped = stopped;
    }

    public String getStoppedDaysNum() {
        return stoppedDaysNum;
    }

    public void setStoppedDaysNum(String stoppedDaysNum) {
        this.stoppedDaysNum = stoppedDaysNum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(String specialOffer) {
        this.specialOffer = specialOffer;
    }

    public String getValueGal2() {
        return valueGal2;
    }

    public void setValueGal2(String valueGal2) {
        this.valueGal2 = valueGal2;
    }

    public String getValueBrshrqe() {
        return valueBrshrqe;
    }

    public void setValueBrshrqe(String valueBrshrqe) {
        this.valueBrshrqe = valueBrshrqe;
    }

    public String getValueTagm3() {
        return valueTagm3;
    }

    public void setValueTagm3(String valueTagm3) {
        this.valueTagm3 = valueTagm3;
    }

    public String getInvitationsCount() {
        return invitationsCount;
    }

    public void setInvitationsCount(String invitationsCount) {
        this.invitationsCount = invitationsCount;
    }

    public String getInbodyCount() {
        return inbodyCount;
    }

    public void setInbodyCount(String inbodyCount) {
        this.inbodyCount = inbodyCount;
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

    public String getAppDisplay() {
        return appDisplay;
    }

    public void setAppDisplay(String appDisplay) {
        this.appDisplay = appDisplay;
    }

    public String getAppNotify() {
        return appNotify;
    }

    public void setAppNotify(String appNotify) {
        this.appNotify = appNotify;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
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

    public Integer getExpireNumDays() {
        return expireNumDays;
    }

    public void setExpireNumDays(Integer expireNumDays) {
        this.expireNumDays = expireNumDays;
    }
}
