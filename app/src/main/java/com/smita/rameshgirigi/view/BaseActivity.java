package com.smita.rameshgirigi.view;

import android.app.ProgressDialog;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    private ProgressDialog dialog = null;

    public void dismissProgressDialog() {
        try {
            if ((dialog != null) && (dialog.isShowing())) dialog.dismiss();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void showProgressDialog(String message) {
        if (dialog == null) dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        dialog.setCancelable(true);
        try {
            if (!dialog.isShowing()) dialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}