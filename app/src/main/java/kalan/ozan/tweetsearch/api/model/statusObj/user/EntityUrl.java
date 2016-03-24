package kalan.ozan.tweetsearch.api.model.statusObj.user;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class EntityUrl {
    @SerializedName("urls")
    List<Urls> urlUrls;

    public List<Urls> getUrlUrls() {
        return urlUrls;
    }
}
