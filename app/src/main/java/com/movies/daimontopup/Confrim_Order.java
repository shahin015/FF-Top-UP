package com.movies.daimontopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Confrim_Order extends AppCompatActivity {
    TextView orderTxtvie,orderPlayerid,total,selecttype,youraccountNo,yourtransgation,payment;
    RadioButton bkash,nagad,roket,upay;
    String bank,playerid,daimond,taka;
    EditText account_nummber,transcationId,fullname,phone_number;
    Button palceorder;

    DataClass dataClass;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,dbref;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_order);
        orderPlayerid=findViewById(R.id.orderplayerid);
        orderTxtvie=findViewById(R.id.orderTExtview);
        total=findViewById(R.id.total);
        bkash=findViewById(R.id.bkash);
        nagad=findViewById(R.id.nagad);
        roket=findViewById(R.id.roket);
        upay=findViewById(R.id.upay);
        selecttype=findViewById(R.id.select_all);
        youraccountNo=findViewById(R.id.youraccountNumber);
        yourtransgation=findViewById(R.id.tranxjetionid);
        payment=findViewById(R.id.payment);
        account_nummber=findViewById(R.id.account_number);
        transcationId=findViewById(R.id.transcationId);
        fullname=findViewById(R.id.fullname);
        phone_number=findViewById(R.id.phoneNa);
        palceorder=findViewById(R.id.placeorde);

        dataClass=new DataClass();
        progressDialog=new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("order");


         playerid=getIntent().getStringExtra("id");
        daimond=getIntent().getStringExtra("daimond");
        taka=getIntent().getStringExtra("taka");
        //Toast.makeText(this, playerid+daimond, Toast.LENGTH_SHORT).show();
        orderTxtvie.setText("Your Order IS :"+daimond +" Daimond");
        orderPlayerid.setText("Your Player ID IS : "+playerid);
        total.setText("You Have To pay : "+taka+" ৳");
        payment.setText("You need Send "+taka+" BDT");
        bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttype.setText(getString(R.string.biksh));
                nagad.setChecked(false);
                roket.setChecked(false);
                upay.setChecked(false);
                bank="bkash";
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
                bank="Nagad";
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
                bank="Rocket";
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
                bank="Upay";
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

        String account=account_nummber.getText().toString().trim();
        String trasngationid=transcationId.getText().toString().trim();
        String full_name=fullname.getText().toString().trim();
        String phone=phone_number.getText().toString().trim();

        if (account.isEmpty()||trasngationid.isEmpty()||full_name.isEmpty()||phone.isEmpty()){

            Toast.makeText(this, "Fill all The filds", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Procssing Order");
        progressDialog.show();
        DatasendToFirebse(account,trasngationid,full_name,phone,bank,playerid);




    }

    private void DatasendToFirebse(String account, String trasngationid, String full_name, String phone, String bank, String playerid) {





        Date ct = Calendar.getInstance().getTime();
        String orderno= String.valueOf(ct.getTime());


        Calendar calendar=Calendar.getInstance();
        String times= String.valueOf(calendar.getTime());
        SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yy");
        String date=currentDate.format(calendar.getTime());
        Calendar caFortime=Calendar.getInstance();
        SimpleDateFormat currentTime=new SimpleDateFormat("hh:mm a");

        String time=currentTime.format(caFortime.getTime());
        dataClass.setAccount_no(account);
        dataClass.setTransctionid(trasngationid);
        dataClass.setFullname(full_name);
        dataClass.setPhone(phone);
        dataClass.setBank(bank);
        dataClass.setPlayerid(playerid);
        dataClass.setOrder_no(time);

        databaseReference.child(orderno).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss();

                Intent intent=new Intent(Confrim_Order.this,OrderRecive.class);
                intent.putExtra("name",full_name);
                intent.putExtra("taka",taka);
                intent.putExtra("methord",bank);
                intent.putExtra("playerid",playerid);
                intent.putExtra("orderid",orderno);
                intent.putExtra("date",date+":"+time);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        finish();





    }

    private void setEdittext(String bank) {

        youraccountNo.setText("Enter Your "+bank+" Account No");
        yourtransgation.setText("Enter Your "+ bank+" Transaction ID");

    }

}