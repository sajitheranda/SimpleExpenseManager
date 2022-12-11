package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.Account_helper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public  class PersistentAccountDAO implements AccountDAO {
    /////////////////////////
    private final Map<String, Account> accounts;
    private Account_helper account_helper;

    public PersistentAccountDAO(Context context) {
        this.accounts = new HashMap<>();
        Account_helper account_helper = new Account_helper(context);
        //account_helper=account_helper;
        getalldata();
    }

    public void getalldata(){
        account_helper.readAllData();
        /*
        while (cursor.moveToNext()){
            String accountNo = cursor.getString(0);
            String bankName = cursor.getString(1);
            String accountHolderName = cursor.getString(2);
            double initialBalance = cursor.getDouble(3);

            // Display the values from each column of the current row in the cursor in the TextView
            Account acc=new Account(accountNo,bankName,accountHolderName,initialBalance);
            accounts.put(acc.getAccountNo(), acc);
        }

         */






    }


    @Override
    public List<String> getAccountNumbersList() {
        return new ArrayList<>(accounts.keySet());
    }

    @Override
    public List<Account> getAccountsList() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        if (accounts.containsKey(accountNo)) {
            return accounts.get(accountNo);
        }
        String msg = "Account " + accountNo + " is invalid.";
        throw new InvalidAccountException(msg);
    }

    @Override
    public void addAccount(Account account) {
        Account_helper helper = account_helper;
        String accountNo=account.getAccountNo();
        if (!accounts.containsKey(accountNo)) {
            String msg = "Account " + accountNo + " is already used.";
        }
        //////////////////////// adding account
        accounts.put(accountNo, account);
        //helper.addaccount(account);


    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        Account_helper helper = account_helper;
        if (!accounts.containsKey(accountNo)) {
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        accounts.remove(accountNo);
        //////////////// removing data
        //helper.deleteaccount(accountNo);


    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account_helper helper = account_helper;
        if (!accounts.containsKey(accountNo)) {
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        Account account = accounts.get(accountNo);
        // specific implementation based on the transaction type
        switch (expenseType) {
            case EXPENSE:
                account.setBalance(account.getBalance() - amount);
                break;
            case INCOME:
                account.setBalance(account.getBalance() + amount);
                break;
        }
        accounts.put(accountNo, account);

        /////////////////////////////////////
        double final_ammount=account.getBalance();
        //helper.updateAmount(accountNo,final_ammount);
    }


}