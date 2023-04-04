package com.alatheer.noamany.Data.Remote.updatetoken;

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
    @SerializedName("captain_id")
    @Expose
    private String captainId;
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
    @SerializedName("publisher_blocked")
    @Expose
    private Object publisherBlocked;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("m_password")
    @Expose
    private String mPassword;
    @SerializedName("male_or_female")
    @Expose
    private String maleOrFemale;
    @SerializedName("notify_token")
    @Expose
    private Object notifyToken;
    @SerializedName("fire_basetoken")
    @Expose
    private String fireBasetoken;
    @SerializedName("deleted")
    @Expose
    private String deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getBranchIdFk() {
        return branchIdFk;
    }

    public void setBranchIdFk(String branchIdFk) {
        this.branchIdFk = branchIdFk;
    }

    public String getmNationality() {
        return mNationality;
    }

    public void setmNationality(String mNationality) {
        this.mNationality = mNationality;
    }

    public String getmBirthday() {
        return mBirthday;
    }

    public void setmBirthday(String mBirthday) {
        this.mBirthday = mBirthday;
    }

    public String getmCard() {
        return mCard;
    }

    public void setmCard(String mCard) {
        this.mCard = mCard;
    }

    public String getmJobAddress() {
        return mJobAddress;
    }

    public void setmJobAddress(String mJobAddress) {
        this.mJobAddress = mJobAddress;
    }

    public String getmSocialStatus() {
        return mSocialStatus;
    }

    public void setmSocialStatus(String mSocialStatus) {
        this.mSocialStatus = mSocialStatus;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmJob() {
        return mJob;
    }

    public void setmJob(String mJob) {
        this.mJob = mJob;
    }

    public String getmTel() {
        return mTel;
    }

    public void setmTel(String mTel) {
        this.mTel = mTel;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCaptainId() {
        return captainId;
    }

    public void setCaptainId(String captainId) {
        this.captainId = captainId;
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

    public Object getPublisherBlocked() {
        return publisherBlocked;
    }

    public void setPublisherBlocked(Object publisherBlocked) {
        this.publisherBlocked = publisherBlocked;
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

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getMaleOrFemale() {
        return maleOrFemale;
    }

    public void setMaleOrFemale(String maleOrFemale) {
        this.maleOrFemale = maleOrFemale;
    }

    public Object getNotifyToken() {
        return notifyToken;
    }

    public void setNotifyToken(Object notifyToken) {
        this.notifyToken = notifyToken;
    }

    public String getFireBasetoken() {
        return fireBasetoken;
    }

    public void setFireBasetoken(String fireBasetoken) {
        this.fireBasetoken = fireBasetoken;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
