package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;


import android.content.Context;
import android.database.Cursor;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.Transaction_helper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentTransactionDAO  implements TransactionDAO {
    private final List<Transaction> transactions;
    private Transaction_helper tranhelp;

    public PersistentTransactionDAO(Context context) {
        //tranhelp=new Transaction_helper(context);
        transactions = new LinkedList<>();
        //getalldata();


    }

    public void getalldata(){
        Cursor cursor = tranhelp.readAllData();
        //////////int day, int month, int year

        while (cursor.moveToNext()){
            Calendar calendar = Calendar.getInstance();
            int day=cursor.getInt(1);
            int month=cursor.getInt(2);
            int year=cursor.getInt(3);
            calendar.set(year, month, day);


            //Date date =
            String accountNo = cursor.getString(0);
            Date date=calendar.getTime();
            ExpenseType expenseType = ExpenseType.valueOf(cursor.getString(4).toUpperCase());
            double amount = cursor.getDouble(5);

            // Display the values from each column of the current row in the cursor in the TextView
            Transaction transaction = new Transaction(date, accountNo, expenseType, amount);
            transactions.add(transaction);
        }
        ExpenseType exp;


    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        Transaction transaction = new Transaction(date, accountNo, expenseType, amount);
        transactions.add(transaction);
        //tranhelp.addTransferData(transaction);

    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        return transactions;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        int size = transactions.size();
        if (size <= limit) {
            return transactions;
        }
        // return the last <code>limit</code> number of transaction logs
        return transactions.subList(size - limit, size);
    }
}
