package com.movies.daimontopup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tx138,tx288,tx426,tx576,tx732,tx870,tx1020,tx1488,tx3036,tx6072,tx9108,tx12144,txWeekly,txMonthly,TvtextView,productCount,total;
    Button byunow;
    LinearLayout layout;
    String daimond,totaltaka;
    EditText playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx138=findViewById(R.id.tx138);
        tx288=findViewById(R.id.tx288);
        tx426=findViewById(R.id.tx426);
        tx576=findViewById(R.id.tx576);
        tx732=findViewById(R.id.tx732);
        tx870=findViewById(R.id.tx870);
        tx1020=findViewById(R.id.tx1020);
        tx1488=findViewById(R.id.tx1488);
        tx3036=findViewById(R.id.tx3036);
        tx6072=findViewById(R.id.tx6072);
        tx9108=findViewById(R.id.tx9108);
        tx12144=findViewById(R.id.tx12144);
        txWeekly=findViewById(R.id.txweekly);
        txMonthly=findViewById(R.id.txmonthly);
        TvtextView=findViewById(R.id.tvtextview);
        byunow=findViewById(R.id.button);
        layout=findViewById(R.id.layot);
        productCount=findViewById(R.id.producitcunt);
        total=findViewById(R.id.total);
        playerId=findViewById(R.id.playerid);
        byunow.setClickable(false);




        byunow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String payer=playerId.getText().toString().trim();
                if (payer.isEmpty()){
                    Toast.makeText(MainActivity.this, "select Daimond and Enter Your Game ID ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (daimond.isEmpty()){
                    Toast.makeText(MainActivity.this, "select Daimond ", Toast.LENGTH_SHORT).show();
                    tx138.requestFocus();
                    return;
                }

                Toast.makeText(MainActivity.this, daimond, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,Confrim_Order.class);
                intent.putExtra("id",payer);
                intent.putExtra("daimond",daimond);
                intent.putExtra("taka",totaltaka);
                startActivity(intent);

            }
        });


        tx138.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="90";
                String daimon="138";
                String bonas="23";
                String main="115";
                hisab(main,bonas,daimon,taka);
                daimond=daimon;
                totaltaka=taka;
            }
        });


        tx288.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="180";
                String daimon="288";
                String bonas="48";
                String main="240";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        tx426.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="270";
                String daimon="426";
                String bonas="355";
                String main="71";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        tx576.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="360";
                String daimon="576";
                String bonas="96";
                String main="480";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        tx732.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="450";
                String daimon="732";
                String bonas="122";
                String main="610";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });
        tx870.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="540";
                String daimon="870";
                String bonas="145";
                String main="725";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        tx1020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="630";
                String daimon="1020";
                String bonas="170";
                String main="850";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        tx1488.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="900";
                String daimon="1488";
                String bonas="248";
                String main="1240";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });


        tx3036.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="1800";
                String daimon="3036";
                String bonas="506";
                String main="2530";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        tx6072.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="3600";
                String daimon="6072";
                String bonas="1012";
                String main="5060";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        tx9108.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="5400";
                String daimon="9108";
                String bonas="1518";
                String main="7590";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });


        tx12144.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="7200";
                String daimon="12144";
                String bonas="2014";
                String main="9108";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });


        txMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="800";
                String daimon="3000";
                String bonas="100";
                String main="00";
                daimond=daimon;
                totaltaka=taka;
                hisab(main,bonas,daimon,taka);
            }
        });

        txWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taka="170";
                String daimon="320";
                String bonas="56";
                String main="0";
                totaltaka=taka;
                daimond=daimon;
                hisab(main,bonas,daimon,taka);
            }
        });



    }

    private void hisab(String main, String bonas, String daimon, String taka) {


        TvtextView.setText("Top Up " +main+" Daimond + Bonus"+bonas+" Daimond \n ৳:"+taka);
        productCount.setText("Total:"+daimon+ " Daimond \n");
        byunow.setClickable(true);
        layout.setVisibility(View.VISIBLE);
        total.setText(taka+" BDT");




    }


}