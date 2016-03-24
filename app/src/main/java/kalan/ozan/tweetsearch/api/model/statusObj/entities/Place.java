package kalan.ozan.tweetsearch.api.model.statusObj.entities;


import com.google.gson.annotations.SerializedName;

public class Place {

    public String id;
    public String url;
    public String name;
    public String country;

    @SerializedName("place_type")
    public String placeType;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("country_code")
    public String countryCode;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPlaceType() {
        return placeType;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
