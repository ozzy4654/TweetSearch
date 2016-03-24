package kalan.ozan.tweetsearch.api.model.statusObj;

import com.google.gson.annotations.SerializedName;

public class StatusMetaData {

    @SerializedName("iso_language_code")
    public String langCode;
    @SerializedName("result_type")
    public String resultType;

    public String getLangCode() {
        return langCode;
    }

    public String getResultType() {
        return resultType;
    }
}
