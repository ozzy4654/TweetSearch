package kalan.ozan.tweetsearch.api.model.statusObj.entities;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Entities {

    @SerializedName("hashtags")
    List<Hashtag> hashtags = new ArrayList<>();

    @SerializedName("symbols")
    List<String> symbols = new ArrayList<>();

    @SerializedName("urls")
    List<UrlEntities> urls = new ArrayList<>();

    @SerializedName("user_mentions")
    List<UserMentions> userMentions;

    @SerializedName("media")
    List<TweetMedia> tweetMedia;


    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public List<UserMentions> getUserMentions() {
        return userMentions;
    }

    public List<UrlEntities> getUrls() {
        return urls;
    }

    public List<TweetMedia> getTweetMedia() {
        return tweetMedia;
    }
}
