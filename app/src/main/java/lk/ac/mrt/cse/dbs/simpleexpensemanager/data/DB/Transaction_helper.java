package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Date;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class Transaction_helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db200160R.db";
    private static final int DATABASE_VERSION = 1;//database version 1

    public final static String TABLE_NAME = "Transaction_table";

    /**Table Fields*/
    public final static String account_no = "account_no";
    public final static String COLUMN_day = "day";
    public final static String COLUMN_month = "month";
    public final static String COLUMN_year = "year";
    public final static String COLUMN_type = "type";
    public final static String COLUMN_AMOUNT = "amount";
    //public final static String COLUMN_STATUS = "status";



    public Transaction_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_TRANSACTION_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
                + account_no + " TEXT, "
                + COLUMN_day + " INTEGER, "
                + COLUMN_month + " INTEGER, "
                + COLUMN_year + " INTEGER, "
                + COLUMN_type + " TEXT, "
                + COLUMN_AMOUNT + " REAL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void addTransferData (Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        /////////int day, int month, int year
        Calendar calendar = Calendar.getInstance();
        Date transactionDate =transaction.getDate();


        contentValues.put(account_no, transaction.getAccountNo());
        contentValues.put(COLUMN_day, calendar.get(Calendar.DATE));
        contentValues.put(COLUMN_month, calendar.get(Calendar.MONTH));
        contentValues.put(COLUMN_year, calendar.get(Calendar.YEAR));
        contentValues.put(COLUMN_type, transaction.getExpenseType().toString());
        contentValues.put(COLUMN_AMOUNT, transaction.getAmount());
        db.insert(TABLE_NAME, null, contentValues);
        //db.close();


    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }


}