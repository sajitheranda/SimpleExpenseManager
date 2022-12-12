package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class Account_helper {

    private static final String DATABASE_NAME = "200160R";
    private static final String ACCOUNT_TABLE = "account";
    private static final String TRANSACTION_TABLE = "transction";
    private static final int VERSION = 2;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    ////////////////////////////////////////////////////////////
    public final static String account_no = "account_no";
    public final static String column_bank = "bank";
    public final static String column_holder = "holder";
    public final static String column_balance = "balance";



    /////////////////////////////////////
    private DBHelper dbhelper;

    public  Account_helper(DBHelper dbhelper){
        this.dbhelper=dbhelper;
    }


    public void addAccount(Account account) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();//this is returns the database

        ContentValues values = new ContentValues();
        values.put(account_no, account.getAccountNo());
        values.put(column_bank, account.getBankName());
        values.put(column_holder, account.getAccountHolderName());
        values.put(column_balance, account.getBalance());

        db.insert(ACCOUNT_TABLE, null, values);
        db.close();//after using it we close that database
    }

    public Account getAccount(String accountNumber) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String getAccountQuery = "SELECT * FROM "+ACCOUNT_TABLE+" WHERE accountNumber = " + accountNumber;
        Cursor cursor = db.rawQuery(getAccountQuery, null);
        Account account =null;
        if (cursor != null) {//if there is no account such as the given number then cursor will be null
            account = new Account(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3));
        }
        db.close();
        return account;
    }

    public List<Account> getAccountsList() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        List<Account> accounts = new ArrayList<>();
        String getAccountQuery = "SELECT * FROM "+ACCOUNT_TABLE;

        Cursor cursor = db.rawQuery(getAccountQuery, null);

        if (((Cursor) cursor).moveToFirst()) {
            do {
                Account account = new Account(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3));
                accounts.add(account);
            } while (cursor.moveToNext());
        }
        db.close();
        return accounts;
    }

    public void updateValues(Account account) {
        SQLiteDatabase db= dbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(account_no, account.getAccountNo());
        values.put(column_bank, account.getBankName());
        values.put(column_holder, account.getAccountHolderName());
        values.put(column_balance, account.getBalance());
        db.update(ACCOUNT_TABLE, values, account_no+" = ?",new String[]{account.getAccountNo()});
        db.close();
    }

    public void removeAccount(String account_number) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(ACCOUNT_TABLE, account_no+" = ?", new String[]{account_number});
        db.close();
    }









}