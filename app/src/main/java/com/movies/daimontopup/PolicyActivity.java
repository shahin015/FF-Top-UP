package com.movies.daimontopup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class PolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);

        SharedPreferences sharedPreferences = getSharedPreferences("Policy",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("Policy", "Policy");
        myEdit.commit();

        SharedPreferences ads = getSharedPreferences("ads", MODE_PRIVATE);

        String Ads=ads.getString("ads","");
        if (Ads.contains("on")){

        }



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}