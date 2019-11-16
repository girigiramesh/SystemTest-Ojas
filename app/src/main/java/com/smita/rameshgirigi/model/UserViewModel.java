package com.smita.rameshgirigi.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Response;

public class UserViewModel {
    private static final String TAG = "UserViewModel";

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("created_at")
    @Expose
    public String created_at;

    public UserViewModel(String title, String createdAt) {
        this.title = title;
        this.created_at = createdAt;
    }

    private List<Response> responseDetailsList;

    public List<Response> getResponseDetailsList() {
        return responseDetailsList;
    }

    public void setResponseDetailsList(List<Response> responseDetailsList) {
        this.responseDetailsList = responseDetailsList;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedAat() {
        return created_at;
    }

    public JSONObject toJson() {
        String jsonRepresentation = new Gson().toJson(this, Response.class);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonRepresentation);
        } catch (JSONException e) {
            Log.e(TAG, "Error converting to JSON in toJson(): " + e.getMessage());
        }
        return jsonObject;
    }

    public static Response fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, Response.class);
    }
}
