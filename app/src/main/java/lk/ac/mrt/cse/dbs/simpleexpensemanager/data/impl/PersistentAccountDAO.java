package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.Account_helper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.DBHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class PersistentAccountDAO implements AccountDAO {


    //private DBHelper dbhelp;/////////////////helper class
    private Account_helper account_helper;


    public PersistentAccountDAO(DBHelper dbhelp) {
        account_helper = new Account_helper(dbhelp);

    }

    @Override
    public List<String> getAccountNumbersList() {
        List<Account> accountsList = account_helper.getAccountsList();
        List<String> accountNumbersList = new ArrayList<>();

        for (Account account : accountsList){
            accountNumbersList.add(account.getAccountNo());
        }
        return accountNumbersList;
    }

    @Override
    public List<Account> getAccountsList() {
        return account_helper.getAccountsList();
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        Account account = account_helper.getAccount(accountNo);
        if(account==null)
            throw new InvalidAccountException("Invalid account number");
        return account;
    }

    @Override
    public void addAccount(Account account) {
        account_helper.addAccount(account);
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        if (getAccount(accountNo)==null)
            throw new InvalidAccountException("Invalid account number");
        else
            account_helper.removeAccount(accountNo);
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account account = getAccount(accountNo);
        if (account == null)
            throw new InvalidAccountException("Invalid account");
        else {
            double balance = account.getBalance();
            if (expenseType == ExpenseType.EXPENSE && balance > amount) {
                account.setBalance(balance - amount);
            } else if (expenseType == ExpenseType.INCOME) {
                account.setBalance(balance + amount);
            }
            account_helper.updateValues(account);
        }
    }
}
