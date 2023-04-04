package com.alatheer.noamany.Data.Remote.branches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("from_id")
    @Expose
    private String fromId;
    @SerializedName("lat_map")
    @Expose
    private String latMap;
    @SerializedName("long_map")
    @Expose
    private String longMap;

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

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getLatMap() {
        return latMap;
    }

    public void setLatMap(String latMap) {
        this.latMap = latMap;
    }

    public String getLongMap() {
        return longMap;
    }

    public void setLongMap(String longMap) {
        this.longMap = longMap;
    }
}
