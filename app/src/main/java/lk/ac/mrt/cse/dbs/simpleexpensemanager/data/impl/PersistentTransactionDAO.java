package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;


import android.content.Context;
import android.database.Cursor;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.DBHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.Transaction_helper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentTransactionDAO implements TransactionDAO {


    private Transaction_helper transaction_helper;//transaction_helper



    public PersistentTransactionDAO(DBHelper dbHelper) {
        transaction_helper = new Transaction_helper(dbHelper);
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        transaction_helper.logTransac(date, accountNo, expenseType, amount);
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        return transaction_helper.getAllTransac();
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return transaction_helper.getPaginatedTransac(limit);
    }
}
