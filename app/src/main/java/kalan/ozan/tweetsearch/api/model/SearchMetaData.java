package kalan.ozan.tweetsearch.api.model;


import com.google.gson.annotations.SerializedName;

public class SearchMetaData {
    @SerializedName("completed_in")
    public String searchTime;

    @SerializedName("max_id")
    public String maxIdString;

    @SerializedName("max_id_str")
    public String maxIdStr;

    @SerializedName("next_results")
    public String nxtResults;

    public String query;

    @SerializedName("refresh_url")
    public String refreashUrl;

    @SerializedName("count")
    public String    count;

    @SerializedName("since_id")
    public String sinceId;

    @SerializedName("since_id_str")
    public String sinceIdStr;


    public String getNxtResults() {
        return nxtResults;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public String getMaxIdString() {
        return maxIdString;
    }

    public String getMaxIdStr() {
        return maxIdStr;
    }

    public String getRefreashUrl() {
        return refreashUrl;
    }

    public String getSinceId() {
        return sinceId;
    }

    public String getSinceIdStr() {
        return sinceIdStr;
    }

    public String getQuery() {
        return query;
    }

    public String getCount() {
        return count;
    }

}
