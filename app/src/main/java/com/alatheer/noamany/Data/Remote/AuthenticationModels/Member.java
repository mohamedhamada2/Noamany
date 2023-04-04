package com.alatheer.noamany.Data.Remote.AuthenticationModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("m_code")
    @Expose
    private String mCode;
    @SerializedName("m_name")
    @Expose
    private String mName;
    @SerializedName("branch_id_fk")
    @Expose
    private String branchIdFk;
    @SerializedName("m_nationality")
    @Expose
    private String mNationality;
    @SerializedName("m_birthday")
    @Expose
    private String mBirthday;
    @SerializedName("m_card")
    @Expose
    private String mCard;
    @SerializedName("m_job_address")
    @Expose
    private String mJobAddress;
    @SerializedName("m_social_status")
    @Expose
    private String mSocialStatus;
    @SerializedName("m_type")
    @Expose
    private String mType;
    @SerializedName("m_address")
    @Expose
    private String mAddress;
    @SerializedName("m_job")
    @Expose
    private String mJob;
    @SerializedName("m_tel")
    @Expose
    private String mTel;
    @SerializedName("m_email")
    @Expose
    private String mEmail;
    @SerializedName("comm_id")
    @Expose
    private String commId;
    @SerializedName("m_image")
    @Expose
    private String mImage;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("suspend")
    @Expose
    private String suspend;
    @SerializedName("blocked")
    @Expose
    private String blocked;
    @SerializedName("reason_blocked")
    @Expose
    private String reasonBlocked;
    @SerializedName("date_blocked")
    @Expose
    private String dateBlocked;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("m_password")
    @Expose
    private String mPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMCode() {
        return mCode;
    }

    public void setMCode(String mCode) {
        this.mCode = mCode;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getBranchIdFk() {
        return branchIdFk;
    }

    public void setBranchIdFk(String branchIdFk) {
        this.branchIdFk = branchIdFk;
    }

    public String getMNationality() {
        return mNationality;
    }

    public void setMNationality(String mNationality) {
        this.mNationality = mNationality;
    }

    public String getMBirthday() {
        return mBirthday;
    }

    public void setMBirthday(String mBirthday) {
        this.mBirthday = mBirthday;
    }

    public String getMCard() {
        return mCard;
    }

    public void setMCard(String mCard) {
        this.mCard = mCard;
    }

    public String getMJobAddress() {
        return mJobAddress;
    }

    public void setMJobAddress(String mJobAddress) {
        this.mJobAddress = mJobAddress;
    }

    public String getMSocialStatus() {
        return mSocialStatus;
    }

    public void setMSocialStatus(String mSocialStatus) {
        this.mSocialStatus = mSocialStatus;
    }

    public String getMType() {
        return mType;
    }

    public void setMType(String mType) {
        this.mType = mType;
    }

    public String getMAddress() {
        return mAddress;
    }

    public void setMAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getMJob() {
        return mJob;
    }

    public void setMJob(String mJob) {
        this.mJob = mJob;
    }

    public String getMTel() {
        return mTel;
    }

    public void setMTel(String mTel) {
        this.mTel = mTel;
    }

    public String getMEmail() {
        return mEmail;
    }

    public void setMEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public String getMImage() {
        return mImage;
    }

    public void setMImage(String mImage) {
        this.mImage = mImage;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSuspend() {
        return suspend;
    }

    public void setSuspend(String suspend) {
        this.suspend = suspend;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getReasonBlocked() {
        return reasonBlocked;
    }

    public void setReasonBlocked(String reasonBlocked) {
        this.reasonBlocked = reasonBlocked;
    }

    public String getDateBlocked() {
        return dateBlocked;
    }

    public void setDateBlocked(String dateBlocked) {
        this.dateBlocked = dateBlocked;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMPassword() {
        return mPassword;
    }

    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
