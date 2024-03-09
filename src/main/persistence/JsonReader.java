package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Account;
import model.AccountLog;
import model.Score;
import org.json.*;

// Represents a reader that reads AccountLog from JSON data stored in file
// This class is modeled from the example application provided
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses AccountLog from JSON object and returns it
    private AccountLog parseAccountLog(JSONObject jsonObject) {
        AccountLog accountLog = new AccountLog();
        addAccounts(accountLog, jsonObject);
        return accountLog;
    }

    // MODIFIES: accountLog
    // EFFECTS: parses account from JSON object and adds it to AccountLog
    private void addAccounts(AccountLog accountLog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(accountLog, nextAccount);
        }
    }

    // MODIFIES: accountLog
    // EFFECTS: parses account from JSON object and adds it to AccountLog
    private void addAccount(AccountLog accountLog, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String bio = jsonObject.getString("bio");
        String favSong = jsonObject.getString("favourite song");
        String password = jsonObject.getString("password");

        Account acc = new Account(name, age, bio, password, favSong);
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(acc, nextScore);
        }
        accountLog.addAccount(acc);
    }

    // MODIFIES: scores
    // EFFECTS: parses score from JSON object and adds it to scores
    private void addScore(Account acc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int score = jsonObject.getInt("score");
        double accuracy = jsonObject.getDouble("accuracy");
        Score newScore = new Score(name, score, accuracy);
        acc.addScore(newScore);
    }
}
