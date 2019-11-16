package com.smita.rameshgirigi.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.smita.rameshgirigi.ListAdapter;
import com.smita.rameshgirigi.R;
import com.smita.rameshgirigi.model.UserViewModel;
import com.smita.rameshgirigi.network.RetrofitHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rv_lists;
    private ListAdapter listAdapter;
    ArrayList<String> responseList = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_lists = findViewById(R.id.rv_lists);
        showProgressDialog("please wait...!");

        // handle network call by using retrofit
        RetrofitHandler.getInstance().getMessageList("story", 1).enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                dismissProgressDialog();
                if (response.isSuccessful()) {
                    JSONObject jsonRootObject = new JSONObject();
                    JSONArray jsonArray = jsonRootObject.optJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject finalObject = jsonArray.getJSONObject(i);
                            String title = finalObject.getString("title");
                            String createdAt = finalObject.getString("created_at");
                            //Use the title and createdAt as per our requirement
                            responseList.add(String.valueOf(new UserViewModel(title, createdAt)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        rv_lists.setAdapter(listAdapter);
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dismissProgressDialog();
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}