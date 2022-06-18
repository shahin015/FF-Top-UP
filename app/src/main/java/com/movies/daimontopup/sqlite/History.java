package com.movies.daimontopup.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.movies.daimontopup.MainActivity;
import com.movies.daimontopup.R;

public class History extends AppCompatActivity {

    MY_SQliteHelper my_sQliteHelper;
    TextView view;
    private Button delete;

    private EditText delete_edit_text;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        view=findViewById(R.id.textview);
        delete=findViewById(R.id.delete);
        delete_edit_text=findViewById(R.id.edtext);
        mAdView = findViewById(R.id.adView);

        my_sQliteHelper=new MY_SQliteHelper(this);
        SQLiteDatabase sqLiteDatabase=my_sQliteHelper.getWritableDatabase();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ids=delete_edit_text.getText().toString();

                if (ids.isEmpty()){
                    Toast.makeText(History.this, "Select order Number you went To Delete", Toast.LENGTH_SHORT).show();
                    return;
                }else {


                int value=  my_sQliteHelper.deletedata(ids);
                if (value>0){
                    Toast.makeText(History.this, "Data Is delete", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(History.this, MainActivity.class);

                    startActivity(intent);
                    finish();
                }else Toast.makeText(History.this, "Data Is not delete", Toast.LENGTH_SHORT).show();}
            }

        });


        Cursor cursor= my_sQliteHelper.displayalldata();
        if (cursor.getColumnCount()==0){
            Toast.makeText(History.this, "No Data ", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences ads = getSharedPreferences("ads", MODE_PRIVATE);
        String Ads=ads.getString("ads","");
        if (Ads.contains("on")){


            loadAds();

        }


        StringBuffer stringBuffer=new StringBuffer();
        while (cursor.moveToNext()){
            stringBuffer.append("Order No:"+cursor.getString(0)+"\n");
            stringBuffer.append("Name:"+cursor.getString(1)+"\n");
            stringBuffer.append("Phone :"+cursor.getString(2)+"\n");
            stringBuffer.append("Player ID :"+cursor.getString(3)+"\n");
            stringBuffer.append("Trx Id:"+cursor.getString(4)+"\n");
            stringBuffer.append("Bank:"+cursor.getString(5)+"\n");
            stringBuffer.append("Account No:"+cursor.getString(6)+"\n \n");

        }
        shwData("Order History",stringBuffer.toString());








    }



    private void shwData(String resultSet, String toString) {
        view.setText(resultSet+"\n \n"+toString);

    }


    private void loadAds() {

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    @Override
    public void onBackPressed() {
        mAdView.destroy();
        super.onBackPressed();
    }
}



