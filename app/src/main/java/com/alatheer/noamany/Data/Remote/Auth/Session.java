package com.alatheer.noamany.Data.Remote.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {
    @SerializedName("aes256Key")
    @Expose
    private String aes256Key;
    @SerializedName("authenticationLimit")
    @Expose
    private Integer authenticationLimit;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("updateStatus")
    @Expose
    private String updateStatus;
    @SerializedName("version")
    @Expose
    private String version;

    public String getAes256Key() {
        return aes256Key;
    }

    public void setAes256Key(String aes256Key) {
        this.aes256Key = aes256Key;
    }

    public Integer getAuthenticationLimit() {
        return authenticationLimit;
    }

    public void setAuthenticationLimit(Integer authenticationLimit) {
        this.authenticationLimit = authenticationLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
