package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a log of all created accounts
public class AccountLog implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("accounts", accountsToJson());

        return json;
    }

    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Account a : accounts) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}
