package persistence;

import model.Account;
import model.AccountLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    Account acc1;
    Account acc2;

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccountLog() {
        try {
            AccountLog accountLog = new AccountLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(accountLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            accountLog = reader.read();
            assertEquals(0, accountLog.getAccounts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccountLog() {
        acc1 = new Account("1", 1,"1", "1", "1");
        acc2 = new Account("2", 2,"2", "2", "2");
        try {
            AccountLog accountLog = new AccountLog();
            accountLog.addAccount(acc1);
            accountLog.addAccount(acc2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(accountLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            accountLog = reader.read();
            List<Account> accs = accountLog.getAccounts();
            assertEquals(2, accs.size());
            checkAccount("1", 1, "1", "1", "1", accs.get(0));
            checkAccount("2", 2, "2", "2", "2", accs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
