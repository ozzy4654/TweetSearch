package kalan.ozan.tweetsearch.api.model.statusObj.user;


import com.google.gson.annotations.SerializedName;

public class UserEntities {


    @SerializedName("url")
    public EntityUrl entityUrl;

    @SerializedName("description")
    public EntityDesc entityDesc;

    public EntityUrl getEntityUrl() {
        return entityUrl;
    }

    public EntityDesc getEntityDesc() {
        return entityDesc;
    }


}
