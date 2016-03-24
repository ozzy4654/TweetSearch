package kalan.ozan.tweetsearch.api.model.statusObj.user;


import com.google.gson.annotations.SerializedName;

public class RetweetUser {


    public String id;

    @SerializedName("id_str")
    public String idStr;

    public String name;

    @SerializedName("screen_name")
    public String screenName;

    public String location;
    public String description;
    public String url;

    @SerializedName("entities")
    public UserEntities userEntities;

    @SerializedName("protected")
    public boolean protect;

    @SerializedName("followers_count")
    public String followerCount;

    @SerializedName("friends_count")
    public String friendCount;

    @SerializedName("listed_count")
    public String listedCount;

    @SerializedName("created_at")
    public String profileInceptionDate;

    @SerializedName("favourites_count")
    public String favsCount;

    @SerializedName("utc_offset")
    public String utcOffset;

    @SerializedName("time_zone")
    public String timeZone;

    @SerializedName("geo_enabled")
    public boolean geoEnabled;

    public boolean verified;

    @SerializedName("statuses_count")
    public String statusCount;

    public String lang;

    @SerializedName("contributors_enabled")
    public boolean contribEnabled;

    @SerializedName("is_translator")
    public boolean isTrans;

    @SerializedName("is_translation_enabled")
    public boolean isTransEnabled;

    @SerializedName("profile_background_color")
    public String profileBckColor;

    @SerializedName("profile_background_image_url")
    public String backgroundImgUrl;

    @SerializedName("profile_background_image_url_https")
    public String backgrounndImgHttpsUrl;

    @SerializedName("profile_background_tile")
    public boolean profileBckTile;

    @SerializedName("profile_image_url")
    public String profileImageUrl;

    @SerializedName("profile_image_url_https")
    public String profileImageHttpsUrl;

    @SerializedName("profile_link_color")
    public String profLinkColor;

    @SerializedName("profile_sidebar_border_color")
    public String sidebardColor;

    @SerializedName("profile_sidebar_fill_color")
    public String sidebarFillColor;

    @SerializedName("profile_text_color")
    public String textColor;

    @SerializedName("profile_use_background_image")
    public boolean useBckImg;

    @SerializedName("has_extended_profile")
    public boolean extendedProfile;

    @SerializedName("default_profile")
    public boolean defaultProfile;

    @SerializedName("default_profile_image")
    public boolean defaultProfileImg;

    public Object following;

    @SerializedName("follow_request_sent")
    public Object followRequestSent;

    public Object notifications;


    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getProfileImageHttpsUrl() {
        return profileImageHttpsUrl;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public UserEntities getUserEntities() {
        return userEntities;
    }

    public String getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public boolean isProtect() {
        return protect;
    }

    public String getFollowerCount() {
        return followerCount;
    }

    public String getFriendCount() {
        return friendCount;
    }

    public String getListedCount() {
        return listedCount;
    }

    public String getProfileInceptionDate() {
        return profileInceptionDate;
    }

    public String getFavsCount() {
        return favsCount;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public boolean isGeoEnabled() {
        return geoEnabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getStatusCount() {
        return statusCount;
    }

    public String getLang() {
        return lang;
    }

    public boolean isContribEnabled() {
        return contribEnabled;
    }

    public boolean isTrans() {
        return isTrans;
    }

    public boolean isTransEnabled() {
        return isTransEnabled;
    }

    public String getProfileBckColor() {
        return profileBckColor;
    }

    public String getBackgroundImgUrl() {
        return backgroundImgUrl;
    }

    public String getBackgrounndImgHttpsUrl() {
        return backgrounndImgHttpsUrl;
    }

    public boolean isProfileBckTile() {
        return profileBckTile;
    }

    public String getProfLinkColor() {
        return profLinkColor;
    }

    public String getSidebardColor() {
        return sidebardColor;
    }

    public String getSidebarFillColor() {
        return sidebarFillColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public boolean isUseBckImg() {
        return useBckImg;
    }

    public boolean isExtendedProfile() {
        return extendedProfile;
    }

    public boolean isDefaultProfile() {
        return defaultProfile;
    }

    public boolean isDefaultProfileImg() {
        return defaultProfileImg;
    }

    public Object getFollowing() {
        return following;
    }

    public Object getFollowRequestSent() {
        return followRequestSent;
    }

    public Object getNotifications() {
        return notifications;
    }
}
