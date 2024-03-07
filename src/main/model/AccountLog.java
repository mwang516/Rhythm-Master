package model;

import java.util.ArrayList;

// Represents a log of all created accounts
public class AccountLog {
    private ArrayList<Account> accounts;

    public AccountLog() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }
}
