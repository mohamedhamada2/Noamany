package com.alatheer.noamany.Data.About;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class About {

    @SerializedName("about_app")
    @Expose
    private AboutApp aboutApp;
    @SerializedName("success")
    @Expose
    private Integer success;

    public AboutApp getAboutApp() {
        return aboutApp;
    }

    public void setAboutApp(AboutApp aboutApp) {
        this.aboutApp = aboutApp;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
