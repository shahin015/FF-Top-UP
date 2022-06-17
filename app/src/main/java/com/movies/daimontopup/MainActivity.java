package com.movies.daimontopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.movies.daimontopup.sqlite.History;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView clear,tx138,tx288,tx426,tx576,tx732,
            tx870,tx1020,tx1488,tx3036,tx6072,tx9108,
            tx12144,txWeekly,txMonthly,TvtextView,
            productCount,total,lineOne;
    Button byunow,history_main;
    LinearLayout layout;
    String daimond,totaltaka;
    EditText playerId;

    private AdView mAdView;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseRe,databaseRemote;
    ProgressDialog progressDialog;
    PackCalss packCalss;
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear = findViewById(R.id.clear);
        mAdView = findViewById(R.id.adView);
        lineOne=findViewById(R.id.lineOne);
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
        history_main=findViewById(R.id.history_main);
        progressDialog=new ProgressDialog(this);
        packCalss=new PackCalss();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pack");
        // below line is used to get reference for our database.
        databaseRe = firebaseDatabase.getReference("LineData");
        databaseRemote = firebaseDatabase.getReference();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences Policy = getSharedPreferences("Policy", MODE_PRIVATE);
        p=Policy.getString("Policy","");

        String s1 = sh.getString("id", "");


        if (!isNetworkAvailable(getApplicationContext())){
            history_main.setVisibility(View.GONE);

            new AlertDialog.Builder(this).setTitle("NO Network ")
                    .setMessage("Connect Data or wifi")
                    .setCancelable(false)
                    .setPositiveButton("Try without Network", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!isNetworkAvailable(getApplicationContext())){

                                Toast.makeText(MainActivity.this, "For best setvice Pleace Connect Internet", Toast.LENGTH_SHORT).show();
                            }else {
                                dialogInterface.dismiss();
                                loadData();



                            }
                        }
                    }).setNegativeButton("Close app", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finishAndRemoveTask();
                        }
                    }).create().show();


        }else {
            loadData();

            databaseRemote.child("adsControl").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String valos=snapshot.getValue().toString();
                    if (valos.contains("on")){

                        loadAds();

                        SharedPreferences sharedPreferences = getSharedPreferences("ads",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("ads","on");
                        myEdit.commit();


                    }else {
                        SharedPreferences sharedPreferences = getSharedPreferences("ads",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("ads","off");
                        myEdit.commit();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            FirebaseMessaging.getInstance().subscribeToTopic("all");

            if (s1.contains("Data")){
                history_main.setVisibility(View.VISIBLE);
            }else {
                history_main.setVisibility(View.GONE);
            }

            if (p.isEmpty()) {


                new AlertDialog.Builder(this).
                        setMessage("Pleace Read Our Privicy policy")
                        .setPositiveButton("Read Now", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (p.isEmpty()){

                                    Intent intent=new Intent(MainActivity.this,PolicyActivity.class);
                                    startActivity(intent);

                                    // Toast.makeText(MainActivity.this, "Please Read Privicy policy", Toast.LENGTH_SHORT).show();
                                }else {dialogInterface.dismiss();}

                                Intent intent=new Intent(MainActivity.this,PolicyActivity.class);
                                startActivity(intent);


                            }
                        }).setCancelable(false).create().show();


            }

            List<PackCalss>list=new ArrayList<>();

        }
















        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        history_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, History.class);
                startActivity(intent);


            }
        });
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


                if (payer.length()>=5 && payer.length()<=12){
                    Intent intent=new Intent(MainActivity.this,Confrim_Order.class);
                    intent.putExtra("id",payer);
                    intent.putExtra("daimond",daimond);
                    intent.putExtra("taka",totaltaka);
                    startActivity(intent);

                }else {

                    Toast.makeText(MainActivity.this, "Enter Valid Game UID", Toast.LENGTH_SHORT).show();
                }

                //  Toast.makeText(MainActivity.this, daimond, Toast.LENGTH_SHORT).show();


            }
        });


        tx138.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx138.getText().toString();
                dailog(massage);


                databaseReference.child("pack 1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });






            }
        });


        tx288.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String massage=tx288.getText().toString();
                dailog(massage);

                databaseReference.child("pack 2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        tx426.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx426.getText().toString();
                dailog(massage);


                databaseReference.child("pack 3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        tx576.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String massage=tx576.getText().toString();
                dailog(massage);
                databaseReference.child("pack 4").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {




                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        tx732.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String massage=tx732.getText().toString();
                dailog(massage);

                databaseReference.child("pack 5").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });
        tx870.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx870.getText().toString();
                dailog(massage);

                databaseReference.child("pack 6").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        tx1020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx1020.getText().toString();
                dailog(massage);
                databaseReference.child("pack 7").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        tx1488.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx1488.getText().toString();
                dailog(massage);

                databaseReference.child("pack 8").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });


        tx3036.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx3036.getText().toString();
                dailog(massage);

                databaseReference.child("pack 9").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        tx6072.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx6072.getText().toString();
                dailog(massage);

                databaseReference.child("pack 10").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        tx9108.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String massage=tx9108.getText().toString();
                dailog(massage);
                databaseReference.child("pack 11").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });


        tx12144.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=tx12144.getText().toString();
                dailog(massage);
                databaseReference.child("pack 12").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });


        txMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=txMonthly.getText().toString();
                dailog(massage);

                databaseReference.child("pack 13").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });

        txWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String massage=txWeekly.getText().toString();
                dailog(massage);
                databaseReference.child("pack 14").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String taka=snapshot.child("taka").getValue().toString();
                        String daimon=snapshot.child("totaldaimond").getValue().toString();
                        String bonas=snapshot.child("bonas").getValue().toString();;
                        String main=snapshot.child("maindaimond").getValue().toString();
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                        String taka="90";
                        String daimon="138";
                        String bonas="23";
                        String main="115";
                        daimond=daimon;
                        totaltaka=taka;
                        hisab(main,bonas,daimon,taka);
                    }
                });
            }
        });



    }

    private void loadAds() {

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    private void hisab(String main, String bonas, String daimon, String taka) {


        TvtextView.setText("Top Up " +main+" Daimond + Bonus"+bonas+" Daimond \n ৳:"+taka);
        productCount.setText("Total:"+daimon+ " Daimond \n");
        byunow.setClickable(true);
        layout.setVisibility(View.VISIBLE);
        total.setText(taka+" BDT");
        progressDialog.dismiss();




    }

    private void loadData() {
        progressDialog.setMessage("Loading ");
        progressDialog.setCancelable(true);
        progressDialog.show();
        // Toast.makeText(this, "Data Load", Toast.LENGTH_SHORT).show();
        databaseRe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String lineone=snapshot.child("Line_one").getValue().toString();
                String l2=snapshot.child("Line_tow").getValue().toString();
                String l3=snapshot.child("Line_three").getValue().toString();
                String l4=snapshot.child("Line_four").getValue().toString();

                lineOne.setText(lineone);


                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();


            }
        });

    }

    private void dailog(String massage){

        progressDialog.setMessage(massage+" Loading");
        progressDialog.setCancelable(true);
        progressDialog.show();


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                finishAndRemoveTask();

                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

}