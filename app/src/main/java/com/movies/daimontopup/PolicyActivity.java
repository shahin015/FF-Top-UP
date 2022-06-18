package com.movies.daimontopup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class PolicyActivity extends AppCompatActivity {

    private WebView webView;
    public String filename="privicy.html";
    private Button trams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        webView=findViewById(R.id.webview);
        trams=findViewById(R.id.trams);



        SharedPreferences sharedPreferences = getSharedPreferences("Policy",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("Policy", "Policy");
        myEdit.commit();

        SharedPreferences ads = getSharedPreferences("ads", MODE_PRIVATE);

        String Ads=ads.getString("ads","");
        if (Ads.contains("on")){

        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/"+filename);



        trams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trams.getTag().toString();
                if (trams.getTag().equals("policy")){
                    trams.setTag("trams");
                    webView.loadUrl("file:///android_asset/trams&condition.html");
                    trams.setText("Read:privicy Policy");
                }else {
                    webView.loadUrl("file:///android_asset/"+filename);
                    trams.setText("Read:trams and Conditions");
                    trams.setTag("policy");

                }

            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}