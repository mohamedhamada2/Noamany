package com.alatheer.noamany.Data.About;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutApp {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("site_name")
    @Expose
    private String siteName;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("date_ar")
    @Expose
    private String dateAr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateAr() {
        return dateAr;
    }

    public void setDateAr(String dateAr) {
        this.dateAr = dateAr;
    }
}