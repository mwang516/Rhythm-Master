package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a score entry with song name, score, and accuracy
public class Score implements Writable {
    private final String name; // name of the song
    private final int score; // total score out of 1000000
    private final double accuracy; // accuracy represented by a percentage

    public Score(String name, int score, double accuracy) {
        this.name = name;
        this.score = score;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("score", score);
        json.put("accuracy", accuracy);

        return json;
    }
}
