package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class Account_helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db200160R";
    private static final int DATABASE_VERSION = 1;

    public final static String TABLE_NAME = "account_table";

    public final static String account_no = "account_no";
    public final static String column_bank = "bank";
    public final static String column_holder = "holder";
    public final static String column_balance = "amount";

    public Account_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
                + account_no + " TEXT, "
                + column_bank + " TEXT, "
                + column_holder + " TEXT, "
                + column_balance + " REAL)";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);

        // Insert Into Table


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void getters(){

    }

    public void readAllData() {
        /*
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=null;
        cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        Map<String, Account> accounts=new HashMap<>();
        */

        //return cursor;
    }

    public Cursor readParticularData (int accountNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME + " where " +
                account_no + " = " + accountNo, null);
        return cursor;
    }

    public void updateAmount(String accountNo, double amount) {
        Log.d ("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + TABLE_NAME + " set " + column_balance + " = " + amount + " where " +
                account_no + " = " + accountNo);
    }

    public void addaccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();//content values

        values.put(account_no, account.getAccountNo());
        values.put(column_bank, account.getBankName());
        values.put(column_holder, account.getAccountHolderName());
        values.put(column_balance, account.getBalance());

        db.insert(TABLE_NAME, null, values);


        //db.close();
    }


    public void deleteaccount(String Accountno){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "account_no=?", new String[]{Accountno});
        //db.close();
    }

}