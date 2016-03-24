package kalan.ozan.tweetsearch.api.model;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import kalan.ozan.tweetsearch.api.model.statusObj.StatusesObj;

public class MyData {

    @SerializedName("statuses")
    public ArrayList<StatusesObj> statuses;

    @SerializedName("search_metadata")
    public SearchMetaData searchMetaData;


    @Override
    public String toString() {

        ArrayList<String> mDataDetails = new ArrayList<>();
        mDataDetails.add(statuses.toString());

        mDataDetails.add(searchMetaData.toString());
        return mDataDetails.toString();
    }

    public ArrayList<StatusesObj> getStatuses() {
        return statuses;
    }

    public SearchMetaData getSearchMetaData() {
        return searchMetaData;
    }

}
