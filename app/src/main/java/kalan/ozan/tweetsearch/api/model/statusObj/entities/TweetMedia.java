package kalan.ozan.tweetsearch.api.model.statusObj.entities;


import java.util.List;

import com.google.gson.annotations.SerializedName;
import kalan.ozan.tweetsearch.api.model.statusObj.entities.sizes.Sizes;

public class TweetMedia {

    public String id;

    @SerializedName("id_str")
    public String idStr;

    List<String> indices;

    @SerializedName("media_url")
    public String mediaUrl;

    @SerializedName("media_url_https")
    public String mediaUrlHttps;

    public String url;

    @SerializedName("display_url")
    public String displayUrl;

    @SerializedName("expanded_url")
    public String expandedUrl;

    public String type;

    public Sizes sizes;

    @SerializedName("source_status_id")
    public String statusId;

    @SerializedName("source_status_id_str")
    public String statusIdStr;

    @SerializedName("source_user_id")
    public String sourceUsrId;

    @SerializedName("source_user_id_str")
    public String sourceUsrIdStr;

    public String getStatusId() {
        return statusId;
    }

    public String getStatusIdStr() {
        return statusIdStr;
    }

    public String getSourceUsrId() {
        return sourceUsrId;
    }

    public String getSourceUsrIdStr() {
        return sourceUsrIdStr;
    }

    public Sizes getSizes() {
        return sizes;
    }

    public String getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public List<String> getIndices() {
        return indices;
    }

    public String getType() {
        return type;
    }

    public String getMediaUrlHttps() {
        return mediaUrlHttps;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public String getUrl() {
        return url;
    }
}
