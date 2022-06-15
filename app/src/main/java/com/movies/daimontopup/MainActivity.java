package com.movies.daimontopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView clear,tx138,tx288,tx426,tx576,tx732,tx870,tx1020,tx1488,tx3036,tx6072,tx9108,tx12144,txWeekly,txMonthly,TvtextView,productCount,total;
    Button byunow,history_main;
    LinearLayout layout;
    String daimond,totaltaka;
    EditText playerId;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    PackCalss packCalss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear = findViewById(R.id.clear);
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
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("id", "");
        if (s1!=null){
            history_main.setVisibility(View.VISIBLE);


        }



        List<PackCalss>list=new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pack");




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
                progressDialog.dismiss();




            }
        });


        tx288.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void hisab(String main, String bonas, String daimon, String taka) {


        TvtextView.setText("Top Up " +main+" Daimond + Bonus"+bonas+" Daimond \n ৳:"+taka);
        productCount.setText("Total:"+daimon+ " Daimond \n");
        byunow.setClickable(true);
        layout.setVisibility(View.VISIBLE);
        total.setText(taka+" BDT");




    }

    private void dailog(String massage){

        progressDialog.setMessage(massage);
        progressDialog.show();


    }


}