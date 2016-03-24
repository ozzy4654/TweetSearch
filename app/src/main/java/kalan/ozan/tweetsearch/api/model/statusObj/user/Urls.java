package kalan.ozan.tweetsearch.api.model.statusObj.user;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Urls {

    public String url;

    @SerializedName("expanded_url")
    public String expandedUrl;

    @SerializedName("display_url")
    public String displyUrl;

    List<Integer> indices;

    public String getUrl() {
        return url;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public String getDisplyUrl() {
        return displyUrl;
    }

    public List<Integer> getIndices() {
        return indices;
    }

}
