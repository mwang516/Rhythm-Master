package persistence;

import model.Account;
import model.AccountLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AccountLog accountLog = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccountLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            AccountLog accountLog = reader.read();
            assertEquals(0, accountLog.getAccounts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccountLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            AccountLog accountLog = reader.read();
            List<Account> accounts = accountLog.getAccounts();
            assertEquals(2, accounts.size());
            checkAccount("1", 1, "1", "1", "1", accounts.get(0));
            checkAccount("2", 2, "2", "2", "2", accounts.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
