package com.alatheer.noamany.Data.Remote.Advertisement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ads implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("rkm")
    @Expose
    private String rkm;
    @SerializedName("sender_date_ar")
    @Expose
    private String senderDateAr;
    @SerializedName("sender_address")
    @Expose
    private String senderAddress;
    @SerializedName("sender_company")
    @Expose
    private String senderCompany;
    @SerializedName("sender_phone")
    @Expose
    private String senderPhone;
    @SerializedName("ads_image")
    @Expose
    private String adsImage;
    @SerializedName("youtube_link")
    @Expose
    private String youtubeLink;
    @SerializedName("ads_from_date")
    @Expose
    private String adsFromDate;
    @SerializedName("ads_to_date")
    @Expose
    private String adsToDate;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("enable")
    @Expose
    private String enable;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("tik_tok")
    @Expose
    private String tik_tok;

    public String getTik_tok() {
        return tik_tok;
    }

    public void setTik_tok(String tik_tok) {
        this.tik_tok = tik_tok;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    @SerializedName("insta")
    @Expose
    private String insta;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRkm() {
        return rkm;
    }

    public void setRkm(String rkm) {
        this.rkm = rkm;
    }

    public String getSenderDateAr() {
        return senderDateAr;
    }

    public void setSenderDateAr(String senderDateAr) {
        this.senderDateAr = senderDateAr;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getAdsImage() {
        return adsImage;
    }

    public void setAdsImage(String adsImage) {
        this.adsImage = adsImage;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getAdsFromDate() {
        return adsFromDate;
    }

    public void setAdsFromDate(String adsFromDate) {
        this.adsFromDate = adsFromDate;
    }

    public String getAdsToDate() {
        return adsToDate;
    }

    public void setAdsToDate(String adsToDate) {
        this.adsToDate = adsToDate;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
