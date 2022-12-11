package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class PersistentExpenseManager extends ExpenseManager{
    //private Account_helper achelp;
    //private Transaction_helper tranhelp;
    private Context context;


    public PersistentExpenseManager(Context context){
        //achelp=achelp;
        //tranhelp=tranhelp;
        setup();
    }

    public void setup(){
        /*** Begin generating dummy data for In-Memory implementation ***/

        TransactionDAO transactionDAO = new PersistentTransactionDAO(context);
        setTransactionsDAO(transactionDAO);

        AccountDAO accountDAO = new PersistentAccountDAO(context) ;
        setAccountsDAO(accountDAO);

        // dummy data
        Account dummyAcct1 = new Account("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        Account dummyAcct2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);

        //Transaction trans=new Transaction("2022/05/06","12345A","Expense",1323213);


        getAccountsDAO().addAccount(dummyAcct1);
        getAccountsDAO().addAccount(dummyAcct2);

        /*** End ***/


    }
}
