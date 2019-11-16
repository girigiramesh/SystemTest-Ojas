package com.smita.rameshgirigi.network;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OjasInterface {

    @GET("/api/v1/search_by_date?tags=story&page=1")
    Call<String> getMessageList(@Query("tags") String tags, @Query("page") int page);
}
