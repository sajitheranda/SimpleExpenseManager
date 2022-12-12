package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class Transaction_helper  {
    private static final String TRANSACTION_TABLE = "transction";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public final static String account_no = "account_no";
    public final static String column_date = "date";
    public final static String column_type = "type";
    public final static String column_amount = "amount";

    private DBHelper dbhelper;

    public  Transaction_helper(DBHelper dbhelper){
        this.dbhelper=dbhelper;
    }

    public void logTransac(Date date, String accountNo, ExpenseType expenseType, double amount) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(account_no, accountNo);
        values.put(column_date , format.format(date));//to format the date type
        values.put(column_type, String.valueOf(expenseType));
        values.put(column_amount, amount);
        db.insert(TRANSACTION_TABLE,null,values);
        db.close();
    }


    public List<Transaction> getAllTransac() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        List<Transaction> transactionsList = new ArrayList<>();
        String getTransactionsQuery = "SELECT * FROM "+TRANSACTION_TABLE;

        Cursor cursor = db.rawQuery(getTransactionsQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Transaction transaction = new Transaction(format.parse(cursor.getString(1)),
                            cursor.getString(0),
                            ExpenseType.valueOf(cursor.getString(2)),
                            cursor.getDouble(3));
                    transactionsList.add(transaction);

                }while (cursor.moveToNext());
            }
        }catch (ParseException e){}
        db.close();
        return transactionsList;
    }

    public List<Transaction> getPaginatedTransac(int limit) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        List<Transaction> transactionsList = new ArrayList<>();
        String getTransactionsQuery = "SELECT * FROM "+TRANSACTION_TABLE+" LIMIT "+limit;

        Cursor cursor = db.rawQuery(getTransactionsQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Transaction transaction = new Transaction(format.parse(cursor.getString(1)),
                            cursor.getString(0),
                            ExpenseType.valueOf(cursor.getString(2)),
                            cursor.getDouble(3));
                    transactionsList.add(transaction);
                }while (cursor.moveToNext());
            }
        }catch (ParseException e){}
        db.close();
        return transactionsList;
    }




}