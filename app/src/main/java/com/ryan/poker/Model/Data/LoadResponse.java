package com.ryan.poker.Model.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadResponse {
    @SerializedName("Open")
    @Expose
    private String open;

    @SerializedName("OpenLink")
    @Expose
    private String openLink;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getOpenLink() {
        return openLink;
    }

    public void setOpenLink(String openLink) {
        this.openLink = openLink;
    }
}
