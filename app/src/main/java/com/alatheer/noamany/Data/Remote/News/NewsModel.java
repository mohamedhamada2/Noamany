package com.alatheer.noamany.Data.Remote.News;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsModel implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("news_name")
    @Expose
    private String newsName;
    @SerializedName("news_date")
    @Expose
    private String newsDate;
    @SerializedName("news_details")
    @Expose
    private String newsDetails;
    @SerializedName("news_video")
    @Expose
    private String newsVideo;
    @SerializedName("news_image")
    @Expose
    private String newsImage;
    @SerializedName("approve")
    @Expose
    private String approve;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publisher_name")
    @Expose
    private String publisherName;
    @SerializedName("date_ar")
    @Expose
    private String dateAr;
    @SerializedName("date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsDetails() {
        return newsDetails;
    }

    public void setNewsDetails(String newsDetails) {
        this.newsDetails = newsDetails;
    }

    public String getNewsVideo() {
        return newsVideo;
    }

    public void setNewsVideo(String newsVideo) {
        this.newsVideo = newsVideo;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDateAr() {
        return dateAr;
    }

    public void setDateAr(String dateAr) {
        this.dateAr = dateAr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
