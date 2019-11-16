package com.smita.rameshgirigi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ListResponse {
    @SerializedName("response")
    @Expose
    private List<Response> response = new ArrayList<Response>();

    public ListResponse(List<Response> response) {
        this.response = response;
    }

    /**
     * @return The response
     */
    public List<Response> getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(List<Response> response) {
        this.response = response;
    }
}
