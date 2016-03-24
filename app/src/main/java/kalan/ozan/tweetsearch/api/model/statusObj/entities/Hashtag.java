package kalan.ozan.tweetsearch.api.model.statusObj.entities;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Hashtag {

    @SerializedName("text")
    public String text;

    @SerializedName("indices")
    public List<Integer> indices;

    public List<Integer> getIndices() {
        return indices;
    }

    public String getText() {
        return text;
    }
}
