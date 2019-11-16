package com.smita.rameshgirigi.network;

import com.smita.rameshgirigi.util.StringConverterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {
    private static RetrofitHandler ourInstance = new RetrofitHandler();

    public static RetrofitHandler getInstance() {
        return ourInstance;
    }

    private Retrofit ipApiService = new Retrofit.Builder()
            .baseUrl("https://hn.algolia.com")
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private OjasInterface service = ipApiService.create(OjasInterface.class);

    private RetrofitHandler() {
    }

    public Call<String> getMessageList(String tags, int page) {
        return service.getMessageList(tags, page);
    }
}
