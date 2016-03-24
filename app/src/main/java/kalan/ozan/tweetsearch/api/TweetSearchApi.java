package kalan.ozan.tweetsearch.api;


import kalan.ozan.tweetsearch.api.model.MyData;
//import retrofit.Callback;
//import retrofit.http.*;
import retrofit2.*;
import retrofit2.http.*;

public interface TweetSearchApi {

    @Headers("Authorization: Bearer AAAAAAAAAAAAAAAAAAAAAO8gVwAAAAAAznRZHDqt6%2BqK1RXG%2Bo7kTaf8WJ8%3DcSIDn8O44zxU2DoWv9MCDliX5ConzhinKrhD5k5u23oSl8DNVY")
    @GET("1.1/search/tweets.json?result_type=recent&count=5")
    Call<MyData> statusList (
            @Query("q") String query,
            @Query("geocode") String geoLocation);

    @Headers("Authorization: Bearer AAAAAAAAAAAAAAAAAAAAAO8gVwAAAAAAznRZHDqt6%2BqK1RXG%2Bo7kTaf8WJ8%3DcSIDn8O44zxU2DoWv9MCDliX5ConzhinKrhD5k5u23oSl8DNVY")
    @GET("1.1/search/tweets.json?result_type=recent&count=5")
    Call<MyData> getMoreData (
            @Query("q") String query,
            @Query("geocode") String geoLocation,
            @Query("max_id") String maxId);

}
