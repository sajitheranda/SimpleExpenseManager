package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "200160R";// this is my index number
    private static final String ACCOUNT_TABLE = "account";
    private static final String TRANSACTION_TABLE = "transction";
    private static final int VERSION = 2;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    ////////////////////////////////////////////////////////////
    public final static String account_no = "account_no";
    public final static String column_bank = "bank";
    public final static String column_holder = "holder";
    public final static String column_balance = "balance";
    ////////////////////////////////////////////////////////////
    public final static String column_date = "date";
    public final static String column_type = "type";
    public final static String column_amount = "amount";
    ////////////////////////////////////////////////////////////

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        //SQLiteDatabase sqLiteDB = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createAccountTable = "CREATE TABLE "+ACCOUNT_TABLE+ " ("
                + account_no + " TEXT(40) PRIMARY KEY, "
                + column_bank + " TEXT(40), "
                + column_holder + " TEXT(40), "
                + column_balance + " REAL)";

        String createTransactionTable = "CREATE TABLE "+TRANSACTION_TABLE+ " ("
                + account_no + " TEXT(40), "
                + column_date + " TEXT(40), "
                + column_type + " TEXT(40), "
                + column_amount + " REAL, "+
                "FOREIGN KEY ("+account_no+") REFERENCES "+ACCOUNT_TABLE+"("+account_no+"))";
        db.execSQL(createAccountTable);
        db.execSQL(createTransactionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldver, int newver) {
            String dropAccountTable = "DROP TABLE IF EXISTS " + ACCOUNT_TABLE;
            String dropTransactionTable = "DROP TABLE IF EXISTS " + TRANSACTION_TABLE;
            db.execSQL(dropAccountTable);
            db.execSQL(dropTransactionTable);
            onCreate(db);
    }

}
