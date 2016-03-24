package kalan.ozan.tweetsearch.api.model.statusObj.entities;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UserMentions {
    @SerializedName("screen_name")
    public String scrnName;

    public String name;
    public String id;

    @SerializedName("id_str")
    public String idStr;

    List<String> indices;

    public String getScrnName() {
        return scrnName;
    }

    public String getName() {
        return name;
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
}
