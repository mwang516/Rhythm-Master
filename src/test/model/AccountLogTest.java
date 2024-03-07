package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountLogTest {
    Account testAccount;
    AccountLog testLog;

    @BeforeEach
    public void runBefore() {
        testAccount = new Account("Matthew", 19, "Hello world", "1234");
        testLog = new AccountLog();
    }

    @Test
    public void ConstructorTest() {
        assertTrue(testLog.getAccounts().isEmpty());
    }

    @Test
    public void addAccountTest() {
        testLog.addAccount(testAccount);
        assertEquals(testAccount, testLog.getAccounts().get(0));
    }

    @Test
    public void getAccountsTest() {
        ArrayList<Account> accs = new ArrayList<>();
        accs.add(testAccount);

        testLog.addAccount(testAccount);
        assertEquals(accs, testLog.getAccounts());
    }
}

