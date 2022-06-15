package com.movies.daimontopup.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MY_SQliteHelper extends SQLiteOpenHelper {
    private Context context;

    public static final String DATABASE_NAME="Order";
    public static final String TABLE_NAME="OrderHistory";
    public static final String ID="_id";
    public static final String NAME="name";
    public static final String PHONE="Phone";
    public static final String PLAYERID="PlayerId";
    public static final String TRANSTIONID="Trasnagtionid";
    public static final String BANK="Bank";
    public static final String ACCOUNT="Account";
    public static final int Verstion=1;
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+PHONE+" INTEGER ,"+ PLAYERID+" VARCHAR(15),"+TRANSTIONID+" VARCHAR(15),"+BANK+" VARCHAR(14),"+ACCOUNT+" VARCHAR(14));";
    public static final String SELECT_ALL= "SELECT * FROM " +TABLE_NAME;



    public MY_SQliteHelper(Context context) {
        super(context, DATABASE_NAME, null, Verstion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       try {
           sqLiteDatabase.execSQL(CREATE_TABLE);
         //  Toast.makeText(context, "Data Base Create Sucessfull", Toast.LENGTH_SHORT).show();


       }catch (Exception e){
         //  Toast.makeText(context, "Faild To Create Data Base", Toast.LENGTH_SHORT).show();

       }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);

            onCreate(sqLiteDatabase);
          //  Toast.makeText(context, "Calling Data Base agine ", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Problem Found", Toast.LENGTH_SHORT).show();
        }







    }

    public long  instartData(String full_name,String phone,String playerid,String trasngationid,String bank,String account){
      SQLiteDatabase sqLiteDatabase=  this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,full_name);
        contentValues.put(PHONE,phone);
        contentValues.put(PLAYERID,playerid);
        contentValues.put(TRANSTIONID,trasngationid);
        contentValues.put(BANK,bank);
        contentValues.put(ACCOUNT,account);
        long row=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return row;

    }


    public Cursor  displayalldata(){

        SQLiteDatabase sqLiteDatabase=  this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }


    public   boolean UpdateData(String full_name,String phone,String playerid,String trasngationid,String bank,String account,String id) {

        SQLiteDatabase sqLiteDatabase=  this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,full_name);
        contentValues.put(PHONE,phone);
        contentValues.put(PLAYERID,playerid);
        contentValues.put(TRANSTIONID,trasngationid);
        contentValues.put(BANK,bank);
        contentValues.put(ACCOUNT,account);

        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{id});
        return true;


    }


   public int deletedata(String id){
        SQLiteDatabase sqLiteDatabase=  this.getWritableDatabase();

       return  sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{id});

    }
}
