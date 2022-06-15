package com.movies.daimontopup;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.movies.daimontopup.sqlite.History;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OrderRecive extends AppCompatActivity {
    TextView ordern_number,orderdate,ordertTotal,mehord,mr,countdown;
    String name,taka,methord,payedrid,orderid,date;
    private Button history,download;
    int pageHeight = 1120;
    int pagewidth = 792;
    Bitmap bmp, scaledbmp;
    private static final int PERMISSION_REQUEST_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_recive);
        ordern_number=findViewById(R.id.orde_number);
        orderdate=findViewById(R.id.orderDate);
        ordertTotal=findViewById(R.id.orderTotal);
        mehord=findViewById(R.id.ordermethord);
        countdown=findViewById(R.id.countdown);



        mr=findViewById(R.id.mr);
        history=findViewById(R.id.history);
        name=getIntent().getStringExtra("name");
        taka=getIntent().getStringExtra("taka");
       methord=getIntent().getStringExtra("methord");
       payedrid=getIntent().getStringExtra("playerid");
       orderid=getIntent().getStringExtra("orderid");
       date=getIntent().getStringExtra("date");
       orderdate.setText(": "+date);
       bmp=BitmapFactory.decodeResource(getResources(),R.drawable.icons);
       scaledbmp=Bitmap.createScaledBitmap(bmp,1200,518,false);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("id", orderid);
        myEdit.commit();




       ordertTotal.setText(": "+taka+" ৳ ");
       ordern_number.setText(": "+orderid);
       mehord.setText(": "+methord);

       mr.setText(name+" Thank You, Your Order has been recived ");


        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdown.setText("Your Daimond Will Recive: " + millisUntilFinished / 1000+" s");
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                countdown.setText("done!");
            }

        }.start();
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderRecive.this, History.class);
                startActivity(intent);

            }
        });




        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkPermission()) {
                    generatePDF();

                } else {
                    requestPermission();
                }

            }
        });


    }









    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }





    private void generatePDF() {
        Toast.makeText(this, "Stroage Full ", Toast.LENGTH_SHORT).show();
    }



}