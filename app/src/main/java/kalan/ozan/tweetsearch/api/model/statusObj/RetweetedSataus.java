package kalan.ozan.tweetsearch.api.model.statusObj;

import com.google.gson.annotations.SerializedName;
import kalan.ozan.tweetsearch.api.model.statusObj.entities.Entities;
import kalan.ozan.tweetsearch.api.model.statusObj.user.RetweetUser;

public class RetweetedSataus {


    @SerializedName("created_at")
    public String timeStamp;

    public String id;

    @SerializedName("id_str")
    public String idStr;

    @SerializedName("text")
    public String tweetText;

    public boolean truncated;

    @SerializedName("metadata")
    StatusMetaData statusMetaData;

    public String source;

    @SerializedName("in_reply_to_status_id")
    public String replyStatusIdString;

    @SerializedName("in_reply_to_status_id_str")
    public String replyStatusIdStr;

    @SerializedName("in_reply_to_user_id")
    public String replyUserIdString;

    @SerializedName("in_reply_to_user_id_str")
    public String replyUserIdStr;

    @SerializedName("in_reply_to_screen_name")
    public String replyScrnName;

    @SerializedName("user")
    public RetweetUser retweetUser;

    public Object geo;
    public Object coordinates;
    public Object place;
    public Object contributors;

    @SerializedName("is_quote_status")
    public boolean isQuoteStatus;

    @SerializedName("retweet_count")
    public String retweets;

    @SerializedName("favorite_count")
    public String favCount;

    public Entities entitites;

    public boolean favorited;
    public boolean retweeted;

    @SerializedName("possibly_sensitive")
    public boolean possiblySens;

    public String lang;


    public String getReplyScrnName() {
        return replyScrnName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getTweetText() {
        return tweetText;
    }

    public RetweetUser getTweetUser() {
        return retweetUser;
    }

    public Entities getEntitites() {
        return entitites;
    }

    public Object getGeo() {
        return geo;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public Object getPlace() {
        return place;
    }

    public String getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public StatusMetaData getStatusMetaData() {
        return statusMetaData;
    }

    public String getSource() {
        return source;
    }

    public String getReplyStatusIdString() {
        return replyStatusIdString;
    }

    public String getReplyStatusIdStr() {
        return replyStatusIdStr;
    }

    public String getReplyUserIdString() {
        return replyUserIdString;
    }

    public String getReplyUserIdStr() {
        return replyUserIdStr;
    }

    public Object getContributors() {
        return contributors;
    }

    public boolean isQuoteStatus() {
        return isQuoteStatus;
    }

    public String getRetweets() {
        return retweets;
    }

    public String getFavCount() {
        return favCount;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public boolean isPossiblySens() {
        return possiblySens;
    }

    public String getLang() {
        return lang;
    }
}
