package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.DBHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class PersistentExpenseManager extends ExpenseManager{

    private Context context;

    public PersistentExpenseManager(Context context) {
        this.context = context;
        try {
            setup();
        } catch (ExpenseManagerException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void setup()  throws ExpenseManagerException {
        DBHelper dbhelper = new DBHelper(context);

        TransactionDAO persistentTransactionDAO = new PersistentTransactionDAO(dbhelper);
        setTransactionsDAO(persistentTransactionDAO);


        AccountDAO persistentAccountDAO = new PersistentAccountDAO(dbhelper);
        setAccountsDAO(persistentAccountDAO);

        // dummy data
        Account dummyAcct1 = new Account("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        Account dummyAcct2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
        getAccountsDAO().addAccount(dummyAcct1);
        getAccountsDAO().addAccount(dummyAcct2);
    }

}
