package kalan.ozan.tweetsearch.api.model.statusObj.entities;


import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UrlEntities {

    public String url;
    public List<String> indices;

    @SerializedName("expanded_url")
    public String expandedUrl;

    @SerializedName("display_url")
    public String displyUrl;

    public String getUrl() {
        return url;
    }

    public List<String> getIndices() {
        return indices;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public String getDisplyUrl() {
        return displyUrl;
    }
}
