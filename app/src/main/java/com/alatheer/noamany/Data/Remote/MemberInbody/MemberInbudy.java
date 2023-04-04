package com.alatheer.noamany.Data.Remote.MemberInbody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberInbudy {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("inbody_date")
    @Expose
    private String inbodyDate;
    @SerializedName("m_code")
    @Expose
    private String mCode;
    @SerializedName("m_id")
    @Expose
    private String mId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInbodyDate() {
        return inbodyDate;
    }

    public void setInbodyDate(String inbodyDate) {
        this.inbodyDate = inbodyDate;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
