package com.movies.daimontopup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderRecive extends AppCompatActivity {
    TextView ordern_number,orderdate,ordertTotal,mehord,mr;
    String name,taka,methord,payedrid,orderid,date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_recive);
        ordern_number=findViewById(R.id.orde_number);
        orderdate=findViewById(R.id.orderDate);
        ordertTotal=findViewById(R.id.orderTotal);
        mehord=findViewById(R.id.ordermethord);
        mr=findViewById(R.id.mr);
        name=getIntent().getStringExtra("name");
        taka=getIntent().getStringExtra("taka");
       methord=getIntent().getStringExtra("methord");
       payedrid=getIntent().getStringExtra("playerid");
       orderid=getIntent().getStringExtra("orderid");
       date=getIntent().getStringExtra("date");
       orderdate.setText("Date: "+date);

       ordertTotal.setText(taka+" ৳ ");
       ordern_number.setText(orderid);
       mehord.setText(methord);

       mr.setText(name+" Thank You, Your Order has been recived ");
    }
}