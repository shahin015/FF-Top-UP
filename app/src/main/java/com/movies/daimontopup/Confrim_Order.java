package com.movies.daimontopup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.movies.daimontopup.notofaction.FcmNotificationsSender;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


//
//git remote add origin https://github.com/shahin015/FF-Top-UP.git
//        git branch -M main
//        git push -u origin main

public class Confrim_Order extends AppCompatActivity {
    TextView orderTxtvie, orderPlayerid, total, selecttype, youraccountNo, yourtransgation, payment;
    RadioButton bkash, nagad, roket, upay;
    String playerid, daimond, taka;
    EditText account_nummber, transcationId, fullname, phone_number;
    Button palceorder;

    DataClass dataClass;
    String bank = "bKash";
    String user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,df;
    ProgressDialog progressDialog;


    String account, trasngationid, full_name, phone, orderno, d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_order);
        orderPlayerid = findViewById(R.id.orderplayerid);
        orderTxtvie = findViewById(R.id.orderTExtview);
        total = findViewById(R.id.total);
        bkash = findViewById(R.id.bkash);
        nagad = findViewById(R.id.nagad);
        roket = findViewById(R.id.roket);
        upay = findViewById(R.id.upay);
        selecttype = findViewById(R.id.select_all);
        youraccountNo = findViewById(R.id.youraccountNumber);
        yourtransgation = findViewById(R.id.tranxjetionid);
        payment = findViewById(R.id.payment);
        account_nummber = findViewById(R.id.account_number);
        transcationId = findViewById(R.id.transcationId);
        fullname = findViewById(R.id.fullname);
        phone_number = findViewById(R.id.phoneNa);
        palceorder = findViewById(R.id.placeorde);

        dataClass = new DataClass();
        progressDialog = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("order");
        df = firebaseDatabase.getReference("adminkey");
        FirebaseMessaging.getInstance().subscribeToTopic("all");


        playerid = getIntent().getStringExtra("id");
        daimond = getIntent().getStringExtra("daimond");
        taka = getIntent().getStringExtra("taka");

        orderTxtvie.setText("Your Order IS :" + daimond + " Daimond");
        orderPlayerid.setText("Your Player ID IS : " + playerid);
        total.setText("You Have To pay : " + taka + " ৳");
        payment.setText("You need Send " + taka + " BDT");
        bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selecttype.setText(getString(R.string.biksh));
                nagad.setChecked(false);
                roket.setChecked(false);
                upay.setChecked(false);
                bank = "bkash";
                setEdittext(bank);


            }
        });
        nagad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttype.setText(getString(R.string.nagad));
                roket.setChecked(false);
                upay.setChecked(false);
                bkash.setChecked(false);
                bank = "Nagad";
                setEdittext(bank);

            }
        });
        roket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selecttype.setText(getString(R.string.roket));
                nagad.setChecked(false);

                upay.setChecked(false);
                bkash.setChecked(false);
                bank = "Rocket";
                setEdittext(bank);
            }
        });
        upay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttype.setText(getString(R.string.upay));

                nagad.setChecked(false);
                roket.setChecked(false);

                bkash.setChecked(false);
                bank = "Upay";
                setEdittext(bank);
            }
        });

        palceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidition();
            }
        });


    }

    private void checkValidition() {

        account = account_nummber.getText().toString().trim();
        trasngationid = transcationId.getText().toString().trim();
        full_name = fullname.getText().toString().trim();
        phone = phone_number.getText().toString().trim();

        if (account.isEmpty() || trasngationid.isEmpty() || full_name.isEmpty() || phone.isEmpty()) {

            Toast.makeText(this, "Fill all The filds", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Procssing Order");
        progressDialog.show();
        DatasendToFirebse(account, trasngationid, full_name, phone, bank, playerid);


    }

    private void DatasendToFirebse(String account, String trasngationid, String full_name, String phone, String bank, String playerid) {
        Date ct = Calendar.getInstance().getTime();
        orderno = String.valueOf(ct.getTime());
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calendar.getTime());
        Calendar caFortime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(caFortime.getTime());

        d = date + ":" + time;

        dataClass.setAccount_no(account);
        dataClass.setTransctionid(trasngationid);
        dataClass.setFullname(full_name);
        dataClass.setPhone(phone);
        dataClass.setBank(bank);
        dataClass.setPlayerid(playerid);
        dataClass.setOrder_no(orderno);
        dataClass.setDate(d);
        dataClass.setColorcode("f");

        databaseReference.child(orderno).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                 user="ePtIaHfERgGm2TS-M4gczP:APA91bHuo6dPLwh7Hxfha1LWdMG2oy17m-jq16YEAotg81eIRhidhGonYjpK8twbfmbsgeZAYNtr4LCun0GuYVgQwxYDtiKxNdxE-2DxfQZjnwtLURDA5paRqu_5HFr6XsKQQKx12znv";


                String titile="New Order From";
                String massage=full_name.toString();
                df.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       user =snapshot.getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                FcmNotificationsSender notificationsSender=new FcmNotificationsSender(user,titile,massage,getApplicationContext(),
                        Confrim_Order.this);
                notificationsSender.SendNotifications();
                progressDialog.dismiss();
                moveclass();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();

            }
        });


    }

    private void moveclass() {


        Intent intent = new Intent(Confrim_Order.this, OrderRecive.class);
        intent.putExtra("name", full_name);
        intent.putExtra("taka", taka);
        intent.putExtra("methord", bank);
        intent.putExtra("playerid", playerid);
        intent.putExtra("orderid", orderno);
        intent.putExtra("date", d);
        startActivity(intent);
        finish();

    }

    private void setEdittext(String bank) {

        youraccountNo.setText("Enter Your " + bank + " Account No");
        yourtransgation.setText("Enter Your " + bank + " Transaction ID");

    }

}